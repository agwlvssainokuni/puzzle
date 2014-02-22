package cherry.calc10.node;

public class NumberNode implements Node {

	private final int number;

	public NumberNode(int num) {
		number = num;
	}

	public int value() {
		return number;
	}

	public String expression() {
		return String.valueOf(number);
	}

}
