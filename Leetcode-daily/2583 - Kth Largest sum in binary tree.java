// 22-10-2024
// MEDIUM

// You are given the root of a binary tree and a positive integer k.

// The level sum in the tree is the sum of the values of the nodes that are on
// the same level.

// Return the kth largest level sum in the tree (not necessarily distinct). If
// there are fewer than k levels in the tree, return -1.

// Note that two nodes are on the same level if they have the same distance from
// the root.

// Example 1:
// Input: root = [5,8,9,2,1,3,7,4,6], k = 2
// Output: 13
// Explanation: The level sums are the following:
// - Level 1: 5.
// - Level 2: 8 + 9 = 17.
// - Level 3: 2 + 1 + 3 + 7 = 13.
// - Level 4: 4 + 6 = 10.
// The 2nd largest level sum is 13.

// Example 2:
// Input: root = [1,2,null,3], k = 1
// Output: 3

// Explanation: The largest level sum is 3.

// Constraints:
// The number of nodes in the tree is n.
// 2 <= n <= 105
// 1 <= Node.val <= 106
// 1 <= k <= n
import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class Solution {
  public long kthLargestLevelSum(TreeNode root, int k) {
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    // ArrayList<Long> al = new ArrayList<>();
    PriorityQueue<Long> pq = new PriorityQueue<>();

    while (!q.isEmpty()) {
      int size = q.size();
      long sum = 0;
      for (int i = 0; i < size; i++) {
        TreeNode node = q.poll();
        sum += node.val;

        if (node.left != null)
          q.add(node.left);
        if (node.right != null)
          q.add(node.right);
      }
      // al.add(sum);
      if (pq.size() < k)
        pq.add(sum);
      else {
        if (pq.peek() < sum) {
          pq.poll();
          pq.add(sum);
        }
      }
    }

    // if (al.size() < k)
    // return -1;

    // Collections.sort(al, Collections.reverseOrder());
    // return al.get(k-1);

    if (pq.size() < k)
      return -1;

    return pq.peek();
  }
}