import java.util.Arrays;

class Solution {

  // Memomoized Solution
  private static int findLCS1(int ind1, int ind2, String s1, String s2, int[][] dp) {
    if (ind1 < 0 || ind2 < 0)
      return 0;

    if (dp[ind1][ind2] != -1)
      return dp[ind1][ind2];

    if (s1.charAt(ind1) == s2.charAt(ind2))
      return 1 + findLCS1(ind1 - 1, ind2 - 1, s1, s2, dp);

    return dp[ind1][ind2] = 0 + Math.max(findLCS1(ind1 - 1, ind2, s1, s2, dp), findLCS1(ind1, ind2 - 1, s1, s2, dp));

  }

  public int longestCommonSubsequence1(String text1, String text2) {
    int n = text1.length(), m = text2.length();
    int[][] dp = new int[n][m];

    for (int[] row : dp)
      Arrays.fill(row, -1);

    return findLCS1(n - 1, m - 1, text1, text2, dp);

  }

  // ----------------------------------------------
  // RIGHT SHIFT OF INDICES BY 1
  // Right shift of indices by 1 won't change the answer, but this approach is
  // required for the Tabulation method
  // becoz, we can't represent -ve indices in the base case of tabulation method
  private static int findLCS(int ind1, int ind2, String s1, String s2, int[][] dp) {
    if (ind1 == 0 || ind2 == 0)
      return 0;

    if (dp[ind1][ind2] != -1)
      return dp[ind1][ind2];

    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
      return 1 + findLCS(ind1 - 1, ind2 - 1, s1, s2, dp);

    return dp[ind1][ind2] = 0 + Math.max(findLCS(ind1 - 1, ind2, s1, s2, dp), findLCS(ind1, ind2 - 1, s1, s2, dp));

  }

  public int longestCommonSubsequence2(String text1, String text2) {
    int n = text1.length(), m = text2.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int[] row : dp)
      Arrays.fill(row, -1);

    return findLCS(n, m, text1, text2, dp);

  }

  // ----------------------------------------------
  // Tabulation Approach
  public int longestCommonSubsequence3(String text1, String text2) {
    int n = text1.length(), m = text2.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int j = 0; j <= m; j++)
      dp[0][j] = 0;

    for (int i = 0; i <= n; i++)
      dp[i][0] = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = 0 + Math.max(dp[i - 1][j], dp[i][j - 1]);
        }

      }
    }
    return dp[n][m];
  }

  // -----------------------------------------------------------------------
  // Tabulation with space optimization
  public int longestCommonSubsequence4(String text1, String text2) {
    int n = text1.length(), m = text2.length();
    int[] prev = new int[m + 1];

    // set whole first row as 0
    for (int j = 0; j <= m; j++)
      prev[j] = 0;

    for (int i = 1; i <= n; i++) {
      int[] curr = new int[m + 1];
      for (int j = 1; j <= m; j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          curr[j] = 1 + prev[j - 1];
        } else {
          curr[j] = 0 + Math.max(prev[j], curr[j - 1]);
        }
      }
      prev = curr;
    }
    return prev[m];
  }
}