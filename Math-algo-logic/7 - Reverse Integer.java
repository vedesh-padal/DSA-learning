// Given a signed 32-bit integer x, return x with its digits reversed. If 
// reversing x causes the value to go outside the signed 32-bit integer 
// range [-2^31, 2^31 - 1], then return 0.  ==> Important

// Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

// Example 1:
// Input: x = 123
// Output: 321

// Example 2:
// Input: x = -123
// Output: -321

// Example 3:
// Input: x = 120
// Output: 21

// Constraints:
// -231 <= x <= 231 - 1

class Solution {
  public int reverse(int x) {
    int res = 0;
    boolean isNegative = (x < 0);
    x = Math.abs(x);

    while (x > 0) {
      int lastDigit = x % 10;
      x /= 10;

      if (res > (Integer.MAX_VALUE - lastDigit) / 10)
        return 0;

      res = res * 10 + lastDigit;
    }
    return isNegative ? -res : res;
  }
}