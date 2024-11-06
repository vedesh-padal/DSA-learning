// MEDIUM
// trees

// Given a binary tree, where every node value is a number. Find the sum of all
// the numbers that are formed from root to leaf paths. The formation of the
// numbers would be like 10*parent + current (see the examples for more
// clarification).

import java.util.ArrayList;

class Node {
  int data;
  Node left, right;

  Node(int d) {
    data = d;
    left = null;
    right = null;
  }
}

class Solution {

  private static int findSum(Node root, int currNum) {

    if (root == null)
      return 0;

    int num = currNum * 10 + root.data;

    if (root.left == null && root.right == null)
      return num;

    return findSum(root.left, num) + findSum(root.right, num);

  }

  // Optimal Approach
  public static int treePathsSum(Node root) {
    return findSum(root, 0);
  }

  // ======================================================

  private static void solve(Node root, int currNum, ArrayList<Integer> nums) {

    if (root == null) {
      return;
    }

    int num = currNum * 10 + root.data;

    if (root.left == null && root.right == null) {
      nums.add(num);
      return;
    }

    solve(root.left, num, nums);
    solve(root.right, num, nums);
  }

  public static int treePathsSum_MY_TRY(Node root) {
    // add code here.
    ArrayList<Integer> nums = new ArrayList<>();

    solve(root, 0, nums);

    int sum = 0;

    // System.out.println(nums);

    for (int num : nums)
      sum += num;

    return sum;
  }
}