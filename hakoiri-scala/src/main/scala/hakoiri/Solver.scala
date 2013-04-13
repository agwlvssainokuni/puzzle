package hakoiri

object Solver extends App {

}

trait Hakoiri {

  type Coord = (Int, Int)
  type Board = Map[Coord, String]
  val width: Int
  val height: Int

  def layout(koma: List[Koma], board: Board = Map()): Option[Board] =
    koma match {
      case Nil =>
        Some(board)
      case k :: ks =>
        layoutOne(k.name, k.coords, board) match {
          case None =>
            None
          case Some(b) =>
            layout(ks, b)
        }
    }

  def layoutOne(name: String, coords: List[Coord], board: Board): Option[Board] =
    coords match {
      case Nil =>
        Some(board)
      case c :: cs =>
        if (board contains c)
          None
        else
          layoutOne(name, cs, (board + ((c, name))))
    }

  case class Koma(name: String, x: Int, y: Int, w: Int, h: Int) {
    def move: List[Koma] =
      List(-1, 1) flatMap { dx =>
        List(-1, 1) flatMap { dy =>
          if (x + dx < 0 || x + dx + (w - 1) > width - 1)
            List()
          else if (y + dy < 0 || y + dy + (h - 1) > height - 1)
            List()
          else
            List(Koma(name, x + dx, y + dy, w, h))
        }
      }
    def coords: List[Coord] =
      (0 until w).toList flatMap { dx =>
        (0 until h).toList map { dy =>
          (x + dx, y + dy)
        }
      }
  }

}