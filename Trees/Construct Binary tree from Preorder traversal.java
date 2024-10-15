// MEDIUM
// trees

// Given an array of integers preorder, which represents the preorder traversal
// of a BST (i.e., binary search tree), construct the tree and return its root.

// It is guaranteed that there is always possible to find a binary search tree
// with the given requirements for the given test cases.

// A binary search tree is a binary tree where for every node, any descendant of
// Node.left has a value strictly less than Node.val, and any descendant of
// Node.right has a value strictly greater than Node.val.

// A preorder traversal of a binary tree displays the value of the node first,
// then traverses Node.left, then traverses Node.right.

package trees;

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
  // inorder to avoid nodeInd to be a global variable,
  // we can do this:
  // pass new int[]{0} as nodeInd, and access and update it this way
  // access: i[0], update: i[0]++
  private TreeNode findBST(int[] preorder, int[] nodeInd, int start, int end) {
    if (nodeInd[0] == preorder.length || preorder[nodeInd[0]] < start || preorder[nodeInd[0]] > end)
      return null;

    // in preorder traversal: root, left, right
    // we are considering nodeInd, as it is moving from left to right
    // and when we consider root as a element from preorder[]
    // we need to consider the pre-order of left subtree
    // and right subtree

    // int val = preorder[nodeInd++];
    int val = preorder[nodeInd[0]++];

    TreeNode newNode = new TreeNode(val);

    // we are doing it this way and the IF condition is very helpful for that

    newNode.left = findBST(preorder, nodeInd, start, val);
    newNode.right = findBST(preorder, nodeInd, val, end);

    return newNode;
  }

  public TreeNode bstFromPreorder(int[] preorder) {
    // int nodeInd = 0; // this won't work
    // return findBST(preorder, nodeInd, Integer.MIN_VALUE, Integer.MAX_VALUE);

    // becoz nodeInd have to be global
    // if you want to avoid that, u can pass it in an array with single element
    return findBST(preorder, new int[] { 0 }, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
}