package solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

	/**
	 * 6. ZigZag Conversion
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert(String s, int numRows) {
		if (numRows <= 0)
			throw new IllegalArgumentException("numRows must >= 1");

		if (s == null)
			throw new IllegalArgumentException("String must not null");

		if (numRows == 1 || numRows >= s.length())
			return s;

		throw new RuntimeException("to do");
	}

	/**
	 * 22st,Generate Parentheses
	 * 
	 * @param n
	 * @return "((()))", "(()())", "(())()", "()(())", "()()()"
	 */
	public List<String> generateParenthesis(int n) {
		List<String> ret = new ArrayList<String>();
		allParentheses(n, n, "", ret);
		return ret;
	}

	public static void allParentheses(int left, int right, String str, List<String> res) {
		if (left > right)
			return;

		if (left == 0 && right == 0) {
			res.add(str);
			return;
		}

		if (left > 0)
			allParentheses(left - 1, right, str + "(", res);
		if (right > 0)
			allParentheses(left, right - 1, str + ")", res);
	}

	/**
	 * 31. Next Permutation
	 * 
	 * @param nums
	 */

	/*
	 * Implement next permutation, which rearranges numbers into the
	 * lexicographically next greater permutation of numbers.
	 * 
	 * If such arrangement is not possible, it must rearrange it as the lowest
	 * possible order (ie, sorted in ascending order).
	 * 
	 * The replacement must be in-place, do not allocate extra memory.
	 * 
	 * Here are some examples. Inputs are in the left-hand column and its
	 * corresponding outputs are in the right-hand column. 1,2,3 ¡ú 1,3,2 3,2,1 ¡ú
	 * 1,2,3 1,1,5 ¡ú 1,5,1
	 */

	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 1)
			return;
		if (isMax(nums))
			reverse(nums);

	}

	private void reverse(int[] nums) {
		int[] result = new int[nums.length];
		for (int i = nums.length - 1, j = 0; i >= 0; --i, j++) {
			result[j] = nums[i];
		}
	}

	public boolean isMax(int[] num) {
		if (num.length == 0)
			return true;
		for (int i = 1; i < num.length; ++i) {
			if (num[i - 1] - num[i] < 0)
				return false;
		}
		return true;
	}

	/**
	 * 35st,Search Insert Position
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] nums, int target) {
		for (int i = 0; i < nums.length; ++i) {
			if (nums[i] >= target) {
				return i;
			}
		}
		return nums.length;
	}

	/**
	 * 46. Permutations
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		premute(nums.length, nums, new ArrayList<>(), list);
		return list;
	}

	public void premute(int size, int[] nums, List<Integer> element, List<List<Integer>> result) {
		if (element.size() == size) {
			result.add(element);
			return;
		}

		for (int i = 0; i < nums.length; ++i) {
			if (!element.contains(nums[i])) {
				List<Integer> tmp = new ArrayList<>(element);
				tmp.add(nums[i]);
				premute(size, nums, tmp, result);
			}
		}
	}

	/**
	 * 62. Unique Paths
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];

		for (int i = 0; i < dp.length; ++i) {
			dp[i][0] = 1;
		}

		for (int i = 0; i < dp[0].length; ++i) {
			dp[0][i] = 1;
		}

		for (int i = 1; i < m; ++i) {
			for (int j = 1; j < n; ++j) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}

		return dp[m - 1][n - 1];
	}

	/**
	 * 64. Minimum Path Sum
	 * 
	 * Given a m x n grid filled with non-negative numbers, find a path from top
	 * left to bottom right which minimizes the sum of all numbers along its
	 * path.
	 * 
	 * Note: You can only move either down or right at any point in time.
	 * 
	 * @param grid
	 * @return
	 */
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int[][] result = new int[grid.length][grid[0].length];
		result[0][0] = grid[0][0];
		return minPathSum(grid, grid.length - 1, grid[0].length - 1, result);
	}

	private int minPathSum(int[][] grid, int x, int y, int[][] result) {
		if (x == 0 && y == 0)
			return grid[x][y];
		if (result[x][y] != 0)
			return result[x][y];
		if (x == 0) {
			int path =  minPathSum(grid, x, y - 1, result) + grid[x][y];
			result[x][y] = path;
			return path;
		}
		if (y == 0) {
			int path = minPathSum(grid, x - 1, y, result) + grid[x][y];
			result[x][y] = path;
			return path;
		}

		int path = Math.min(minPathSum(grid, x - 1, y, result), minPathSum(grid, x, y - 1, result)) + grid[x][y];
		result[x][y] = path;
		return path;
	}

	/**
	 * 75. Sort Colors
	 * 
	 * @param nums
	 *            Given an array with n objects colored red, white or blue, sort
	 *            them so that objects of the same color are adjacent, with the
	 *            colors in the order red, white and blue.
	 * 
	 *            Here, we will use the integers 0, 1, and 2 to represent the
	 *            color red, white, and blue respectively.
	 */
	public void sortColors(int[] nums) {
		if (nums == null || nums.length == 0)
			return;
		int[] colors = { 0, 1, 2 };
		int[] sorted = new int[nums.length];
		int count = 0;
		for (int c : colors) {
			for (int e : nums) {
				if (e == c) {
					sorted[count++] = e;
				}
			}
		}
		System.arraycopy(sorted, 0, nums, 0, nums.length);
	}

	/**
	 * 77st,Combinations
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	/*
	 * public List<List<Integer>> combine(int n, int k) {
	 * 
	 * }
	 */

	/**
	 * 89. Gray Code
	 * 
	 * @param n
	 * @return
	 */
	/*
	 * public List<Integer> grayCode(int n) {
	 * 
	 * }
	 */

	/**
	 * 95st,Unique Binary Search Trees II
	 * 
	 * @param n
	 * @return
	 */

	/*
	 * public List<TreeNode> generateTrees(int n) {
	 * 
	 * }
	 */

	/* 96st,Unique Binary Search Trees */
	public int numTrees(int n) {
		int[] nums = new int[n + 1];
		nums[0] = 1;
		nums[1] = 1;
		for (int i = 2; i <= n; ++i) {
			int count = 0;
			for (int j = 1; j <= i; ++j) {
				count += nums[j - 1] * nums[i - j];
			}
			nums[i] = count;
		}

		return nums[n];
	}

	/**
	 * 116st,Populating Next Right Pointers in Each Node
	 * 
	 * @param root
	 */
	public void connect(TreeLinkNode root) {
		if (root == null)
			return;
		Queue<TreeLinkNode> parent = new LinkedList<TreeLinkNode>();
		Queue<TreeLinkNode> child = new LinkedList<TreeLinkNode>();
		parent.add(root);
		while (!parent.isEmpty() || !child.isEmpty()) {
			if (!parent.isEmpty()) {
				TreeLinkNode node = parent.poll();
				node.next = parent.peek();
				if (node.left != null) {
					child.add(node.left);
				}
				if (node.right != null) {
					child.add(node.right);
				}
			} else {
				parent.addAll(child);
				child.clear();
			}
		}
	}

	/**
	 * 118. Pascal's Triangle
	 * 
	 * @param numRows
	 * @return
	 */
	public List<List<Integer>> generate(int numRows) {
		if (numRows == 0)
			return new ArrayList<List<Integer>>();

		List<List<Integer>> list = new ArrayList<>();
		List<Integer> row = new ArrayList<>();
		row.add(1);
		list.add(row);
		if (numRows == 1) {
			return list;
		}
		for (int i = 1; i < numRows; ++i) {
			list.add(generate(list.get(i - 1)));
		}
		return list;
	}

	public List<Integer> generate(List<Integer> current) {
		List<Integer> nextRow = new ArrayList<>(current.size() + 1);
		nextRow.addAll(current);
		nextRow.set(0, 1);
		nextRow.add(1);
		for (int i = 1; i < current.size(); ++i) {
			nextRow.set(i, current.get(i - 1) + current.get(i));
		}

		return nextRow;
	}

	/**
	 * 119. Pascal's Triangle II
	 * 
	 * @param rowIndex
	 * @return
	 */
	public List<Integer> getRow(int rowIndex) {
		List<Integer> current = new ArrayList<>();
		current.add(1);

		if (rowIndex == 0)
			return current;

		for (int i = 1; i < rowIndex; ++i) {
			current = generate(current);
		}

		return current;
	}

	/**
	 * 122st,Best Time to Buy and Sell Stock II
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length <= 1)
			return 0;

		int lastPrice = prices[0];
		int profit = 0;
		int length = prices.length - 1;
		int lastBuyPrice = prices[0];

		for (int i = 1; i < length; ++i) {
			if (prices[i] >= lastPrice) {
				lastPrice = prices[i];
				continue;
			} else {
				int currentProfit = lastPrice - lastBuyPrice;
				profit += currentProfit > 0 ? currentProfit : 0;
				lastBuyPrice = prices[i];
				lastPrice = prices[i];
			}
		}

		if (prices[length] > lastPrice)
			profit += prices[length] - lastBuyPrice;
		else
			profit += lastPrice - lastBuyPrice;

		return profit;
	}

	/**
	 * 133. Clone Graph
	 * 
	 * @param node
	 * @return
	 */
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		return node;
	}

	/**
	 * 230st,Kth Smallest Element in a BST
	 * 
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallest(TreeNode root, int k) {
		List<Integer> values = new ArrayList<Integer>();
		inOrderTraverse(root, values);
		return values.get(k - 1);
	}

	private void inOrderTraverse(TreeNode root, List<Integer> list) {
		if (root == null)
			return;
		inOrderTraverse(root.left, list);
		list.add(root.val);
		inOrderTraverse(root.right, list);
	}

	/**
	 * 299st, Bulls and Cows
	 * 
	 * @param secret
	 * @param guess
	 * @return
	 */
	public String getHint(String secret, String guess) {
		assert secret != null && guess != null && secret.length() == guess.length();

		Integer bulls = 0, cows = 0;
		int length = secret.length();
		List<Character> chars = new ArrayList<Character>();
		for (int i = 0; i < length; ++i) {
			chars.add(secret.charAt(i));
			if (secret.charAt(i) == guess.charAt(i))
				bulls++;
		}
		for (int i = 0; i < length; ++i) {
			Character c = guess.charAt(i);
			if (chars.contains(c)) {
				cows++;
				chars.remove(c);
			}
		}
		cows -= bulls;
		return bulls.toString() + "A" + cows.toString() + "B";
	}

	/**
	 * 300st,Longest Increasing Subsequence
	 * 
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS(int[] nums) {
		if (nums.length == 0)
			return 0;
		int max = 0;
		if (nums.length == 1)
			return 1;
		int lastValue = nums[0];
		int current = 1;
		for (int i = 1; i < nums.length - 1; ++i) {
			if (nums[i] > lastValue) {
				current++;
			} else {
				max = max > current ? max : current;
				current = 1;
			}
			lastValue = nums[i];
		}
		if (nums[nums.length - 1] > lastValue)
			current++;
		max = max > current ? max : current;
		return max;
	}

	/**
	 * 309. Best Time to Buy and Sell Stock with Cooldown
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit2(int[] prices) {
		return 1;
	}

	/**
	 * 318. Maximum Product of Word Lengths
	 * 
	 * @param words
	 * @return
	 */
	public int maxProduct(String[] words) {
		int max = 0;
		int[] bitChecker = new int[words.length];
		for (int i = 0; i < words.length; ++i) {
			int num = 0;
			for (int j = 0; j < words[i].length(); ++j) {
				num |= 1 << (words[i].charAt(j) - 'a');
			}
			bitChecker[i] = num;
		}

		for (int i = 0; i < words.length; ++i) {
			for (int j = i + 1; j < words.length; ++j) {
				if ((bitChecker[i] & bitChecker[j]) == 0) {
					max = max > words[i].length() * words[j].length() ? max : words[i].length() * words[j].length();
				}
			}
		}
		return max;
	}

	/**
	 * 319. Bulb Switcher
	 * 
	 * @param n
	 * @return
	 */
	public int bulbSwitch(int n) {
		if (n == 1 || n == 0)
			return n;

		boolean[] result = new boolean[n];
		for (int i = 0; i < n; ++i)
			result[i] = false;

		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (j % i == 0)
					result[j - 1] = result[j - 1] ? false : true;
			}
		}

		int count = 0;
		for (boolean b : result) {
			if (b)
				count++;
		}

		return count;
	}

	/**
	 * 322. Coin Change
	 * 
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChange(int[] coins, int amount) {
		if (amount == 0)
			return 0;

		int max = Integer.MIN_VALUE;
		for (int c : coins)
			max = max > c ? max : c;

		int size = amount > max ? amount : max;
		int[] dp = new int[size + 1];

		for (int i = 0; i <= size; ++i) {
			dp[i] = -1;
		}
		for (int coin : coins) {
			dp[coin] = 1;
		}
		for (int i = 1; i <= amount; ++i) {
			int min = Integer.MAX_VALUE;
			for (int c : coins) {
				if (c == i) {
					min = 0;
					break;
				}

				if (i - c < 0 || dp[i - c] == -1)
					continue;

				min = min > dp[i - c] ? dp[i - c] : min;
			}
			if (min != Integer.MAX_VALUE)
				dp[i] = min + 1;
		}

		return dp[amount];
	}

	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}

	public static void main(String[] args) {
		int[][] array = new int[2][3];
		System.out.println(array.length);
	}
}
