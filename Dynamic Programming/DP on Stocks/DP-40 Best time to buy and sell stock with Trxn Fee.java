/*
  You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

  Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

  Note:
  You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
  The transaction fee is only charged once for each stock purchase and sale.

  Example 1:

  Input: prices = [1,3,2,8,4,9], fee = 2
  Output: 8
  Explanation: The maximum profit can be achieved by:
  - Buying at prices[0] = 1
  - Selling at prices[3] = 8
  - Buying at prices[4] = 4
  - Selling at prices[5] = 9
  The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
  Example 2:

  Input: prices = [1,3,7,5,10,3], fee = 3
  Output: 6
  

  Constraints:

  1 <= prices.length <= 5 * 104
  1 <= prices[i] < 5 * 104
  0 <= fee < 5 * 104
*/


class Solution {
  
  // APPROACH - 1 => tUf
  public int maxProfit1(int[] prices, int fee) {
    int aheadBuy = 0, aheadNotBuy = 0, currBuy, currNotBuy;

    int n = prices.length;
    for (int ind = n - 1; ind >= 0; ind--) {
      currNotBuy = Math.max( prices[ind] - fee + aheadBuy, 0 + aheadNotBuy);

      currBuy = Math.max( -prices[ind] + aheadNotBuy, 0 + aheadBuy);

      aheadBuy = currBuy;
      aheadNotBuy = currNotBuy;
    }

    return aheadBuy;
  }

  // APPROACH - 2 => best sol. on LC
  public int maxProfit2(int[] prices, int fee) {
    int buy = Integer.MIN_VALUE; // max. profit when holding a stock
    int sell = 0; // max. profit when not holding a stock

    for (int price : prices) {
      // Update buy: Max profit if we buy today or keep holding
      buy = Math.max(buy, sell - price);

      // Update sell: Max profit if we sell today or keep not holding
      sell = Math.max(sell, buy + price - fee);
    }
    // return max. profit when not holding a stock
    return sell;
  }
}