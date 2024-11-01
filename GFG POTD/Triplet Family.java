// 27-10-2024
// EASY

// Given an array arr of integers. Find whether three numbers are such that the
// sum of two elements equals the third element.

// Example:
// Input: arr[] = [1, 2, 3, 4, 5]
// Output: true
// Explanation: The pair (1, 2) sums to 3.

// Input: arr[] = [5, 3, 4]
// Output: false
// Explanation: No triplets satisfy the condition.
// Expected Time Complexity: O(n2)
// Expected Auxilary Space: O(1)

// Constraints:
// 1 <= arr.size() <= 103
// 0 <= arr[i] <= 105

import java.util.Arrays;

class Solution {
  public boolean findTriplet(int[] arr) {
    Arrays.sort(arr);
    int n = arr.length;
    int k = n - 1;

    while (k >= 2) {
      int l = 0, h = k - 1;
      while (l < h) {
        int sum = arr[l] + arr[h];
        if (sum == arr[k])
          return true;
        else if (sum < arr[k])
          l++;
        else
          h--;
      }
      k--;

    }
    return false;
  }
}