import java.util.Arrays;

class Solution {

  // MEMOIZATION APPROACH
  private static int find(int ind, int buy, int[] prices, int[][] dp) {
    if (ind >= prices.length)
      return 0;

    if (dp[ind][buy] != -1)
      return dp[ind][buy];

    if (buy == 1)
      return dp[ind][buy] = Math.max(-prices[ind] + find(ind + 1, 0, prices, dp),
          0 + find(ind + 1, 1, prices, dp));

    else // sell - sold, so ind + 2, because, can't just buy the next day after selling
      return dp[ind][buy] = Math.max(prices[ind] + find(ind + 2, 1, prices, dp),
          0 + find(ind + 1, 0, prices, dp));
  }

  public int maxProfit1(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n][2];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(0, 1, prices, dp);
  }

  // -------------------------------------------------------------------------------
  // TABULATION APPROACH
  public int maxProfit2(int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 2][2];

    for (int ind = n - 1; ind >= 0; ind--) {
      // buy
      dp[ind][1] = Math.max(-prices[ind] + dp[ind + 1][0], 0 + dp[ind + 1][1]);

      // sell
      dp[ind][0] = Math.max(prices[ind] + dp[ind + 2][1], 0 + dp[ind + 1][0]);
    }
    return dp[0][1];
  }

  // -------------------------------------------------------------------------------
  // SPACE OPTIMIZED TABULATION
  public int maxProfit3(int[] prices) {
    int n = prices.length;
    int[] front2 = new int[2];
    int[] front1 = new int[2];
    int[] curr = new int[2];

    for (int ind = n - 1; ind >= 0; ind--) {
      // buy
      curr[1] = Math.max(-prices[ind] + front1[0], 0 + front1[1]);

      // sell
      curr[0] = Math.max(prices[ind] + front2[1], 0 + front1[0]);

      front2 = new int[] { front1[0], front1[1] };
      front1 = new int[] { curr[0], curr[1] };
    }

    return front1[1];
  }
}