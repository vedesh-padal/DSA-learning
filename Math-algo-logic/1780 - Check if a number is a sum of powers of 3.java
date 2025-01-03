// MEDIUM
// math, recursion

// Given an integer n, return true if it is possible to represent n as the 
// sum of distinct powers of three. Otherwise, return false.

// An integer y is a power of three if there exists an integer x such that y == 3x.

// Example 1:
// Input: n = 12
// Output: true
// Explanation: 12 = 31 + 32

// Example 2:
// Input: n = 91
// Output: true
// Explanation: 91 = 30 + 32 + 34

// Example 3:
// Input: n = 21
// Output: false
 
// Constraints:
// 1 <= n <= 107

class Solution {

    private boolean solve(int n, int pow) {
        if (n == 0)
            return true;
        if (n < pow)
            return false;
        
        return solve(n, pow*3) || solve(n - pow, pow*3);
    }

    // with Recursion
    public boolean checkPowersOfThree(int n) {
        return solve(n, 1);
    }

    // ================================================================
    // Pure Math
    public boolean checkPowersOfThree_math(int n) {

        // to check if `n` can be represented as sum of distinct powers of 3
        // that means, number should be expressible in base-3, instead of base-2
        // i.e.,                 ...  3^4 3^3 3^2 3^1 3^0
        // 113 (81 + 27 + 3 + 1) =     1   1   0   1   1

        // with only digits 0 or 1, since 2 would need a second instance of a power of 3, which is not allowed

        // we keep dividing n by 3, until 0
        // and check if the remainder when divided by 3 is 2, if yes, then it is not possible
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n = n/3;
        }
        return true;
    }
}