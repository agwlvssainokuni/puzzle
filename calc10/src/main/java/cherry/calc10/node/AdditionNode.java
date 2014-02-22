package cherry.calc10.node;

public class AdditionNode extends OperationNode {

	public AdditionNode(Node op1, Node op2) {
		super(op1, op2);
	}

	public int value() {
		return op1.value() + op2.value();
	}

	protected char operator() {
		return '+';
	}

}
