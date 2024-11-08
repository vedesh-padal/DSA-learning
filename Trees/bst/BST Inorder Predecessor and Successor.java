package trees.bst;

// there are 3 ways to do it
// the one written here is the optimal one using the BST property

// other two methods: (should be told in interviews):
// 1. store the inorder, and do a traversal in that inorder list to find the 
//    first element greater than the target element
// 2. perform inorder traversal, and when doing, the first value that you encounter
//    that is greater than the given target element, we return that


class Solution {
  public TreeNode findInorderSuccessor(TreeNode root, TreeNode target) {
    TreeNode curr = root;
    TreeNode successor = null;

    while (curr != null) {
      if (curr.val > target.val) {
        successor = curr;
        curr = curr.left;
      }
      else {
        curr = curr.right;
      }
    }

    return successor;
  }

  public TreeNode findInorderPredecessor(TreeNode root, TreeNode target) {
    TreeNode curr = root;
    TreeNode predecessor = null;

    while (curr != null) {
      if (curr.val < target.val) {
        predecessor = curr;
        curr = curr.right;
      }
      else {
        curr = curr.left;
      }
    }
    
    return predecessor;
  }
}