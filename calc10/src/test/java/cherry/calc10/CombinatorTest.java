package cherry.calc10;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

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
		assertEquals(4, combination.get(0).value());
		assertEquals("(2+2)", combination.get(0).expression());
		assertEquals(4, combination.get(1).value());
		assertEquals("(2*2)", combination.get(1).expression());
		assertEquals(0, combination.get(2).value());
		assertEquals("(2-2)", combination.get(2).expression());
		assertEquals(1, combination.get(3).value());
		assertEquals("(2/2)", combination.get(3).expression());
	}

	@Test
	public void testCombine_2_3() {

		Combinator combinator = new Combinator();
		Node node2 = new NumberNode(2);
		Node node3 = new NumberNode(3);
		List<Node> combination = combinator.combine(node2, node3);
		assertEquals(3, combination.size());
		assertEquals(5, combination.get(0).value());
		assertEquals("(2+3)", combination.get(0).expression());
		assertEquals(6, combination.get(1).value());
		assertEquals("(2*3)", combination.get(1).expression());
		assertEquals(1, combination.get(2).value());
		assertEquals("(3-2)", combination.get(2).expression());
	}

	@Test
	public void testCombine_2_6() {

		Combinator combinator = new Combinator();
		Node node2 = new NumberNode(2);
		Node node6 = new NumberNode(6);
		List<Node> combination = combinator.combine(node2, node6);
		assertEquals(4, combination.size());
		assertEquals(8, combination.get(0).value());
		assertEquals("(2+6)", combination.get(0).expression());
		assertEquals(12, combination.get(1).value());
		assertEquals("(2*6)", combination.get(1).expression());
		assertEquals(4, combination.get(2).value());
		assertEquals("(6-2)", combination.get(2).expression());
		assertEquals(3, combination.get(3).value());
		assertEquals("(6/2)", combination.get(3).expression());
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
		assertEquals(8, combination.get(0).value());
		assertEquals("(2+6)", combination.get(0).expression());
		assertEquals(12, combination.get(1).value());
		assertEquals("(2*6)", combination.get(1).expression());
		assertEquals(4, combination.get(2).value());
		assertEquals("(6-2)", combination.get(2).expression());
		assertEquals(3, combination.get(3).value());
		assertEquals("(6/2)", combination.get(3).expression());
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

		assertEquals(13, combination.size());
		assertEquals("(2+4)", combination.get(0).expression());
		assertEquals("(2*4)", combination.get(1).expression());
		assertEquals("(4-2)", combination.get(2).expression());
		assertEquals("(4/2)", combination.get(3).expression());

		assertEquals("(2+5)", combination.get(4).expression());
		assertEquals("(2*5)", combination.get(5).expression());
		assertEquals("(5-2)", combination.get(6).expression());

		assertEquals("(3+4)", combination.get(7).expression());
		assertEquals("(3*4)", combination.get(8).expression());
		assertEquals("(4-3)", combination.get(9).expression());

		assertEquals("(3+5)", combination.get(10).expression());
		assertEquals("(3*5)", combination.get(11).expression());
		assertEquals("(5-3)", combination.get(12).expression());
	}

	@Test
	public void testCombineList_1() {

		Combinator combinator = new Combinator();

		List<Node> nodes1 = new LinkedList<Node>();
		nodes1.add(new NumberNode(1));

		List<Node> combination = combinator.combine(nodes1);
		assertEquals(1, combination.size());
		assertEquals(1, combination.get(0).value());
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
		assertEquals("(2-1)", combination.get(2).expression());
		assertEquals("(2/1)", combination.get(3).expression());
	}

	@Test
	public void testCombineList_123() {

		Combinator combinator = new Combinator();

		List<Node> nodes123 = new LinkedList<Node>();
		nodes123.add(new NumberNode(1));
		nodes123.add(new NumberNode(2));
		nodes123.add(new NumberNode(3));

		List<Node> combination = combinator.combine(nodes123);
		assertEquals(40, combination.size());

		// [0]
		assertEquals("(1+(2+3))", combination.get(0).expression());
		assertEquals("(1*(2+3))", combination.get(1).expression());
		assertEquals("((2+3)-1)", combination.get(2).expression());
		assertEquals("((2+3)/1)", combination.get(3).expression());

		assertEquals("(1+(2*3))", combination.get(4).expression());
		assertEquals("(1*(2*3))", combination.get(5).expression());
		assertEquals("((2*3)-1)", combination.get(6).expression());
		assertEquals("((2*3)/1)", combination.get(7).expression());

		assertEquals("(1+(3-2))", combination.get(8).expression());
		assertEquals("(1*(3-2))", combination.get(9).expression());
		assertEquals("(1-(3-2))", combination.get(10).expression());
		assertEquals("(1/(3-2))", combination.get(11).expression());

		// [1]
		assertEquals("(2+(1+3))", combination.get(12).expression());
		assertEquals("(2*(1+3))", combination.get(13).expression());
		assertEquals("((1+3)-2)", combination.get(14).expression());
		assertEquals("((1+3)/2)", combination.get(15).expression());

		assertEquals("(2+(1*3))", combination.get(16).expression());
		assertEquals("(2*(1*3))", combination.get(17).expression());
		assertEquals("((1*3)-2)", combination.get(18).expression());

		assertEquals("(2+(3-1))", combination.get(19).expression());
		assertEquals("(2*(3-1))", combination.get(20).expression());
		assertEquals("(2-(3-1))", combination.get(21).expression());
		assertEquals("(2/(3-1))", combination.get(22).expression());

		assertEquals("(2+(3/1))", combination.get(23).expression());
		assertEquals("(2*(3/1))", combination.get(24).expression());
		assertEquals("((3/1)-2)", combination.get(25).expression());

		// [2]
		assertEquals("(3+(1+2))", combination.get(26).expression());
		assertEquals("(3*(1+2))", combination.get(27).expression());
		assertEquals("(3-(1+2))", combination.get(28).expression());
		assertEquals("(3/(1+2))", combination.get(29).expression());

		assertEquals("(3+(1*2))", combination.get(30).expression());
		assertEquals("(3*(1*2))", combination.get(31).expression());
		assertEquals("(3-(1*2))", combination.get(32).expression());

		assertEquals("(3+(2-1))", combination.get(33).expression());
		assertEquals("(3*(2-1))", combination.get(34).expression());
		assertEquals("(3-(2-1))", combination.get(35).expression());
		assertEquals("(3/(2-1))", combination.get(36).expression());

		assertEquals("(3+(2/1))", combination.get(37).expression());
		assertEquals("(3*(2/1))", combination.get(38).expression());
		assertEquals("(3-(2/1))", combination.get(39).expression());

	}

}
