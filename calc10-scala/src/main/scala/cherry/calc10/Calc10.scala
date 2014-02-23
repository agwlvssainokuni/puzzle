/*
 * Copyright 2006,2014 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package cherry.calc10

import org.apache.commons.lang3.math.Fraction

import com.typesafe.scalalogging.slf4j.Logging

object Calc10 extends App with Logging {

  val TEN = Fraction.getFraction(10, 1)

  for {
    num <- 0 to args(0).toInt
    l = num % 10; div0 = num / 10
    k = div0 % 10; div1 = div0 / 10
    j = div1 % 10; div2 = div1 / 10
    i = div2 % 10
  } {
    (for {
      node <- combinations(Seq(node(i), node(j), node(k), node(l)))
      if node.value.equals(TEN)
    } yield node.expression).distinct match {
      case result =>
        logger.info(Seq(
          num,
          result.size,
          result.mkString("\"", ",", "\"")).mkString(","))
    }
  }

  def node(n: Int) = NumNode(Fraction.getFraction(n, 1))

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
          AddNode(op1, op2),
          MulNode(op1, op2),
          SubNode(op1, op2)) ++
          (if (!op2.isZero)
            Seq(DivNode(op1, op2))
          else Seq())
      }).flatten

}

trait Node {
  val value: Fraction
  val expression: String
  def isZero = value.equals(Fraction.ZERO)
}

trait OpNode extends Node {
  val operator: String
  lazy val expression = "(" + op1.expression + operator + op2.expression + ")"
  val op1: Node
  val op2: Node
}

case class NumNode(val value: Fraction) extends Node {
  lazy val expression: String = value.intValue.toString
}

case class AddNode(val op1: Node, val op2: Node) extends OpNode {
  val operator = "+"
  lazy val value = op1.value.add(op2.value)
}

case class SubNode(val op1: Node, val op2: Node) extends OpNode {
  val operator = "-"
  lazy val value = op1.value.subtract(op2.value)
}

case class MulNode(val op1: Node, val op2: Node) extends OpNode {
  val operator = "*"
  lazy val value = op1.value.multiplyBy(op2.value)
}

case class DivNode(val op1: Node, val op2: Node) extends OpNode {
  val operator = "/"
  lazy val value = op1.value.divideBy(op2.value)
}
