/*
  A thief is robbing a store and can carry a maximal weight of W into his knapsack. There are N items and the ith item weighs wi and is of value vi. Considering the constraints of the maximum weight that a knapsack can carry, you have to find and return the maximum value that a thief can generate by stealing items.

  Detailed explanation ( Input/output format, Notes, Images )
  Constraints:
  1 <= T <= 10
  1 <= N <= 10^2
  1<= wi <= 50
  1 <= vi <= 10^2
  1 <= W <= 10^3

  Time Limit: 1 second
  Sample Input:
  1 
  4
  1 2 4 5
  5 4 8 6
  5
  Sample Output:
  13
*/


import java.util.*;

class Solution {

  // Memoization approach
  private static int find(int ind, int w, int[] wt, int[] val, int[][] dp) {
    if (ind == 0) {
      if (wt[ind] <= w)
        return val[ind];
      return 0;
    }

    if (dp[ind][w] != -1)
      return dp[ind][w];

    int notTake = 0 + find(ind - 1, w, wt, val, dp);
    int take = Integer.MIN_VALUE;
    if (wt[ind] <= w)
      take = val[ind] + find(ind - 1, w - wt[ind], wt, val, dp);

    return dp[ind][w] = Math.max(take, notTake);
  }

  static int knapsack1(int[] weight, int[] value, int n, int maxWeight) {

    /*
     * Your class should be named Solution
     * Don't write main().
     * Don't read input, it is passed as function argument.
     * Change in the given tree itself.
     * No need to return or print the output.
     * Taking input and printing output is handled automatically.
     */

    int[][] dp = new int[n][maxWeight + 1];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(n - 1, maxWeight, weight, value, dp);

  }

  // Tabulation method
  static int knapsack2(int[] weight, int[] value, int n, int maxWeight) {

    int[][] dp = new int[n][maxWeight + 1];

    for (int wt = weight[0]; wt <= maxWeight; wt++) {
      dp[0][wt] = value[0];
    }

    for (int ind = 1; ind < n; ind++) {
      for (int wt = 0; wt <= maxWeight; wt++) {
        int notTake = 0 + dp[ind - 1][wt];
        int take = Integer.MIN_VALUE;
        if (weight[ind] <= wt) {
          take = value[ind] + dp[ind - 1][wt - weight[ind]];
        }

        dp[ind][wt] = Math.max(take, notTake);
      }
    }
    return dp[n - 1][maxWeight];
  }

  ////// THE MOST OPTIMIZED
  // Tabulation with single array space optimization
  static int knapsack3(int[] weight, int[] value, int n, int maxWeight) {

    int[] prev = new int[maxWeight + 1];

    for (int wt = weight[0]; wt <= maxWeight; wt++) {
      prev[wt] = value[0];
    }

    for (int ind = 1; ind < n; ind++) {
      for (int wt = maxWeight; wt >= 0; wt--) {
        int notTake = 0 + prev[wt];
        int take = Integer.MIN_VALUE;
        if (weight[ind] <= wt) {
          take = value[ind] + prev[wt - weight[ind]];
        }

        prev[wt] = Math.max(take, notTake);
      }
    }
    return prev[maxWeight];
  }
}