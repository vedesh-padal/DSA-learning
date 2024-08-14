import java.util.Arrays;

class Solution {

  // /// Memomized solution
  // i representing the index of s1, j of s2
  private static int findMinDistToEdit(String s1, String s2, int i, int j, int[][] dp) {
    if (i < 0)
      return (j + 1); // s1 is empty, so, converting empty string to s2 requires inserting all chars of s2 to s1

    if (j < 0)
      return (i + 1); // s2 is empty, so, s1 should be converted to empty string, which requries deleting all chars in s1

    if (dp[i][j] != -1)
      return dp[i][j];

    // if chars in both words matched, means we need not edit, so 0 + decrease the
    // indices and call
    if (s1.charAt(i) == s2.charAt(j))
      return dp[i][j] = 0 + findMinDistToEdit(s1, s2, i - 1, j - 1, dp);
    else {
      int numOfInsertOps = 1 + findMinDistToEdit(s1, s2, i, j - 1, dp); // hypothetically we inserted, so i does not move, but j decreases
      int numOfDeleteOps = 1 + findMinDistToEdit(s1, s2, i - 1, j, dp); // deleted from s1, but still searching for char in s2, so j as it is
      int numOfReplaceOps = 1 + findMinDistToEdit(s1, s2, i - 1, j - 1, dp); // replaced, so both indices will decrease

      // you perform either one of the operation from the above, so, instead of adding
      // 1 everytime, just add 1 time below (simplicity)
      return dp[i][j] = Math.min(numOfInsertOps, Math.min(numOfDeleteOps, numOfReplaceOps));
    }
  }

  public int minDistance1(String word1, String word2) {
    int n = word1.length(), m = word2.length();

    int[][] dp = new int[n][m];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return findMinDistToEdit(word1, word2, n - 1, m - 1, dp);

  }

  // --------------------------------------------------------------
  // Tabulation 2D DP table

  public int minDistance2(String word1, String word2) {
    int n = word1.length(), m = word2.length();

    int[][] dp = new int[n + 1][m + 1];

    for (int i = 0; i <= n; i++)
      dp[i][0] = i; // becoz, it is 1 based indexing, you don't have to add 1

    for (int j = 0; j <= m; j++)
      dp[0][j] = j;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1))
          dp[i][j] = 0 + dp[i - 1][j - 1];
        else {
          int numOfInsertOps = dp[i][j - 1]; // hypothetically we inserted, so i does not move, but j decreases
          int numOfDeleteOps = dp[i - 1][j]; // deleted from s1, but still searching for char in s2, so j as it is
          int numOfReplaceOps = dp[i - 1][j - 1]; // replaced, so both indices will decrease

          dp[i][j] = 1 + Math.min(numOfInsertOps, Math.min(numOfDeleteOps, numOfReplaceOps));
        }
      }
    }

    return dp[n][m];

  }

  // --------------------------------------------------------------
  // Tabulation 2 rows (prev, curr)

  public int minDistance3(String word1, String word2) {
    int n = word1.length(), m = word2.length();

    int[] prev = new int[m + 1];

    // first row -- base case
    for (int j = 0; j <= m; j++)
      prev[j] = j;

    for (int i = 1; i <= n; i++) {

      int[] curr = new int[m + 1];
      curr[0] = i;

      for (int j = 1; j <= m; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1))
          curr[j] = 0 + prev[j - 1];
        else {
          int numOfInsertOps = curr[j - 1]; // hypothetically we inserted, so i does not move, but j decreases
          int numOfDeleteOps = prev[j]; // deleted from s1, but still searching for char in s2, so j as it is   => becoz of this, we can't further space optimize
          int numOfReplaceOps = prev[j - 1]; // replaced, so both indices will decrease

          curr[j] = 1 + Math.min(numOfInsertOps, Math.min(numOfDeleteOps, numOfReplaceOps));
        }
      }
      prev = curr;
    }

    return prev[m];

  }

}