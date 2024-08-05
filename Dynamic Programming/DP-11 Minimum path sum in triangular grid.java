/*
  Given a triangle array, return the minimum path sum from top to bottom.

  For each step, you may move to an adjacent number of the row below. More formally, 
  if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

  Example 1:

  Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
  Output: 11
  Explanation: The triangle looks like:
    2
    3 4
    6 5 7
    4 1 8 3
  The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
  Example 2:

  Input: triangle = [[-10]]
  Output: -10
*/


import java.util.List;
import java.util.Arrays;

class Solution {

  ///// Memoized solution
  private static int find(int i, int j, int n, List<List<Integer>> triangle, int[][] dp) {
      if (i == n-1)
          return triangle.get(n-1).get(j);

      if (dp[i][j] != -1)
          return dp[i][j];

      int d = triangle.get(i).get(j) + find(i+1, j, n, triangle, dp);
      int dg = triangle.get(i).get(j) + find(i+1, j+1, n, triangle, dp);

      return dp[i][j] = Math.min(d, dg);
  }

  public int minimumTotal1(List<List<Integer>> triangle) {
      int n = triangle.size();
      int[][] dp = new int[n][n];
      for (int[] row: dp)
          Arrays.fill(row, -1);

      return find(0, 0, n, triangle, dp);
  }

  // ----------------------------------------------------------------

  // // Tabulation method
  public int minimumTotal2(List<List<Integer>> triangle)   {
      int n = triangle.size();

      int[][] dp = new int[n][n];
      for (int j=0; j < triangle.get(n-1).size(); j++)    
          dp[n-1][j] = triangle.get(n-1).get(j);

      for (int i=n-2; i >= 0; i--)    {
          int m = triangle.get(i).size();
          for (int j=i; j >= 0; j--)    {
              int d = triangle.get(i).get(j) + dp[i+1][j];
              int dg = triangle.get(i).get(j) + dp[i+1][j+1];

              dp[i][j] = Math.min(d, dg);
          }
      }
      return dp[0][0];
  }

  // ----------------------------------------------------------------
  // Tabulation method with space optimization
  public int minimumTotal3(List<List<Integer>> triangle)   {
      int n = triangle.size();

      int[] next = new int[n];
      for (int j=0; j < triangle.get(n-1).size(); j++)    
          next[j] = triangle.get(n-1).get(j);

      for (int i=n-2; i >= 0; i--)    {
          int m = triangle.get(i).size();
          int[] curr = new int[n];
          for (int j=i; j >= 0; j--)    {
              int d = triangle.get(i).get(j) + next[j];
              int dg = triangle.get(i).get(j) + next[j+1];

              curr[j] = Math.min(d, dg);
          }
          next = curr;
      }
      return next[0];
  }
}