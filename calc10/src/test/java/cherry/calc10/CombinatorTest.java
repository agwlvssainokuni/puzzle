package cherry.calc10;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.math.Fraction;
import org.junit.Test;

import cherry.calc10.node.Node;
import cherry.calc10.node.NumberNode;

public class CombinatorTest {

	@Test
	public void testCombine_2_2() {

		Combinator combinator = new Combinator();
		Node node0 = new NumberNode(2);
		Node node1 = new NumberNode(2);
		List<Node> combination = combinator.combine(node0, node1);
		assertEquals(4, combination.size());
		assertEquals(Fraction.getFraction(4, 1), combination.get(0).value());
		assertEquals("(2+2)", combination.get(0).expression());
		assertEquals(Fraction.getFraction(4, 1), combination.get(1).value());
		assertEquals("(2*2)", combination.get(1).expression());
		assertEquals(Fraction.ZERO, combination.get(2).value());
		assertEquals("(2-2)", combination.get(2).expression());
		assertEquals(Fraction.ONE, combination.get(3).value());
		assertEquals("(2/2)", combination.get(3).expression());
	}

	@Test
	public void testCombine_2_3() {

		Combinator combinator = new Combinator();
		Node node2 = new NumberNode(2);
		Node node3 = new NumberNode(3);
		List<Node> combination = combinator.combine(node2, node3);
		assertEquals(4, combination.size());
		assertEquals(Fraction.getFraction(5, 1), combination.get(0).value());
		assertEquals("(2+3)", combination.get(0).expression());
		assertEquals(Fraction.getFraction(6, 1), combination.get(1).value());
		assertEquals("(2*3)", combination.get(1).expression());
		assertEquals(Fraction.ONE.negate(), combination.get(2).value());
		assertEquals("(2-3)", combination.get(2).expression());
	}

	@Test
	public void testCombine_2_6() {

		Combinator combinator = new Combinator();
		Node node2 = new NumberNode(2);
		Node node6 = new NumberNode(6);
		List<Node> combination = combinator.combine(node2, node6);
		assertEquals(4, combination.size());
		assertEquals(Fraction.getFraction(8, 1), combination.get(0).value());
		assertEquals("(2+6)", combination.get(0).expression());
		assertEquals(Fraction.getFraction(12, 1), combination.get(1).value());
		assertEquals("(2*6)", combination.get(1).expression());
		assertEquals(Fraction.getFraction(-4, 1), combination.get(2).value());
		assertEquals("(2-6)", combination.get(2).expression());
		assertEquals(Fraction.getFraction(1, 3), combination.get(3).value());
		assertEquals("(2/6)", combination.get(3).expression());
	}

	@Test
	public void testCombineList_2_6() {

		Combinator combinator = new Combinator();

		List<Node> nodes2 = new LinkedList<Node>();
		nodes2.add(new NumberNode(2));

		List<Node> nodes6 = new LinkedList<Node>();
		nodes6.add(new NumberNode(6));

		List<Node> combination = combinator.combine(nodes2, nodes6);
		assertEquals(4, combination.size());
		assertEquals(Fraction.getFraction(8, 1), combination.get(0).value());
		assertEquals("(2+6)", combination.get(0).expression());
		assertEquals(Fraction.getFraction(12, 1), combination.get(1).value());
		assertEquals("(2*6)", combination.get(1).expression());
		assertEquals(Fraction.getFraction(-4, 1), combination.get(2).value());
		assertEquals("(2-6)", combination.get(2).expression());
		assertEquals(Fraction.getFraction(1, 3), combination.get(3).value());
		assertEquals("(2/6)", combination.get(3).expression());
	}

	@Test
	public void testCombineList_23_45() {

		Combinator combinator = new Combinator();

		List<Node> nodes23 = new LinkedList<Node>();
		nodes23.add(new NumberNode(2));
		nodes23.add(new NumberNode(3));

		List<Node> nodes45 = new LinkedList<Node>();
		nodes45.add(new NumberNode(4));
		nodes45.add(new NumberNode(5));

		List<Node> combination = combinator.combine(nodes23, nodes45);

		assertEquals(16, combination.size());
		int index = 0;
		assertEquals("(2+4)", combination.get(index++).expression());
		assertEquals("(2*4)", combination.get(index++).expression());
		assertEquals("(2-4)", combination.get(index++).expression());
		assertEquals("(2/4)", combination.get(index++).expression());

		assertEquals("(2+5)", combination.get(index++).expression());
		assertEquals("(2*5)", combination.get(index++).expression());
		assertEquals("(2-5)", combination.get(index++).expression());
		assertEquals("(2/5)", combination.get(index++).expression());

		assertEquals("(3+4)", combination.get(index++).expression());
		assertEquals("(3*4)", combination.get(index++).expression());
		assertEquals("(3-4)", combination.get(index++).expression());
		assertEquals("(3/4)", combination.get(index++).expression());

		assertEquals("(3+5)", combination.get(index++).expression());
		assertEquals("(3*5)", combination.get(index++).expression());
		assertEquals("(3-5)", combination.get(index++).expression());
		assertEquals("(3/5)", combination.get(index++).expression());
	}

