// Given an m x n binary matrix filled with 0's and 1's, find the largest square
// containing only 1's and return its area.

// solved this after solving LC Daily 1277
// count square submatrices with all 1s
class Solution {
  public int maximalSquare(char[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int maxi = Integer.MIN_VALUE;

    int[][] dp = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
        } else if (matrix[i][j] == '1') {
          dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
        }
        maxi = Math.max(maxi, dp[i][j]);
      }
    }
    // here, maxi is the side of the maximal square
    return maxi * maxi; // return area of largest square
  }
}