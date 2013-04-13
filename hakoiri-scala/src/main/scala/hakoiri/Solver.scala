package hakoiri

import scala.collection.mutable

object Solver extends App {

}

trait Hakoiri {

  type Coord = (Int, Int)
  type Board = mutable.Map[Coord, String]
  val width: Int
  val height: Int

  def board(koma: Seq[Koma]): Option[Board] = {
    val result: Board = mutable.Map()
    val kiter = koma.iterator
    while (kiter.hasNext) {
      val km = kiter.next()
      val citer = km.coords.iterator
      while (citer.hasNext) {
        val xy = citer.next()
        if (result contains xy)
          return None
        else
          result += ((xy, km.name))
      }
    }
    Some(result)
  }

  case class Koma(name: String, x: Int, y: Int, w: Int, h: Int) {
    def move: Seq[Koma] =
      Seq(-1, 1) flatMap { dx =>
        Seq(-1, 1) flatMap { dy =>
          if (x + dx < 0 || x + dx + (w - 1) > width - 1)
            Seq()
          else if (y + dy < 0 || y + dy + (h - 1) > height - 1)
            Seq()
          else
            Seq(Koma(name, x + dx, y + dy, w, h))
        }
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