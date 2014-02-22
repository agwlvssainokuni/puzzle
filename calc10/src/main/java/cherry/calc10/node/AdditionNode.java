package cherry.calc10.node;

import org.apache.commons.lang3.math.Fraction;

public class AdditionNode extends OperationNode {

	public AdditionNode(Node op1, Node op2) {
		super(op1, op2);
	}

	@Override
	public Fraction value() {
		return op1.value().add(op2.value());
	}

	@Override
	protected char operator() {
		return '+';
	}

}
