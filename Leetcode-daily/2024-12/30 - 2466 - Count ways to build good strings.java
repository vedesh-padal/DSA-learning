// MEDIUM
// dynamic-programming

/*
    Given the integers zero, one, low, and high, we can construct a string by
    starting with an empty string, and then at each step perform either of the following:

    Append the character '0' zero times.
    Append the character '1' one times.
    This can be performed any number of times.

    A good string is a string constructed by the above process having a length between low and high (inclusive).

    Return the number of different good strings that can be constructed
    satisfying these properties. Since the answer can be large, return it modulo // 10^9 + 7.
    

    Example 1:
    Input: low = 3, high = 3, zero = 1, one = 1
    Output: 8
    Explanation: 
    One possible valid good string is "011". 
    It can be constructed as follows: "" -> "0" -> "01" -> "011". 
    All binary strings from "000" to "111" are good strings in this example.

    Example 2:
    Input: low = 2, high = 3, zero = 1, one = 2
    Output: 5
    Explanation: The good strings are "00", "11", "000", "110", and "011".
    
    Constraints:
    1 <= low <= high <= 10^5
    1 <= zero, one <= low
*/

class Solution {
    private static final int MOD = (int)(1e9 + 7);

    private int solve(int currLen, int low, int high, int zero, int one, int[] dp) {
        if (currLen > high)
            return 0;

        if (dp[currLen] != -1)
            return dp[currLen];

        long count = 0;
        if (currLen >= low)
            count = 1;

        // pick zero string
        count = (count + solve(currLen + zero, low, high, zero, one, dp)) % MOD;
        count = (count + solve(currLen + one, low, high, zero, one, dp)) % MOD;

        return dp[currLen] = (int)count;
    }

    public int countGoodStrings_TopDown(int low, int high, int zero, int one) {
        // dp[i] = no. of good strings of length i
        int[] dp = new int[high + 1];
        java.util.Arrays.fill(dp, -1);

        // appending string doesn't matter, we only care the length
        // currStringLen
        return solve(0, low, high, zero, one, dp);
    }

    // ==================================================
    // TABULATION APPROACH
    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        // here we are moving from 0 length to highest length,
        // can be done in other way also
        // however, in both cases, we init with 1
        dp[0] = 1;  // no. of strings of length 0

        // here, `i` represents the length of the curr. string
        for (int i = 1; i <= high; i++) {
            int pickZeroString = ((i - zero) >= 0) ? dp[i - zero] : 0;
            int pickOneString = ((i - one) >= 0) ? dp[i - one] : 0;

            dp[i] = (pickZeroString + pickOneString) % MOD;
        }

        // now we have just built the dp array
        // but, we need the no. of good strings => no. of strings of length in range: [low, high]
        int count = 0;
        for (int i = low; i <= high; i++) {
            count = (count + dp[i]) % MOD;
        }
        return count;
    }
}