// You are given two binary trees root1 and root2.

// Imagine that when you put one of them to cover the other, some nodes of the
// two trees are overlapped while the others are not. You need to merge the two
// trees into a new binary tree. The merge rule is that if two nodes overlap,
// then sum node values up as the new value of the merged node. Otherwise, the
// NOT null node will be used as the node of the new tree.

// Return the merged tree.
// Note: The merging process must start from the root nodes of both trees.

class TreeNode {
  TreeNode left, right; // Child pointers
  int val; // Node value

  public TreeNode(int d) {
    this.val = d; // Initialize value
    left = right = null; // Set children to null
  }
}

class Solution {
  public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null)
      return null;

    int v1 = (root1 != null) ? root1.val : 0;
    int v2 = (root2 != null) ? root2.val : 0;

    TreeNode root = new TreeNode(v1 + v2);

    root.left = mergeTrees((root1 != null) ? root1.left : null, (root2 != null) ? root2.left : null);
    root.right = mergeTrees((root1 != null) ? root1.right : null, (root2 != null) ? root2.right : null);

    return root;
  }
}