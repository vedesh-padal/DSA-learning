// EASY
// trees, dfs

/*
    Given a non-empty special binary tree consisting of nodes with the
    non-negative value, where each node in this tree has exactly two or zero
    sub-node. If the node has two sub-nodes, then this node's value is the smaller value among 
    its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

    Given such a binary tree, you need to output the second minimum value 
    in the set made of all the nodes' value in the whole tree.

    If no such second minimum value exists, output -1 instead.

    Example 1:
    Input: root = [2,2,5,null,null,5,7]
    Output: 5
    Explanation: The smallest value is 2, the second smallest value is 5.

    Example 2:
    Input: root = [2,2,2]
    Output: -1
    Explanation: The smallest value is 2, but there isn't any second smallest value.
    
    Constraints:
    The number of nodes in the tree is in the range [1, 25].
    1 <= Node.val <= 231 - 1
    root.val == min(root.left.val, root.right.val) for each internal node of the tree.
*/
import java.util.*;

import trees.bst.TreeNode;

class Solution {

    private void dfs2(TreeNode node, long[] minis) {
        if (node == null)
            return;

        if (node.val > minis[0] && node.val < minis[1]) {
            minis[1] = node.val;
        }

        else if (node.val == minis[0]) {
            dfs2(node.left, minis);
            dfs2(node.right, minis);
        }
    }
    
    public int findSecondMinimumValue(TreeNode root) {
        // mini, 2ndMini
        long[] minis = { root.val, Long.MAX_VALUE };

        dfs2(root, minis);
        return (minis[1] == Long.MAX_VALUE) ? -1 : (int)minis[1];
    }


    // this doesn't work, edge case:
    // [1,1,3,1,1,3,4,3,1,1,1,3,8,4,8,3,3,1,6,2,1]
    // expected: 2, my output: 3
    // deep down there is a 2, under 3 in send level
    public int findSecondMinimumValue_firstTry(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int mini = root.val;

        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                TreeNode curr = q.poll();
                if (curr.val > mini) {
                    return curr.val;
                }
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
        }
        return -1;
    }
}