package cherry.calc10.node;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberNodeTest {

	@Test
	public void testValue() {
		Node node0 = new NumberNode(0);
		assertEquals(0, node0.value());
		Node node9 = new NumberNode(9);
		assertEquals(9, node9.value());
	}

	@Test
	public void testExpression() {
		Node node1 = new NumberNode(1);
		assertEquals("1", node1.expression());
		Node node8 = new NumberNode(8);
		assertEquals("8", node8.expression());
	}

}
