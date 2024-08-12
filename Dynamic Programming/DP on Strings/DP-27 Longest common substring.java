import java.util.Arrays;

class Solution {
  public int longestCommonSubstr(String s1, String s2) {
    // code here
    int n = s1.length();
    int m = s2.length();

    int[][] dp = new int[n + 1][m + 1];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    for (int i = 0; i <= n; i++)
      dp[i][0] = 0;

    for (int j = 0; j <= m; j++)
      dp[0][j] = 0;

    int max = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
          max = Math.max(dp[i][j], max);
        } else {
          dp[i][j] = 0;
        }
      }
    }

    return max;

  }
}