/*
  You are given a string s. You can convert s to a palindrome
  by adding characters in front of it.

  Return the shortest palindrome you can find by performing this transformation.

  Example 1:
  Input: s = "aacecaaa"
  Output: "aaacecaaa"

  Example 2:
  Input: s = "abcd"
  Output: "dcbabcd"
  
  Constraints:
  0 <= s.length <= 5 * 104
  s consists of lowercase English letters only.
*/


class Solution {
  public String shortestPalindrome(String s) {
    StringBuilder res = new StringBuilder();
    int n = s.length();
    int j = 0, end = n;
    while (true) {
      j = 0;
      for (int i = end - 1; i >= 0; i--) {
        if (s.charAt(i) == s.charAt(j))
          j++;
      }
      if (j == end)
        break;

      end = j;
    }
    res.append(s.substring(end, n)).reverse().append(s.substring(0, end)).append(s.substring(end, n));

    return res.toString();
  }
}

// can be further optimized using Rabin Karp Algo, but I am yet to learn it