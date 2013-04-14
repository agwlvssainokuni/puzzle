package hakoiri

import scala.Option.option2Iterable
import scala.annotation.tailrec

object Solver extends App with Hakoiri {
  val width = 4
  val height = 5
  val maxStep = 1000

  val start: Board = Board(
    Koma("A", "A", 1, 0, 2, 2),
    Koma("B", "B", 0, 0, 1, 2),
    Koma("C", "B", 3, 0, 1, 2),
    Koma("D", "B", 0, 2, 1, 2),
    Koma("E", "B", 3, 2, 1, 2),
    Koma("F", "F", 1, 2, 2, 1),
    Koma("G", "G", 1, 3, 1, 1),
    Koma("H", "G", 2, 3, 1, 1),
    Koma("I", "G", 0, 4, 1, 1),
    Koma("J", "G", 3, 4, 1, 1))

  def isGoal(board: Board): Boolean =
    board.koma exists {
      case Koma("A", _, 1, 3, _, _) => true
      case _ => false
    }

  for {
    (path, number) <- solve(start).zipWithIndex
  } {
    println(s"解 ${number}")
    for {
      (board, step) <- path.zipWithIndex
      layoutStr <- board.layoutOpt() { _.name }
    } {
      println(s"[STEP ${step}]")
      println(layoutStr)
      println()
    }
  }
}

trait Hakoiri {

  type Path = List[Board]

  val width: Int
  val height: Int
  val maxStep: Int

  def isGoal(board: Board): Boolean

  def solve(start: Board): List[Path] = {
    @tailrec
    def loop(count: Int, paths: List[Path], history: Set[String]): (List[Path], Set[String]) = {
      val (nextPaths, nextHistory) = nextStep(paths, history)
      println(s"ステップ ${count}, 経路パターン数 ${nextPaths.size}, 履歴数 ${nextHistory.size}")
      if (count > maxStep)
        (nextPaths, nextHistory)
      else {
        val goal = nextPaths.filter { path => isGoal(path.head) }
        if (goal.isEmpty)
          loop(count + 1, nextPaths, nextHistory)
        else
          (goal, nextHistory)
      }
    }

    val result = for {
      layoutStr <- start.layoutOpt() { _.kind }
      (answer, _) = loop(1, List(List(start)), Set(layoutStr))
    } yield {
      answer map { _.reverse }
    }
    result.get
  }

  def nextStep(paths: List[Path], history: Set[String]): (List[Path], Set[String]) = {
    var tempHist = history
    val nextPaths = for {
      path <- paths
      current = path.head
      koma <- current.koma
      moved <- koma.move
      next = Board(current.koma map { km => if (km == koma) moved else km }: _*)
      layoutStr <- next.layoutOpt() { _.kind }
      if (!(tempHist contains layoutStr))
    } yield {
      tempHist = tempHist + layoutStr
      next :: path
    }
    (nextPaths, tempHist)
  }

  case class Board(koma: Koma*) {
    def layoutOpt(spacer: String = " ")(f: Koma => String): Option[String] = {
      val entries = for {
        km <- koma
        coord <- km.coords
      } yield {
        (coord, f(km))
      }
      val layout = Map[(Int, Int), String](entries: _*)
      if (layout.size != entries.size)
        None
      else
        Some(layoutString(layout, spacer))
    }
    private def layoutString(layout: Map[(Int, Int), String], spacer: String): String = {
      (for (y <- 0 until height) yield {
        (for (x <- 0 until width) yield {
          layout.get((x, y)) getOrElse spacer
        }).mkString
      }).mkString("\n")
    }
  }

  case class Koma(name: String, kind: String, x: Int, y: Int, w: Int, h: Int) {
    def coords: Seq[(Int, Int)] =
      for {
        dx <- 0 until w
        dy <- 0 until h
      } yield {
        (x + dx, y + dy)
      }
    def move: List[Koma] =
      for {
        dx <- List(-1, 0, 1) if (x + dx >= 0 && x + dx + (w - 1) <= width - 1)
        dy <- List(-1, 0, 1) if (y + dy >= 0 && y + dy + (h - 1) <= height - 1)
        if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0))
      } yield {
        Koma(name, kind, x + dx, y + dy, w, h)
      }
  }

}
