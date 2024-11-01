// 30-10-2024
// EASY
// adobe

// Given an array arr[] of positive integers. Find the number of pairs of integers whose difference equals a given number k.
// Note: (a, b) and (b, a) are considered the same. Also, the same numbers at different indices are considered different.

// Examples:

// Input: arr[] = [1, 5, 3, 4, 2], k = 3
// Output: 2
// Explanation: There are 2 pairs with difference 3,the pairs are {1, 4} and {5, 2} 

// Input: arr[] = [8, 12, 16, 4, 0, 20], k = 4
// Output: 5
// Explanation: There are 5 pairs with difference 4, the pairs are {0, 4}, {4, 8}, {8, 12}, {12, 16} and {16, 20}.

// Constraints:
// 1 <= arr.size() <= 106
// 1 <= k <= 106
// 0 <= arr[i] <= 106


import java.util.HashMap;

class Solution {
  int countPairsWithDiffK(int[] arr, int k) {
    // code here
    // Set<Integer> hset = new HashSet<>();
    HashMap<Integer, Integer> hmap = new HashMap<>(); // incase of duplicates, we
    // also need to track the count

    // given: a - b = k
    int cnt = 0;

    for (int num : arr) {
      // a = k + b
      // b = a - k
      if (hmap.containsKey(num + k)) {
        cnt += hmap.get(num + k);
      }
      if (hmap.containsKey(num - k)) {
        cnt += hmap.get(num - k);
      }

      hmap.put(num, hmap.getOrDefault(num, 0) + 1);
    }
    return cnt;
  }
}
