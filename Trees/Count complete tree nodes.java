// Given the root of a complete binary tree, return the number of the nodes in
// the tree.

// According to Wikipedia, every level, except possibly the last, is completely
// filled in a complete binary tree, and all nodes in the last level are as far
// left as possible. It can have between 1 and 2h nodes inclusive at the last
// level h.

// Design an algorithm that runs in less than O(n) time complexity.

// Example 1:
// Input: root = [1,2,3,4,5,6]
// Output: 6

// Example 2:
// Input: root = []
// Output: 0

// Example 3:
// Input: root = [1]
// Output: 1

// Constraints:
// The number of nodes in the tree is in the range [0, 5 * 104].
// 0 <= Node.val <= 5 * 104
// The tree is guaranteed to be complete.

package trees;

class Solution {

  // making use of property of complete binary tree
  // no. of nodes in a complete binary tree = 2^h - 1
  public int countNodes(TreeNode root) {
    if (root == null)
      return 0;

    int leftHeight = getLeftHeight(root);
    int rightHeight = getRightHeight(root);

    // we have found a subtree which is complete tree
    // we make use of the property of complete binary tree
    // and directly calculate the height
    if (leftHeight == rightHeight)
      return ((1 << leftHeight) - 1);
    else
      return 1 + countNodes(root.left) + countNodes(root.right);

  }

  private int getLeftHeight(TreeNode root) {
    TreeNode t = root;
    int cnt = 0;
    while (t != null) {
      cnt++;
      t = t.left; // we are able to do this, becoz it is a complete binary tree
    }
    return cnt;
  }

  private int getRightHeight(TreeNode root) {
    TreeNode t = root;
    int cnt = 0;
    while (t != null) {
      cnt++;
      t = t.right; // we are able to do this, becoz it is a complete binary tree
    }
    return cnt;
  }

  // ===================================================
  private void count(TreeNode root, int[] cnt) {
    if (root == null)
      return;
    cnt[0]++;
    count(root.left, cnt);
    count(root.right, cnt);
  }

  // just normal counting - not very optimal incase of large trees
  public int countNodes_NOT_VERY_OPTIMAL(TreeNode root) {
    int[] cnt = new int[] { 0 };
    count(root, cnt);
    return cnt[0];
  }
}