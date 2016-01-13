package solution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SolutionTest {
	private Solution s;

	@Before
	public void setUp() throws Exception {
		s = new Solution();
	}

	@Test
	public void testKthSmallest() {
		/* fail("Not yet implemented"); */
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		assertEquals(1, s.kthSmallest(root, 1));
		assertEquals(2, s.kthSmallest(root, 2));
	}

	@Test
	public void testNumTrees() {
		assertEquals(2, s.numTrees(2));
	}

	@Test
	public void testGetHint() {
		assertTrue("1A1B".equals(s.getHint("1123", "0111")));
		assertTrue("1A3B".equals(s.getHint("1807", "7810")));
	}

	@Test
	public void testNumArray() {
		int[] nums = { -2, 0, 3, -5, 2, -1 };
		NumArray numArray = new NumArray(nums);
		assertTrue(numArray.sumRange(0, 2) == 1);
		assertTrue(numArray.sumRange(2, 5) == -1);
		assertTrue(numArray.sumRange(0, 5) == -3);
	}

	/*
	 * @Test public void testLengthOfLIS() { int[] nums = { 1, 2 };
	 * assertEquals(2, s.lengthOfLIS(nums)); int[] nums1 = { 10, 9, 2, 5, 3, 7,
	 * 101, 18 }; assertEquals(4, s.lengthOfLIS(nums1)); }
	 */

	@Test
	public void testSearchInsert() {
		int[] nums1 = { 1, 3, 5, 6 };
		assertEquals(2, s.searchInsert(nums1, 5));
		assertEquals(1, s.searchInsert(nums1, 2));
		assertEquals(4, s.searchInsert(nums1, 7));
		assertEquals(0, s.searchInsert(nums1, 0));
	}

	@Test
	public void testGenerateParenthesis() {
		List<?> list = s.generateParenthesis(3);
		for (Object e : list)
			System.out.println(e);
	}

	@Test
	public void testConvert() {
	}

	@Test
	public void testMaxProductZ() {
		String [] words = {"a", "aa", "aaa", "aaaa"};
		assertEquals(0, s.maxProduct(words));
		String [] words1 = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
		assertEquals(16, s.maxProduct(words1));
	}
	
	@Test
	public void testUniquePaths() {
		assertEquals(1, s.uniquePaths(1, 1));
		assertEquals(2, s.uniquePaths(2, 2));
		assertEquals(1, s.uniquePaths(1, 2));
	}
	
	@Test
	public void testCoinChange() {
		int [] coins = new int[] {1, 2};
		assertEquals(1, s.coinChange(coins, 2));
		assertEquals(2, s.coinChange(coins, 3));
		assertEquals(2, s.coinChange(coins, 4));
	}

	@Test
	public void testBulbSwitch() {
		String [] a = new String[] {};
		System.out.println(a.getClass());
		System.out.println("".getClass());
		assertEquals(1, s.bulbSwitch(99999));
	}
	
	@Test
	public void testPermute() {
		List<List<Integer>> list = s.permute(new int[] {1, 2, 3});
		for (List<Integer> e : list)
			System.out.println(e);
	}
	
	@Test
	public void testArray() {
		
	}
}
