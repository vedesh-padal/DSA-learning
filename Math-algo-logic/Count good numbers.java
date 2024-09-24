/*
 * A digit string is good if the digits (0-indexed) at even indices are even and
 * the digits at odd indices are prime (2, 3, 5, or 7).
 * 
 * For example, "2582" is good because the digits (2 and 8) at even positions
 * are even and the digits (5 and 2) at odd positions are prime. However, "3245"
 * is not good because 3 is at an even index but is not even.
 * Given an integer n, return the total number of good digit strings of length
 * n. Since the answer may be large, return it modulo 109 + 7.
 * 
 * A digit string is a string consisting of digits 0 through 9 that may contain
 * leading zeros.
 * 
 * Example 1:
 * Input: n = 1
 * Output: 5
 * Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".

 * Example 2:
 * Input: n = 4
 * Output: 400

 * Example 3:
 * Input: n = 50
 * Output: 564908303
 * 
 * Constraints:
 * 1 <= n <= 1015
 */

class Solution {

  public int MOD = (int)(1e9 + 7);

  // efficient way of writing power function (using recursion: O(logN))
  private long countGoodNumbers(int base, int pow)  {
    if (pow == 0)
      return 1; // x^0 = 1;

    long ans = countGoodNumbers(base, pow / 2);
    ans = (ans * ans) % MOD;  // x^(y/2) * x^(y/2) = x^y

    // if power is odd, we will have to multiply with base once again
    if (pow % 2 == 1) {
      ans = (ans * base) % MOD;
    }
    
    return ans;
  }

  public int countGoodNumbers(int n)  {
    int numOddIndices = n / 2;
    int numEvenIndices = n / 2 + n % 2;

    // acc. to question, num of possible digits at even indices: 5 (0, 2, 4, 6, 8) [ even numbers ]
    // and, num of possible digits at odd indices: 4 (2, 3, 5, 7) [ prime numbers ]

    // we are multiplying, becoz we want it for all places
    return (int)(countGoodNumbers(5, numEvenIndices) * countGoodNumbers(4, numOddIndices) % MOD);
  }
}