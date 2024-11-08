package trees;

import java.util.ArrayList;

class Solution {

  // inorder traversal of a BST will give in sorted order
  private void find(TreeNode root, ArrayList<Integer> al) {
    if (root == null)
      return;

    find(root.left, al);
    al.add(root.val);
    find(root.right, al);
  }

  public int kthSmallest_INITIAL(TreeNode root, int k) {
    ArrayList<Integer> al = new ArrayList<>();
    
    find(root, al);
    System.out.println(al);
    
    return al.get(k-1);
  }
  
  // =========================================
  private int cnt = 0;
  private int result = -1;

  private void findKthSmallest(TreeNode root, int k) {
    if (root == null)
      return;

    findKthSmallest(root.left, k);
    cnt++;
    if (cnt == k) {
      result = root.val;
      return;
    }

    findKthSmallest(root.right, k);

  }
  
  public int kthSmallest_BETTER(TreeNode root, int k) {

    findKthSmallest(root, k);

    return result;
  }
  
  // =========================================
  // MOST OPTIMAL WAY:
  // MORRIS ORDER TRAVERSAL:
  // TC: O(N), SC: O(1)
  public int kthSmallest(TreeNode root, int k) {

    TreeNode curr = root;
    int cnt = 0;

    while (curr != null) {
      if (curr.left == null) {
        cnt++;
        if (cnt == k)
          return curr.val;
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
        else { // prev.right == curr
          prev.right = null;
          cnt++;
          if (cnt == k)
            return curr.val;
          curr = curr.right;
        }
      }

    }
    return -1;
  }
}