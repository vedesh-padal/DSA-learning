// MEDIUM
// arrays, sorting, binar-search

// You are given a 2D integer array items where items[i] = [pricei, beautyi]
// denotes the price and beauty of an item respectively.

// You are also given a 0-indexed integer array queries. For each queries[j],
// you want to determine the maximum beauty of an item whose price is less than
// or equal to queries[j]. If no such item exists, then the answer to this query is 0.

// Return an array answer of the same length as queries where answer[j] is the
// answer to the jth query.

// Example 1:
// Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
// Output: [2,4,5,5,6,6]
// Explanation:
// - For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the
// answer for this query is 2.
// - For queries[1]=2, the items which can be considered are [1,2] and [2,4].
// The maximum beauty among them is 4.
// - For queries[2]=3 and queries[3]=4, the items which can be considered are
// [1,2], [3,2], [2,4], and [3,5].
// The maximum beauty among them is 5.
// - For queries[4]=5 and queries[5]=6, all items can be considered.
// Hence, the answer for them is the maximum beauty of all items, i.e., 6.

// Example 2:
// Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
// Output: [4]
// Explanation:
// The price of every item is equal to 1, so we choose the item with the maximum beauty 4.
// Note that multiple items can have the same price and/or beauty.

// Example 3:
// Input: items = [[10,1000]], queries = [5]
// Output: [0]
// Explanation:
// No item has a price less than or equal to 5, so no item can be chosen.
// Hence, the answer to the query is 0.

// Constraints:
// 1 <= items.length, queries.length <= 105
// items[i].length == 2
// 1 <= pricei, beautyi, queries[j] <= 109

import java.util.Arrays;

class Solution {

  // OPTIMAL APPROACH using Binary Search
  private int binarySearch(int[][] items, int price) {
    int l = 0, r = items.length - 1;
    int maxB = 0;

    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (items[mid][0] > price) {
        r = mid - 1;
      } else {
        // found suitable price, now keep moving right to get the max. price
        maxB = Math.max(maxB, items[mid][1]);
        l = mid + 1;
      }
    }
    return maxB;
  }

  public int[] maximumBeauty(int[][] items, int[] queries) {
    Arrays.sort(items, (a, b) -> a[0] - b[0]);
    int numQ = queries.length;

    int[] ans = new int[numQ];
    // max. beauty seen so far
    int maxi = items[0][1];
    for (int i = 1; i < items.length; i++) {
      maxi = Math.max(items[i][1], maxi);
      items[i][1] = maxi;
    }

    // for each query, find the max. beauty with price <= queryPrice
    for (int i = 0; i < queries.length; i++) {
      ans[i] = binarySearch(items, queries[i]);
    }
    return ans;
  }

  // ==========================================
  // my try
  public int[] maximumBeauty_MY_TRY(int[][] items, int[] queries) {
    Arrays.sort(items, (a, b) -> {
      // if prices are same, sort by asc. order of beauty values
      if (a[0] == b[0])
        return a[1] - b[1];
      else
        return a[0] - b[0]; // else sort acc. to prices
    });

    for (int[] row : items) {
      System.out.println(Arrays.toString(row));
    }

    int[][] qInd = new int[queries.length][2];
    for (int i = 0; i < queries.length; i++) {
      qInd[i] = new int[] { queries[i], i };
    }

    System.out.println("\n\n");

    Arrays.sort(qInd, (a, b) -> a[0] - b[0]);

    for (int[] row : qInd) {
      System.out.println(Arrays.toString(row));
    }

    System.out.println("\n\n");

    int[] ans = new int[queries.length];

    int maxB = 0; // maxBeauty so far

    int i = 0, j = 0;
    while (i < queries.length) {
      while (j < items.length && items[j][0] <= qInd[i][0]) {
        maxB = Math.max(maxB, items[j][1]);
        j++;
      }

      // put it in the original position of query indices
      ans[qInd[i][1]] = maxB;
      i++;
    }

    System.out.println(Arrays.toString(ans));

    return ans;
  }
}