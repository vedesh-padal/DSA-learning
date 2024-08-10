/*
  You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

  => Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

  You may assume that you have an infinite number of each kind of coin.

  The answer is guaranteed to fit into a signed 32-bit integer.
  
  Example 1:

  Input: amount = 5, coins = [1,2,5]
  Output: 4
  Explanation: there are four ways to make up the amount:
  5=5
  5=2+2+1
  5=2+1+1+1
  5=1+1+1+1+1
  Example 2:

  Input: amount = 3, coins = [2]
  Output: 0
  Explanation: the amount of 3 cannot be made up just with coins of 2.
  Example 3:

  Input: amount = 10, coins = [10]
  Output: 1
  

  Constraints:

  1 <= coins.length <= 300
  1 <= coins[i] <= 5000
  All the values of coins are unique.
  0 <= amount <= 5000
*/

import java.util.Arrays;

class Solution {

  private static int find(int ind, int target, int[] coins, int[][] dp) {
    if (ind == 0)
      return (target % coins[0] == 0) ? 1 : 0;

    if (dp[ind][target] != -1)
      return dp[ind][target];

    int notTake = find(ind - 1, target, coins, dp);
    int take = 0;
    if (coins[ind] <= target)
      take = find(ind, target - coins[ind], coins, dp);

    return dp[ind][target] = take + notTake;
  }

  public int change(int amount, int[] coins) {
    int n = coins.length;
    int[][] dp = new int[n][amount + 1];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(n - 1, amount, coins, dp);
  }


  // -------------------
  // SPACE OPTIMIZED TABULATION APPROACH

  public int change2(int amount, int[] coins) {
    int n = coins.length;
    int[] dp = new int[amount + 1];

    for (int t = 0; t <= amount; t++)
      dp[t] = (t % coins[0]) == 0 ? 1 : 0;

    for (int ind = 1; ind < n; ind++) {
      int[] curr = new int[amount + 1];
      for (int t = 0; t <= amount; t++) {
        int notTake = dp[t];
        int take = 0;
        if (coins[ind] <= t)
          take = curr[t - coins[ind]];

        curr[t] = take + notTake;
      }
      dp = curr;
    }
    return dp[amount];

  }
}