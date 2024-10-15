// medium  -  gfg
// trees

package trees;

import java.util.*;

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

class Tree {

  // recursive function
  private void getDiagonalData(Node node, int level, Map<Integer, ArrayList<Integer>> levelData) {
    if (node == null)
      return;

    levelData.computeIfAbsent(level, k -> new ArrayList<>()).add(node.data);

    // move left
    getDiagonalData(node.left, level + 1, levelData);

    // move right
    getDiagonalData(node.right, level, levelData);
  }

  public ArrayList<Integer> diagonal(Node root) {
    // key point:
    // levels of diagonal
    // To find the diagonal view of a binary tree, we perform
    // a recursive traversal that stores nodes in a hashmap
    // based on their diagonal levels. Left children increase
    // the diagonal level, while right children remain on the same level.

    Map<Integer, ArrayList<Integer>> levelData = new HashMap<>();

    getDiagonalData(root, 0, levelData);

    ArrayList<Integer> ans = new ArrayList<>();

    int level = 0;

    while (levelData.containsKey(level)) {
      ArrayList<Integer> al = levelData.get(level);
      ans.addAll(al);
      level++;
    }

    return ans;

  }
}