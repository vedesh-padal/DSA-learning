// EASY
// arrays, hashmap

/*
    Given an array arr of 0s and 1s. Find and return the length of the longest subarray with equal number of 0s and 1s.

    Examples:
    Input: arr[] = [1, 0, 1, 1, 1, 0, 0]
    Output: 6
    Explanation: arr[1...6] is the longest subarray with three 0s and three 1s.

    Input: arr[] = [0, 0, 1, 1, 0]
    Output: 4
    Explnation: arr[0...3] or arr[1...4] is the longest subarray with two 0s and two 1s.

    Input: arr[] = [0]
    Output: 0
    Explnation: There is no subarray with an equal number of 0s and 1s.

    Constraints:
    1 <= arr.size() <= 10^5
    0 <= arr[i] <= 1
*/

import java.util.HashMap;

class Solution {
    public int maxLen(int[] arr) {
        // Your code here
        // INTUITION:
        // considering 0 as -1, and 1 as 1
        // and finding the longest subarray with 0 sum is what this 
        // problem will become
        int maxi = 0;
        // prefix sum and at which index
        // we store only the left most index, to get max. subarray size
        HashMap<Integer, Integer> preInd = new HashMap<>();
        int preSum = 0;
        
        for (int r = 0; r < arr.length; r++) {
            preSum += (arr[r] == 0) ? -1 : 1;
            
            if (preSum == 0)
                maxi = Math.max(maxi, r + 1);
                
            if (preInd.containsKey(preSum)) {
                maxi = Math.max(maxi, r - preInd.get(preSum));
            }
            else {
                preInd.put(preSum, r);
            }
        }
        return maxi;
    }
}
