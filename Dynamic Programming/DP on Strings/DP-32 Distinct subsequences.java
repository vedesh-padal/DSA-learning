import java.util.Arrays;

class Solution {

  ////// THIS APPROACH SEEMED BETTER WHEN SCORING ON LEETCODE - beating most
  ////// users, but not sure
  // Memoized appproach -- 0 based indexing
  private static int findDistinctSubSeq1(String s1, String s2, int ind1, int ind2, int[][] dp) {

    // we have matched all chars of s2 with chars of s2, so we return 1
    if (ind2 < 0)
      return 1;

    // we have checked all chars of s1, but we are not able
    // to match all chars of s2, therefore return 0
    if (ind1 < 0)
      return 0;

    if (dp[ind1][ind2] != -1)
      return dp[ind1][ind2];

    if (s1.charAt(ind1) == s2.charAt(ind2)) {
      int leaveOne = findDistinctSubSeq1(s1, s2, ind1 - 1, ind2 - 1, dp);
      int stay = findDistinctSubSeq1(s1, s2, ind1 - 1, ind2, dp);

      return dp[ind1][ind2] = (leaveOne + stay);
    } else
      return dp[ind1][ind2] = findDistinctSubSeq1(s1, s2, ind1 - 1, ind2, dp);

  }

  public int numDistinct1(String s, String t) {
    int n = s.length(), m = t.length();
    int[][] dp = new int[n][m];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return findDistinctSubSeq1(s, t, n - 1, m - 1, dp);
  }

  // ------------------------------------------------------------------------
  // Memoized appproach -- 1-based indexing
  private static int findDistinctSubSeq2(String s1, String s2, int ind1, int ind2, int[][] dp) {

    // we have matched all chars of s2 with chars of s2, so we return 1
    if (ind2 == 0)
      return 1;

    // we have checked all chars of s1, but we are not able
    // to match all chars of s2, therefore return 0
    if (ind1 == 0)
      return 0;

    if (dp[ind1][ind2] != -1)
      return dp[ind1][ind2];

    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
      int leaveOne = findDistinctSubSeq2(s1, s2, ind1 - 1, ind2 - 1, dp);
      int stay = findDistinctSubSeq2(s1, s2, ind1 - 1, ind2, dp);

      return dp[ind1][ind2] = (leaveOne + stay);
    } else
      return dp[ind1][ind2] = findDistinctSubSeq2(s1, s2, ind1 - 1, ind2, dp);

  }

  public int numDistinct2(String s, String t) {
    int n = s.length(), m = t.length();
    int[][] dp = new int[n + 1][m + 1];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return findDistinctSubSeq2(s, t, n, m, dp);
  }

  // -----------------------------------------------------------------------
  // Tabulation approach

  public int numDistinct3(String s, String t) {
    int n = s.length(), m = t.length();
    int[][] dp = new int[n + 1][m + 1]; // in order to deal with -ve indices (that was written in the recursive solution)

    // initialize first column with 1, because there's one empty subsequence in a
    // string
    for (int i = 0; i <= n; i++)
      dp[i][0] = 1;

    // initialize the first row with zero (except dp[0][0]) without this we don't
    // have the string to compare j with i
    for (int j = 1; j <= m; j++)
      dp[0][j] = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        int leaveOne = dp[i - 1][j - 1];
        int stay = dp[i - 1][j];

        // comparing with i-1 and j-1
        // because for tabulation we take 1-based indexing to avoid finding negative
        // indices or table/array
        if (s.charAt(i - 1) == t.charAt(j - 1))
          dp[i][j] = leaveOne + stay;
        else
          dp[i][j] = stay;
      }
    }
    return dp[n][m];
  }

  // -------------------------------------
  // space optimized tabulation -- this seems as good as Memoized solution
  public int numDistinct4(String s, String t) {
    int n = s.length(), m = t.length();

    int[] prev = new int[m + 1];
    prev[0] = 1;

    for (int i = 1; i <= n; i++) {
      int[] curr = new int[m + 1];
      curr[0] = 1;
      for (int j = 1; j <= m; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          curr[j] = prev[j - 1] + prev[j];
        else
          curr[j] = prev[j];
      }
      prev = curr;
    }
    return prev[m];
  }

  // -------------------------------------   THE OG SOLUTION
    // FURTHER space optimized tabulation      [ 1D Space optimized soln ]
    public int numDistinct5(String s, String t) {
      int n = s.length(), m = t.length();

      int[] curr = new int[m+1];
      curr[0] = 1;

      for (int i=1; i<=n; i++)    {
          // m to 1 only, because, replacing those that are not needed, 
          // and we can't go left to right, because we need previous ones, to calculate the current one
          for (int j=m; j >= 1; j--)    { 
              if (s.charAt(i-1) == t.charAt(j-1))
                  curr[j] = curr[j-1] + curr[j];      // overwriting, and space optimizing
              else
                  curr[j] = curr[j];
          }
      }
      return curr[m];
  }

}