// Given an integer n, break it into the sum of k positive integers, 
// where k >= 2, and maximize the product of those integers.

// Return the maximum product you can get.

// Example 1:
// Input: n = 2
// Output: 1
// Explanation: 2 = 1 + 1, 1 × 1 = 1.

// Example 2:
// Input: n = 10
// Output: 36
// Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.

// Constraints:
// 2 <= n <= 58

import java.util.Arrays;

class Solution {

    private int solve(int n, int[] dp) {
        if (n == 1)
            return 1;

        if (dp[n] != -1)
            return dp[n];

        int result = Integer.MIN_VALUE;

        for (int i = 1; i <= n - 1; i++) {
            // based on the tree diagram, able to get to this conclusion
            int product = i * Math.max(n - i, solve(n - i, dp));

            result = Math.max(result, product);
        }
        return dp[n] = result;
    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, dp);
    }
}