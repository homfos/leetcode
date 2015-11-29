package solution;

import java.util.StringTokenizer;

/**297,Serialize and Deserialize Binary Tree
 * @author homfos
 *
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	StringBuilder sb = new StringBuilder();
    	serialize(root, sb);
    	return sb.toString().replaceAll(",$", "");
    }
    
    private void serialize(TreeNode root, StringBuilder sb) {
    	if (root == null) {
    		sb.append("#,");
    		return; 
    	}
    	
    	sb.append(root.val+",");
    	serialize(root.left, sb);
    	serialize(root.right, sb);
    }
    
 // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if (data == null || data.length() == 0)
    		return null;
    	StringTokenizer st = new StringTokenizer(data, ",");
    	return deserialize(st);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(StringTokenizer st) {
    	if (!st.hasMoreTokens())
    		return null;
    	String val = st.nextToken();
    	
    	if (val.equals("#"))
    		return null;
    	
    	TreeNode root = new TreeNode(Integer.parseInt(val));
    	root.left = deserialize(st);
    	root.right = deserialize(st);
    	return root;
    }
    
    public static void main(String [] args) {
    	Codec c = new Codec();
    	TreeNode root = new TreeNode(0);
    	root.left = new TreeNode(1);
    	root.right = new TreeNode(2);
    	System.out.println(c.serialize(root));
    	root = c.deserialize(c.serialize(root));
    	System.out.println(root);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
