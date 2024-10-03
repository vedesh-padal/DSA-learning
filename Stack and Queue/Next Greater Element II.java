// Given a circular integer array nums (i.e., the next element of
// nums[nums.length - 1] is nums[0]), return the next greater number for every
// element in nums.

// The next greater number of a number x is the first greater number to its
// traversing-order next in the array, which means you could search circularly
// to find its next greater number. If it doesn't exist, return -1 for this
// number.

// Example 1:
// Input: nums = [1,2,1]
// Output: [2,-1,2]
// Explanation: The first 1's next greater number is 2;
// The number 2 can't find next greater number.
// The second 1's next greater number needs to search circularly, which is also 2.

// Example 2:
// Input: nums = [1,2,3,4,3]
// Output: [2,3,4,-1,4]

// Constraints:
// 1 <= nums.length <= 104
// -109 <= nums[i] <= 109

import java.util.Stack;

class Solution {

  // EFFICIENT WAY TO WRITE CODE
  public int[] nextGreaterElements(int[] nums) {
    int n = nums.length;

    Stack<Integer> stk = new Stack<>();
    int[] res = new int[n];

    for (int i = 2 * n - 1; i >= 0; i--) {
      while (!stk.isEmpty() && stk.peek() <= nums[i % n])
        stk.pop();

      if (i < n)
        res[i] = stk.isEmpty() ? -1 : stk.peek();

      stk.push(nums[i % n]);
    }
    return res;
  }

  // ============================================================
  // UNDERSTANDABLE CODE
  public int[] nextGreaterElements_2loops(int[] nums) {
    // visualizingly double the arr
    // during first traversal from right, just do stk operations
    // when i < 0, reset i to n-1
    // now do NGE

    Stack<Integer> stk = new Stack<>();
    int n = nums.length;
    int i = n - 1;
    while (i >= 0) {
      while (!stk.isEmpty() && stk.peek() <= nums[i])
        stk.pop();

      stk.push(nums[i]);
      i--;
    }

    int[] res = new int[n];
    i = n - 1;

    for (i = n - 1; i >= 0; i--) {
      while (!stk.isEmpty() && stk.peek() <= nums[i])
        stk.pop();

      if (stk.isEmpty())
        res[i] = -1;
      else
        res[i] = stk.peek();

      stk.push(nums[i]);
    }

    return res;
  }
}