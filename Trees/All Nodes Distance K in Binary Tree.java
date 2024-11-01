// MEDIUM
// trees

// Given the root of a binary tree, the value of a target node target, and an
// integer k, return an array of the values of all nodes that have a distance k
// from the target node.

// You can return the answer in any order.

// Example 1:
// Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
// Output: [7,4,1]
// Explanation: The nodes that are a distance 2 from the target node (with value
// 5) have values 7, 4, and 1.

// Example 2:
// Input: root = [1], target = 1, k = 3
// Output: []

// Constraints:
// The number of nodes in the tree is in the range [1, 500].
// 0 <= Node.val <= 500
// All the values Node.val are unique.
// target is the value of one of the nodes in the tree.
// 0 <= k <= 1000

// MAIN IDEA / INTUITION:
// keeping track of the parent of each node
// using a hashmap, mapping the node with it's pointer
// THEN, start from the target node
// then traverse 3 directions from Target node GREEDILY
// using Queue

// STEP WISE EXPLANATION:
// Using BFS :-
// 1 Make the parent and current node map to traverse upwards.
// 2 We will do a BFS traversal starting from the target node until we reach the Kth level
// 3 Using BFS will check left, right child and also parent of current node.
// 4 when reached Kth distance, break out of BFS loop and remaining node's values in our queue is our result.
// 5 Will take all the element from queue and add to our list as our answer.

package trees;
import java.util.*;

class Solution {

  private void markParents(TreeNode root, Map<TreeNode, TreeNode> parentTrack) {
    // another way of asking
    // might give target value instead of node directly
    // then we have to store the targetNode in this call and passing it
    // to the calling function of this

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      TreeNode node = q.poll();
      if (node.left != null) {
        q.add(node.left);
        parentTrack.put(node.left, node);
      }
      if (node.right != null) {
        q.add(node.right);
        parentTrack.put(node.right, node);
      }
    }
  }

  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    Map<TreeNode, TreeNode> parentTrack = new HashMap<>();
    markParents(root, parentTrack);

    Map<TreeNode, Boolean> visited = new HashMap<>();
    Queue<TreeNode> q = new LinkedList<>();
    visited.put(target, true);
    q.add(target);

    int curr = 0;

    while (!q.isEmpty()) {
      int size = q.size();
      if (curr == k)
        break;
      curr++;

      for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();

        // after polling, we check 3 adjacent:
        // top (parent), children (bottom: left, right)

        if (node.left != null && visited.get(node.left) == null) {
          q.add(node.left);
          visited.put(node.left, true);
        }

        if (node.right != null && visited.get(node.right) == null) {
          q.add(node.right);
          visited.put(node.right, true);
        }

        if (parentTrack.get(node) != null && visited.get(parentTrack.get(node)) == null) {
          q.add(parentTrack.get(node));
          visited.put(parentTrack.get(node), true);
        }
      }
    }

    // now, after the break of the while loop
    // when we find that curr == k
    // all the nodes (their values) are the required answer
    // i.e., at distance K from the target node
    List<Integer> res = new ArrayList<>();
    while (!q.isEmpty()) {
      res.add(q.poll().val);
    }
    return res;
  }
}