package solution;

import java.util.Arrays;

/**303st,Range Sum Query - Immutable
 * @author homfos
 *
 */
public class NumArray {
	private int [] data = null;
	private int [] sums = null;

	public NumArray(int[] nums) {
		data = nums;
		sums = new int[data.length];
		int sum = 0;
		for (int i = 0; i < data.length; ++i) {
			sum += data[i];
			sums[i] = sum;
		}
	}

	public int sumRange(int i, int j) {
		return sums[j] - sums[i] + data[i];
	}
	
	public static void main(String []args) {
		 int [] nums = {-2, 0, 3, -5, 2, -1};
		 NumArray numArray = new NumArray(nums);
		 numArray.sumRange(1, 2);
	}
}
