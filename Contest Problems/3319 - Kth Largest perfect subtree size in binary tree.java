// You are given the root of a binary tree and an integer k.

// Return an integer denoting the size of the kth largest perfect binary subtree,
// or -1 if it doesn't exist.

// A perfect binary tree is a tree where all leaves are on the same level, and
// every parent has two children.

// Example 1:
// Input: root = [5,3,6,5,2,5,7,1,8,null,null,6,8], k = 2
// Output: 3
// Explanation:
// The roots of the perfect binary subtrees are highlighted in black. Their
// sizes, in decreasing order are [3, 3, 1, 1, 1, 1, 1, 1].
// The 2nd largest size is 3.

// Example 2:
// Input: root = [1,2,3,4,5,6,7], k = 1
// Output: 7
// Explanation:
// The sizes of the perfect binary subtrees in decreasing order are [7, 3, 3, 1,
// 1, 1, 1]. The size of the largest perfect binary subtree is 7.

// Example 3:
// Input: root = [1,2,3,null,4], k = 3
// Output: -1
// Explanation:
// The sizes of the perfect binary subtrees in decreasing order are [1, 1].
// There are fewer than 3 perfect binary subtrees.

// Constraints:
// The number of nodes in the tree is in the range [1, 2000].
// 1 <= Node.val <= 2000
// 1 <= k <= 1024
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

  private int[] find(TreeNode root, List<Integer> res) {
    if (root == null)
      return new int[] { 0, -1 };

    int[] left = find(root.left, res);
    int[] right = find(root.right, res);

    int cuH = Math.max(left[1], right[1]) + 1;

    int leftNumNodes = left[0];
    int rightNumNodes = right[0];

    int actualNumNodes = leftNumNodes + rightNumNodes + 1; // in this subtree

    int numNodes = (int) (Math.pow(2, cuH + 1) - 1); // expected

    if (actualNumNodes == numNodes) {
      res.add(numNodes);
    }

    return new int[] { actualNumNodes, cuH };

  }

  // O(n * logn) approach as it involves power calculation
  public int kthLargestPerfectSubtree_MY_TRY(TreeNode root, int k) {

    List<Integer> res = new ArrayList<>();
    find(root, res);
    Collections.sort(res, Collections.reverseOrder());

    if (k > res.size()) {
      return -1;
    }
    return res.get(k - 1);
  }

  // ==================================================

  private int findSize(TreeNode root, List<Integer> res)  {
    if (root == null)
      return 0;

    int left = findSize(root.left, res);
    int right = findSize(root.right, res);

    if (left == -1 || right == -1)
      return -1;

    if (left == right)
      res.add(left + right + 1);  // add no. of nodes in this subtree

    else 
      return -1;
    
    return left + right + 1;

  }


  // O(n) approach - efficient - calculating size of subtree
  public int kthLargestPerfectSubtree(TreeNode root, int k) {
    List<Integer> res = new ArrayList<>();
    findSize(root, res);

    Collections.sort(res);

    return k > res.size() ? -1 : res.get(k-1);
  }
}