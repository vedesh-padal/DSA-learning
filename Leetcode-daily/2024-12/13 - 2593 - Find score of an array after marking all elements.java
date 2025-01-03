// MEDIUM - EASY
// arrays, sorting, heap

// You are given an array nums consisting of positive integers.

// Starting with score = 0, apply the following algorithm:

// Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
// Add the value of the chosen integer to score.
// Mark the chosen element and its two adjacent elements if they exist.
// Repeat until all the array elements are marked.
// Return the score you get after applying the above algorithm.

// Example 1:
// Input: nums = [2,1,3,4,5,2]
// Output: 7
// Explanation: We mark the elements as follows:
// - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
// - 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
// - 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
// Our score is 1 + 2 + 4 = 7.

// Example 2:
// Input: nums = [2,3,5,1,3,2]
// Output: 5
// Explanation: We mark the elements as follows:
// - 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
// - 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, 
// so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
// - 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
// Our score is 1 + 2 + 2 = 5.
 
// Constraints:
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 106

import java.util.PriorityQueue;

class Solution {
    // super efficient approach: some kind of clever sliding window - YET TO UNDERSTAND - and try to come up with these type of solutions
    public long findScore_slidingWindow(int[] nums) {
        long res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            int start = i;
            while (i + 1 < nums.length && nums[i + 1] < nums[i]) {
                i++;
            }
            for (int j = i; j >= start; j -= 2) {
                res += nums[j];
            }
        }
        return res;
    }

    // ==================================================
    
    // not actually efficient one, straight forward approach
    // I wonder, why 2d array of nums with sorted, and doing some logic is efficient, as it also involved O(nlogn)
    public long findScore(int[] nums) {
        int n = nums.length;
        long score = 0;

        // { num, idx }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        boolean[] vis = new boolean[n];
        int marked = 0;

        while (!pq.isEmpty()) {
            // int num = pq.peek()[0];
            int ind = pq.peek()[1];
            pq.poll();
            if (vis[ind] == false) {
                vis[ind] = true;
                marked++;
                score += nums[ind];
                if (ind - 1 >= 0 && vis[ind - 1] == false) {
                    vis[ind - 1] = true;
                    marked++;
                }
                if (ind + 1 < n && vis[ind + 1] == false) {
                    vis[ind + 1] = true;
                    marked++;
                }
            }
            if (marked == n) {
                break;
            }
        }
        return score;

    }
}