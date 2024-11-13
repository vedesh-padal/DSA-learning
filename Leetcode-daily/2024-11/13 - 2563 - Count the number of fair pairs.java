// MEDIUM
// sorting, arrays, binary-search

// Given a 0-indexed integer array nums of size n and two integers lower and
// upper, return the number of fair pairs.

// A pair (i, j) is fair if:
// 0 <= i < j < n, and
// lower <= nums[i] + nums[j] <= upper

// Example 1:
// Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
// Output: 6
// Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).

// Example 2:
// Input: nums = [1,7,9,2,5], lower = 11, upper = 11
// Output: 1
// Explanation: There is a single fair pair: (2,3).

// Constraints:
// 1 <= nums.length <= 105
// nums.length == n
// -109 <= nums[i] <= 109
// -109 <= lower <= upper <= 109

import java.util.Arrays;

class Solution {
  public long countFairPairs(int[] nums, int lower, int upper) {
    int n = nums.length;
    Arrays.sort(nums);

    long res = 0;

    for (int i = 0; i < n; i++) {
      int leftInd = lowerBound(nums, i + 1, n, lower - nums[i]);
      int rightInd = upperBound(nums, i + 1, n, upper - nums[i]);

      int x = leftInd - 1 - i;
      int y = rightInd - 1 - i;

      res += (y - x);
    }

    return res;
  }

  private int lowerBound(int[] nums, int start, int end, int target) {
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] < target) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
    return start;
  }

  private int upperBound(int[] nums, int start, int end, int target) {
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] <= target) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
    return start;
  }
}