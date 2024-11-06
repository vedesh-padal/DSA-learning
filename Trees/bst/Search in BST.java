// You are given the root of a binary search tree (BST) and an integer val.

// Find the node in the BST that the node's value equals val and return
// the subtree rooted with that node. If such a node does not exist, return null.

package trees.bst;
import trees.TreeNode;

class Solution {
  public TreeNode searchBST_recursive(TreeNode root, int val) {
    if (root == null)
      return null;

    if (root.val == val)
      return root;

    if (root.val < val)
      return searchBST(root.right, val);
    else
      return searchBST(root.left, val);
  }

  public TreeNode searchBST(TreeNode root, int val) {
    while (root != null && root.val != val) {
      root = root.val < val ? root.right : root.left;
    }

    return root;
  }
}