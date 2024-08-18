import java.util.Arrays;

class Solution {

  // GREEDY APPROACH => fast and efficient in this case, but too many buys and sells
  public int maxProfit2(int[] prices) {
    int profit = 0;
    for (int i = 1; i < prices.length; i++) {
      profit += Math.max(0, prices[i] - prices[i - 1]); // whenever there is a profit we are selling to gain profit,
                                                        // else we are buying
    }
    return profit;
  }
  
  // -----------------------------------------------------------------------------------------
  // DP using memoization ==> TLE [ worst soln ] => becoz, creates hell lot of
  // space -> dp table and also, recursive
  private static int find(int ind, int buy, int[] prices, int[][] dp) {

    // even if you are yet to sell the stock, you made 0 profit [ which means you
    // bought on the last day ]
    if (ind == prices.length)
      return 0;

    if (dp[ind][buy] != -1)
      return dp[ind][buy];

    int profit = 0;
    if (buy == 1) {
      profit = Math.max(-prices[ind] + find(ind + 1, 0, prices, dp), // stock bought
          0 + find(ind + 1, 1, prices, dp)); // stock not bought
    } else {
      profit = Math.max(prices[ind] + find(ind + 1, 1, prices, dp), // stock sold
          0 + find(ind + 1, 0, prices, dp)); // still not sold
    }

    return profit;
  }

  public int maxProfit3(int[] prices) {
    // we will have two DP states
    int[][] dp = new int[prices.length][2]; // the second state is whether you will buy or not buy
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(0, 1, prices, dp); // starting from first index, and a boolean flag indicating whether to buy or
                                            // not. 1 => true => can buy
  }
  
  // -----------------------------------------------------------------------------------------
  // Tabulation space optimized to just 4 variables
  public int maxProfit4(int[] prices) {
    int aheadNotBuy = 0, aheadBuy = 0;
    int currNotBuy = 0, currBuy = 0;
    int n = prices.length;
    for (int i = n - 1; i >= 0; i--) {
      currNotBuy = Math.max(prices[i] + aheadBuy, aheadNotBuy);
      currBuy = Math.max(-prices[i] + aheadNotBuy, aheadBuy);

      aheadBuy = currBuy;
      aheadNotBuy = currNotBuy;
    }
    return aheadBuy;
  }

  // -----------------------------------------------------------------------------------------
  // DP APPROACH   (previously solved solution, before understanding DP)
  public int maxProfit1(int[] prices) {
    // it is not possible to hold stock on first day, so hold is set to least value
    int hold = Integer.MIN_VALUE, notHold = 0;

    for (int price : prices) {
      int prevHold = hold, prevNotHold = notHold;

      // either keep hold, or buy in stock today at stock price
      hold = Math.max(prevHold, prevNotHold - price);

      // either keep notHold, or sell out stock today at stock price
      notHold = Math.max(prevNotHold, prevHold + price);
    }

    // max. profit must be in not hold state
    return notHold;
  }
}