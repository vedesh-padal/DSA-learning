// You are given a 0-indexed m x n matrix grid consisting of positive integers.

// You can start at any cell in the first column of the matrix, and traverse the
// grid in the following way:

// From a cell (row, col), you can move to any of the cells: (row - 1, col + 1),
// (row, col + 1) and (row + 1, col + 1) such that the value of the cell you
// move to, should be strictly bigger than the value of the current cell.
// Return the maximum number of moves that you can perform.

import java.util.Arrays;

class Solution {

  private static final int[][] dirs = { { -1, 1 }, { 0, 1 }, { 1, 1 } };

  private int solve(int i, int j, int[][] grid, int[][] dp) {

    if (dp[i][j] != -1)
      return dp[i][j];

    int maxi = 0;

    for (int k = 0; k < 3; k++) {
      int nrow = i + dirs[k][0];
      int ncol = j + dirs[k][1];

      if (nrow >= 0 && nrow < grid.length && ncol >= 0 &&
          ncol < grid[0].length && grid[nrow][ncol] > grid[i][j]) {

        maxi = Math.max(maxi, 1 + solve(nrow, ncol, grid, dp));
      }
    }

    return dp[i][j] = maxi;
  }

  public int maxMoves(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];

    for (int[] row : dp)
      Arrays.fill(row, -1);

    int maxi = Integer.MIN_VALUE;

    // starting from each element in the first column
    for (int i = 0; i < m; i++) {
      maxi = Math.max(maxi, solve(i, 0, grid, dp));
    }
    return maxi;
  }
}