// MEDIUM - EASY
// tree, dfs, bfs

/*
    Given a binary tree root, a node X in the tree is named good if in the path from 
    root to X there are no nodes with a value greater than X.

    Return the number of good nodes in the binary tree.

    Example 1:
    Input: root = [3,1,4,3,null,1,5]
    Output: 4
    Explanation: Nodes in blue are good.
    Root Node (3) is always a good node.
    Node 4 -> (3,4) is the maximum value in the path starting from the root.
    Node 5 -> (3,4,5) is the maximum value in the path
    Node 3 -> (3,1,3) is the maximum value in the path.

    Example 2:
    Input: root = [3,3,null,4,2]
    Output: 3
    Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.

    Example 3:
    Input: root = [1]
    Output: 1
    Explanation: Root is considered as good.
    
    Constraints:
    The number of nodes in the binary tree is in the range [1, 10^5].
    Each node's value is between [-10^4, 10^4].
*/
import trees.bst.TreeNode;

class Solution {
    private int dfs(TreeNode node, int maxiSoFar) {
        if (node == null)
            return 0;
            
        int curr = (node.val >= maxiSoFar) ? 1 : 0;
        maxiSoFar = Math.max(node.val, maxiSoFar);
        int left = dfs(node.left, maxiSoFar);
        int right = dfs(node.right, maxiSoFar);
        return (curr + left + right);
    }

    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }
}