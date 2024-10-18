// HARD - LC - 987
// trees

// Given the root of a binary tree, calculate the vertical order traversal of
// the binary tree.

// For each node at position (row, col), its left and right children will be at
// positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of
// the tree is at (0, 0).

// The vertical order traversal of a binary tree is a list of top-to-bottom
// orderings for each column index starting from the leftmost column and ending
// on the rightmost column. There may be multiple nodes in the same row and same
// column. In such a case, sort these nodes by their values.

// Return the vertical order traversal of the binary tree.

package trees;

import java.util.*;

class NodeInfo {
  int val;
  int row;
  public NodeInfo(int val, int row) {
    this.val = val;
    this.row = row;
  }
}

class Triplet {
  TreeNode node;
  int row;
  int col;
  public Triplet(TreeNode node, int row, int col) {
    this.node = node;
    this.row = row;
    this.col = col;
  }
}

class Solution {
  public List<List<Integer>> verticalTraversal(TreeNode root) {

    List<List<Integer>> res = new ArrayList<>();
    if (root == null)
      return res;

    // map to hold lists of NodeInfo by col. index
    Map<Integer, List<NodeInfo>> tmap = new TreeMap<>();

    Queue<Triplet> q = new LinkedList<>();

    q.add(new Triplet(root, 0, 0));

    while (!q.isEmpty()) {
      Triplet p = q.poll();
      TreeNode node = p.node;
      int row = p.row;
      int col = p.col;

      tmap.computeIfAbsent(col, k -> new ArrayList<>()).add(new NodeInfo(node.val, row));

      if (node.left != null)
        q.add(new Triplet(node.left, row + 1, col - 1));

      if (node.right != null)
        q.add(new Triplet(node.right, row + 1, col + 1));

    }

    for (List<NodeInfo> nodeInfoList : tmap.values()) {
      // for each vertical index stored elements
      // sort the values that were on the same level (same row and col)

      // here, nodeInfoList means in the same vertical index
      Collections.sort(nodeInfoList, (a, b) -> {

        // if they are in the same row too, then we sort by the node values
        if (a.row != b.row)
          return a.row - b.row;

        return a.val - b.val;
      });

      List<Integer> al = new ArrayList<>();
      for (NodeInfo it : nodeInfoList)
        al.add(it.val);

      res.add(al);
    }

    return res;
  }
}