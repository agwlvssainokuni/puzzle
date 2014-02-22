package cherry.calc10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cherry.calc10.Permutation.Callback;
import cherry.calc10.node.Node;
import cherry.calc10.node.NumberNode;

public class CheckerCallback implements Callback {

	private final Combinator combinator = new Combinator();

	private int count = 0;

	public void init() {
		count = 0;
	}

	public void execute(int[] digits) {

		StringBuilder builder = new StringBuilder();

		List<Node> nodes = new ArrayList<Node>(digits.length);
		for (int i = 0; i < digits.length; i++) {
			nodes.add(new NumberNode(digits[i]));
			builder.append(digits[i]);
		}
		builder.append('\t');

		List<Node> combination = combinator.combine(nodes);

		List<Node> succeeded = new LinkedList<Node>();
		Iterator<Node> iter = combination.iterator();
		while (iter.hasNext()) {
			Node node = iter.next();
			if (node.value() == 10) {
				succeeded.add(node);
			}
		}

		builder.append(succeeded.size());
		if (succeeded.size() > 0) {

			builder.append('\t');
			boolean first = true;
			Iterator<Node> it = succeeded.iterator();
			while (it.hasNext()) {
				Node nd = it.next();
				if (!first) {
					builder.append(',');
				}
				builder.append(nd.expression());
				first = false;
			}

			count++;
		}

		System.out.println(new String(builder));
	}

	public void finish() {
		System.out.println("SUCCESS: " + count);
	}

}
