import java.util.Arrays;

class Solution {

  private static int find(int ind, int W, int[] val, int[] wt, int[][] dp) {

    if (ind == 0)
      return (W / wt[0]) * val[0];

    if (dp[ind][W] != -1)
      return dp[ind][W];

    int notTake = 0 + find(ind - 1, W, val, wt, dp);
    int take = Integer.MIN_VALUE;
    if (wt[ind] <= W)
      take = val[ind] + find(ind, W - wt[ind], val, wt, dp);

    return dp[ind][W] = Math.max(take, notTake);
  }

  static int knapSack1(int N, int W, int val[], int wt[]) {
    // code here
    int[][] dp = new int[N][W + 1];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(N - 1, W, val, wt, dp);
  }

  static int knapSack2(int N, int W, int val[], int wt[]) {
    // code here
    int[] prev = new int[W + 1];

    for (int w = wt[0]; w <= W; w++) {
      prev[w] = ((int) (w / wt[0])) * val[0]; // dividing with wt[0], because we are the first row in the dp table
    }

    for (int ind = 1; ind < N; ind++) {
      int[] curr = new int[W + 1];
      for (int w = 0; w <= W; w++) {
        int notTake = 0 + prev[w];
        int take = Integer.MIN_VALUE;
        if (wt[ind] <= w)
          take = val[ind] + curr[w - wt[ind]];

        curr[w] = Math.max(take, notTake);
      }
      prev = curr;
    }
    return prev[W];
  }

  // ------------------------------------------------------------
  // can still space optimize with one array
  // but we have to traverse from right to left, and doing this we can update the
  // same array, becoz:
  // we are just depending on the previous row for the index before the current
  // the previous row values can be overridden without any problem
  // as the current row update needs previous indices of current row, and current
  // index of previous row
  static int knapSack3(int N, int W, int val[], int wt[]) {
    // code here
    int[] curr = new int[W + 1];

    for (int w = wt[0]; w <= W; w++) {
      curr[w] = ((int) (w / wt[0])) * val[0]; // dividing with wt[0], because we are the first row in the dp table
    }

    for (int ind = 1; ind < N; ind++) {
      for (int w = 0; w <= W; w++) { // right to left traversal and updaing the same array
        int notTake = 0 + curr[w];
        int take = Integer.MIN_VALUE;
        if (wt[ind] <= w)
          take = val[ind] + curr[w - wt[ind]];

        curr[w] = Math.max(take, notTake);
      }
    }
    return curr[W];
  }
}