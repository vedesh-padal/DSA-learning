/*
  Given a sequence of matrices, find the most efficient way to multiply these
   matrices together. The efficient way is the one that involves the least number of multiplications.

  The dimensions of the matrices are given in an array arr[] of size N 
  (such that N = number of matrices + 1) where the ith matrix has the dimensions (arr[i-1] x arr[i]).

  Example 1:

  Input: N = 5
  arr = {40, 20, 30, 10, 30}
  Output: 26000
  Explanation: There are 4 matrices of dimension 
    40x20, 20x30, 30x10, 10x30. Say the matrices are 
    named as A, B, C, D. Out of all possible combinations,
    the most efficient way is (A*(B*C))*D. 
    The number of operations are -
    20*30*10 + 40*20*10 + 40*10*30 = 26000.

  Example 2:

  Input: N = 4
  arr = {10, 30, 5, 60}
  Output: 4500
  Explanation: The matrices have dimensions 
    10*30, 30*5, 5*60. Say the matrices are A, B 
    and C. Out of all possible combinations,the
    most efficient way is (A*B)*C. The 
    number of multiplications are -
    10*30*5 + 10*5*60 = 4500.

  Your Task:
  You do not need to take input or print anything. Your task is to complete the function
   matrixMultiplication() which takes the value N and the array arr[] as input parameters 
   and returns the minimum number of multiplication operations needed to be performed.

  Expected Time Complexity: O(N3)
  Expected Auxiliary Space: O(N2)

  Constraints: 
  2 ≤ N ≤ 100
  1 ≤ arr[i] ≤ 500
*/


import java.util.Arrays;

class Solution {

  // TOP DOWN -- MEMOIZATION APPROACH
  private static int find(int i, int j, int[] arr, int[][] dp) {
    if (i == j)
      return 0;

    if (dp[i][j] != -1)
      return dp[i][j];

    int mini = Integer.MAX_VALUE;
    // partitioning / forming blocks
    for (int k = i; k < j; k++) {
      int steps = arr[i - 1] * arr[k] * arr[j] + find(i, k, arr, dp) + find(k + 1, j, arr, dp);

      mini = Math.min(mini, steps);
    }

    return dp[i][j] = mini;
  }

  static int matrixMultiplication1(int N, int arr[]) {
    // code here
    int[][] dp = new int[N][N];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(1, N - 1, arr, dp);
  }


  // BOTTOM UP -- TABULATION APPROACH
  static int matrixMultiplication2(int N, int arr[]) {
    // code here
    int[][] dp = new int[N][N];

    // base case
    for (int i = 0; i < N; i++)
      dp[i][i] = 0;

    for (int i = N - 1; i >= 1; i--) {
      for (int j = i + 1; j < N; j++) {

        int mini = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
          int steps = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];

          mini = Math.min(mini, steps);
        }
        dp[i][j] = mini;
      }
    }

    return dp[1][N - 1];
  }
}