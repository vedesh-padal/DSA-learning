package trees;

import java.util.ArrayList;

class Node {
  int data;
  Node left;
  Node right;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }
}

class Solution {

  private static void find(Node root, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> res) {
    if (root == null)
      return;

    curr.add(root.data);

    if (root.left == null && root.right == null) {
      res.add(new ArrayList<>(curr));
    }

    else {
      if (root.left != null)
        find(root.left, curr, res);

      if (root.right != null)
        find(root.right, curr, res);

    }

    curr.remove(curr.size() - 1);

  }

  public static ArrayList<ArrayList<Integer>> Paths(Node root) {
    // code here
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    find(root, new ArrayList<>(), res);
    return res;
  }
}
