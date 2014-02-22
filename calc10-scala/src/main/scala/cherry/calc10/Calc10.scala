package cherry.calc10

import org.apache.commons.lang3.math.Fraction.getFraction

object Calc10 extends Application {

  for {
    i <- 0 to 9
    j <- 0 to 9
    k <- 0 to 9
    l <- 0 to 9
    lst = Seq(
      NumberNode(getFraction(i, 1)),
      NumberNode(getFraction(j, 1)),
      NumberNode(getFraction(k, 1)),
      NumberNode(getFraction(l, 1)))
    node <- Combinations(lst)
  } {
    try {
      if (node.value == getFraction(10, 1))
        printf("%d%d%d%d: %s\n", i, j, k, l, node.expression)
    } catch {
      case ex: ArithmeticException => Unit
    }
  }

}
