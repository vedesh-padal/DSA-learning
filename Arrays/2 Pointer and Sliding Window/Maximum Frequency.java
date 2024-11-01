// Given an interger array arr[] of size n and an interger k.In one operation,
// you can choose an index i where 0<i Return the maximum frequency of an
// element after using atmost k Increment operations.

// Example 1:

// Input:
// n=3
// arr[] = {2,2,4},k=4
// Output: 3
// Explanation: Apply two operations on index 0 and two operations
// on index 1 to make arr[]={4,4,4}.Frequency of 4 is 3.

// Example 2:
// Input:
// n=4
// arr[] = {7,7,7,7},k=5
// Output: 4
// Explanation: It is optimal to not use any operation.
// Frequency of 7 is 4.

// Constraints:
// 1 ≤ n,k ≤ 105
// 1 ≤ arr[i] ≤ 106

import java.util.Arrays;

class Solution {
  public static int maxFrequency(int[] arr, int n, int k) {
    // code
    Arrays.sort(arr);

    int l = 0, r = 0;
    int maxi = 0;

    // stores sum of sliding window
    int sum = 0;

    while (r < n) {
      // add the curr. element to the window
      sum += arr[r];

      // expected --------- what we have
      while ((r - l + 1) * arr[r] > sum + k) {
        sum -= arr[l];
        l++;
      }

      // we found a valid windows, for which we can perform
      // k number of add operations
      maxi = Math.max(maxi, r - l + 1);
      r++;
    }

    return maxi;
  }
}