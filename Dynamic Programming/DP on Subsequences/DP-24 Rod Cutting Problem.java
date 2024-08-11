/*
  Given a rod of length N inches and an array of prices, price[]. price[i] denotes the value of a piece of length i. Determine the maximum value obtainable by cutting up the rod and selling the pieces.

  Note: Consider 1-based indexing.

  Example 1:

  Input:
  N = 8
  Price[] = {1, 5, 8, 9, 10, 17, 17, 20}
  Output:
  22
  Explanation:
  The maximum obtainable value is 22 by 
  cutting in two pieces of lengths 2 and 
  6, i.e., 5+17=22.
  Example 2:

  Input:
  N=8
  Price[] = {3, 5, 8, 9, 10, 17, 17, 20}
  Output: 
  24
  Explanation: 
  The maximum obtainable value is 
  24 by cutting the rod into 8 pieces 
  of length 1, i.e, 8*price[1]= 8*3 = 24. 
  
  Your Task:  
  You don't need to read input or print anything. 
  Your task is to complete the function cutRod() which takes the array A[] and its size N as inputs and 
  returns the maximum price obtainable.

  Expected Time Complexity: O(N2)
  Expected Auxiliary Space: O(N)

  Constraints:
  1 ≤ N ≤ 1000
  1 ≤ Ai ≤ 105
*/


import java.util.Arrays;

class Solution {

  private static int maximizeRodValue(int ind, int remainingLength, int[] price, int[][] dp) {
    if (ind == 0) {
      return remainingLength * price[0];
    }

    if (dp[ind][remainingLength] != -1)
      return dp[ind][remainingLength];

    // Option 1: Do not cut the current piece
    int notPick = 0 + maximizeRodValue(ind - 1, remainingLength, price, dp);

    int pieceLength = ind + 1;
    int pick = Integer.MIN_VALUE;
    if (pieceLength <= remainingLength)
      pick = price[ind] + maximizeRodValue(ind, remainingLength - pieceLength, price, dp);

    return dp[ind][remainingLength] = Math.max(pick, notPick);
  }

  public int cutRod1(int price[], int n) {
    // code here

    int[][] dp = new int[n][n + 1];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return maximizeRodValue(n - 1, n, price, dp);

  }


  // ------------------------------------------------
  // space optimized tabulation method

  public int cutRod2(int price[], int n) {
    // code here

    int[] prev = new int[n + 1];

    for (int rodLength = 0; rodLength <= n; rodLength++)
      prev[rodLength] = rodLength * price[0];

    for (int ind = 1; ind < n; ind++) {
      int[] curr = new int[n + 1];
      for (int remainingLength = 0; remainingLength <= n; remainingLength++) {
        int notPick = prev[remainingLength];
        int pieceLength = ind + 1;
        int pick = Integer.MIN_VALUE;
        if (pieceLength <= remainingLength)
          pick = price[ind] + curr[remainingLength - pieceLength];

        curr[remainingLength] = Math.max(pick, notPick);
      }
      prev = curr;
    }
    return prev[n];

  }
}