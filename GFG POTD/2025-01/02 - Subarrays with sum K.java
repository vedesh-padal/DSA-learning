// MEDIUM
// hashmap, arrays

/*
    Given an unsorted array of integers, find the number of continuous subarrays
    having sum exactly equal to a given number k.

    Examples:
    Input: arr = [10, 2, -2, -20, 10], k = -10
    Output: 3
    Explaination: Subarrays: arr[0...3], arr[1...4], arr[3...4] have sum exactly equal to -10.

    Input: arr = [9, 4, 20, 3, 10, 5], k = 33
    Output: 2
    Explaination: Subarrays: arr[0...2], arr[2...4] have sum exactly equal to 33.

    Input: arr = [1, 3, 5], k = 0
    Output: 0
    Explaination: No subarray with 0 sum.

    Constraints:
    1 ≤ arr.size() ≤ 10^5
    -10^3 ≤ arr[i] ≤ 10^3
    -10^7 ≤ k ≤ 10^7
*/

import java.util.HashMap;

class Solution {
    public int countSubarrays(int arr[], int k) {
        // code here
        // since there are -ve numbers in the array, we are bound to use hashmap
        // and used to store the prefix sum and the count of that particular prefix sum
        
        HashMap<Integer, Integer> hmap = new HashMap<>();
        int n = arr.length;
        int prefixSum = 0;
        hmap.put(0, 1); // for the cases when prefixSum is directly equal to K
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            prefixSum += arr[i];
            
            if (hmap.containsKey(prefixSum - k)) {
                count += hmap.get(prefixSum - k);
            }
            
            hmap.put(prefixSum, hmap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}