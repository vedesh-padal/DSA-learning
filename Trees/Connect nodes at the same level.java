// medium - gfg
// trees

package trees;

import java.util.*;

class Node {
  int data;
  Node left;
  Node right;
  Node nextRight;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
    nextRight = null;
  }
}

class Solution {

  // This idea is to use level order traversal to connect nodes
  // at the same level. A NULL is pushed after each level to
  // track the end of the level. As nodes are processed, each node’s
  // nextRight pointer is set to the next node in the queue. If a NULL
  // is encountered and the queue isn’t empty, another NULL is added to
  // mark the end of the next level. This ensures that all nodes at the
  // same level are linked.

  public void connect(Node root) {
    
    // LEVEL ORDER TRAVERSAL
    Queue<Node> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      int size = q.size();
      Node prev = null;

      for (int i = 0; i < size; i++) {
        Node curr = q.peek();
        q.poll();

        if (prev != null)
          prev.nextRight = curr;

        prev = curr;

        if (curr.left != null)
          q.add(curr.left);
        if (curr.right != null)
          q.add(curr.right);

      }
      // this level is complete
      // so add null at the last of this level
      prev.nextRight = null;
    }
  }
}