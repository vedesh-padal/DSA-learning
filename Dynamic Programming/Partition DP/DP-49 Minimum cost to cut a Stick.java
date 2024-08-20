/*
  Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:

  Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.

  You should perform the cuts in order, you can change the order of the cuts as you wish.

  The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.

  Return the minimum total cost of the cuts.

*/

// Classic Partition DP Question
// A TOUGH QUESTION

import java.util.Arrays;

class Solution {

  // this is giving index out of bounds
  // NOTE: this is not just a subsequence -> pick and not pick problem, as this
  // involves
  // dividing into different partitions and then finding costs for each of the
  // possible partition and finding the least cost
  private static int minimizeRodValue(int ind, int remainingLength, int[] cuts, int[][] dp) {
    if (ind == 0)
      dp[0][remainingLength] = remainingLength * cuts[0];

    if (dp[ind][remainingLength] != -1)
      return dp[ind][remainingLength];

    int notPick = 0 + minimizeRodValue(ind - 1, remainingLength, cuts, dp);

    int pieceLength = ind + 1; // current piece length is based on 1 based index
    int pick = Integer.MAX_VALUE;
    if (pieceLength <= remainingLength)
      pick = cuts[ind] + minimizeRodValue(ind, remainingLength - pieceLength, cuts, dp);

    return dp[ind][remainingLength] = Math.min(pick, notPick);
  }

  public int minCost(int n, int[] cuts) {
    int[][] dp = new int[n][n + 1];

    for (int[] row : dp)
      Arrays.fill(row, -1);

    return minimizeRodValue(n - 1, n, cuts, dp);
  }

  // ------------------------------------------------------------------------------------------
  // PARTIION DP
  // Memoized solution
  private static int find(int i, int j, int[] cuts, int[][] dp) {
    if (i > j)
      return 0;

    if (dp[i][j] != -1)
      return dp[i][j];

    int mini = (int) (1e9);

    // for each cut position, we get different partitions
    for (int k = i; k <= j; k++) {
      int cost = cuts[j + 1] - cuts[i - 1] + find(i, k - 1, cuts, dp) + find(k + 1, j, cuts, dp);

      mini = Math.min(mini, cost);
    }

    return dp[i][j] = mini;
  }

  public int minCost2(int n, int[] cuts) {
    int numCuts = cuts.length;
    int[] arr = new int[numCuts + 2];
    arr[0] = 0;
    for (int i = 1; i < numCuts + 1; i++)
      arr[i] = cuts[i - 1];

    arr[numCuts + 1] = n;

    Arrays.sort(arr);

    int[][] dp = new int[numCuts + 2][numCuts + 2];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(1, numCuts, arr, dp);

  }

  // -----------------------------------------------------------------
  // Tabulation Solution
  public int minCost3(int n, int[] cuts) {
    int numCuts = cuts.length;
    int[] arr = new int[numCuts + 2];
    arr[0] = 0;
    for (int i = 1; i < numCuts + 1; i++)
      arr[i] = cuts[i - 1];

    arr[numCuts + 1] = n;

    Arrays.sort(arr);

    int[][] dp = new int[numCuts + 2][numCuts + 2];

    for (int i = numCuts; i >= 1; i--) {
      for (int j = 1; j <= numCuts; j++) {
        if (i > j)
          continue;

        int mini = Integer.MAX_VALUE;

        for (int k = i; k <= j; k++) {
          int cost = arr[j + 1] - arr[i - 1] + dp[i][k - 1] + dp[k + 1][j];

          mini = Math.min(mini, cost);
        }
        dp[i][j] = mini;
      }
    }

    return dp[1][numCuts];

  }
}