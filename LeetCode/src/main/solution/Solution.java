package solution;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	/**
	 * 95st,Unique Binary Search Trees II
	 * 
	 * @param n
	 * @return
	 */
	
	/*public List<TreeNode> generateTrees(int n) {

	}*/
	
	/*96st,Unique Binary Search Trees*/
	public int numTrees(int n) {
		int [] nums = new int[n+1];
		nums[0] = 1;
		nums[1] = 1;
		for (int i = 2; i <= n; ++i) {
			int count = 0;
			for (int j = 1;j <= i; ++j) {
				count += nums[j - 1] * nums[i - j];
			}
			nums[i] = count;
		}

		return nums[n];
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
}


