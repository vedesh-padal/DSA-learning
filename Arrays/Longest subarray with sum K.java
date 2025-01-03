// MEDIUM
// arrays, hashmap

/*
    Given an array arr[] containing integers and an integer k, your task is to
    find the length of the longest subarray where the sum of its elements is
    equal to the given value k. It is guaranteed that a valid subarray exists.

    NOTE: HAS NEGATIVE NUMBERS

    Examples:
    Input: arr[] = [10, 5, 2, 7, 1, 9], k = 15
    Output: 4
    Explanation: The subarray [5, 2, 7, 1] has a sum of 15 and length 4.

    Input: arr[] = [-1, 2, -3], k = -2
    Output: 3
    Explanation: The subarray [-1, 2, -3] has a sum of -2 and length 3.

    Input: arr[] = [1, -1, 5, -2, 3], k = 3
    Output: 4
    Explanation: The subarray [1, -1, 5, -2] has a sum of 3 and a length 4.

    Constraints:
    1 ≤ arr.size() ≤ 10^6
    -10^9 ≤ arr[i], k ≤ 10^9
*/

// since there are -ve elements, we are bound to use hashmap, instead of 2-pointers

import java.util.HashMap;

class Solution {
    public int lenOfLongestSubarr(int[] arr, int k) {
        // code here
        int maxLen = 0;
        // prefixSum, index => this much prefixSum is found at what index
        HashMap<Integer, Integer> hmap = new HashMap<>();
        int prefixSum = 0;    // running prefix sum
        
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            
            if (prefixSum == k) {
                // actually Math.max is not required
                maxLen = Math.max(maxLen, i+1);
            }
            
            if (hmap.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - hmap.get(prefixSum - k));
            }
            
            // we only update if this prefix sum is not already present
            // becoz, if it is already present, then we will not be storing the farthest index
            if (!hmap.containsKey(prefixSum)) {
                hmap.put(prefixSum, i);
            }
        }
        return maxLen;
    }
}
