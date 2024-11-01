// MEDIUM - EASY
// https://leetcode.com/problems/delete-leaves-with-a-given-value/description/

// Given a binary tree root and an integer target, delete all the leaf nodes
// with value target.

// Note that once you delete a leaf node with value target, if its parent node
// becomes a leaf node and has the value target, it should also be deleted (you
// need to continue doing that until you cannot).

import trees.TreeNode;

class Solution {

  public TreeNode removeLeafNodes(TreeNode root, int target) {

    if (root == null)
      return null;

    root.left = removeLeafNodes(root.left, target);
    root.right = removeLeafNodes(root.right, target);

    if (root.left == null && root.right == null && root.val == target)
      return root = null;

    return root;

  }
}