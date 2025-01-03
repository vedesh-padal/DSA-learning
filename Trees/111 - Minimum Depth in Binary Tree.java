package trees;
// EASY
// trees

/*
    Given a binary tree, find its minimum depth.

    The minimum depth is the number of nodes along the shortest path from the
    root node down to the nearest leaf node.

    Note: A leaf is a node with no children.

    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 2

    Example 2:
    Input: root = [2,null,3,null,4,null,5,null,6]
    Output: 5

    Constraints:
    The number of nodes in the tree is in the range [0, 105].
    -1000 <= Node.val <= 1000
*/

class Solution {

    // Level order traversal - optimal
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        java.util.Queue<TreeNode> q = new java.util.LinkedList<>();
        q.offer(root);
        int depth = 0;

        while (!q.isEmpty()) {
            int sz = q.size();
            depth++;
            while (sz-- > 0) {
                TreeNode curr = q.poll();

                if (curr.left == null && curr.right == null)
                    return depth;
                
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
        }
        return depth;        
    }


    // ====================================
    private int mini = Integer.MAX_VALUE;

    private void find(TreeNode root, int level) {
        if (root == null)
            return;
        
        if (root.left == null && root.right == null && level < mini) {
            mini = level;
        }

        find(root.left, level + 1);
        find(root.right, level + 1);
    }

    public int minDepth_kindaBetter(TreeNode root) {
        if (root == null)
            return 0;

        find(root, 1);
        return mini;
    }


    // ======================================
    public int minDepth_firstTry(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return 1;

        if (root.left == null)
            return 1 + minDepth(root.right);

        if (root.right == null)
            return 1 + minDepth(root.left);

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}