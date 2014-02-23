package cherry.calc10

import org.apache.commons.lang3.math.Fraction

object Calc10 extends Application {

  val TEN = Fraction.getFraction(10, 1)

  for {
    number <- 0 to 9999
    n0 = node(number % 10); div0 = number / 10
    n1 = node(div0 % 10); div1 = div0 / 10
    n2 = node(div1 % 10); div2 = div1 / 10
    n3 = node(div2 % 10)
    result = (for {
      node <- combinations(Seq(n3, n2, n1, n0))
      if node.value.equals(TEN)
    } yield {
      node.expression
    }).distinct
  } {
    printf("%d: %s\n", number, result.mkString(","))
  }

  def node(n: Int) = NumberNode(Fraction.getFraction(n, 1))

  def combinations(nodes: Seq[Node]): Seq[Node] =
    if (nodes.size <= 1)
      nodes
    else
      (for {
        count <- (1 until nodes.size)
        comb <- nodes.combinations(count)
        op1 <- combinations(comb)
        op2 <- combinations(nodes.diff(comb))
      } yield {
        Seq(
          AdditionNode(op1, op2),
          MultiplicationNode(op1, op2),
          SubtractionNode(op1, op2),
          SubtractionNode(op2, op1)) ++
          (if (!op2.isZero)
            Seq(DivisionNode(op1, op2))
          else
            Seq()) ++
          (if (!op1.isZero)
            Seq(DivisionNode(op2, op1))
          else Seq())
      }).flatten

}

trait Node {
  val value: Fraction
  val expression: String
  def isZero = value.equals(Fraction.ZERO)
}

trait OperationNode extends Node {
  val operator: String
  lazy val expression = "(" + op1.expression + operator + op2.expression + ")"
  val op1: Node
  val op2: Node
}

case class NumberNode(val value: Fraction) extends Node {
  lazy val expression: String = value.intValue.toString
}

case class AdditionNode(val op1: Node, val op2: Node) extends OperationNode {
  val operator = "+"
  lazy val value = op1.value.add(op2.value)
}

case class SubtractionNode(val op1: Node, val op2: Node) extends OperationNode {
  val operator = "-"
  lazy val value = op1.value.subtract(op2.value)
}

case class MultiplicationNode(val op1: Node, val op2: Node) extends OperationNode {
  val operator = "*"
  lazy val value = op1.value.multiplyBy(op2.value)
}

case class DivisionNode(val op1: Node, val op2: Node) extends OperationNode {
  val operator = "/"
  lazy val value = op1.value.divideBy(op2.value)
}
