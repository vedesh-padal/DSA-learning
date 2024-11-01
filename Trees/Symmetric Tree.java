// EASY
// trees

// Given the root of a binary tree, check whether it is a mirror of 
// itself (i.e., symmetric around its center).

package trees;

import java.util.Queue;
import java.util.LinkedList;

class Solution {

  // recursive version
  private boolean checkSymmetry(TreeNode root1, TreeNode root2) {
    if (root1 == null || root2 == null) {
      // both should be null if they are symmetric
      return root1 == root2; // here we are checking if both are null
    }

    return (root1.val == root2.val) &&
        checkSymmetry(root1.left, root2.right) &&
        checkSymmetry(root1.right, root2.left);
  }

  public boolean isSymmetric_RECURSIVE(TreeNode root) {
    if (root == null)
      return true;

    return checkSymmetry(root.left, root.right);
  }

  // ===================================================================
  // ITERATIVE VERSION
  // but this will take more space compared to recursive version
  public boolean isSymmetric(TreeNode root) {
    if (root == null)
      return true;

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root.left);
    q.add(root.right);

    while (!q.isEmpty()) {
      TreeNode left = q.poll();
      TreeNode right = q.poll();

      if (left == null && right == null)
        continue;

      if (left == null || right == null || left.val != right.val)
        return false;

      q.add(left.left);
      q.add(right.right);
      q.add(left.right);
      q.add(right.left);
    }
    return true;
  }
}