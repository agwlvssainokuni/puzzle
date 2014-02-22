package cherry.calc10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cherry.calc10.node.AdditionNode;
import cherry.calc10.node.DivisionNode;
import cherry.calc10.node.MultiplicationNode;
import cherry.calc10.node.Node;
import cherry.calc10.node.SubtractionNode;

public class Combinator {

	public List<Node> combine(Node node1, Node node2) {

		int op1 = node1.value();
		int op2 = node2.value();
		List<Node> combination = new ArrayList<Node>(4);

		combination.add(new AdditionNode(node1, node2));
		combination.add(new MultiplicationNode(node1, node2));

		if (op1 >= op2) {
			combination.add(new SubtractionNode(node1, node2));
		} else {
			combination.add(new SubtractionNode(node2, node1));
		}

		if (op1 == 0) {
			if (op2 != 0) {
				combination.add(new DivisionNode(node1, node2));
			}
		} else if (op2 == 0) {
			combination.add(new DivisionNode(node2, node1));
		} else {
			if (op1 % op2 == 0) {
				combination.add(new DivisionNode(node1, node2));
			} else if (op2 % op1 == 0) {
				combination.add(new DivisionNode(node2, node1));
			}
		}

		return combination;
	}

	public List<Node> combine(List<Node> nodes1, List<Node> nodes2) {

		List<Node> combination = new LinkedList<Node>();

		Iterator<Node> iter1 = nodes1.iterator();
		while (iter1.hasNext()) {
			Node node1 = iter1.next();
			Iterator<Node> iter2 = nodes2.iterator();
			while (iter2.hasNext()) {
				Node node2 = iter2.next();
				combination.addAll(combine(node1, node2));
			}
		}

		return combination;
	}

	public List<Node> combine(List<Node> nodes) {

		List<Node> combination = new LinkedList<Node>();

		List<Node> nodesA;
		List<Node> nodesB;
		switch (nodes.size()) {
		case 1:
			combination.addAll(nodes);
			break;

		case 2:
			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(0));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(1));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));
			break;

		case 3:
			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(0));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(1));
			nodesB.add(nodes.get(2));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(1));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(2));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(2));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(1));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));
			break;

		case 4:
			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(0));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(1));
			nodesB.add(nodes.get(2));
			nodesB.add(nodes.get(3));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(1));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(2));
			nodesB.add(nodes.get(3));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(2));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(1));
			nodesB.add(nodes.get(3));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(3));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(1));
			nodesB.add(nodes.get(2));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(0));
			nodesA.add(nodes.get(1));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(2));
			nodesB.add(nodes.get(3));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new LinkedList<Node>();
			nodesA.add(nodes.get(0));
			nodesA.add(nodes.get(2));
			nodesB = new LinkedList<Node>();
			nodesB.add(nodes.get(1));
			nodesB.add(nodes.get(3));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));
			break;

		default:
			break;
		}

		return combination;
	}

}
