// 15-10-2024
// MEDIUM
// arrays, hashmap

// Given an unsorted array of integers arr[], and a target tar, determine the
// number of subarrays whose elements sum up to the target value.

// Examples:
// Input: arr[] = [10, 2, -2, -20, 10] , tar = -10
// Output: 3
// Explanation: Subarrays with sum -10 are: [10, 2, -2, -20], [2, -2, -20, 10]
// and [-20, 10].

// Input: arr[] = [1, 4, 20, 3, 10, 5] , tar = 33
// Output: 1
// Explanation: Subarray with sum 33 is: [20,3,10].

// Expected Time Complexity: O(n)
// Expected Auxilary Space: O(n)

// Constraints:
// 1 <= arr.size() <= 106
// -105 <= arr[i] <= 105
// -105 <= tar <= 105

import java.util.HashMap;

class Solution {
  // Function to count the number of subarrays which adds to the given sum.
  static int subArraySum(int arr[], int tar) {
    // stores sum count, as in, how many times that sum is occuring
    HashMap<Integer, Integer> hmap = new HashMap<>();
    int sum = 0;
    int cnt = 0;

    hmap.put(0, 1);

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];

      // check if there is a prefix sum tha when
      // subtracted gives us the target
      if (hmap.containsKey(sum - tar)) {
        cnt += hmap.get(sum - tar);
      }

      // store the currenet cummulative sum in the map
      hmap.put(sum, hmap.getOrDefault(sum, 0) + 1);
    }
    return cnt;
  }
}