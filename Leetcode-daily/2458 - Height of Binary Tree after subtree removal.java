// HARD
// trees

// You are given the root of a binary tree with n nodes. Each node is assigned a
// unique value from 1 to n. You are also given an array queries of size m.

// You have to perform m independent queries on the tree where in the ith query
// you do the following:

// Remove the subtree rooted at the node with the value queries[i] from the
// tree. It is guaranteed that queries[i] will not be equal to the value of the root.
// Return an array answer of size m where answer[i] is the height of the tree
// after performing the ith query.

// Note:
// The queries are independent, so the tree returns to its initial state after each query.
// The height of a tree is the number of edges in the longest simple path from
// the root to some node in the tree.

// Constraints:

// The number of nodes in the tree is n.
// 2 <= n <= 105
// 1 <= Node.val <= n
// All the values in the tree are unique.
// m == queries.length
// 1 <= m <= min(n, 104)
// 1 <= queries[i] <= n
// queries[i] != root.val

import trees.TreeNode;

class Solution {

  @SuppressWarnings("unused")
  // GIVES TLE - 35/40 - MY TRY
  private int helper(TreeNode root, int key) {
    if (root == null || root.val == key)
      return 0;

    return 1 + Math.max(helper(root.left, key), helper(root.right, key));

  }

  // ===========================================================

  private int[] level = new int[100001]; // level of each node
  private int[] height = new int[100001]; // height of each node
  private int[] levelMaxHt = new int[100001];
  private int[] levelSecondMaxHt = new int[100001];

  // pre-computation
  private int findHeight(TreeNode root, int lvl) {
    if (root == null)
      return 0;

    level[root.val] = lvl;
    height[root.val] = Math.max(findHeight(root.left, lvl + 1), findHeight(root.right, lvl + 1)) + 1;

    // updating max. height and 2nd max. height in that level
    if (levelMaxHt[lvl] < height[root.val]) {
      levelSecondMaxHt[lvl] = levelMaxHt[lvl];
      levelMaxHt[lvl] = height[root.val];
    } 
    else if (levelSecondMaxHt[lvl] < height[root.val]) {
      levelSecondMaxHt[lvl] = height[root.val];
    }

    return height[root.val];
  }

  public int[] treeQueries(TreeNode root, int[] queries) {
    int[] ans = new int[queries.length];

    // for (int i = 0; i < queries.lcength; i++)
    //    ans[i] = helper(root, queries[i]) - 1;

    // return ans;

    // EFFICIENT APPROACH
    findHeight(root, 0); // here 0 is level

    // INTUITION:
    // if a node is deleted, check other nodes in the same level
    // and find the max. one
    // if the about to delete is the max. one, then return the second max. height
    // in that level (that is why we pre-computed heights of each node, and also
    // maintained their levels)
    int i = 0;
    for (int node : queries) {
      // node = that we want to delete
      int nodeLevel = level[node]; // O(1)

      // L + H - 1
      // int tempResult = L + H - 1;
      int tempResult = nodeLevel
          + (levelMaxHt[nodeLevel] == height[node] ? levelSecondMaxHt[nodeLevel] : levelMaxHt[nodeLevel]) - 1;

      ans[i] = tempResult;
      i++;
    }

    return ans;
  }
}