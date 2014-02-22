package cherry.calc10

object Combinations {

  def apply(nodes: Seq[Node]): Seq[Node] =
    if (nodes.size <= 1)
      nodes
    else
      (for {
        count <- (1 until nodes.size)
        comb <- nodes.combinations(count)
        op1 <- apply(comb)
        op2 <- apply(nodes.diff(comb))
      } yield {
        Seq(
          AdditionNode(op1, op2),
          MultiplicationNode(op1, op2),
          SubtractionNode(op1, op2),
          SubtractionNode(op2, op1),
          DivisionNode(op1, op2),
          DivisionNode(op2, op1))
      }).flatten

}
