// MEDIUM

// Given two integers dividend and divisor, divide two integers without using
// multiplication, division, and mod operator.

// The integer division should truncate toward zero, which means losing its
// fractional part. For example, 8.345 would be truncated to 8, and -2.7335
// would be truncated to -2.

// Return the quotient after dividing dividend by divisor.

// Note: Assume we are dealing with an environment that could only store
// integers within the 32-bit signed integer range: [−231, 231 − 1]. For this
// problem, if the quotient is strictly greater than 231 - 1, then return 231 -
// 1, and if the quotient is strictly less than -231, then return -231.

// Example 1:
// Input: dividend = 10, divisor = 3
// Output: 3
// Explanation: 10/3 = 3.33333.. which is truncated to 3.

// Example 2:
// Input: dividend = 7, divisor = -3
// Output: -2
// Explanation: 7/-3 = -2.33333.. which is truncated to -2.

// Constraints:
// -2^31 <= dividend, divisor <= 2^31 - 1
// divisor != 0


class Solution {
  // explanation in notes
  public int divide(int dividend, int divisor) {
      // problematic
      // corner case: -2^31 / 1
      // IF THIS CONDITION IS NOT PUT,
      // we will be returning 0
      if (dividend == Integer.MIN_VALUE && divisor == -1)
          return Integer.MAX_VALUE;

      if (dividend == divisor)
          return 1;

      // boolean sign = true;    // initially assuming +ve number
      
      // if (dividend >= 0 && divisor < 0)
      //     sign = false;
      // if (dividend < 0 && divisor > 0)
      //     sign = false;


      // athi sundar
      // Determine the sign of the result
      boolean negative = (dividend < 0) ^ (divisor < 0);  // XOR to determine if signs are different

      // since told that Q, d can be out of int range
      long n = Math.abs((long)dividend);
      long d = Math.abs((long)divisor);

      long ans = 0;    // quotient

      while (n >= d)  {
          int cnt = 0;
          while ( n >= ( d << (cnt + 1) ) ) 
              cnt++;

          // here (1 << cnt)  represents no. of times it was divided
          // we try to pick the largest 2's power first
          ans = ans + (1L << cnt);

          n = n - ( d << cnt );   // removing the largest dividable number by d from n
      }

      // now we check the sign and assign value with sign
      ans = negative ? -ans : ans;

      // if out of int bounds
      if (ans > Integer.MAX_VALUE)
          return Integer.MAX_VALUE;
      
      if (ans < Integer.MIN_VALUE)
          return Integer.MIN_VALUE;
      
      return (int) ans;
  }
}