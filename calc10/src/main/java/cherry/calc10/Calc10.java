package cherry.calc10;

import cherry.calc10.Permutation.Callback;

public class Calc10 {

	public static void main(String[] args) {
		Permutation permutation = new Permutation();
		Callback callback = new CheckerCallback();
		permutation.iterate(callback);
	}

}
