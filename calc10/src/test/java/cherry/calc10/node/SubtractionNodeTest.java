package cherry.calc10.node;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SubtractionNodeTest {

	@Test
	public void testValue() {
		Node node1 = new SubtractionNode(new NumberNode(0), new NumberNode(9));
		assertEquals(-9, node1.value());
		Node node2 = new SubtractionNode(new NumberNode(9), new NumberNode(0));
		assertEquals(9, node2.value());
		Node node3 = new SubtractionNode(new NumberNode(8), new NumberNode(3));
		assertEquals(5, node3.value());
	}

	@Test
	public void testExpression() {
		Node node1 = new SubtractionNode(new NumberNode(0), new NumberNode(9));
		assertEquals("(0-9)", node1.expression());
		Node node2 = new SubtractionNode(new NumberNode(9), new NumberNode(0));
		assertEquals("(9-0)", node2.expression());
		Node node3 = new SubtractionNode(new NumberNode(8), new NumberNode(3));
		assertEquals("(8-3)", node3.expression());
	}

}
