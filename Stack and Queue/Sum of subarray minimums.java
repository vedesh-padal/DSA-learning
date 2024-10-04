// Given an array of integers arr, find the sum of min(b), where b ranges over
// every (contiguous) subarray of arr. Since the answer may be large, return the
// answer modulo 109 + 7.

// Example 1:
// Input: arr = [3,1,2,4]
// Output: 17
// Explanation:
// Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4],
// [3,1,2,4].
// Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
// Sum is 17.

// Example 2:
// Input: arr = [11,81,94,43,3]
// Output: 444

// Constraints:
// 1 <= arr.length <= 3 * 104
// 1 <= arr[i] <= 3 * 104

import java.util.Stack;

class Solution {

  private int[] findNSE(int[] arr, int n) {
    Stack<Integer> stk = new Stack<>();

    int[] nse = new int[n];

    for (int i = n - 1; i >= 0; i--) {
      while (!stk.isEmpty() && arr[stk.peek()] >= arr[i])
        stk.pop();

      nse[i] = (stk.isEmpty()) ? n : stk.peek();

      stk.push(i); // note that we are storing indices
      // in order to calculate the array size
    }
    return nse;
  }

  private int[] findPSEE(int[] arr, int n) {
    Stack<Integer> stk = new Stack<>();
    int[] psee = new int[n];

    for (int i = 0; i < n; i++) {
      // we are searching for smaller or equal element
      // so we just keep removing until we find larger, and not larger or equal
      // elements
      while (!stk.isEmpty() && arr[stk.peek()] > arr[i])
        stk.pop();

      psee[i] = (stk.isEmpty()) ? -1 : stk.peek();

      stk.push(i);
    }
    return psee;
  }

  public int sumSubarrayMins(int[] arr) {
    int n = arr.length;

    int[] nse = findNSE(arr, n); // next smaller element
    int[] psee = findPSEE(arr, n); // previous smaller or equal element
    // equal also considered to avoid edge case, where we end up considering
    // same subarray twice

    int total = 0;
    int MOD = (int) (1e9 + 7);

    for (int i = 0; i < n; i++) {
      // len of subarray, i.e.,
      // how many element are there on the left, for which arr[i] is min. element
      int left = i - psee[i]; // looking backward
      int right = nse[i] - i; // looking forward

      // FINDING EACH ARR ELEMENT individual contribution
      // in subarrays with it being minimum

      // left * right indicates no. of subarrays with curr. element included
      // and this formula helps in calculating becoz:
      // left indicates: no. of starting points with arr[i] included in subarray
      // right indiciates: no. of ending points with arr[i] included in subarray
      // multiplying with 1L as it can be very large number
      // and again multiplying with arr[i], as it represents the sum
      // ex: 12 times 3 can occur, leading to sum of the minimums of those subarrays
      // being: 12 x 3
      total = (int) (total + ((left * right) * 1L * arr[i]) % MOD) % MOD;
    }
    return total;
  }
}