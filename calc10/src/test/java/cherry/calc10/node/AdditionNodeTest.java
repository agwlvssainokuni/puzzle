package cherry.calc10.node;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.math.Fraction;
import org.junit.Test;

public class AdditionNodeTest {

	@Test
	public void testValue() {
		Node node1 = new AdditionNode(new NumberNode(0), new NumberNode(9));
		assertEquals(Fraction.getFraction(9, 1), node1.value());
		Node node2 = new AdditionNode(new NumberNode(2), new NumberNode(8));
		assertEquals(Fraction.getFraction(10, 1), node2.value());
	}

	@Test
	public void testExpression() {
		Node node1 = new AdditionNode(new NumberNode(0), new NumberNode(9));
		assertEquals("(0+9)", node1.expression());
		Node node2 = new AdditionNode(new NumberNode(2), new NumberNode(8));
		assertEquals("(2+8)", node2.expression());
	}

}
