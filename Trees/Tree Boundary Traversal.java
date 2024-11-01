// MEDIUAM
// trees

// Given a Binary Tree, find its Boundary Traversal. The traversal should 
// be in the following order: 

// Left boundary nodes: defined as the path from the root to the left-most 
// node ie- the leaf node you could reach when you always travel preferring 
// the left subtree over the right subtree. 

// Leaf nodes: All the leaf nodes except for the ones that are part of left 
// or right boundary.

// Reverse right boundary nodes: defined as the path from the right-most 
// node to the root. The right-most node is the leaf node you could reach 
// when you always travel preferring the right subtree over the left subtree. 
// Exclude the root from this as it was already included in the traversal of left boundary nodes.

// Note: If the root doesn't have a left subtree or right subtree, then 
// the root itself is the left or right boundary. 

// Example:
// Input:
//         1 
//       /   \
//      2     3  
//     / \   / \ 
//    4   5 6   7
//       / \
//      8   9
// Output: 1 2 4 8 9 6 7 3

package trees;
import java.util.ArrayList;

class Node {
  int data;
  Node left, right;

  public Node(int d) {
    data = d;
    left = right = null;
  }
}

class Solution {

  private boolean isLeafNode(Node node) {
    return node.left == null && node.right == null;
  }

  private void addLeftBoundary(Node node, ArrayList<Integer> res) {
    Node curr = node.left;

    while (curr != null) {

      if (!isLeafNode(curr))
        res.add(curr.data);

      // our goal is always travel left
      if (curr.left != null)
        curr = curr.left;
      else
        curr = curr.right;
    }
  }

  private void addRightBoundary(Node node, ArrayList<Integer> res) {
    Node curr = node.right;
    // we should reverse this
    // as we are going from top to bottom
    // but, we should add bottom to top in the res
    ArrayList<Integer> al = new ArrayList<>();

    while (curr != null) {
      if (!isLeafNode(curr))
        al.add(curr.data);

      // our goal is to always travel right
      if (curr.right != null)
        curr = curr.right;
      else
        curr = curr.left;
    }

    for (int i = al.size() - 1; i >= 0; i--) {
      res.add(al.get(i));
    }
  }

  private void addLeafNodes(Node node, ArrayList<Integer> res) {

    // basically pre-order traversal, but adding only leaf nodes
    if (isLeafNode(node)) {
      res.add(node.data);
      return;
    }

    if (node.left != null)
      addLeafNodes(node.left, res);

    if (node.right != null)
      addLeafNodes(node.right, res);
  }

  ArrayList<Integer> boundary(Node node) {

    // we are doing Anti-clockwise traversal
    // around boundary from root

    ArrayList<Integer> res = new ArrayList<>();

    if (node == null)
      return res;

    if (!isLeafNode(node))
      res.add(node.data);

    addLeftBoundary(node, res);

    // bottom boundary
    addLeafNodes(node, res);

    // from bottom to right
    addRightBoundary(node, res);

    return res;

  }
}
