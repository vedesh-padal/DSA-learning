// Given the root of a binary tree and an integer targetSum, return all
// root-to-leaf paths where the sum of the node values in the path equals
// targetSum. Each path should be returned as a list of the node values, not
// node references.

// A root-to-leaf path is a path starting from the root and ending at any leaf
// node. A leaf is a node with no children.
package trees;
import java.util.*;

class Solution {
  private void dfs(TreeNode root, int tar, List<List<Integer>> res, List<Integer> curr) {
    if (root == null)
      return;
    if (root.left == null && root.right == null) {
      curr.add(root.val);
      if (tar - root.val == 0) {
        res.add(new ArrayList<>(curr));
      }
      curr.remove(curr.size() - 1);
      return;
    }

    // considering left
    curr.add(root.val);
    dfs(root.left, tar - root.val, res, curr);
    // considering right
    // curr.remove(curr.size() - 1);
    dfs(root.right, tar - root.val, res, curr);
    curr.remove(curr.size() - 1);
  }

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> res = new ArrayList<>();
    dfs(root, targetSum, res, new ArrayList<>());
    return res;
  }
}