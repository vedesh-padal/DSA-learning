// EASY

// Given the root of a binary tree, return the length of the diameter of the
// tree.

// The diameter of a binary tree is the length of the longest path between any
// two nodes in a tree. This path may or may not pass through the root.

// The length of a path between two nodes is represented by the number of edges
// between them.

// Example 1:
// Input: root = [1,2,3,4,5]
// Output: 3
// Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

// Example 2:
// Input: root = [1,2]
// Output: 1

// Constraints:
// The number of nodes in the tree is in the range [1, 104].
// -100 <= Node.val <= 100

package trees;

class Solution {

  private int find(TreeNode node, int[] maxi) {
    if (node == null)
      return 0;

    int left = find(node.left, maxi);
    int right = find(node.right, maxi);

    maxi[0] = Math.max(maxi[0], left + right);

    return Math.max(left, right) + 1;
  }

  public int diameterOfBinaryTree(TreeNode root) {
    if (root == null)
      return 0;

    // int maxi = Integer.MIN_VALUE;
    // a work around for pass-by-ref
    // and when u want to avoid using global var
    int[] ans = new int[] { Integer.MIN_VALUE };
    find(root, ans);
    return ans[0];
  }
}