// Given a root node reference of a BST and a key, delete the node 
// with the given key in the BST. Return the root node reference 
// (possibly updated) of the BST.

// Basically, the deletion can be divided into two stages:

// Search for a node to remove.
// If the node is found, delete the node.

package trees.bst;

class Solution {
  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null)
      return root;

    if (root.val == key)
      return helper(root);

    TreeNode curr = root;

    while (curr != null) {
      if (curr.val > key) {
        if (curr.left != null && curr.left.val == key) {
          // curr.left = helper(curr.left);
          curr.left = helper2(curr.left);
          break;
        } 
        else {
          curr = curr.left;
        }
      } 
      else {
        if (curr.right != null && curr.right.val == key) {
          // curr.right = helper(curr.right);
          curr.right = helper2(curr.right);
          break;
        }
        else {
          curr = curr.right;
        }
      }
    }

    return root;
  }

  // find the right most node of the left child,
  // and attach the right of the parent of the deleting node
  // to the right of the right most of the left child

  // you can also write this logic in the other way around too
  private TreeNode helper(TreeNode root) {
    if (root.left == null)
      return root.right;

    if (root.right == null)
      return root.left;

    TreeNode rightChild = root.right;
    TreeNode rightMostOfLeftChild = findRightMost(root.left);
    rightMostOfLeftChild.right = rightChild;
    return root.left;
  }

  private TreeNode findRightMost(TreeNode root) {
    TreeNode curr = root;
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr;
  }

  // @SuppressWarnings("unused")
  // Other way of doing
  private TreeNode helper2(TreeNode root) {
    if (root.left == null)
      return root.right;

    if (root.right == null)
      return root.left;

    TreeNode leftChild = root.left;
    TreeNode leftMostOfRightChild = findLeftMost(root.right);
    leftMostOfRightChild.left = leftChild;
    return root.right;
  }

  private TreeNode findLeftMost(TreeNode root) {
    TreeNode curr = root;
    while (curr.left != null) {
      curr = curr.left;
    }
    return curr;
  }
}