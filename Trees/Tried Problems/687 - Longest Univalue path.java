// MEDIUM
// trees, dfs

/*
    Given the root of a binary tree, return the length of the longest path, where
    each node in the path has the same value. This path may or may not pass through the root.

    The length of the path between two nodes is represented by the number of edges between them.

    Example 1:
    Input: root = [5,4,5,1,1,null,5]
    Output: 2
    Explanation: The shown image shows that the longest path of the same value (i.e. 5).

    Example 2:
    Input: root = [1,4,5,4,4,null,5]
    Output: 2
    Explanation: The shown image shows that the longest path of the same value (i.e. 4).

    Constraints:
    The number of nodes in the tree is in the range [0, 10^4].
    -1000 <= Node.val <= 1000
    The depth of the tree will not exceed 1000.
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  
    TreeNode(int val) {
      this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

class Solution {
    private int maxi;

    private int dfs(TreeNode node, int upper) {
        if (node == null)
            return 0;

        int leftLongest = dfs(node.left, node.val);
        int rightLongest = dfs(node.right, node.val);

        // MY MISTAKE: in first try, was directly adding 1,
        // BUT WE NEED TO CONSIDER THE EDGE CASES, WHEN CHILD IS NULL, SO NEED TO HANDLE THAT WAY
        
        // maxi = Math.max(maxi, leftLongest + rightLongest);
        // return ((upper == node.val) ? 1 : 0) + Math.max(leftLongest, rightLongest);

        int leftPath = 0, rightPath = 0;
        if (node.left != null && node.left.val == node.val) {
            leftPath = 1 + leftLongest;
        }

        if (node.right != null && node.right.val == node.val) {
            rightPath = 1 + rightLongest;
        }

        maxi = Math.max(maxi, leftPath + rightPath);

        return Math.max(leftPath, rightPath);
    }

    public int longestUnivaluePath(TreeNode root) {
        maxi = 0;
        dfs(root, -1001);   // parent val;
        return maxi;
    }
}