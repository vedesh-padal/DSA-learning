// You are given an integer array nums and an integer k. Find the maximum subarray sum 
// of all the subarrays of nums that meet the following conditions:

// The length of the subarray is k, and
// All the elements of the subarray are distinct.
// Return the maximum subarray sum of all the subarrays that meet the conditions. 
// If no subarray meets the conditions, return 0.

// A subarray is a contiguous non-empty sequence of elements within an array.

// Example 1:
// Input: nums = [1,5,4,2,9,9,9], k = 3
// Output: 15
// Explanation: The subarrays of nums with length 3 are:
// - [1,5,4] which meets the requirements and has a sum of 10.
// - [5,4,2] which meets the requirements and has a sum of 11.
// - [4,2,9] which meets the requirements and has a sum of 15.
// - [2,9,9] which does not meet the requirements because the element 9 is repeated.
// - [9,9,9] which does not meet the requirements because the element 9 is repeated.
// We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions

// Example 2:
// Input: nums = [4,4,4], k = 3
// Output: 0
// Explanation: The subarrays of nums with length 3 are:
// - [4,4,4] which does not meet the requirements because the element 4 is repeated.
// We return 0 because no subarrays meet the conditions.

// Constraints:
// 1 <= k <= nums.length <= 105
// 1 <= nums[i] <= 105

import java.util.HashMap;

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long maxSum = 0;
        long sum = 0;

        // to keep track of the repeating elements in the window when expanding
        HashMap<Integer, Integer> hmap = new HashMap<>();

        // sliding window
        int l = 0;

        for (int r = 0; r < n; r++) {
            int num = nums[r];
            sum += num;
            // incrementing the count of the expansion member
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
            k--; // since we are expanding the window
            // so, k here will be representing more elements to check

            // if the new member is duplicate / already present previously in the window,
            // and till we have have exactly k elements for the window (decreasing window
            // size till k size window)
            // we remove the left part (shrinking)
            while (hmap.get(num) != 1 || k < 0) {
                sum -= nums[l];
                hmap.put(nums[l], hmap.get(nums[l]) - 1);
                k++;
                l++;
            }

            if (k == 0) {
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

}