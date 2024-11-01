// MEDIUM
// dp, math

// There is only one character 'A' on the screen of a notepad. You can perform
// one of two operations on this notepad for each step:

// Copy All: You can copy all the characters present on the screen (a partial
// copy is not allowed).
// Paste: You can paste the characters which are copied last time.
// Given an integer n, return the minimum number of operations to get the
// character 'A' exactly n times on the screen.

// Example 1:
// Input: n = 3
// Output: 3
// Explanation: Initially, we have one character 'A'.
// In step 1, we use Copy All operation.
// In step 2, we use Paste operation to get 'AA'.
// In step 3, we use Paste operation to get 'AAA'.

// Example 2:
// Input: n = 1
// Output: 0

// Constraints:
// 1 <= n <= 1000

class Solution {

  int n;

  private int helper(int currLen, int pasteLen, int[][] dp)   {
      // we have reached n, i.e., we have all chars on notepad
      // so no more operation
      if (currLen == n)
          return 0;

      // we have exceeded the no. of A's needed, so we return a max. value
      // inorder for the upper function to consider minimum
      if (currLen > n)
          return 10000;

      // this will involve subproblems
      // so, check if the result was already calculated
      if (dp[currLen][pasteLen] != 0)
          return dp[currLen][pasteLen];

      // we have 2 options at each stage:
      // 1. Copy All + Paste:
      //      - just copy all option won't work as it will lead to 
      //        currLen and pasteLen remain same in each call downwards
      //      - So, we copy all and paste at the same time:
      //         for copy all => 1 operation, for paste => 1 operation
      //         so, in this option, Current Length is doubled, and pasteLen is currLen before doubling
      //          and we recursively call with these parameters
      int option1 = 1 + 1 + helper(currLen * 2, currLen, dp);

      // 2. Just Paste:
      //      1 operation => paste
      //      currLen increases by pasteLen, and pasteLen remains same
      //      and we recursively call with the above parameters
      int option2 = 1 + helper(currLen + pasteLen, pasteLen, dp);

      // we pick the minimum among the two operations above
      return dp[currLen][pasteLen] = Math.min(option1, option2);

  }

  public int minSteps(int n) {
      if (n == 1)
          return 0;

      this.n = n;

      // [i, j] :
      // i = curr. number of characters => atmost can be n
      // j = previous copy length => can be atmost n/2 
      // [ becoz, in the next step, we have curr. no. of chars = n/2 * 2]

      // and, to be safe, we add 1 to each dimension
      int[][] dp = new int[n + 1][n/2 + 1];

      // we are adding 1 prior becoz
      // we are copying the first char 'A' first
      // that is why we are passing: currNumChars = 1, and numClipChars = 1 (becoz copied)
      return 1 + helper(1, 1, dp);
  }
}