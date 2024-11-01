// MEDIUM

// Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).

// Example 1:
// Input: x = 2.00000, n = 10
// Output: 1024.00000

// Example 2:
// Input: x = 2.10000, n = 3
// Output: 9.26100

// Example 3:
// Input: x = 2.00000, n = -2
// Output: 0.25000
// Explanation: 2-2 = 1/22 = 1/4 = 0.25

// Constraints:
// -100.0 < x < 100.0
// -2^31 <= n <= 2^31-1
// n is an integer.
// Either x is not zero or n > 0.
// -10^4 <= xn <= 10^4

class Solution {

  private double solve(double x, long n) {
    if (n == 0)
      return 1;

    if (n < 0)
      return solve(1 / x, -n);

    if (n % 2 == 0)
      return solve(x * x, n / 2);
    else
      return x * solve(x * x, (n - 1) / 2);
  }

  public double myPow(double x, int n) {
    // converting `n` to long and passing it
    // becoz, if n is INT_MIN
    // then for -ve number we are doing pow(1/x, -n);
    // here -n would result it INT_MAX + 1 , which is out of int bounds
    // so we convert to long

    return solve(x, (long) n);
  }
}