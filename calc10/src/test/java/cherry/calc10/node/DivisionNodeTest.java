package cherry.calc10.node;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DivisionNodeTest {

	@Test
	public void testValue() {
		Node node1 = new DivisionNode(new NumberNode(0), new NumberNode(9));
		assertEquals(0, node1.value());
		Node node2 = new DivisionNode(new NumberNode(9), new NumberNode(1));
		assertEquals(9, node2.value());
		Node node3 = new DivisionNode(new NumberNode(8), new NumberNode(4));
		assertEquals(2, node3.value());
	}

	@Test
	public void testExpression() {
		Node node1 = new DivisionNode(new NumberNode(0), new NumberNode(9));
		assertEquals("(0/9)", node1.expression());
		Node node2 = new DivisionNode(new NumberNode(9), new NumberNode(1));
		assertEquals("(9/1)", node2.expression());
		Node node3 = new DivisionNode(new NumberNode(8), new NumberNode(4));
		assertEquals("(8/4)", node3.expression());
	}

}
