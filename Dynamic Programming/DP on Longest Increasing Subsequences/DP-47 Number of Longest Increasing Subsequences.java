/*
  Given an integer array nums, return the number of longest increasing subsequences.

  Notice that the sequence has to be strictly increasing.

  Example 1:
  Input: nums = [1,3,5,4,7]
  Output: 2
  Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

  Example 2:
  Input: nums = [2,2,2,2,2]
  Output: 5
  Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
  
  Constraints:
  1 <= nums.length <= 2000
  -106 <= nums[i] <= 106
*/

class Solution {
  public int findNumberOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    int[] count = new int[n];

    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      count[i] = 1;
    }

    int maxi = 1;

    for (int i = 0; i < n; i++) {
      for (int prev = 0; prev < i; prev++) {
        if (nums[prev] < nums[i] && 1 + dp[prev] > dp[i]) {
          dp[i] = 1 + dp[prev];
          // we have encountered a new one
          // so inherit
          count[i] = count[prev];
        } else if (nums[prev] < nums[i] && 1 + dp[prev] == dp[i]) {
          // this is already encountered, 
          // so increase the count based on the previously stored count
          count[i] += count[prev];
        }
      }
      maxi = Math.max(dp[i], maxi);
    }

    int c = 0;
    for (int i = 0; i < n; i++) {
      if (dp[i] == maxi)
        c += count[i];
    }

    return c;
  }
}