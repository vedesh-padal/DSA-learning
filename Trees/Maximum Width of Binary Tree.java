// MEDIUM

// Given the root of a binary tree, return the maximum width of the given tree.

// The maximum width of a tree is the maximum width among all levels.

// The width of one level is defined as the length between the end-nodes (the
// leftmost and rightmost non-null nodes), where the null nodes between the
// end-nodes that would be present in a complete binary tree extending down to
// that level are also counted into the length calculation.

// It is guaranteed that the answer will in the range of a 32-bit signed integ

// Constraints:
// The number of nodes in the tree is in the range [1, 3000].
// -100 <= Node.val <= 100

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
package trees;

import java.util.*;

class Pair {
  TreeNode node;
  int ind;

  public Pair(TreeNode node, int ind) {
    this.node = node;
    this.ind = ind;
  }
}

class Solution {
  public int widthOfBinaryTree(TreeNode root) {
    // We will be doing Level Order Traversal
    // and we mark the indices of the nodes when doing level order traversal
    // but, since the no. of nodes can be very large
    // and especially when the tree is in skewed
    // when we try to calculate indices of left and right nodes of a parent
    // we do that by (2*i + 1), (2*i + 2)
    // and that number can go really high when the tree is skewed

    // So, apply some logic to subract the calculated index of the next level
    // with the previous level min. index, This way we avoid overflow

    Queue<Pair> q = new LinkedList<>();
    // node, index
    q.add(new Pair(root, 0));

    int maxWidth = Integer.MIN_VALUE;

    while (!q.isEmpty()) {
      int size = q.size(); // curr. level size
      int minInd = q.peek().ind;
      int first = 0, last = 0; // first and last index to find the width at that level
      for (int i = 0; i < size; i++) {
        TreeNode node = q.peek().node;
        int currInd = q.peek().ind - minInd; // index for the next level, and avoiding overflow
        q.poll();
        
        if (i == 0)
          first = currInd;
        if (i == size - 1)
          last = currInd;

        if (node.left != null)
          q.add(new Pair(node.left, 2 * currInd + 1));

        if (node.right != null)
          q.add(new Pair(node.right, 2 * currInd + 2));

        maxWidth = Math.max(maxWidth, last - first + 1);
      }
    }
    return maxWidth;
  }
}