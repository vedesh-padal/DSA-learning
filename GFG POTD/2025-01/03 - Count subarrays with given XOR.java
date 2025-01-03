// MEDIUM - EASY
// arrays, hashmap

// Given an array of integers arr[] and a number k, count the number of subarrays having XOR of their elements as k.
// Examples: 
// Input: arr[] = [4, 2, 2, 6, 4], k = 6
// Output: 4
// Explanation: The subarrays having XOR of their elements as 6 are [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], and [6]. Hence, the answer is 4.

// Input: arr[] = [5, 6, 7, 8, 9], k = 5
// Output: 2
// Explanation: The subarrays having XOR of their elements as 5 are [5] and [5, 6, 7, 8, 9]. Hence, the answer is 2.

// Constraints:
// 1 ≤ arr.size() ≤ 10^5
// 0 ≤ arr[i] ≤10^5
// 0 ≤ k ≤ 10^5

import java.util.HashMap;

class Solution {
    public long subarrayXor(int arr[], int k) {
        // code here
        long cnt = 0;
        int xor = 0;
        int n = arr.length;
        // XOR value, count
        HashMap<Integer, Long> hmap = new HashMap<>();
        hmap.put(0, (long)1); // for the case when the current running xor is directly giving our required xor
        
        for (int i = 0; i < n; i++) {
            xor = xor ^ arr[i];
            int prevPartXor = xor ^ k;
            if (hmap.containsKey(prevPartXor)) {
                cnt += hmap.get(prevPartXor);
            }
            hmap.put(xor, hmap.getOrDefault(xor, (long)0) + (long)1);
        }
        return cnt;
    }
}