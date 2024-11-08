// Given the root of a binary tree, determine if it is a valid binary search tree (BST).

// A valid BST is defined as follows:
// - The left subtree of a node contains only nodes with keys less than the node's key.
// - The right subtree of a node contains only nodes with keys greater than the node's key.
// - Both the left and right subtrees must also be binary search trees.
 

package trees.bst;

class Solution {

  private boolean check(TreeNode root, long mini, long maxi) {
    if (root == null)
      return true;
    
    if (root.val >= maxi || root.val <= mini)
      return false;

    return check(root.left, mini, root.val) && check(root.right, root.val, maxi);
  }

  public boolean isValidBST(TreeNode root) {

    return check(root, Long.MIN_VALUE, Long.MAX_VALUE);

  }
}
