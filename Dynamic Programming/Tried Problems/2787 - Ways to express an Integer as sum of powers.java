// MEDIUM
// dynamic-programming

/*
    Given two positive integers n and x.

    Return the number of ways n can be expressed as the sum of the xth power of
    unique positive integers, in other words, the number of sets of unique
    integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.

    Since the result can be very large, return it modulo 109 + 7.

    For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.

    Example 1:
    Input: n = 10, x = 2
    Output: 1
    Explanation: We can express n as the following: n = 32 + 12 = 10.
    It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.

    Example 2:
    Input: n = 4, x = 1
    Output: 2
    Explanation: We can express n in the following ways:
    - n = 4^1 = 4.
    - n = 3^1 + 1^1 = 4.

    Constraints:
    1 <= n <= 300
    1 <= x <= 5
*/

class Solution {
    int[][] dp;
    private static final int MOD = (int)(1e9 + 7);

    private int solve(long currSum, int currNum, int x, int target) {

        if (currSum > target)
            return 0;
            
        if (currSum == target)
            return 1;

        if (currNum > target)
            return 0;

        if (currSum < dp.length && dp[(int)currSum][currNum] != -1)
            return dp[(int)currSum][currNum];

        long t = (long)Math.pow(currNum, x);

        int pick = solve(currSum + t, currNum + 1, x, target);
        int notPick = solve(currSum, currNum + 1, x, target);

        return dp[(int)currSum][currNum] = (pick % MOD + notPick % MOD) % MOD;
    }

    public int numberOfWays(int n, int x) {
        dp = new int[n + 1][n + 1];
        for (int[] row: dp)
            java.util.Arrays.fill(row, -1);

        return solve(0, 1, x, n);
    }
}