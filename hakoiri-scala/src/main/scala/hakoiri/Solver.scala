package hakoiri

object Solver extends App with Hakoiri {
  val width = 4
  val height = 5
  val start: Board = Seq(
    Koma("A", 1, 0, 2, 2),
    Koma("B", 0, 0, 1, 2),
    Koma("C", 3, 0, 1, 2),
    Koma("D", 0, 2, 1, 2),
    Koma("E", 3, 2, 1, 2),
    Koma("F", 1, 2, 2, 1),
    Koma("G", 1, 3, 1, 1),
    Koma("H", 2, 3, 1, 1),
    Koma("I", 0, 4, 1, 1),
    Koma("J", 3, 4, 1, 1))

  solve(start).zipWithIndex foreach {
    case (path, number) =>
      println(s"解 ${number}")
      path.reverse.zipWithIndex foreach {
        case (board, step) =>
          println(s"[STEP ${step}]")
          board2layout(board) foreach { layout =>
            println(layout2string(layout))
          }
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

  def board2layout(board: Board): Option[Layout] = {
    var result = Map[Coord, String]()
    for (koma <- board; coord <- koma.coords) yield {
      if (result contains coord)
        return None
      else
        result = result + ((coord, koma.name))
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
        layoutOpt = board2layout(next) if (layoutOpt.isDefined)
        layoutStr = layout2string(layoutOpt.get) if (!(tempHist contains layoutStr))
      } yield {
        tempHist = tempHist + layoutStr
        next :: path
      }
      (result, tempHist)
    }
    step(List(List(start)), Set()) match {
      case (result, _) => result
    }
  }

  case class Koma(name: String, x: Int, y: Int, w: Int, h: Int) {
    def move: List[Koma] =
      for {
        dx <- List(-1, 0, 1)
        dy <- List(-1, 0, 1)
        if ((dx == 0 && dy != 0) || (dx != 0 && dy == 0))
        if (x + dx >= 0 && x + dx + (w - 1) <= width - 1)
        if (y + dy >= 0 && y + dy + (h - 1) <= height - 1)
      } yield {
        Koma(name, x + dx, y + dy, w, h)
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