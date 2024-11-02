// arrays, hashmap

// Given an unsorted array arr and a number k which is smaller than size of the array. 
// Find if the array contains duplicates within k distance.

// Examples:
// Input: arr[] = [1, 2, 3, 4, 1, 2, 3, 4] and k = 3
// Output: false
// Explanation: All duplicates are more than k distance away.

// Input: arr[] = [1, 2, 3, 1, 4, 5] and k = 3
// Output: true
// Explanation: 1 is repeated at distance 3.

// Constraints:
// 1 ≤ arr.size() ≤ 106
// 1 ≤ k < arr.size()
// 1 ≤ arr[i] ≤ 105

import java.util.HashSet;

class Solution {
  public boolean checkDuplicatesWithinK(int[] arr, int k) {
      // your code
      HashSet<Integer> hset = new HashSet<>();
      
      for (int i = 0; i < arr.length; i++) {
          if (hset.contains(arr[i])) {
              return true;
          }
          hset.add(arr[i]);
          
          // I was mistaken to understand the question as:
          // within k distance from beginning of the array
          // BUT, it is actually k distance b/w two duplicates
          // remove (k + 1)th distant term
          if (i >= k) {
              hset.remove(arr[i-k]);
          }
      }
     
      return false;
  }
}