/*
  LC - 76 - HARD
  Given two strings s and t of lengths m and n respectively, 
  return the minimum window substring of s such that every character in t (including duplicates) 
  is included in the window. If there is no such substring, return the empty string "".

  The testcases will be generated such that the answer is unique.

  Example 1:
  Input: s = "ADOBECODEBANC", t = "ABC"
  Output: "BANC"
  Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

  Example 2:
  Input: s = "a", t = "a"
  Output: "a"
  Explanation: The entire string s is the minimum window.

  Example 3:
  Input: s = "a", t = "aa"
  Output: ""
  Explanation: Both 'a's from t must be included in the window.
  Since the largest window of s only has one 'a', return empty string.
  

  Constraints:
  m == s.length
  n == t.length
  1 <= m, n <= 105
  s and t consist of uppercase and lowercase English letters.
  
  Follow up: Could you find an algorithm that runs in O(m + n) time?
*/


class Solution {
  // sliding window approach
  public String minWindow(String s, String t) {
    int n = s.length();
    int m = t.length();

    if (n < m)
      return "";

    int[] hash = new int[256];

    for (int i = 0; i < m; i++)
      hash[t.charAt(i)]++;

    int l = 0, r = 0;
    int minLen = Integer.MAX_VALUE, startIndex = -1;
    int cnt = 0;

    while (r < n) {
      if (hash[s.charAt(r)] > 0)
        cnt++;

      hash[s.charAt(r)]--;

      while (cnt == m) {
        if ((r - l + 1) < minLen) {
          minLen = (r - l + 1);
          startIndex = l;
        }

        hash[s.charAt(l)]++;

        if (hash[s.charAt(l)] > 0)
          cnt = cnt - 1;

        l++;
      }
      r++;
    }
    return (minLen == Integer.MAX_VALUE) ? "" : s.substring(startIndex, startIndex + minLen);
  }

  // naive solution O(n^2)
  // not working exactly, failing for few test cases - have to debug
  public String minWindow2(String s, String t) {
    int n = s.length();
    int m = t.length();
    int startIndex = -1, minLen = (int) (1e8);

    for (int i = 0; i < n; i++) {
      int[] hash = new int[256];
      int cnt = 0;

      for (int j = 0; j < m; j++)
        hash[t.charAt(j)]++;

      for (int j = i; j < n; j++) {
        if (hash[s.charAt(j)] > 0)
          cnt++;
        hash[s.charAt(j)]--;
        if (cnt == m) {
          minLen = Math.min((j - i + 1), minLen);
          startIndex = i;
          break;
        }
      }
    }
    return (minLen == (int) (1e8)) ? "" : s.substring(startIndex, startIndex + minLen);
  }

}