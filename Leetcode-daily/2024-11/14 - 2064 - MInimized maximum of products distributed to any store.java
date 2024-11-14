// MEDIUM
// arrays, binary-search

// You are given an integer n indicating there are n specialty retail stores.
// There are m product types of varying amounts, which are given as a 0-indexed
// integer array quantities, where quantities[i] represents the number of
// products of the ith product type.

// You need to distribute all products to the retail stores following these rules:

// A store can only be given at most one product type but can be given any amount of it.
// After distribution, each store will have been given some number of products
// (possibly 0). Let x represent the maximum number of products given to any
// store. You want x to be as small as possible, i.e., you want to minimize the
// maximum number of products that are given to any store.
// Return the minimum possible x.

// Example 1:
// Input: n = 6, quantities = [11,6]
// Output: 3
// Explanation: One optimal way is:
// - The 11 products of type 0 are distributed to the first four stores in these amounts: 2, 3, 3, 3
// - The 6 products of type 1 are distributed to the other two stores in these amounts: 3, 3
// The maximum number of products given to any store is max(2, 3, 3, 3, 3, 3) = 3.
// Example 2:
// Input: n = 7, quantities = [15,10,10]
// Output: 5
// Explanation: One optimal way is:
// - The 15 products of type 0 are distributed to the first three stores in these amounts: 5, 5, 5
// - The 10 products of type 1 are distributed to the next two stores in these amounts: 5, 5
// - The 10 products of type 2 are distributed to the last two stores in these amounts: 5, 5
// The maximum number of products given to any store is max(5, 5, 5, 5, 5, 5, 5) = 5.
// Example 3:
// Input: n = 1, quantities = [100000]
// Output: 100000
// Explanation: The only optimal way is:
// - The 100000 products of type 0 are distributed to the only store.
// The maximum number of products given to any store is max(100000) = 100000.

// Constraints:
// m == quantities.length
// 1 <= m <= n <= 10^5
// 1 <= quantities[i] <= 10^5

// BINARY SEARCH ON ANSWER
// similar to koko eating bananas

class Solution {

  // iterating with this expected max. number if it can distribute 
  // as specific in the question
  private boolean canDistribute(int x, int[] quantities, int shops) {
    for (int quantity : quantities) {
      // another way of writing Math.ceil(quantity / x);
      // for faster calculation
      shops -= (quantity + x - 1) / x;

      // which means we still have one or more product category to distribute
      // but all shops were already alloted products of a category
      if (shops < 0)
        return false;
    }
    return true;
  }

  // TC: O(MlogK) => where: K => max. product count, M => no. of products
  public int minimizedMaximum(int n, int[] quantities) {

    int maxProdCnt = 0;
    for (int quant : quantities)
      maxProdCnt = Math.max(maxProdCnt, quant);

    int l = 1, h = maxProdCnt;

    int result = 0;

    while (l <= h) {
      int mid = l + (h - l) / 2;

      // we are greedy, to check if there is a minimium max. number
      //  that can be distributed
      if (canDistribute(mid, quantities, n)) {
        result = mid;
        h = mid - 1;
      }
      // we have chosen less number, and that is not allowing all products to be used
      // so try increasing the chosen number (that is minimum max. number)
      else {
        l = mid + 1;
      }
    }

    return result;
  }
}