// HARD
// arrays, dynamic-programming, sliding-window

/*
    Given an integer array nums and an integer k, find three non-overlapping
    subarrays of length k with maximum sum and return them.

    Return the result as a list of indices representing the starting position of
    each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

    Example 1:
    Input: nums = [1,2,1,2,6,7,5,1], k = 2
    Output: [0,3,5]
    Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting
    indices [0, 3, 5].
    We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.

    Example 2:
    Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
    Output: [0,2,4]

    Constraints:
    1 <= nums.length <= 2 * 104
    1 <= nums[i] < 216
    1 <= k <= floor(nums.length / 3)
*/
import java.util.*;

// APPRAOCH from codestorywithMIK
class Solution {

    int[][] memo;

    int helper(int[] subArrSum, int count, int i, int k) {
        if (count == 0) {
            return 0;
        }

        if (i >= subArrSum.length) {
            return Integer.MIN_VALUE;
        }

        if (memo[i][count] != -1) {
            return memo[i][count];
        }

        int take = subArrSum[i] + helper(subArrSum, count-1, i+k, k);
        int notTake = helper(subArrSum, count, i+1, k);

        return memo[i][count] = Math.max(take, notTake);
    }

    // we can't memoize this, becoz here, `res` is changing, so we choose to memoize helper function
    // becoz there, only 2 vars are changing
    void solve(int[] subArrSum, int count, int i, int k, List<Integer> res) {
        if (count == 0)
            return;
        
        if (i >= subArrSum.length)
            return;

        int takeI = subArrSum[i] + helper(subArrSum, count-1, i+k, k);
        int notTakeI = helper(subArrSum, count, i+1, k);

        // taking index i is resulting in greater sum compared to not taking index i, and applying recursively
        if (takeI >= notTakeI) {
            res.add(i);
            // next, take another subarray
            solve(subArrSum, count-1, i+k, k, res);
        }
        else {
            solve(subArrSum, count, i+1, k, res);
        }
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // we can have (n - k + 1) start positions of subarrays of size k
        int n = nums.length;
        int[] subArrSum = new int[n - k + 1];
        
        // sliding window to calculate the subarray sums of size k
        int i = 0, j = 0;
        int windowSum = 0;
        while (j < n) {
            windowSum += nums[j];
            if ((j - i + 1) == k) {
                subArrSum[i] = windowSum;
                windowSum -= nums[i];
                i++;
            }
            j++;
        }

        // largest sum possible starting from index i with j subarrays remaining
        memo = new int[n][4];
        for (int[] row: memo)
            Arrays.fill(row, -1);

        List<Integer> res = new ArrayList<>();

        solve(subArrSum, 3, 0, k, res);
        
        // return res.stream().mapToInt(p -> p).toArray();
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}