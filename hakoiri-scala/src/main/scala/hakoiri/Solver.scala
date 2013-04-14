package hakoiri

import scala.Option.option2Iterable
import scala.annotation.tailrec

object Solver extends App with Hakoiri {
  val width = 4
  val height = 5
  val start: Board = Seq(
    Koma("A", "A", 1, 0, 2, 2),
    Koma("B", "B", 0, 0, 1, 2),
    Koma("C", "B", 3, 0, 1, 2),
    Koma("D", "B", 0, 2, 1, 2),
    Koma("E", "B", 3, 2, 1, 2),
    Koma("F", "B", 1, 2, 2, 1),
    Koma("G", "G", 1, 3, 1, 1),
    Koma("H", "G", 2, 3, 1, 1),
    Koma("I", "G", 0, 4, 1, 1),
    Koma("J", "G", 3, 4, 1, 1))

  def isGoal(board: Board): Boolean =
    board exists { koma =>
      koma.name == "A" && koma.x == 1 && koma.y == 3
    }

  for {
    (path, number) <- solve(start).zipWithIndex
  } {
    println(s"解 ${number}")
    for {
      (board, step) <- path.zipWithIndex
      layout <- board2layout(board) { _.name }
      layoutStr = layout2string(layout)
    } {
      println(s"[STEP ${step}]")
      println(layoutStr)
      println()
    }
  }
}

trait Hakoiri {

  type Coord = (Int, Int)
  type Layout = Map[Coord, String]
  type Board = Seq[Koma]
  type Path = List[Board]

  val width: Int
  val height: Int

  def isGoal(board: Board): Boolean

  def board2layout(board: Board)(f: Koma => String): Option[Layout] = {
    var result = Map[Coord, String]()
    for {
      koma <- board
      coord <- koma.coords
    } yield {
      if (result contains coord)
        return None
      else
        result = result + ((coord, f(koma)))
    }
    Some(result)
  }

  def layout2string(board: Layout): String = {
    (for (y <- 0 until height) yield {
      (for (x <- 0 until width) yield {
        board.get((x, y)) getOrElse " "
      }).mkString
    }).mkString("\n")
  }

  def solve(start: Board): List[Path] = {
    def step(paths: List[Path], history: Set[String]): (List[Path], Set[String]) = {
      var tempHist = history
      var result = for {
        path <- paths
        current = path.head
        koma <- current
        moved <- koma.move
        next = current map { km => if (km == koma) moved else km }
        layout <- board2layout(next) { _.kind }
        layoutStr = layout2string(layout)
        if (!(tempHist contains layoutStr))
      } yield {
        tempHist = tempHist + layoutStr
        next :: path
      }
      (result, tempHist)
    }

    @tailrec
    def stepLoop(count: Int, paths: List[Path], history: Set[String]): (List[Path], Set[String]) = {
      val (newPaths, newHistory) = step(paths, history)
      println(s"ステップ ${count}, 経路パターン数 ${newPaths.size}, 履歴数 ${newHistory.size}")
      if (count >= 1000)
        (newPaths, newHistory)
      else {
        val goal = newPaths.filter { path => isGoal(path.head) }
        if (goal.isEmpty)
          stepLoop(count + 1, newPaths, newHistory)
        else
          (goal, newHistory)
      }
    }

    val result = for {
      layout <- board2layout(start) { _.kind }
      layoutStr = layout2string(layout)
      (answer, _) = stepLoop(1, List(List(start)), Set(layoutStr))
    } yield {
      answer map { _.reverse }
    }
    result.get
  }

  case class Koma(name: String, kind: String, x: Int, y: Int, w: Int, h: Int) {
    def move: List[Koma] =
      for {
        dx <- List(-1, 0, 1) if (x + dx >= 0 && x + dx + (w - 1) <= width - 1)
        dy <- List(-1, 0, 1) if (y + dy >= 0 && y + dy + (h - 1) <= height - 1)
        if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0))
      } yield {
        Koma(name, kind, x + dx, y + dy, w, h)
      }
    def coords: Seq[Coord] =
      for {
        dx <- 0 until w
        dy <- 0 until h
      } yield {
        (x + dx, y + dy)
      }
  }

}