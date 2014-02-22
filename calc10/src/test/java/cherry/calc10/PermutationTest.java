package cherry.calc10;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cherry.calc10.Permutation.Callback;

public class PermutationTest {

	@Test
	public void testPermutation() {
		Permutation permutation = new Permutation();
		permutation.iterate(new Callback() {
			int count = 0;

			public void init() {
				count = 1;
			}

			public void execute(int[] digits) {
				int number = 0;
				for (int i = 0; i < digits.length; i++) {
					number *= 10;
					number += digits[i];
				}
				assertEquals(count++, number);
			}

			public void finish() {
				assertEquals(10000, count);
			}
		});
	}

}
