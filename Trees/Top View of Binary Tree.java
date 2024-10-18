// MEDIUM
// trees

// Given below is a binary tree. The task is to print the top view of binary
// tree. Top view of a binary tree is the set of nodes visible when the tree is
// viewed from the top. For the given below tree

//        1
//     /     \
//    2       3
//   /  \    /   \
// 4    5  6   7

// Top view will be: 4 2 1 3 7
// Note: Return nodes from leftmost node to rightmost node. Also if 2 nodes are
// outside the shadow of the tree and are at same position then consider the
// left ones only(i.e. leftmost).
// For ex - 1 2 3 N 4 5 N 6 N 7 N 8 N 9 N N N N N will give 8 2 1 3 as answer.
// Here 8 and 9 are on the same position but 9 will get shadowed.

// Example 1:

// Input:
//       1
//    /    \
//   2      3
// Output: 2 1 3
// Example 2:

// Input:
//        10
//     /      \
//   20        30
//  /   \    /    \
// 40   60  90    100
// Output: 40 20 10 30 100
// Your Task:
// Since this is a function problem. You don't have to take input. Just complete
// the function topView() that takes root node as parameter and returns a list
// of nodes visible from the top view from left to right.

// Expected Time Complexity: O(NlogN)
// Expected Auxiliary Space: O(N).

// Constraints:
// 1 ≤ N ≤ 105
// 1 ≤ Node Data ≤ 105

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

class Pair {
  Node node;
  int hd;
  public Pair(Node node, int hd) {
    this.node = node;
    this.hd = hd;
  }
}

class Solution {
  // Function to return a list of nodes visible from the top view
  // from left to right in Binary Tree.
  static ArrayList<Integer> topView(Node root) {
    // add your code
    // we use level order traversal and vertical index concept
    // we cannot use recursive traversal becoz, it will visit the
    // bottom nodes first and that will lead to mis-finding of the
    // top view

    ArrayList<Integer> res = new ArrayList<>();
    if (root == null)
      return res;

    // TreeMap to store the Horizontal Distance and the node value
    // helps us in retrieving the node first seen in that vertical index
    // by keeping track of the horizontal distance

    // by default in ascending order sorted map based on key
    Map<Integer, Integer> tmap = new TreeMap<>();

    // Queue for Level Order Traversal
    Queue<Pair> q = new LinkedList<>();

    q.add(new Pair(root, 0));

    while (!q.isEmpty()) {
      Pair p = q.poll();
      int hd = p.hd;
      Node node = p.node;

      // first time seen (top view)
      // so put it in TreeMap
      if (tmap.get(hd) == null)
        tmap.put(hd, node.data);

      if (node.left != null)
        q.add(new Pair(node.left, hd - 1));

      if (node.right != null)
        q.add(new Pair(node.right, hd + 1));
    }

    // now we add it to our returning list
    for (Map.Entry<Integer, Integer> entry : tmap.entrySet())
      res.add(entry.getValue());

    return res;
  }
}