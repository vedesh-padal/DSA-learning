/*
  https://www.geeksforgeeks.org/problems/chocolates-pickup/1
  You are given an n rows and m cols matrix grid representing a field of chocolates where grid[i][j] 
  represents the number of chocolates that you can collect from the (i, j) cell.

  You have two robots that can collect chocolates for you:

  Robot #1 is located at the top-left corner (0, 0), and
  Robot #2 is located at the top-right corner (0, cols - 1).
  Return the maximum number of chocolates collection using both robots by following the rules below:

  From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
  When any robot passes through a cell, It picks up all chocolates, and the cell becomes an empty cell.
  When both robots stay in the same cell, only one takes the chocolates.
  Both robots cannot move outside of the grid at any moment.
  Both robots should reach the bottom row in grid.

*/


import java.util.Arrays;

class Solution {

  static final int low = -(int) (1e4);

  private static int find(int i, int j1, int j2, int n, int m, int[][] grid, int[][][] dp) {

    if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m)
      return low;

    if (dp[i][j1][j2] != -1)
      return dp[i][j1][j2];

    if (i == n - 1) {
      if (j1 == j2)
        return grid[i][j1];
      else
        return grid[i][j1] + grid[i][j2];
    }

    // now, explore all the paths that those two persons/robots can go together
    // for each step by one, there will be 3 other possibilites, so in total 9 possibilites,
    // represented by the following for loop code

    int maxi = 0;

    for (int dj1 = -1; dj1 <= 1; dj1++) {
      for (int dj2 = -1; dj2 <= 1; dj2++) {
        int value = 0;
        if (j1 == j2)
          value = grid[i][j1];
        else
          value = grid[i][j1] + grid[i][j2];

        value += find(i + 1, j1 + dj1, j2 + dj2, n, m, grid, dp);

        maxi = Math.max(maxi, value);
      }
    }
    return dp[i][j1][j2] = maxi;
  }

  public int solve(int n, int m, int grid[][]) {
    // code here
    int[][][] dp = new int[n][m][m];
    for (int[][] mat : dp) {
      for (int[] row : mat)
        Arrays.fill(row, -1);
    }

    // i, first init position, second init position, rows, cols, grid, dp
    return find(0, 0, m - 1, n, m, grid, dp);
  }


  // ----------------------------------------------------------------
  // Tabulation without space optimization
  public int solve2(int n, int m, int grid[][]) {
    // code here
    int[][][] dp = new int[n][m][m];
    for (int[][] mat : dp) {
      for (int[] row : mat)
        Arrays.fill(row, -1);
    }

    // base case initialization
    for (int j1 = 0; j1 < m; j1++) {
      for (int j2 = 0; j2 < m; j2++) {
        if (j1 == j2)
          dp[n - 1][j1][j2] = grid[n - 1][j1];
        else
          dp[n - 1][j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
      }
    }

    for (int i = n - 2; i >= 0; i--) {
      for (int j1 = 0; j1 < m; j1++) {
        for (int j2 = 0; j2 < m; j2++) {

          int maxi = 0;
          for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
              int value = 0;
              if (j1 == j2)
                value = grid[i][j1];
              else
                value = grid[i][j1] + grid[i][j2];

              if (j1 + dj1 >= 0 && j1 + dj1 < m && j2 + dj2 >= 0 && j2 + dj2 < m) {
                value += dp[i + 1][j1 + dj1][j2 + dj2];
              } else {
                value += low;
              }

              maxi = Math.max(maxi, value);
            }
          }
          dp[i][j1][j2] = maxi;
        }
      }
    }

    return dp[0][0][m - 1];
  }

  // ----------------------------------------------------------------
  // Tabulation WITH SPACE OPTIMIZATION
  public int solve3(int n, int m, int grid[][]) {
    // code here
    int[][] dp = new int[m][m];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    // base case initialization
    for (int j1 = 0; j1 < m; j1++) {
      for (int j2 = 0; j2 < m; j2++) {
        if (j1 == j2)
          dp[j1][j2] = grid[n - 1][j1];
        else
          dp[j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
      }
    }

    for (int i = n - 2; i >= 0; i--) {
      int[][] curr = new int[m][m];
      for (int j1 = 0; j1 < m; j1++) {
        for (int j2 = 0; j2 < m; j2++) {

          int maxi = 0;
          for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
              int value = 0;
              if (j1 == j2)
                value = grid[i][j1];
              else
                value = grid[i][j1] + grid[i][j2];

              if (j1 + dj1 >= 0 && j1 + dj1 < m && j2 + dj2 >= 0 && j2 + dj2 < m) {
                value += dp[j1 + dj1][j2 + dj2];
              } else {
                value += low;
              }

              maxi = Math.max(maxi, value);
            }
          }
          curr[j1][j2] = maxi;
        }
      }
      dp = curr;
    }

    return dp[0][m - 1];
  }

}