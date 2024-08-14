import java.util.Arrays;

class Solution {

  private static int canWildCardMatch1(String s1, String s2, int i, int j, int[][] dp) {

    // s1 and s2 both are exhausted
    // both strings checking is complete, so return true => means they matched
    // we are returning true, because in any of the previous recursion calls, there
    // hasn't been a single case
    // where chars in both strings did not match
    if (i < 0 && j < 0) {
      return 1; // true
    }

    // s1 alone is exhausted
    // there are still chars left in s2
    if (i < 0 && j >= 0)
      return 0; // false

    // s2 alone is exhausted
    if (j < 0 && i >= 0) {
      // if s1 is left, it has to be all *, otherwise it would be false
      for (int k = 0; k <= i; k++) {
        if (s1.charAt(k) != '*')
          return 0; // false
      }
      return 1; // true
    }

    if (dp[i][j] != -1)
      return dp[i][j];

    if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?')
      return dp[i][j] = canWildCardMatch1(s1, s2, i - 1, j - 1, dp);

    else if (s1.charAt(i) == '*') {
      // you have two cases:
      // 1. either * is considered as of 0 length => which will lead to the normal
      // case without * char in s1
      // 2. * is still in s1, but s2 is shrinked by 1 index
      return dp[i][j] = (canWildCardMatch1(s1, s2, i - 1, j, dp) == 1 || canWildCardMatch1(s1, s2, i, j - 1, dp) == 1)
          ? 1
          : 0;
    } else
      return dp[i][j] = 0; // char at i and j did not match => so return false [ false = 0 in dp table ]
  }

  public boolean isMatch1(String s, String p) {

    int n = p.length();
    int m = s.length();

    int[][] dp = new int[n][m];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return (canWildCardMatch1(p, s, n - 1, m - 1, dp) == 1);
  }

  // ----------------------------------------------------------
  // based on 0 based indexing - MEMOIZATION
  private static int canWildCardMatch2(String s1, String s2, int i, int j, int[][] dp) {
    if (i == 0 && j == 0)
      return 1;
    if (i == 0 && j > 0)
      return 0;
    if (j == 0 && i > 0) {
      for (int k = 1; k <= i; k++) {
        if (s1.charAt(k - 1) != '*')
          return 0;
      }
      return 1;
    }

    if (dp[i][j] != -1)
      return dp[i][j];

    if (s1.charAt(i - 1) == s2.charAt(j - 1) || s1.charAt(i - 1) == '?')
      return dp[i][j] = canWildCardMatch2(s1, s2, i - 1, j - 1, dp);

    else if (s1.charAt(i - 1) == '*') {
      return dp[i][j] = (canWildCardMatch2(s1, s2, i - 1, j, dp) == 1 || canWildCardMatch2(s1, s2, i, j - 1, dp) == 1)
          ? 1
          : 0;
    } else
      return dp[i][j] = 0;

  }

  public boolean isMatch2(String s, String p) {

    int n = p.length();
    int m = s.length();

    int[][] dp = new int[n + 1][m + 1];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return (canWildCardMatch2(p, s, n, m, dp) == 1);
  }

  // ---------------------------------------------------
  // //// TABULATION METHOD without space optimization
  public boolean isMatch3(String s, String p) {

    int n = p.length();
    int m = s.length();

    boolean[][] dp = new boolean[n + 1][m + 1];

    dp[0][0] = true;

    for (int j = 1; j <= m; j++)
      dp[0][j] = false;

    // make sure you are writing this type of for loop inside the actual outer
    // for-loop in the actual traversal,
    // to properly assign the base case - during each row traversal
    for (int i = 1; i <= n; i++) {
      boolean flag = true;
      for (int k = 1; k <= i; k++) {
        if (p.charAt(k - 1) != '*') {
          flag = false;
          break; // since once, it is found, we check for next index
        }
      }
      dp[i][0] = flag;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?')
          dp[i][j] = dp[i - 1][j - 1];

        else if (p.charAt(i - 1) == '*')
          dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
        else
          dp[i][j] = false;
      }
    }
    return dp[n][m];
  }
}