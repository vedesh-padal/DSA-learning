// MEDIUM - EASY
// string, two-pointer, stack, greedy

// You are given a 0-indexed string s of even length n. The string consists of
// exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.

// A string is called balanced if and only if:

// It is the empty string, or
// It can be written as AB, where both A and B are balanced strings, or
// It can be written as [C], where C is a balanced string.
// You may swap the brackets at any two indices any number of times.

// Return the minimum number of swaps to make s balanced.

// Example 1:
// Input: s = "][]["
// Output: 1
// Explanation: You can make the string balanced by swapping index 0 with index
// 3.
// The resulting string is "[[]]".

// Example 2:
// Input: s = "]]][[["
// Output: 2
// Explanation: You can do the following to make the string balanced:
// - Swap index 0 with index 4. s = "[]][][".
// - Swap index 1 with index 5. s = "[[][]]".
// The resulting string is "[[][]]".

// Example 3:
// Input: s = "[]"
// Output: 0
// Explanation: The string is already balanced.

// Constraints:
// n == s.length
// 2 <= n <= 106
// n is even.
// s[i] is either '[' or ']'.
// The number of opening brackets '[' equals n / 2, and the number of closing
// brackets ']' equals n / 2.

class Solution {
  // this is more intutitve, COUNTING closing and opening brackets
  // shorter greedy code in solutions was there, but didnt understand
  
  // this is solved based on the hints
  public int minSwaps(String s) {
    int n = s.length();
    int l = 0, r = n - 1;

    int cntO = 0, cntC = 0;
    int swaps = 0;
    while (l < r) {
      if (s.charAt(l) == '[')
        cntO++;
      else
        cntC++;

      if (cntC > cntO) {
        while (s.charAt(r) == ']' && s.charAt(r - 1) == '[')
          r -= 2;

        // once you have found the non-balanced opening bracket
        // from the end of the string
        // swap it with the left one [ here we are just incrementing and decrementing
        // counts]
        // char ch = s.charAt(l);
        // s.c
        swaps++;
        r--;

        // actually after swapping r+1 would have become ], but since we are
        // not actually swapping the chars, we are comoparing it with [
        if (s.charAt(r) == '[' && s.charAt(r + 1) == '[')
          r--;

        cntC--;
        cntO++;
      }
      l++;
    }
    return swaps;
  }
}