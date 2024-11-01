// HARD
// trees
// https://leetcode.com/problems/binary-tree-maximum-path-sum/description/

// A path in a binary tree is a sequence of nodes where each pair of adjacent
// nodes in the sequence has an edge connecting them. A node can only appear in
// the sequence at most once. Note that the path does not need to pass through
// the root.

// The path sum of a path is the sum of the node's values in the path.

// Given the root of a binary tree, return the maximum path sum of any non-empty
// path.

package trees;

class Solution {

  private int findMaxPathSum(TreeNode root, int[] maxi) {
    if (root == null)
      return 0;

    // we avoid taking the -ve path sum coming from
    // children, becoz, we need to propagate max. sum to parents
    // inorder to correctly store max. path sum globally
    int leftSum = Math.max(0, findMaxPathSum(root.left, maxi));
    int rightSum = Math.max(0, findMaxPathSum(root.right, maxi));

    // passing as an array with 1 element to avoid global variable
    // and as we do not have pass by reference for int data type in java
    maxi[0] = Math.max(maxi[0], leftSum + rightSum + root.val);

    // we return to the parent the max. sum path that it has found
    return Math.max(leftSum, rightSum) + root.val;
  }

  public int maxPathSum(TreeNode root) {
    int[] maxi = new int[] { Integer.MIN_VALUE };

    findMaxPathSum(root, maxi);
    return maxi[0];
  }
}
