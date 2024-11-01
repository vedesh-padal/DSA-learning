package trees;

import java.util.*;

class Node {
  int data;
  Node left, right;

  Node(int item) {
    data = item;
    left = right = null;
  }
}

class Tree {
  // Function to return list containing elements of left view of binary tree.
  ArrayList<Integer> leftView(Node root) {
    // Your code here
    ArrayList<Integer> res = new ArrayList<>();

    if (root == null)
      return res;

    Queue<Node> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        Node node = q.poll();
        if (node.left != null)
          q.add(node.left);

        if (node.right != null)
          q.add(node.right);

        if (i == 0)
          res.add(node.data);
      }
    }
    return res;
  }
}