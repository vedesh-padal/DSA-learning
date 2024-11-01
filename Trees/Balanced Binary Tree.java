package trees;

class Solution {

  private int find(TreeNode root) {
    if (root == null)
      return 0;

    int left = find(root.left);
    if (left == -1)
      return -1;

    int right = find(root.right);
    if (right == -1)
      return -1;

    if (Math.abs(left - right) > 1)
      return -1;

    // height of this subtree
    return Math.max(left, right) + 1;
  }

  public boolean isBalanced(TreeNode root)  {
    return find(root) != -1;
  }
}