// EASY - MEDIUM
// trees, bfs, dfs

// Given the root of a binary tree with unique values and the values of two
// different nodes of the tree x and y, return true if the nodes corresponding
// to the values x and y in the tree are cousins, or false otherwise.

// Two nodes of a binary tree are cousins if they have the same depth with
// different parents.

// Note that in a binary tree, the root node is at the depth 0, and children of
// each depth k node are at the depth k + 1.

package trees;
import java.util.*;

class Solution {
  private int xDepth, yDepth;
  private TreeNode xParent, yParent;

  private void dfs(TreeNode root, TreeNode parent, int x, int y, int depth) {
    if (root == null)
      return;

    if (root.val == x) {
      xDepth = depth;
      xParent = parent;
    }

    if (root.val == y) {
      yDepth = depth; 
      yParent = parent;
    }

    dfs(root.left, root, x, y, depth + 1);
    dfs(root.right, root, x, y, depth + 1);
  }

  public boolean isCousins(TreeNode root, int x, int y) {
    dfs(root, null, x, y, 0);
    return xDepth == yDepth && xParent != yParent;
  }

  // ============================================================

  public boolean isCousins_BFS(TreeNode root, int x, int y) {
    if (root == null)
      return false;

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      int size = q.size();
      boolean xExists = false;
      boolean yExists = false;

      for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();

        if (node.val == x)
          xExists = true;
          
        if (node.val == y)
          yExists = true;

        if (node.left != null && node.right != null) {
          // both are existing on same level AND, WITH SAME PARENT, SO, return false
          if (node.left.val == x && node.right.val == y)
            return false;

          if (node.left.val == y && node.right.val == x)
            return false;
        }

        if (node.left != null)
          q.add(node.left);

        if (node.right != null)
          q.add(node.right);
      }
      // if one of them was found on some other level
      // and another found on this level, we early return with true
      if (xExists && yExists)
        return true;
    }
    // case when any one of the nodes (x, y) is not present in tree
    // wont happen, but just writing
    return false;
  }

}