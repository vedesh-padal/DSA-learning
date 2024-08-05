// There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). 
// The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). 
// The robot can only move either down or right at any point in time.

// Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

// The test cases are generated so that the answer will be less than or equal to 2 * 109.

import java.util.Arrays;

class Solution {

  // Memoization method
  private static int find(int i, int j, int[][] dp) {
    if (i == 0 && j == 0)
      return 1;
    if (i < 0 || j < 0)
      return 0;

    if (dp[i][j] != -1)
      return dp[i][j];

    return dp[i][j] = find(i - 1, j, dp) + find(i, j - 1, dp); // explore top, and left
  }

  public int uniquePaths1(int m, int n) {

    int[][] dp = new int[m][n];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(m - 1, n - 1, dp);
  }

  // Tabulation with no space optimization
  public int uniquePaths2(int m, int n) {
    int[][] dp = new int[m][n];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0)
          dp[i][j] = 1;
        else {
          int up = 0, left = 0;
          if (i > 0)
            up = dp[i - 1][j];
          if (j > 0)
            left = dp[i][j - 1];
          dp[i][j] = up + left;
        }
      }
    }
    return dp[m - 1][n - 1];
  }

  // Tabulation with space optimization
  public int uniquePaths(int m, int n) {
    int[] dp = new int[n];
    Arrays.fill(dp, -1);

    for (int i = 0; i < m; i++) {
      int[] curr = new int[n];
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0)
          curr[j] = 1;
        else {
          int up = 0, left = 0;
          if (i > 0)
            up = dp[j];
          if (j > 0)
            left = curr[j - 1];
          curr[j] = up + left;
        }
      }
      dp = curr;
    }
    return dp[n - 1];
  }
}