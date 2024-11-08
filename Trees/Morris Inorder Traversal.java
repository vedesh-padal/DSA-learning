package trees;
import java.util.List;
import java.util.ArrayList;

class Solution {
  public List<Integer> morrisInorder(TreeNode root) {
    
    List<Integer> inorder = new ArrayList<>();
    
    TreeNode curr = root;

    while (curr != null) {
      if (curr.left == null) {
        inorder.add(curr.val);
        curr = curr.right;
      }
      else {
        TreeNode prev = curr.left;

        while (prev.right != null && prev.right != curr) {
          prev = prev.right;
        }

        if (prev.right == null) {
          prev.right = curr;
          curr = curr.left;
        }
        else {
          prev.right = null;
          inorder.add(curr.val);
          curr = curr.right;
        }
      }
    }
    
    return inorder;
  }
}