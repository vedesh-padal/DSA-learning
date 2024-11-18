// EASY - MEDIUM

// Given an unsorted array arr[]. Rotate the array to the left
// (counter-clockwise direction) by d steps, where d is a positive integer. Do
// the mentioned change in the array in place.

// Note: Consider the array as circular.

// Examples :
// Input: arr[] = [1, 2, 3, 4, 5], d = 2
// Output: [3, 4, 5, 1, 2]
// Explanation: when rotated by 2 elements, it becomes 3 4 5 1 2.

// Input: arr[] = [2, 4, 6, 8, 10, 12, 14, 16, 18, 20], d = 3
// Output: [8, 10, 12, 14, 16, 18, 20, 2, 4, 6]
// Explanation: when rotated by 3 elements, it becomes 8 10 12 14 16 18 20 2 4 6.

// Input: arr[] = [7, 3, 9, 1], d = 9
// Output: [3, 9, 1, 7]
// Explanation: when we rotate 9 times, we'll get 3 9 1 7 as resultant array.

// Constraints:
// 1 <= arr.size(), d <= 105
// 0 <= arr[i] <= 105

class Solution {
  // Function to rotate an array by d elements in counter-clockwise direction.
  static void rotateArr(int arr[], int d) {
    // add your code here
    int n = arr.length;
    d = d % n;

    if (d == 1 && d > n) {
      return;
    }

    rotate(arr, 0, d - 1);
    rotate(arr, d, n - 1);
    rotate(arr, 0, n - 1);
  }

  private static void rotate(int[] arr, int s, int e) {
    while (s < e) {
      int t = arr[s];
      arr[s] = arr[e];
      arr[e] = t;
      s++;
      e--;
    }
  }
}