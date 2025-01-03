// EASY - MEDIUM
// tree, bfs, dfs, binary-tree
/*
    Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

    Example 1:
    Input: root = [1,3,2,5,3,null,9]
    Output: [1,3,9]

    Example 2:
    Input: root = [1,2,3]
    Output: [1,3]

    Constraints:
    The number of nodes in the tree will be in the range [0, 10^4].
    -2^31 <= Node.val <= 2^31 - 1
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
    private List<Integer> ans;

    private void dfs(TreeNode node, int lvl) {
        if (node != null) {
            int val = node.val;
            
            // first time visiting that level
            if (lvl == ans.size()) {
                ans.add(val);
            }
            else {
                ans.set(lvl, Math.max(ans.get(lvl), val));
            }

            dfs(node.left, lvl + 1);
            dfs(node.right, lvl + 1);
        }
    }

    // using DFS
    public List<Integer> largestValues(TreeNode root) {
        ans = new ArrayList<>();
        dfs(root, 0);   // node, level
        return ans;
    }

    // ==========================================
    // using BFS
    public List<Integer> largestValues_BFS(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int maxi = Integer.MIN_VALUE;
            while (size-- > 0) {
                TreeNode curr = q.poll();
                maxi = Math.max(maxi, curr.val);
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            }
            res.add(maxi);
        }
        return res;
    }
}