// Given the root of a binary search tree and an integer k, return true 
// if there exist two elements in the BST such that their sum is equal to k, or false otherwise.

package trees.bst;
import java.util.*;


class BSTIterator {
  private Stack<TreeNode> stk;
  private boolean reverse;

  public BSTIterator(TreeNode root, boolean isReverse) {
    reverse = isReverse;
    stk = new Stack<>();
    pushAll(root);
  }

  public boolean hasNext() {
    return !stk.isEmpty();
  }

  public int next() {
    TreeNode tmpNode = stk.pop();
    if (reverse == false) {
      if (tmpNode.right != null)
        pushAll(tmpNode.right);
    } 
    else {
      if (tmpNode.left != null)
        pushAll(tmpNode.left);
    }
    return tmpNode.val;
  }

  private void pushAll(TreeNode node) {
    while (node != null) {
      stk.push(node);

      // push all lefts, becoz we need ascending order -> next node
      if (reverse == false) {
        node = node.left;
      } 
      else {
        node = node.right;
      }
    }
  }
}

class Solution {
  public boolean findTarget_MOST_OPTIMAL(TreeNode root, int k) {
    BSTIterator l = new BSTIterator(root, false);
    BSTIterator r = new BSTIterator(root, true);

    // get the smallest value
    int i = l.next(); // l.before() actually

    // get the largest value
    int j = r.next();

    // classic 2-pointer approach for 2-sum
    while (i < j) {
      if (i + j == k) {
        return true;
      }
      else if (i + j < k) {
        i = l.next();
      } 
      else {
        j = r.next();
      }

    }

    return false;
  }

  // ===============================================
  // SIMPLE WAY OF DOING

  private void inorder(TreeNode root, List<Integer> al) {
    if (root == null)
      return;

    inorder(root.left, al);
    al.add(root.val);
    inorder(root.right, al);
  }

  public boolean findTarget(TreeNode root, int k) {
    List<Integer> al = new ArrayList<>();

    inorder(root, al);

    int l = 0, r = al.size() - 1;

    while (l < r) {
      int sum = al.get(l) + al.get(r);

      if (sum == k) {
        return true;
      }
      else if (sum < k) {
        l++;
      } 
      else {
        r--;
      }
    }
    return false;
  }
}