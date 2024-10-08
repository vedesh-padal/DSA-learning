// Find the largest pair sum in an array of distinct integers.

// Examples :
// Input: arr[] = [12, 34, 10, 6, 40]
// Output: 74
// Explanation: Sum of 34 and 40 is the largest, i.e, 34 + 40 = 74.

// Input: arr[] = [10, 20, 30]
// Output: 50
// Explanation: 20 + 30 = 50.
// Expected Time Complexity: O(n)
// Expected Auxiliary Space: O(1)

// Constraints:
// 2 ≤ arr.size() ≤ 106
// 0 ≤ arr[i] ≤ 106

class Solution {
  public static int pairsum(int[] arr) {
    // code here
    int max1 = -1, max2 = -1;
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      if (arr[i] > max1 && arr[i] != max2) {
        max2 = max1;
        max1 = arr[i];
      } else if (arr[i] > max2 && arr[i] < max1) {
        max2 = arr[i];
      }
    }
    return max1 + max2;
  }
}