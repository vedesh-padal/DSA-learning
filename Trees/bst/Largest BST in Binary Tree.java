// MEDIUM
// important
// bst

// Given a binary tree. Find the size of its largest subtree which is a Binary Search Tree.
// Note: Here Size equals the number of nodes in the subtree.

// Examples :

// Input:   1
//         /  \
//         4   4              
//        / \ 
//       6   8
// Output: 1 
// Explanation: There's no sub-tree with size greater than 1 which forms a BST. 
// All the leaf Nodes are the BSTs with size equal to 1.

// Input:    6
//         /   \
//       6      2              
//        \    / \
//         2  1   3
// Output: 3
// Explanation: The following sub-tree is a BST of size 3:  2
//                                                        /   \
//                                                       1     3

// Expected Time Complexity: O(n).
// Expected Auxiliary Space: O(Height of the BST).

// Constraints:
// 1 ≤ Number of nodes ≤ 105
// 1 ≤ Data of a node ≤ 106

package trees.bst;

class NodeValue {
  int maxNode;  // left subtree's largest node value
  int minNode;  // right subtree's smalles node value
  int maxSize;  // max. size of this subtree

  NodeValue(int maxNode, int minNode, int maxSize) {
    this.maxNode = maxNode;
    this.minNode = minNode;
    this.maxSize = maxSize;
  }
}


// BEAUTIFUL SOLUTION
class Solution {

  private NodeValue largestBSTSubtree(TreeNode root) {
    // an empty tree is a BST of size 0
    if (root == null) {
      return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
    }

    // get values from left and right subtree of curr. tree
    NodeValue left = largestBSTSubtree(root.left);
    NodeValue right = largestBSTSubtree(root.right);

    // curr. node is greater than maxNode in left and is less than minNode in right
    // => it is a valid BST
    if (left.maxNode < root.val && root.val < right.minNode) {
      return new NodeValue(Math.min(root.val, left.minNode), Math.max(root.val, right.maxNode), 1 + left.maxSize + right.maxSize);
    }

    // otherwise, return [-inf, inf], so that parent can't be valid BST
    return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
  }

  public int largestBST(TreeNode root) {
    
    return largestBSTSubtree(root).maxSize;

  }
}