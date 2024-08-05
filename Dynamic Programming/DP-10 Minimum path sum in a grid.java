// Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

// Note: You can only move either down or right at any point in time.

import java.util.Arrays;

class Solution {
  static int high = (int) Math.pow(10, 5);

  // Memoization approach, less runtime, but high memory
  private static int find(int i, int j, int[][] grid, int[][] dp) {
    if (i == 0 && j == 0)
      return grid[i][j];
    if (i < 0 || j < 0)
      return high;

    if (dp[i][j] != -1)
      return dp[i][j];

    int up = grid[i][j] + find(i - 1, j, grid, dp);
    int left = grid[i][j] + find(i, j - 1, grid, dp);

    return dp[i][j] = Math.min(up, left);
  }

  public int minPathSum1(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] dp = new int[m][n];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(m - 1, n - 1, grid, dp);
  }
  // -------------------------------------------------------------------------------

  // Tabulation with space optimization, more run time, but less memory
  public int minPathSum2(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[] prev = new int[n];

    for (int i = 0; i < m; i++) {
      int[] curr = new int[n];
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) {
          curr[j] = grid[i][j];
        } else {
          int up = grid[i][j], left = grid[i][j];

          up += (i > 0) ? prev[j] : high;
          left += (j > 0) ? curr[j - 1] : high;

          curr[j] = Math.min(up, left);
        }
      }
      prev = curr;
    }
    return prev[n - 1];
  }
}