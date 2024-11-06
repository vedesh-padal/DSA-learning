package trees.bst;

// You are given the root node of a binary search tree (BST) and a value to insert into the tree.
// Return the root node of the BST after the insertion. It
// is guaranteed that the new value does not exist in the original BST.

// Notice that there may exist multiple valid ways for the insertion, as long as
// the tree remains a BST after insertion. You can return any of them.

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int val) {
    this.val = val;
  }
}

class Solution {
  // this approach is an easy one
  // inserting at the leaf node where the `to be inserted` val should be
  public TreeNode insertIntoBST(TreeNode root, int val) {
    TreeNode newNode = new TreeNode(val);
    if (root == null)
      return newNode;

    TreeNode curr = root;

    while (true) {
      if (curr.val <= val) {
        if (curr.right != null)
          curr = curr.right;
        else {
          curr.right = newNode;
          break;
        }
      } 
      else {
        if (curr.left != null)
          curr = curr.left;
        else {
          curr.left = newNode;
          break;
        }
      }
    }

    return root;
  }
}