import java.util.Arrays;

class Solution {

  // 3D DP -> Memoization Approach
  private static int find1(int ind, int buy, int cap, int[] prices, int[][][] dp) {

    if (ind == prices.length || cap == 0)
      return 0;

    if (dp[ind][buy][cap] != -1)
      return dp[ind][buy][cap];

    if (buy == 1) {
      return dp[ind][buy][cap] = Math.max(-prices[ind] + find1(ind + 1, 0, cap, prices, dp),
          0 + find1(ind + 1, 1, cap, prices, dp));
    } else {
      return dp[ind][buy][cap] = Math.max(prices[ind] + find1(ind + 1, 1, cap - 1, prices, dp),
          0 + find1(ind + 1, 0, cap, prices, dp));
    }
  }

  public int maxProfit1(int[] prices) {
    int[][][] dp = new int[prices.length][2][3]; // ind, buy/sell, cap { 0, 1, 2 }

    for (int[][] grid : dp) {
      for (int[] row : grid)
        Arrays.fill(row, -1);
    }

    return find1(0, 1, 2, prices, dp);
  }

  // -----------------------------------------------------
  // Tabulation approach
  public int maxProfit2(int[] prices) {
    int n = prices.length;
    int[][][] dp = new int[n + 1][2][3]; // ind, buy/sell, cap { 0, 1, 2 } // note: here it should be n+1, becoz index
                                         // out of bound in tabulation
    // we need not put 0 for the base cases specifically, as they are already 0 by
    // default when initialised
    for (int ind = n - 1; ind >= 0; ind--) {
      for (int buy = 0; buy <= 1; buy++) {
        for (int cap = 1; cap <= 2; cap++) { // we are starting from cap = 1, becoz, we want it to be 0 value when cap = 0
          if (buy == 1) {
            dp[ind][buy][cap] = Math.max(-prices[ind] + dp[ind + 1][0][cap], 0 + dp[ind + 1][1][cap]);
          } else {
            dp[ind][buy][cap] = Math.max(prices[ind] + dp[ind + 1][1][cap - 1], 0 + dp[ind + 1][0][cap]);
          }
        }
      }
    }

    return dp[0][1][2];
  }

  // -----------------------------------------------------
  // SPACE OPTIMIZED Tabulation approach
  public int maxProfit3(int[] prices) {
    int n = prices.length;
    int[][] ahead = new int[2][3];

    // we need not put 0 for the base cases specifically, as they are already 0 by
    // default when initialised
    for (int ind = n - 1; ind >= 0; ind--) {
      int[][] curr = new int[2][3];
      for (int buy = 0; buy <= 1; buy++) {
        for (int cap = 1; cap <= 2; cap++) { // we are starting from cap = 1, becoz, we want it to be 0 value when cap = 0
          if (buy == 1) {
            curr[buy][cap] = Math.max(-prices[ind] + ahead[0][cap], 0 + ahead[1][cap]);
          } else {
            curr[buy][cap] = Math.max(prices[ind] + ahead[1][cap - 1], 0 + ahead[0][cap]);
          }
        }
      }
      ahead = curr;
    }

    return ahead[1][2];
  }

  // -----------------------------------------------
  // ANOTHER APPROACH -> 4 values of transactions B S B S => even -> buy, odd -> sell

  private static int find2(int ind, int trxn, int[] prices, int[][] dp) {

    if (ind == prices.length || trxn == 4)
      return 0;

    if (dp[ind][trxn] != -1)
      return dp[ind][trxn];

    if (trxn % 2 == 0) // BUY
      return dp[ind][trxn] = Math.max(-prices[ind] + find2(ind + 1, trxn + 1, prices, dp),
          0 + find2(ind + 1, trxn, prices, dp));
    else // SELL
      return dp[ind][trxn] = Math.max(prices[ind] + find2(ind + 1, trxn + 1, prices, dp),
          0 + find2(ind + 1, trxn, prices, dp));
  }

  public int maxProfit4(int[] prices) {
    int[][] dp = new int[prices.length][4];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find2(0, 0, prices, dp);
  }

  // -----------------------------------------------------
  // ANOTHER APPROACH Tabulation 4 possibilites of a state
  public int maxProfit5(int[] prices) {
    int n = prices.length;
    int[] ahead = new int[5]; // buy, sell, buy, sell => even number of trxn -> buy , odd number -> sell
    int[] curr = new int[5]; // since this is tabulation, we take 1 extra
    Arrays.fill(ahead, -1);
    Arrays.fill(ahead, 0);

    Arrays.fill(curr, -1);
    curr[4] = 0; // base condition

    // we need not put 0 for the base cases specifically, as they are already 0 by
    // default when initialised
    for (int ind = n - 1; ind >= 0; ind--) {

      for (int trxn = 3; trxn >= 0; trxn--) {
        if (trxn % 2 == 0) { // even number of trxn is BUY
          curr[trxn] = Math.max(-prices[ind] + ahead[trxn + 1], 0 + ahead[trxn]);
        } else { // odd Num trxn is sell
          curr[trxn] = Math.max(prices[ind] + ahead[trxn + 1], 0 + ahead[trxn]);
        }
      }
      ahead = curr;
    }

    return ahead[0];
  }

}