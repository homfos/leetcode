package solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

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

	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	}
}
