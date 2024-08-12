/*
  Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

  A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

  Example 1:

  Input: str1 = "abac", str2 = "cab"
  Output: "cabac"
  Explanation: 
  str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
  str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
  The answer provided is the shortest such string that satisfies these properties.
  Example 2:

  Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
  Output: "aaaaaaaa"
  

  Constraints:

  1 <= str1.length, str2.length <= 1000
  str1 and str2 consist of lowercase English letters.
*/


class Solution {
  public String shortestCommonSupersequence(String str1, String str2) {

    int n = str1.length(), m = str2.length();

    int[][] dp = new int[n + 1][m + 1];

    // base cases => first row and first col ==> filled with zeros
    for (int i = 0; i <= n; i++)
      dp[i][0] = 0;

    for (int j = 0; j <= m; j++)
      dp[0][j] = 0;

    // filling the DP table and finding out the Longest Common subsequence
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1))
          dp[i][j] = 1 + dp[i - 1][j - 1];
        else
          dp[i][j] = 0 + Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }

    int lenLCS = dp[n][m];
    System.out.println(lenLCS);

    StringBuilder ans = new StringBuilder("");
    int i = n, j = m;

    while (i > 0 && j > 0) {
      if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
        ans.append(str1.charAt(i - 1)); // then move diagonally
        i--;
        j--;
      } else if (dp[i - 1][j] > dp[i][j - 1]) {
        ans.append(str1.charAt(i - 1)); // move up, and add the left element
        i--;
      } else {
        ans.append(str2.charAt(j - 1)); // move left, and add the up element
        j--;
      }
    }

    while (i > 0) {
      ans.append(str1.charAt(i - 1));
      i--;
    }
    while (j > 0) {
      ans.append(str2.charAt(j - 1));
      j--;
    }
    System.out.println(ans);
    return ans.reverse().toString();
  }
}