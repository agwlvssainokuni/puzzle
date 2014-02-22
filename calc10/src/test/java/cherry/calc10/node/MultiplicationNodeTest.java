package cherry.calc10.node;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MultiplicationNodeTest {

	@Test
	public void testValue() {
		Node node1 = new MultiplicationNode(new NumberNode(0),
				new NumberNode(9));
		assertEquals(0, node1.value());
		Node node2 = new MultiplicationNode(new NumberNode(9),
				new NumberNode(1));
		assertEquals(9, node2.value());
		Node node3 = new MultiplicationNode(new NumberNode(8),
				new NumberNode(3));
		assertEquals(24, node3.value());
	}

	@Test
	public void testExpression() {
		Node node1 = new MultiplicationNode(new NumberNode(0),
				new NumberNode(9));
		assertEquals("(0*9)", node1.expression());
		Node node2 = new MultiplicationNode(new NumberNode(9),
				new NumberNode(1));
		assertEquals("(9*1)", node2.expression());
		Node node3 = new MultiplicationNode(new NumberNode(8),
				new NumberNode(3));
		assertEquals("(8*3)", node3.expression());
	}

}
