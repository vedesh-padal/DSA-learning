// medium
// trees

// Given a Binary Tree. Check for the Sum Tree for every node except the leaf
// node. Return true if it is a Sum Tree otherwise, return false.

// A SumTree is a Binary Tree where the value of a node is equal to the sum of
// the nodes present in its left subtree and right subtree. An empty tree is
// also a Sum Tree as the sum of an empty tree can be considered to be 0. A leaf
// node is also considered a Sum Tree.

// Examples :

// Input:
//  3
// / \
// 1 2
// Output: true
// Explanation: The sum of left subtree and right subtree is 1 + 2 = 3, which is
// the value of the root node. Therefore,the given binary tree is a sum tree.

// Input:
// 10
// / \
// 20 30
// / \
// 10 10
// Output: false
// Explanation: The given tree is not a sum tree. For the root node, sum of
// elements in left subtree is 40 and sum of elements in right subtree is 30.
// Root element = 10 which is not equal to 30+40.
// Expected Time Complexity: O(n)
// Expected Auxiliary Space: O(Height of the Tree)

// Constraints:
// 2 ≤ number of nodes ≤ 105
// 1 ≤ node value ≤ 105

package trees;

class Node {
  int data;
  Node left, right;
  Node(int item) {
    data = item;
    left = right = null;
  }
}

class Solution {

  private int find(Node root) {
    if (root == null)
      return 0;

    if (root.left == null && root.right == null)
      return root.data;

    int left = find(root.left);
    int right = find(root.right);

    if (left == -1 || right == -1)
      return -1;

    if (root.data == left + right) {
      return root.data + left + right;
    } else
      return -1;
  }

  boolean isSumTree(Node root) {
    // Your code here
    if (root == null)
      return true;

    return find(root) != -1;
  }
}
