
// 1 is an obstacle, and 0 is a valid cell
// Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

import java.util.Arrays;

class Solution {

  // Memoized solution
  private static int find(int i, int j, int[][] grid, int[][] dp) {
    if (i >= 0 && j >= 0 && grid[i][j] == 1)
      return 0;
    if (i == 0 && j == 0) // BASE CASE
      return 1;
    if (i < 0 || j < 0) // OUT OF BOUNDS
      return 0;

    if (dp[i][j] != -1)
      return dp[i][j];

    return dp[i][j] = find(i - 1, j, grid, dp) + find(i, j - 1, grid, dp);
  }

  public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[][] dp = new int[m][n];
    for (int[] row : dp)
      Arrays.fill(row, -1);
    return find(m - 1, n - 1, obstacleGrid, dp);
  }

  // Tabulation method
  public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    int[][] dp = new int[m][n];
    // for (int i=0; i<m; i++)
    // Arrays.fill(obstacleGrid[i], -1);
    for (int[] row : dp)
      Arrays.fill(row, -1);

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {

        if (obstacleGrid[i][j] == 1) {
          dp[i][j] = 0;
          continue;
        }

        if (i == 0 && j == 0) {
          dp[i][j] = 1;
          continue;
        }

        int up = 0, left = 0;
        if (i > 0)
          up = dp[i - 1][j];
        if (j > 0)
          left = dp[i][j - 1];
        dp[i][j] = up + left;
      }
    }
    return dp[m - 1][n - 1];
  }

  // Space optimized tabulation solution
  public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    int[] prev = new int[n];
    Arrays.fill(prev, -1);

    for (int i = 0; i < m; i++) {

      int[] curr = new int[n];

      for (int j = 0; j < n; j++) {

        if (obstacleGrid[i][j] == 1) {
          curr[j] = 0;
          continue;
        }

        if (i == 0 && j == 0) {
          curr[j] = 1;
          continue;
        }

        int up = 0, left = 0;
        if (i > 0)
          up = prev[j];
        if (j > 0)
          left = curr[j - 1];
        curr[j] = up + left;
      }
      prev = curr;
    }
    return prev[n - 1];
  }
}