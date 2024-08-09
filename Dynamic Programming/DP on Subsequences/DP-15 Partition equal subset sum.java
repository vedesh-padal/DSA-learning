import java.util.Arrays;

class Solution {

  // Memoized solution
  private static boolean find(int ind, int target, int[] nums, int[][] dp) {
    if (target == 0)
      return true;
    if (ind == 0)
      return nums[ind] == target;

    if (dp[ind][target] != -1)
      return dp[ind][target] == 0 ? false : true;

    boolean notTaken = find(ind - 1, target, nums, dp);
    boolean taken = false;
    if (nums[ind] <= target)
      taken = find(ind - 1, target - nums[ind], nums, dp);

    dp[ind][target] = (notTaken || taken) ? 1 : 0;

    return notTaken || taken;
  }

  public boolean canPartition1(int[] nums) {
    int totalSum = 0;
    for (int i : nums)
      totalSum += i;

    if (totalSum % 2 == 1)
      return false;
    else {
      int sum = totalSum / 2;
      int n = nums.length;
      int[][] dp = new int[n][sum + 1];
      for (int[] row : dp)
        Arrays.fill(row, -1);

      return find(n - 1, sum, nums, dp);
    }
  }

  // ----------------------------------------------------------------
  // Tabulation with space optimization
  public boolean canPartition2(int[] nums) {
    int totalSum = 0;
    for (int i : nums)
      totalSum += i;

    if (totalSum % 2 == 1)
      return false;
    else {
      int sum = totalSum / 2;
      int n = nums.length;
      boolean[] dp = new boolean[sum + 1];
      dp[0] = true;

      if (nums[0] <= sum)
        dp[nums[0]] = true;

      for (int ind = 1; ind < n; ind++) {
        boolean[] curr = new boolean[sum + 1];
        curr[0] = true;
        for (int target = 1; target <= sum; target++) {
          boolean notTake = dp[target];
          boolean take = false;
          if (nums[ind] <= target)
            take = dp[target - nums[ind]];

          curr[target] = notTake || take;
        }
        dp = curr;
      }
      // if there is a subset that has a sum (which is half the total),
      // then there will also exists another subset with the same sum
      return dp[sum];
    }
  }
}