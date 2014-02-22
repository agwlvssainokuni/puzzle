package cherry.calc10;

public class Permutation {

	public void iterate(Callback callback) {

		callback.init();

		// 1桁
		for (int i = 1; i <= 9; i++) {
			callback.execute(new int[] { i });
		}

		// 2桁
		for (int i = 1; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				callback.execute(new int[] { i, j });
			}
		}

		// 3桁
		for (int i = 1; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 9; k++) {
					callback.execute(new int[] { i, j, k });
				}
			}
		}

		// 4桁
		for (int i = 1; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 9; k++) {
					for (int l = 0; l <= 9; l++) {
						callback.execute(new int[] { i, j, k, l });
					}
				}
			}
		}

		callback.finish();
	}

	interface Callback {
		void init();

		void execute(int[] digits);

		void finish();
	}

}
