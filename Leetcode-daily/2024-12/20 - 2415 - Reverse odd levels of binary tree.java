// MEDIUM
// tree, dfs, binary-tree

/*
    Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.

    For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
    Return the root of the reversed tree.

    A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.

    The level of a node is the number of edges along the path between it and the root node. 

    Example 1:
    Input: root = [2,3,5,8,13,21,34]
    Output: [2,5,3,8,13,21,34]
    Explanation: 
    The tree has only one odd level.
    The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.

    Example 2:
    Input: root = [7,13,11]
    Output: [7,11,13]
    Explanation: 
    The nodes at level 1 are 13, 11, which are reversed and become 11, 13.
    
    Example 3:
    Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
    Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
    Explanation: 
    The odd levels have non-zero values.
    The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
    The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.

    Constraints:
    The number of nodes in the tree is in the range [1, 2^14].
    0 <= Node.val <= 10^5
    root is a perfect binary tree.
*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {

    // BETTER ONE
    // basically dfs traversal, with left node1 going to extreme left, and node2 to extreme right
    private void reverse(TreeNode node1, TreeNode node2, int level) {

        if (node1 == null || node2 == null)
            return;
        
        // we are just swapping values
        if ((level & 1) == 1) {
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }

        reverse(node1.left, node2.right, level + 1);
        reverse(node1.right, node2.left, level + 1);

    }

    public TreeNode reverseOddLevels_DFS_appraoch(TreeNode root) {
        reverse(root.left, root.right, 0 + 1);
        return root;
    }


    // BFS
    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;

        while (!q.isEmpty()) {
            int sz = q.size();
            List<TreeNode> levelNodes = new ArrayList<>();

            for (int i = 0; i < sz; i++) {
                TreeNode curr = q.poll();
                levelNodes.add(curr);
                
                if (curr.left != null) 
                    q.offer(curr.left);
                
                if (curr.right != null)
                    q.offer(curr.right);
            }


            if (level % 2 == 1) {
                // reverse the values of the nodes in that level
                int l = 0, r = sz - 1;
                while (l < r) {
                    int t = levelNodes.get(l).val;
                    levelNodes.get(l).val = levelNodes.get(r).val;
                    levelNodes.get(r).val = t;
                    l++;
                    r--;
                }
            }
            level++;
        }
        return root;
    }

}