// MEDIUM
// bfs, dfs, hashtable, trees

// Given the root of a binary tree, replace the value of each node in the tree
// with the sum of all its cousins' values.

// Two nodes of a binary tree are cousins if they have the same depth with
// different parents.

// Return the root of the modified tree.

// Note that the depth of a node is the number of edges in the path from the
// root node to it.

package trees;
import java.util.*;

class Solution {
  public TreeNode replaceValueInTree(TreeNode root) {
    // Cousins => same depth, but different parents
    // INTUITION:
    // go through each level, calculate the sum of node vals of the next level,
    // at the same time storing the node of the curr. level in a list
    // so that after finding sum of node vals of the next level,
    // we go through each level nodes and subtract common parent vals
    // of the next level from the total sum and put that remaining sum in the curr.
    // node

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    root.val = 0; // since it is common parent to it's children
    // and we should do this becoz in the while loop we will be
    // updating the values of children,
    // so root can be missed, so we update it first

    while (!q.isEmpty()) {
      int size = q.size();
      List<TreeNode> currLevelNodes = new ArrayList<>();
      int nextLevelSum = 0;

      for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();
        currLevelNodes.add(node);

        if (node.left != null) {
          nextLevelSum += node.left.val;
          q.add(node.left);
        }

        if (node.right != null) {
          nextLevelSum += node.right.val;
          q.add(node.right);
        }
      }

      // now we have next level sum, now we have to assign
      // the values to the nodes at the curr. level such that
      // it is total sum of next level - (common parents node vals sum)

      // for each common parent node
      for (TreeNode node : currLevelNodes) {
        int sum = nextLevelSum;
        if (node.left != null)
          sum -= node.left.val;

        // same level, so remove right node val if it exists too
        if (node.right != null)
          sum -= node.right.val;

        // updating with subtracted sum
        if (node.left != null)
          node.left.val = sum;

        if (node.right != null)
          node.right.val = sum;
      }
    }
    return root;
  }
}