// MEDIUM - GFG
// trees

// Given a binary tree having n nodes. Check whether all of its nodes have the 
// value equal to the sum of their child nodes. Return 1 if all the nodes in the 
// tree satisfy the given properties, else it return 0.

// For every node, data value must be equal to the sum of data values in left 
// and right children. Consider data value as 0 for NULL child.  Also, leaves
//  are considered to follow the property.

// Example 1:
// Input:
// Binary tree
//        35
//       /   \
//      20  15
//     /  \  /  \
//    15 5 10 5
// Output: 1
// Explanation: 
// Here, every node is sum of its left and right child.

// Example 2:
// Input:
// Binary tree
//        1
//      /   \
//     4    3
//    /  
//   5    
// Output: 0
// Explanation: 
// Here, 1 is the root node and 4, 3 are its child nodes. 4 + 3 = 7 which is 
// not equal to the value of root node. Hence, this tree does not satisfy the given condition.
// Your Task:
// You don't need to read input or print anything. Your task is to complete 
// the function isSumProperty() that takes the root Node of the binary tree as 
// input and returns 1 if all the nodes in the tree satisfy the following properties, else it returns 0.

// Expected Time Complexiy: O(n).
// Expected Auxiliary Space: O(Height of the Tree).

// Constraints:
// 1 <= n <= 10^5
// 1 <= Data on nodes <= 10^5
package trees;

class Solution {
  public int isSumProperty(TreeNode root) {

    if (root == null || (root.left == null && root.right == null))
      return 1;

    int sum = 0;

    if (root.left != null)
      sum += root.left.val;

    if (root.right != null)
      sum += root.right.val;

    return (sum == root.val) &&
            isSumProperty(root.left) == 1 &&
            isSumProperty(root.right) == 1
            ? 1 : 0;
  }

  // =================================================
  public void changeTree(TreeNode root) {
    if (root == null)
      return;

    int child = 0;

    if (root.left != null)
      child += root.left.val;

    if (root.right != null)
      child += root.right.val;

    if (child >= root.val) {
      root.val = child;
    }
    else { 
      if (root.left != null)
        root.left.val = root.val;
      
      if (root.right != null)
        root.right.val = root.val;
    }

    changeTree(root.left);
    changeTree(root.right);

    int total = 0;

    if (root.left != null)
      total += root.left.val;

    if (root.right != null)
      total += root.right.val;
      
    if (root.left != null || root.right != null)
      root.val = total;
      
  }
}