/*
  You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

  Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

  You may assume that you have an infinite number of each kind of coin.

  Example 1:

  Input: coins = [1,2,5], amount = 11
  Output: 3
  Explanation: 11 = 5 + 5 + 1
  Example 2:

  Input: coins = [2], amount = 3
  Output: -1
  Example 3:

  Input: coins = [1], amount = 0
  Output: 0
  

  Constraints:

  1 <= coins.length <= 12
  1 <= coins[i] <= 231 - 1
  0 <= amount <= 104
*/




import java.util.Arrays;

class Solution {

  final static int high = (int) (1e9);

  private static int find(int ind, int tar, int[] coins, int[][] dp) {
    if (ind == 0) {
      if (tar % coins[ind] == 0)
        return tar / coins[ind];
      return high;
    }

    if (dp[ind][tar] != -1)
      return dp[ind][tar];

    int notTake = 0 + find(ind - 1, tar, coins, dp);
    int take = Integer.MAX_VALUE;
    if (coins[ind] <= tar) {
      take = 1 + find(ind, tar - coins[ind], coins, dp);
      // you are staying at the same index, becoz u can consider the same denomination
      // multiple times as you have infinite supply
    }

    return dp[ind][tar] = Math.min(take, notTake);
  }

  public int coinChange1(int[] coins, int amount) {
    int n = coins.length;
    int[][] dp = new int[n][amount + 1];

    for (int[] row : dp)
      Arrays.fill(row, -1);

    int ans = find(n - 1, amount, coins, dp);
    return ans >= high ? -1 : ans;
  }

  // ---------------------------------------------------------------------------

  // Tabulation with space optimization
  public int coinChange2(int[] coins, int amount) {
    int n = coins.length;
    int[] dp = new int[amount + 1];

    // base case
    for (int t = 0; t <= amount; t++) {
      if (t % coins[0] == 0)
        dp[t] = t / coins[0];
      else
        dp[t] = high;
    }

    for (int ind = 1; ind < n; ind++) {
      int[] curr = new int[amount + 1];

      for (int t = 0; t <= amount; t++) {
        int notTake = dp[t];
        int take = Integer.MAX_VALUE;
        if (coins[ind] <= t)
          take = 1 + curr[t - coins[ind]];

        curr[t] = Math.min(take, notTake);
      }
      dp = curr;
    }

    int ans = dp[amount];
    return ans >= high ? -1 : ans;
  }
}