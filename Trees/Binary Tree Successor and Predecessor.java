package trees;

class Solution {

  private TreeNode prev, successor, predecessor;

  private void inorder(TreeNode root, TreeNode target) {
    if (root == null)
      return;
    
    inorder(root.left, target);

    if (prev == target) {
      successor = root;
    }

    if (root == target) {
      predecessor = prev;
    }
    
    prev = root;

    inorder(root.right, target);

  }


  public void findSuccessorPredecessor(TreeNode root, TreeNode target) {
    prev = null;
    successor = null;
    predecessor = null;

    inorder(root, target);

    System.out.println("Inorder Sucessor of " + target.val + " is : " + successor.val);
    System.out.println("Inorder Predecessor of " + target.val + " is : " + predecessor.val);

  }
}