// MEDIUM
// bit-manipulation

// Given two integers a and b, return the sum of the two integers without using
// the operators + and -.

// Example 1:
// Input: a = 1, b = 2
// Output: 3

// Example 2:
// Input: a = 2, b = 3
// Output: 5

// Constraints:
// -1000 <= a, b <= 1000

class Solution {
  public int getSum(int a, int b) {
    // INTUITION:
    // sum without carry is equivalent to : a^b
    // sum with carry:
    // to find carry: (a & b) << 1
    // and now sum of a and b (a + b) = repeat the above operation
    // by assigning sum (a ^ b) to `a`, and carry (a&b << 1 to `b`)
    // till you find that carry is not 0

    while (b != 0) {
      int carry = (a & b) << 1;
      a = a ^ b;
      b = carry;
    }
    return a;
  }
}