// You are given an integer array nums. The range of a subarray of nums is the
// difference between the largest and smallest element in the subarray.

// Return the sum of all subarray ranges of nums.

// A subarray is a contiguous non-empty sequence of elements within an array.

// Example 1:

// Input: nums = [1,2,3]
// Output: 4
// Explanation: The 6 subarrays of nums are the following:
// [1], range = largest - smallest = 1 - 1 = 0
// [2], range = 2 - 2 = 0
// [3], range = 3 - 3 = 0
// [1,2], range = 2 - 1 = 1
// [2,3], range = 3 - 2 = 1
// [1,2,3], range = 3 - 1 = 2
// So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
// Example 2:

// Input: nums = [1,3,3]
// Output: 4
// Explanation: The 6 subarrays of nums are the following:
// [1], range = largest - smallest = 1 - 1 = 0
// [3], range = 3 - 3 = 0
// [3], range = 3 - 3 = 0
// [1,3], range = 3 - 1 = 2
// [3,3], range = 3 - 3 = 0
// [1,3,3], range = 3 - 1 = 2
// So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
// Example 3:

// Input: nums = [4,-2,-3,4,1]
// Output: 59
// Explanation: The sum of all subarray ranges of nums is 59.

// Constraints:

// 1 <= nums.length <= 1000
// -109 <= nums[i] <= 109

// Follow-up: Could you find a solution with O(n) time complexity?

import java.util.*;

class Solution {

  // 0(n^2)
  public long subArrayRanges_NAIVE(int[] nums) {
    int n = nums.length;
    long ans = 0;

    for (int i = 0; i < n; i++) {
      int mini = nums[i];
      int maxi = nums[i];
      for (int j = i; j < n; j++) {
        mini = Math.min(mini, nums[j]);
        maxi = Math.max(maxi, nums[j]);
        ans += (maxi - mini);
      }
    }

    return ans;
  }
  // =================================================
  public int MOD = (int) (1e9 + 7);

  public long subArrayRanges(int[] nums) {
    int n = nums.length;
    return maxSubarraySum(nums, n) - minSubarraySum(nums, n);
  }

  private long minSubarraySum(int[] nums, int n) {
    int[] nse = findNSE(nums, n);
    int[] psee = findPSEE(nums, n);
    long total = 0;

    for (int i = 0; i < n; i++) {
      int left = i - psee[i];
      int right = nse[i] - i;

      total = (total + (left * right) * 1L * nums[i]);
    }
    return total;
  }

  private long maxSubarraySum(int[] nums, int n) {
    int[] nge = findNGE(nums, n);
    int[] pgee = findPGEE(nums, n);
    long total = 0;

    for (int i = 0; i < n; i++) {
      int left = i - pgee[i];
      int right = nge[i] - i;

      total = (total + (left * right) * 1L * nums[i]);
    }
    return total;
  }

  private int[] findNSE(int[] nums, int n) {
    Stack<Integer> stk = new Stack<>();
    int[] nse = new int[n];

    for (int i = n - 1; i >= 0; i--) {
      while (!stk.isEmpty() && nums[stk.peek()] >= nums[i])
        stk.pop();

      nse[i] = (stk.isEmpty()) ? n : stk.peek();

      stk.push(i);
    }
    return nse;
  }

  private int[] findPSEE(int[] nums, int n) {
    Stack<Integer> stk = new Stack<>();
    int[] psee = new int[n];

    for (int i = 0; i < n; i++) {
      while (!stk.isEmpty() && nums[stk.peek()] > nums[i])
        stk.pop();

      psee[i] = (stk.isEmpty()) ? -1 : stk.peek();

      stk.push(i);
    }
    return psee;
  }

  private int[] findNGE(int[] nums, int n) {
    Stack<Integer> stk = new Stack<>();
    int[] nge = new int[n];

    for (int i = n - 1; i >= 0; i--) {
      while (!stk.isEmpty() && nums[stk.peek()] <= nums[i])
        stk.pop();

      nge[i] = (stk.isEmpty()) ? n : stk.peek();

      stk.push(i);
    }
    return nge;
  }

  private int[] findPGEE(int[] nums, int n) {
    Stack<Integer> stk = new Stack<>();
    int[] pgee = new int[n];

    for (int i = 0; i < n; i++) {
      while (!stk.isEmpty() && nums[stk.peek()] < nums[i])
        stk.pop();

      pgee[i] = (stk.isEmpty()) ? -1 : stk.peek();

      stk.push(i);
      
    }
    return pgee;
  }

}