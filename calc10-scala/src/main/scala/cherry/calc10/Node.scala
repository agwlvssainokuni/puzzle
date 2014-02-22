package cherry.calc10

import org.apache.commons.lang3.math.Fraction

trait Node {
  val value: Fraction
  val expression: String
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
