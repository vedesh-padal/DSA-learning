import java.util.Arrays;

class Solution {

  // Memoized solution
  private static int find(int ind, int prevInd, int n, int[] nums, int[][] dp) {
    if (ind == n)
      return 0;

    if (dp[ind][prevInd + 1] != -1)
      return dp[ind][prevInd + 1];

    int notTake = 0 + find(ind + 1, prevInd, n, nums, dp);
    int take = -(int) (1e5);
    // cases when the curr. num can be picked
    if (prevInd == -1 || nums[ind] > nums[prevInd])
      take = 1 + find(ind + 1, ind, n, nums, dp);

    return dp[ind][prevInd + 1] = Math.max(take, notTake);

  }

  public int lengthOfLIS1(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n][n + 1];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(0, -1, n, nums, dp);
  }

  // -------------------------------------------------------------------------------------------
  // Tabulation Soln
  public int lengthOfLIS2(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n + 1][n + 1];

    for (int i = n - 1; i >= 0; i--) {
      for (int prev_ind = i - 1; prev_ind >= -1; prev_ind--) {

        // since this is tabulation and -ve indices are not valid, we do +1 compared to
        // what it is in memoziation soln
        int len = 0 + dp[i + 1][prev_ind + 1]; // not take

        if (prev_ind == -1 || nums[i] > nums[prev_ind]) {
          len = Math.max(len, 1 + dp[i + 1][i + 1]); // take
        }

        dp[i][prev_ind + 1] = len;
      }
    }
    return dp[0][-1 + 1];
  }

  // -------------------------------------------------------------------------------------------
  // Space optimized Tabulation soln
  public int lengthOfLIS3(int[] nums) {
    int n = nums.length;
    int[] next = new int[n + 1];
    int[] curr = new int[n + 1];

    for (int i = n - 1; i >= 0; i--) {
      for (int prev_ind = i - 1; prev_ind >= -1; prev_ind--) {

        // since this is tabulation and -ve indices are not valid, we do +1 compared to
        // what it is in memoziation soln
        int len = 0 + next[prev_ind + 1]; // not take

        if (prev_ind == -1 || nums[i] > nums[prev_ind]) {
          len = Math.max(len, 1 + next[i + 1]); // take
        }

        curr[prev_ind + 1] = len;
      }
      next = curr;
    }
    return next[-1 + 1]; // can even be curr
  }


  // -------------------------------------------------------------------------------------------
  // BEST - OPTIMAL SOLN --- O(N)
  public int lengthOfLIS4(int[] nums) {
    int n = nums.length;
    
    int[] dp = new int[n];
    Arrays.fill(dp, 1); // initially, even if there does not exist any element before that (increasing
                        // order), then max. len = 1. i.e., dp init with 1

    int maxi = 1; // to keep track of the Longest Increasing Subsequence (LIS)

    for (int i = 0; i < n; i++) {
      for (int prev = 0; prev < i; prev++) { // till the previous element of the curr. index element

        // if the previous element(s) are less than the curr. element, update dp[i]
        // indicating LIS till the curr. index
        if (nums[prev] < nums[i])
          dp[i] = Math.max(1 + dp[prev], dp[i]);
      }
      maxi = Math.max(maxi, dp[i]);
    }

    return maxi;
  }
}