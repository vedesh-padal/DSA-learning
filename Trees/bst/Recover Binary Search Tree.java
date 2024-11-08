// You are given the root of a binary search tree (BST), where the values 
// of exactly two nodes of the tree were swapped by mistake. 
// Recover the tree without changing its structure.

package trees.bst;
import java.util.*;

class Solution {

  // NAIVE APPROACH:
  // first do in-order and store in a list
  // then, sort this list (now, we have the correct inorder if the tree was
  // actually BST)

  // now, we traverse (inorder) through the tree again and
  // compare the expected values, if different swap with correct value in that
  // node

  private void inorderToStore(TreeNode root, List<Integer> al) {
    if (root == null)
      return;

    inorderToStore(root.left, al);
    al.add(root.val);
    inorderToStore(root.right, al);
  }

  private void inorderCheckAndCorrect(TreeNode root, List<Integer> al, int[] i) {
    if (root == null)
      return;

    inorderCheckAndCorrect(root.left, al, i);
    if (root.val != al.get(i[0])) {
      int t = root.val;
      root.val = al.get(i[0]);
      al.set(i[0], t);
    }
    i[0]++;

    inorderCheckAndCorrect(root.right, al, i);
  }

  public void recoverTree_NAIVE(TreeNode root) {
    List<Integer> al = new ArrayList<>();
    inorderToStore(root, al);
    Collections.sort(al);

    inorderCheckAndCorrect(root, al, new int[] { 0 });
  }

  // ===============================================

  // TO SOLVE EFFICIENTLY:
  // we have 2 cases:
  // - if the incorrect nodes are adjacent in inorder
  // - if they are not adjacent

  private TreeNode first, middle, last;
  private TreeNode prev;

  private void inorder(TreeNode curr) {
    if (curr == null)
      return;

    inorder(curr.left);

    // violation, need to swap
    if (prev != null && curr.val < prev.val) {
      if (first == null) {
        first = prev;
        middle = curr;
      } 
      else {
        last = curr;
      }
    }

    prev = curr;

    inorder(curr.right);
  }

  public void recoverTree(TreeNode root) {
    
    first = middle = last = null;
    prev = new TreeNode(Integer.MIN_VALUE);

    inorder(root);

    // the violating nodes are not adjacent (when inorder is written)
    if (first != null && last != null) {
      // swap them
      int t = first.val;
      first.val = last.val;
      last.val = t;
    } 
    else if (first != null && middle != null) {
      int t = first.val;
      first.val = middle.val;
      middle.val = t;
    }

  }

}