	@Test
	public void testCombineList_1() {

		Combinator combinator = new Combinator();

		List<Node> nodes1 = new LinkedList<Node>();
		nodes1.add(new NumberNode(1));

		List<Node> combination = combinator.combine(nodes1);
		assertEquals(1, combination.size());
		assertEquals(Fraction.ONE, combination.get(0).value());
	}

	@Test
	public void testCombineList_12() {

		Combinator combinator = new Combinator();

		List<Node> nodes12 = new LinkedList<Node>();
		nodes12.add(new NumberNode(1));
		nodes12.add(new NumberNode(2));

		List<Node> combination = combinator.combine(nodes12);
		assertEquals(4, combination.size());
		assertEquals("(1+2)", combination.get(0).expression());
		assertEquals("(1*2)", combination.get(1).expression());
		assertEquals("(1-2)", combination.get(2).expression());
		assertEquals("(1/2)", combination.get(3).expression());
	}

	@Test
	public void testCombineList_123() {

		Combinator combinator = new Combinator();

		List<Node> nodes123 = new LinkedList<Node>();
		nodes123.add(new NumberNode(1));
		nodes123.add(new NumberNode(2));
		nodes123.add(new NumberNode(3));

		List<Node> combination = combinator.combine(nodes123);
		assertEquals(48, combination.size());

		int index = 0;

		// [0]
		assertEquals("(1+(2+3))", combination.get(index++).expression());
		assertEquals("(1*(2+3))", combination.get(index++).expression());
		assertEquals("(1-(2+3))", combination.get(index++).expression());
		assertEquals("(1/(2+3))", combination.get(index++).expression());

		assertEquals("(1+(2*3))", combination.get(index++).expression());
		assertEquals("(1*(2*3))", combination.get(index++).expression());
		assertEquals("(1-(2*3))", combination.get(index++).expression());
		assertEquals("(1/(2*3))", combination.get(index++).expression());

		assertEquals("(1+(2-3))", combination.get(index++).expression());
		assertEquals("(1*(2-3))", combination.get(index++).expression());
		assertEquals("(1-(2-3))", combination.get(index++).expression());
		assertEquals("(1/(2-3))", combination.get(index++).expression());

		assertEquals("(1+(2/3))", combination.get(index++).expression());
		assertEquals("(1*(2/3))", combination.get(index++).expression());
		assertEquals("(1-(2/3))", combination.get(index++).expression());
		assertEquals("(1/(2/3))", combination.get(index++).expression());

		// [1]
		assertEquals("(2+(1+3))", combination.get(index++).expression());
		assertEquals("(2*(1+3))", combination.get(index++).expression());
		assertEquals("(2-(1+3))", combination.get(index++).expression());
		assertEquals("(2/(1+3))", combination.get(index++).expression());

		assertEquals("(2+(1*3))", combination.get(index++).expression());
		assertEquals("(2*(1*3))", combination.get(index++).expression());
		assertEquals("(2-(1*3))", combination.get(index++).expression());
		assertEquals("(2/(1*3))", combination.get(index++).expression());

		assertEquals("(2+(1-3))", combination.get(index++).expression());
		assertEquals("(2*(1-3))", combination.get(index++).expression());
		assertEquals("(2-(1-3))", combination.get(index++).expression());
		assertEquals("(2/(1-3))", combination.get(index++).expression());

		assertEquals("(2+(1/3))", combination.get(index++).expression());
		assertEquals("(2*(1/3))", combination.get(index++).expression());
		assertEquals("(2-(1/3))", combination.get(index++).expression());
		assertEquals("(2/(1/3))", combination.get(index++).expression());

		// [2]
		assertEquals("(3+(1+2))", combination.get(index++).expression());
		assertEquals("(3*(1+2))", combination.get(index++).expression());
		assertEquals("(3-(1+2))", combination.get(index++).expression());
		assertEquals("(3/(1+2))", combination.get(index++).expression());

		assertEquals("(3+(1*2))", combination.get(index++).expression());
		assertEquals("(3*(1*2))", combination.get(index++).expression());
		assertEquals("(3-(1*2))", combination.get(index++).expression());
		assertEquals("(3/(1*2))", combination.get(index++).expression());

		assertEquals("(3+(1-2))", combination.get(index++).expression());
		assertEquals("(3*(1-2))", combination.get(index++).expression());
		assertEquals("(3-(1-2))", combination.get(index++).expression());
		assertEquals("(3/(1-2))", combination.get(index++).expression());

		assertEquals("(3+(1/2))", combination.get(index++).expression());
		assertEquals("(3*(1/2))", combination.get(index++).expression());
		assertEquals("(3-(1/2))", combination.get(index++).expression());
		assertEquals("(3/(1/2))", combination.get(index++).expression());
	}

}
