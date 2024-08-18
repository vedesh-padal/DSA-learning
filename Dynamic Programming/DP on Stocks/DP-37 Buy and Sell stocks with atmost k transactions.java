/*
  You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

  Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

  Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

  Example 1:

  Input: k = 2, prices = [2,4,1]
  Output: 2
  Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
  Example 2:

  Input: k = 2, prices = [3,2,6,5,0,3]
  Output: 7
  Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
  

  Constraints:

  1 <= k <= 100
  1 <= prices.length <= 1000
  0 <= prices[i] <= 1000
*/

import java.util.Arrays;

// ALL THE BELOW APPROACHES BASED ON THE NUMBER OF THE TRANSACTION
// EVEN NUM TRXN is the BUY
// ODD NUM TRXN is the sell

class Solution {
  // MEMOIZATION APPROACH    
  private static int find1(int ind, int trxnNum, int[] prices, int[][] dp, int n, int k) {
    if (ind == n || trxnNum == 2 * k) // 2 * k becoz, at max. k transaction, each transaction can be either BUY or SELL
      return 0;

    if (dp[ind][trxnNum] != -1)
      return dp[ind][trxnNum];

    if (trxnNum % 2 == 0) { // buy
      return dp[ind][trxnNum] = Math.max(-prices[ind] + find1(ind + 1, trxnNum + 1, prices, dp, n, k),
          0 + find1(ind + 1, trxnNum, prices, dp, n, k));
    } else {
      return dp[ind][trxnNum] = Math.max(prices[ind] + find1(ind + 1, trxnNum + 1, prices, dp, n, k),
          0 + find1(ind + 1, trxnNum, prices, dp, n, k));
    }
  }

  public int maxProfit1(int k, int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n][2 * k];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find1(0, 0, prices, dp, n, k);
  }

  // -------------------------------------------------------------------------------
  // Tabulation 2D
  public int maxProfit2(int k, int[] prices) {
    int n = prices.length;
    int[][] dp = new int[n + 1][2 * k + 1];

    for (int ind = n - 1; ind >= 0; ind--) {
      for (int trxnNum = 2 * k - 1; trxnNum >= 0; trxnNum--) {
        if (trxnNum % 2 == 0) { // buy
          dp[ind][trxnNum] = Math.max(-prices[ind] + dp[ind + 1][trxnNum + 1],
              0 + dp[ind + 1][trxnNum]);
        } else { // sell
          dp[ind][trxnNum] = Math.max(prices[ind] + dp[ind + 1][trxnNum + 1],
              0 + dp[ind + 1][trxnNum]);
        }
      }
    }
    return dp[0][0];
  }

  // -------------------------------------------------------------------------------
  // TABULATION 2 1D arrays
  public int maxProfit3(int k, int[] prices) {
    int n = prices.length;
    int[] ahead = new int[2 * k + 1];
    int[] curr = new int[2 * k + 1];

    for (int ind = n - 1; ind >= 0; ind--) {
      for (int trxnNum = 2 * k - 1; trxnNum >= 0; trxnNum--) {
        if (trxnNum % 2 == 0) { // buy
          curr[trxnNum] = Math.max(-prices[ind] + ahead[trxnNum + 1],
              0 + ahead[trxnNum]);
        } else { // sell
          curr[trxnNum] = Math.max(prices[ind] + ahead[trxnNum + 1],
              0 + ahead[trxnNum]);
        }
      }
      ahead = curr;
    }
    return ahead[0];
  }
}