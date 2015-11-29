package solution;

import static org.junit.Assert.*;

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
		/*fail("Not yet implemented");*/
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

}
