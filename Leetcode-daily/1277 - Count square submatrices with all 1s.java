// 27-10-2024
// MEDIUM
// dp, recursion

// Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

// Example 1:
// Input: matrix =
// [
//   [0,1,1,1],
//   [1,1,1,1],
//   [0,1,1,1]
// ]
// Output: 15
// Explanation: 
// There are 10 squares of side 1.
// There are 4 squares of side 2.
// There is  1 square of side 3.
// Total number of squares = 10 + 4 + 1 = 15.

// Example 2:
// Input: matrix = 
// [
//   [1,0,1],
//   [1,1,0],
//   [1,1,0]
// ]
// Output: 7
// Explanation: 
// There are 6 squares of side 1.  
// There is 1 square of side 2. 
// Total number of squares = 6 + 1 = 7.
 
// Constraints:
// 1 <= arr.length <= 300
// 1 <= arr[0].length <= 300
// 0 <= arr[i][j] <= 1

import java.util.Arrays;

class Solution {

  private int solve(int i, int j, int[][] mat, int[][] dp) {

    if (i >= mat.length || j >= mat[0].length)
      return 0;

    // inorder to find all possible square submatrices
    // we will be calling this helper function from our main function
    // when our curr. cell value is 1

    // starting from (i, j) if it is 1, we check in 3 directions:
    // right, diagonal, down and expand checking
    if (mat[i][j] == 0)
      return 0;

    // since there WILL be sub-problems, we have dp array
    if (dp[i][j] != -1)
      return dp[i][j];

    int right = solve(i, j + 1, mat, dp);
    int diag = solve(i + 1, j + 1, mat, dp);
    int down = solve(i + 1, j, mat, dp);

    // we are taking min. here:
    // BECOZ: that indicates the side of least square
    // submatrix we can form by travelling right, down, diagonlly
    return dp[i][j] = 1 + Math.min(right, Math.min(diag, down));
  }

  // TOP-DOWN MEMOIZATION
  public int countSquares_RECURSIVE(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int[][] dp = new int[m][n];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    int res = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 1) {
          res += solve(i, j, matrix, dp);
        }
      }
    }
    return res;
  }

  // ====================================
  // BOTTOM-UP DP - TABULATION
  public int countSquares(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int[][] dp = new int[m][n];

    int res = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // if 0th row or 0th col.
        // beginning blocks from where solution starts to build up
        if (i == 0 || j == 0) {
          dp[i][j] = (matrix[i][j] == 0) ? 0 : 1;
          // or just: res += matrix[i][j];
        }
        // we only consider from the cell to top, left, diagonally
        // only when it is 1, becoz if i was 0, it will be cut and
        // submatrix could not be formed
        else if (matrix[i][j] == 1) {
          // first we store, such that subproblems can use
          // since this is bottom up, we do reverse of top-down
          // we stand at a cell, and look up, left, diagonally up-left
          dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
        }
        res += dp[i][j];
      }
    }

    return res;
  }
}