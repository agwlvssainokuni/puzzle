package cherry.calc10.node;

public abstract class OperationNode implements Node {

	protected final Node op1;
	protected final Node op2;

	protected OperationNode(Node o1, Node o2) {
		op1 = o1;
		op2 = o2;
	}

	public final String expression() {
		StringBuilder builder = new StringBuilder();
		builder.append('(').append(op1.expression()).append(operator())
				.append(op2.expression()).append(')');
		return new String(builder);
	}

	protected abstract char operator();

}
