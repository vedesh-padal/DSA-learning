
// not working if there are negative integers [ LEETCODE HARD PROBLEM ]

class Solution {
  public int minimumDifference(int[] nums) {
    int totalSum = 0;
    int n = nums.length;
    for (int i : nums)
      totalSum += Math.abs(i);

    int k = totalSum;

    if (nums[0] == -nums[1])
      return Math.abs(nums[0] - nums[1]);

    boolean[] dp = new boolean[k + 1];

    // base case initialization
    dp[0] = true;
    if (nums[0] <= k)
      dp[Math.abs(nums[0])] = true;

    for (int ind = 1; ind < n; ind++) {
      boolean[] curr = new boolean[k + 1];
      curr[0] = true;
      for (int target = 1; target <= k; target++) {
        boolean notPick = dp[target];
        boolean pick = false;
        if (nums[ind] <= target)
          pick = dp[Math.abs(target - Math.abs(nums[ind]))];

        curr[target] = pick || notPick;
      }
      dp = curr;
    }

    int min = (int) (1e9);
    for (int i = 0; i <= totalSum / 2; i++) {
      if (dp[i] == true) {
        min = Math.min(min, Math.abs((totalSum - i) - i));
      }
    }
    return min;
  }
}