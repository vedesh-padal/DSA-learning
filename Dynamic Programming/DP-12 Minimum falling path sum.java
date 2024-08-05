
// https://leetcode.com/problems/minimum-falling-path-sum/description/
// Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

// A falling path starts at any element in the first row and chooses the element in the next 
// row that is either directly below or diagonally left/right. Specifically, the next element from 
// position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

import java.util.Arrays;

class Solution {
  static final int high = (int) (1e4);

  ////// MEMOIZATION APPROACH -- TLE
  private static int find(int[][] matrix, int i, int j, int n, int[][] dp) {
    if (j < 0 || j >= n)
      return high;

    if (i == 0)
      return matrix[0][j];

    if (dp[i][j] != -1)
      return dp[i][j];

    int tl = matrix[i][j] + find(matrix, i - 1, j - 1, n, dp);
    int t = matrix[i][j] + find(matrix, i - 1, j, n, dp);
    int tr = matrix[i][j] + find(matrix, i - 1, j + 1, n, dp);

    return dp[i][j] = Math.min(tl, Math.min(t, tr));
  }

  public int minFallingPathSum1(int[][] matrix) {
    int n = matrix.length; // given n x n matrix
    int[][] dp = new int[n][n];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    int min = high;
    for (int j = 0; j < n; j++) {
      int ans = find(matrix, n - 1, j, n, dp);
      min = Math.min(ans, min);
    }
    return min;
  }

  // ----------------------------------------------------------------
  public int minFallingPathSum2(int[][] matrix) {
    int n = matrix.length; // given n x n matrix

    int[][] dp = new int[n][n];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    // iterative version of the base case, and it is so, because in the next steps,
    // we will build from below
    // and since this is tabulation approach, we will first have the base cases
    // ready
    for (int j = 0; j < n; j++)
      dp[0][j] = matrix[0][j];

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int t = matrix[i][j] + dp[i - 1][j];
        int tl = matrix[i][j];
        tl += (j - 1 >= 0) ? dp[i - 1][j - 1] : high;

        int tr = matrix[i][j];
        tr += (j + 1 < n) ? dp[i - 1][j + 1] : high;

        dp[i][j] = Math.min(t, Math.min(tl, tr));
      }
    }

    int min = high;
    for (int j = 0; j < n; j++) {
      min = Math.min(min, dp[n - 1][j]);
    }

    return min;
  }

  // ----------------------------------------------------------------
  // Space optimised TABULATION APPROACH
  public int minFallingPathSum3(int[][] matrix) {
    int n = matrix.length; // given n x n matrix

    int[] dp = new int[n];
    Arrays.fill(dp, -1);

    // iterative version of the base case, and it is so, because in the next steps,
    // we will build from below
    // and since this is tabulation approach, we will first have the base cases
    // ready
    for (int j = 0; j < n; j++)
      dp[j] = matrix[0][j];

    for (int i = 1; i < n; i++) {
      int[] curr = new int[n];

      for (int j = 0; j < n; j++) {
        int t = matrix[i][j] + dp[j];
        int tl = matrix[i][j];
        tl += (j - 1 >= 0) ? dp[j - 1] : high;

        int tr = matrix[i][j];
        tr += (j + 1 < n) ? dp[j + 1] : high;

        curr[j] = Math.min(t, Math.min(tl, tr));
      }
      dp = curr;
    }

    int min = high;
    for (int j = 0; j < n; j++) {
      min = Math.min(min, dp[j]);
    }

    return min;
  }

}