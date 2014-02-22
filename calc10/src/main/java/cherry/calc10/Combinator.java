package cherry.calc10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.math.Fraction;

import cherry.calc10.node.AdditionNode;
import cherry.calc10.node.DivisionNode;
import cherry.calc10.node.MultiplicationNode;
import cherry.calc10.node.Node;
import cherry.calc10.node.SubtractionNode;

public class Combinator {

	public List<Node> combine(Node node1, Node node2) {

		List<Node> combination = new ArrayList<Node>(4);

		combination.add(new AdditionNode(node1, node2));
		combination.add(new MultiplicationNode(node1, node2));
		combination.add(new SubtractionNode(node1, node2));

		if (!node2.value().equals(Fraction.ZERO)) {
			combination.add(new DivisionNode(node1, node2));
		}

		return combination;
	}

	public List<Node> combine(List<Node> nodes1, List<Node> nodes2) {

		List<Node> combination = new ArrayList<Node>();

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

		List<Node> combination = new ArrayList<Node>();

		List<Node> nodesA;
		List<Node> nodesB;
		switch (nodes.size()) {
		case 1:
			combination.addAll(nodes);
			break;

		case 2:
			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(0));
			nodesB = new ArrayList<Node>();
			nodesB.add(nodes.get(1));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));
			break;

		case 3:
			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(0));
			nodesB = new ArrayList<Node>();
			nodesB.add(nodes.get(1));
			nodesB.add(nodes.get(2));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(1));
			nodesB = new ArrayList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(2));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(2));
			nodesB = new ArrayList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(1));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));
			break;

		case 4:
			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(0));
			nodesB = new ArrayList<Node>();
			nodesB.add(nodes.get(1));
			nodesB.add(nodes.get(2));
			nodesB.add(nodes.get(3));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(1));
			nodesB = new ArrayList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(2));
			nodesB.add(nodes.get(3));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(2));
			nodesB = new ArrayList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(1));
			nodesB.add(nodes.get(3));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(3));
			nodesB = new ArrayList<Node>();
			nodesB.add(nodes.get(0));
			nodesB.add(nodes.get(1));
			nodesB.add(nodes.get(2));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(0));
			nodesA.add(nodes.get(1));
			nodesB = new ArrayList<Node>();
			nodesB.add(nodes.get(2));
			nodesB.add(nodes.get(3));
			combination.addAll(combine(combine(nodesA), combine(nodesB)));

			nodesA = new ArrayList<Node>();
			nodesA.add(nodes.get(0));
			nodesA.add(nodes.get(2));
			nodesB = new ArrayList<Node>();
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
