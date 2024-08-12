/*
  Given a string s. In one step you can insert any character at any index of the string.

  Return the minimum number of steps to make s palindrome.

  A Palindrome String is one that reads the same backward as well as forward.

  Example 1:

  Input: s = "zzazz"
  Output: 0
  Explanation: The string "zzazz" is already palindrome we do not need any insertions.
  Example 2:

  Input: s = "mbadm"
  Output: 2
  Explanation: String can be "mbdadbm" or "mdbabdm".
  Example 3:

  Input: s = "leetcode"
  Output: 5
  Explanation: Inserting 5 characters the string becomes "leetcodocteel".
*/


class Solution {

  private static int findLenOfLongPalinSequence(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();

    int[] prev = new int[m + 1];

    for (int j = 0; j <= m; j++)
      prev[j] = 0;

    int max = 0;

    for (int i = 1; i <= n; i++) {
      int[] curr = new int[m + 1];
      for (int j = 1; j <= m; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          curr[j] = 1 + prev[j - 1];
          max = Math.max(curr[j], max);
        } else
          curr[j] = 0 + Math.max(prev[j], curr[j - 1]);
      }
      prev = curr;
    }
    return max;
  }

  public int minInsertions(String s) {
    // mininmum no. of insertions = length of the given string - (length of the longest palindrome subsequence)

    // finding length of the longest palindrome sequence

    int lenOfLongestPalinSeq = findLenOfLongPalinSequence(s, new StringBuilder(s).reverse().toString());

    return s.length() - lenOfLongestPalinSeq;
  }
}