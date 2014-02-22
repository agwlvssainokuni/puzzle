package cherry.calc10

import org.apache.commons.lang3.math.Fraction

trait Node {
  def value: Fraction
  def expression: String
}

trait OperationNode extends Node {
  def op1: Node
  def op2: Node
  def operator: String
  def expression: String = "(" + op1.expression + operator + op2.expression + ")"
}

case class NumberNode(val value: Fraction) extends Node {
  def expression: String = value.intValue.toString
}

case class AdditionNode(val op1: Node, val op2: Node) extends OperationNode {
  def operator = "+"
  def value = op1.value.add(op2.value)
}

case class SubtractionNode(val op1: Node, val op2: Node) extends OperationNode {
  def operator = "-"
  def value = op1.value.subtract(op2.value)
}

case class MultiplicationNode(val op1: Node, val op2: Node) extends OperationNode {
  def operator = "*"
  def value = op1.value.multiplyBy(op2.value)
}

case class DivisionNode(val op1: Node, val op2: Node) extends OperationNode {
  def operator = "*"
  def value = op1.value.divideBy(op2.value)
}
