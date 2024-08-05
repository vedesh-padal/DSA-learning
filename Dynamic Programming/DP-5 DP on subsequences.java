/*
  You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
  the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected 
  and it will automatically contact the police if two adjacent houses were broken into on the same night.

  Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

  Example 1:

  Input: nums = [1,2,3,1]
  Output: 4
  Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
  Total amount you can rob = 1 + 3 = 4.
  Example 2:

  Input: nums = [2,7,9,3,1]
  Output: 12
  Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
  Total amount you can rob = 2 + 9 + 1 = 12.
  

  Constraints:

  1 <= nums.length <= 100
  0 <= nums[i] <= 400
*/

import java.util.Arrays;

class Solution {

  // dp => memoization
  private static int find(int ind, int[] nums, int[] dp) {
    if (ind == 0)
      return nums[ind];

    if (ind < 0)
      return 0;

    if (dp[ind] != -1)
      return dp[ind];

    int pick = nums[ind] + find(ind - 2, nums, dp);
    int notPick = 0 + find(ind - 1, nums, dp);

    return dp[ind] = Math.max(pick, notPick);
  }

  public int rob1(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, -1);

    return find(n - 1, nums, dp);
  }

  // Tabulation with space optimization
  public int rob2(int[] nums) {
    int prev2 = 0, prev = nums[0];
    int curr = 0;
    for (int i = 1; i < nums.length; i++) {
      int pick = nums[i];
      if (i > 1)
        pick += prev2;
      int notPick = 0 + prev;
      curr = Math.max(pick, notPick);
      prev2 = prev;
      prev = curr;
    }
    return prev; // when i = n. n-1's value will be prev
  }
}