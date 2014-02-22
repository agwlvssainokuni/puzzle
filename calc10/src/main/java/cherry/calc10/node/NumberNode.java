package cherry.calc10.node;

import org.apache.commons.lang3.math.Fraction;

public class NumberNode implements Node {

	private final Fraction number;

	public NumberNode(int num) {
		number = Fraction.getFraction(num, 1);
	}

	@Override
	public Fraction value() {
		return number;
	}

	public String expression() {
		return String.valueOf(number.intValue());
	}

}
