// EASY
// arrays

// Given an array of integers arr, return true if and only if it is a valid mountain array.

// Recall that arr is a mountain array if and only if:

// arr.length >= 3
// There exists some i with 0 < i < arr.length - 1 such that:
// arr[0] < arr[1] < ... < arr[i - 1] < arr[i] 
// arr[i] > arr[i + 1] > ... > arr[arr.length - 1]

class Solution {
  public boolean validMountainArray(int[] arr) {
    int n = arr.length;
    // becoz there needs to be a mountain
    if (n < 3)
      return false;
    int i;
    for (i = 0; i < n - 1; i++) {
      if (arr[i] == arr[i + 1])
        return false; // there cannot be a valley

      // we noticed the first next element decreasing
      if (arr[i] > arr[i + 1])
        break;
    }

    // i:
    // directly decreasing at the start OR
    // it was always increasing till the end
    if (i == 0 || i == n - 1)
      return false;

    for (; i < n - 1; i++) {
      if (arr[i] == arr[i - 1])
        return false;

      if (arr[i] < arr[i + 1])
        return false;
    }
    return true;
  }
}