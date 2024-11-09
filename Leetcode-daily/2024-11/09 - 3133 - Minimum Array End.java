// MEDIUM
// bit-manipulation

// You are given two integers n and x. You have to construct an array of
// positive integers nums of size n where for every 0 <= i < n - 1, nums[i + 1]
// is greater than nums[i], and the result of the bitwise AND operation between
// all elements of nums is x.

// Return the minimum possible value of nums[n - 1].

// Example 1:
// Input: n = 3, x = 4
// Output: 6
// Explanation:
// nums can be [4,5,6] and its last element is 6.

// Example 2:
// Input: n = 2, x = 7
// Output: 15

// Explanation:
// nums can be [7,15] and its last element is 15.

// Constraints:
// 1 <= n, x <= 108

class Solution {
  public long minEnd(int n, int x) {

    // // MY TRY => gives TLE => 656/765
    // long mini = Long.MAX_VALUE;

    // long mask = x;
    // int cnt = 1; // x itself is included
    // // System.out.println(mask + "\n\n");

    // long curr = x + 1;

    // while (cnt != n) {
    //   if ((curr & mask) == mask) {
    //     System.out.println(curr);
    //     cnt++;
    //   }
    //   curr++;
    //   // System.out.println(curr);
    // }

    // return curr-1;

    // ============================================

    // this works fine even though brute force
    long res = x;
    for (int i = 1; i < n; i++) {
      // we take the current number and increment it. But after incrementing,
      // we force the new number to keep the bit pattern of x by applying a
      // bitwise OR with x. This ensures that no bits from x are lost in
      // the process, and we continue this until the last element is constructed.

      // ex: if x = 5 => 101 => 101 + 1 => 110 => but violates the AND condition
      // so, we OR it with x, as mentioned above
      // => 110 | 101 => 111
      res = (res + 1) | x;
    }

    return res;
  }

  // even efficient solution exists
  // will understand later, kind of tricky
}