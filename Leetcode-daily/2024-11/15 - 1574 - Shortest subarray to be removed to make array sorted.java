// MEDIUM
// two-pointer, arrays, 

// Given an integer array arr, remove a subarray (can be empty) from arr such
// that the remaining elements in arr are non-decreasing.

// Return the length of the shortest subarray to remove.

// A subarray is a contiguous subsequence of the array.

// Example 1:
// Input: arr = [1,2,3,10,4,2,3,5]
// Output: 3
// Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The
// remaining elements after that will be [1,2,3,3,5] which are sorted.
// Another correct solution is to remove the subarray [3,10,4].

// Example 2:
// Input: arr = [5,4,3,2,1]
// Output: 4
// Explanation: Since the array is strictly decreasing, we can only keep a
// single element. Therefore we need to remove a subarray of length 4, either
// [5,4,3,2] or [4,3,2,1].

// Example 3:
// Input: arr = [1,2,3]
// Output: 0
// Explanation: The array is already non-decreasing. We do not need to remove any elements.

// Constraints:
// 1 <= arr.length <= 105
// 0 <= arr[i] <= 109

class Solution {

  // OPTIMAL 2-POINTER APPROACH WITH GOOD EDGE CASES
  public int findLengthOfShortestSubarray(int[] arr) {
    int n = arr.length;

    // from the back, we note the index till which the array would be decreasing
    int j = n - 1; // right part starting index
    while (j > 0 && arr[j - 1] <= arr[j]) {
      j--;
    }

    int res = j; // worst case that: all elements on the left are not in non-decreasing order
    // specially to handle this edge case: [5, 2, 1, 3, 4]
    //                                      i     j
    // res = j, means => j = 2 (index) => no. of elements on the left

    int i = 0; // left part ending index

    // while the left part is in increasing order
    // try to reduce the size of the subarray to be removed
    while (i < j && (i == 0 || arr[i - 1] <= arr[i])) {

      while (j < n && arr[i] > arr[j]) {
        j++;
      }

      // now, we have found the correct jth element such that arr[i] <= arr[j]
      // which would make the remaining array, i.e., [0, i-1] to [j+1, n-1]
      // in non-decreasing order

      // and we want to minimize the size of the subarray that we are trying to remove
      res = Math.min(res, j - i - 1);
      i++;
    }

    return res;
  }

  // ========================================
  // BRUTE FORCE - MY TRY
  public int findLengthOfShortestSubarray_MYTRY_BRUTE(int[] arr) {
    int n = arr.length;
    int mini = Integer.MAX_VALUE;
    int incrCnt = 0;

    for (int i = 0; i < n; i++) {
      if (i + 1 < n && arr[i] <= arr[i + 1])
        incrCnt++;

      for (int j = i; j < n; j++) {
        if (canBeRemoved(arr, i, j)) {
          // System.out.println(mini + " " + i + " " + j);
          mini = Math.min(mini, j - i + 1);
        }
      }
    }

    if (n - 2 >= 0 && (arr[n - 2] <= arr[n - 1]) || n == 1)
      incrCnt++;
      
    // System.out.println(incrCnt);
    return (incrCnt == n) ? 0 : mini;
  }

  private boolean canBeRemoved(int[] arr, int s, int e) {
    int prev = 0;
    for (int i = 0; i < arr.length; i++) {
      if (i >= s && i <= e) {
        continue;
      }
      if (arr[i] < prev) {
        return false;
      }

      prev = arr[i];
    }
    return true;
  }
}