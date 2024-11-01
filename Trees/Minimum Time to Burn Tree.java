// Given a binary tree and a node data called target. Find the minimum time
// required to burn the complete binary tree if the target is set on fire. 
// It is known that in 1 second all nodes connected to a given node get burned. 
// That is its left child, right child, and parent.
// Note: The tree contains unique values.

// Examples : 

// Input:      
//           1
//         /   \
//       2      3
//     /  \      \
//    4    5      6
//        / \      \
//       7   8      9
//                    \
//                    10
// Target Node = 8
// Output: 7
// Explanation: If leaf with the value 8 is set on fire. 
// After 1 sec: 5 is set on fire.
// After 2 sec: 2, 7 are set to fire.
// After 3 sec: 4, 1 are set to fire.
// After 4 sec: 3 is set to fire.
// After 5 sec: 6 is set to fire.
// After 6 sec: 9 is set to fire.
// After 7 sec: 10 is set to fire.
// It takes 7s to burn the complete tree.

// Input:      
//           1
//         /   \
//       2      3
//     /  \      \
//    4    5      7
//   /    / 
//  8    10
// Target Node = 10
// Output: 5

// Expected Time Complexity: O(number of nodes)
// Expected Auxiliary Space: O(height of tree)

// Constraints:
// 1 ≤ number of nodes ≤ 105
// 1 ≤ values of nodes ≤ 105

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

class Solution {

  private static Node mapParents(Node root, Map<Node, Node> parentTrack, int target) {
    Node targetNode = null;

    Queue<Node> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      Node node = q.poll();
      if (node.data == target)
        targetNode = node;

      if (node.left != null) {
        q.add(node.left);
        parentTrack.put(node.left, node);
      }

      if (node.right != null) {
        q.add(node.right);
        parentTrack.put(node.right, node);
      }
    }
    return targetNode;
  }

  public static int minTime(Node root, int target) {
    // Your code goes here
    Map<Node, Node> parentTrack = new HashMap<>();
    Node targetNode = mapParents(root, parentTrack, target);

    Map<Node, Boolean> visited = new HashMap<>();
    Queue<Node> q = new LinkedList<>();

    q.add(targetNode);
    visited.put(targetNode, true);

    int time = 0;

    while (!q.isEmpty()) {
      int size = q.size();
      // to keep track if this group burn atleast one of it's adjacent
      boolean flag = false;

      for (int i = 0; i < size; i++) {
        Node node = q.poll();

        if (node.left != null && visited.get(node.left) == null) {
          visited.put(node.left, true);
          q.add(node.left);
          flag = true;
        }

        if (node.right != null && visited.get(node.right) == null) {
          visited.put(node.right, true);
          q.add(node.right);
          flag = true;
        }

        if (parentTrack.get(node) != null && visited.get(parentTrack.get(node)) == null) {
          visited.put(parentTrack.get(node), true);
          q.add(parentTrack.get(node));
          flag = true;
        }
      }
      
      if (flag == true)
        time++;
    }
    return time;
  }
}