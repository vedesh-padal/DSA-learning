/*
  Given an array of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum. 

  Example 1:

  Input:
  N = 6
  arr[] = {3, 34, 4, 12, 5, 2}
  sum = 9
  Output: 1 
  Explanation: Here there exists a subset with
  sum = 9, 4+3+2 = 9.
  Example 2:

  Input:
  N = 6
  arr[] = {3, 34, 4, 12, 5, 2}
  sum = 30
  Output: 0 
  Explanation: There is no subset with sum 30.

  Your Task:  
  You don't need to read input or print anything. Your task is to complete the function isSubsetSum() which 
  takes the array arr[], its size N and an integer sum as input parameters and returns boolean value 
  true if there exists a subset with given sum and false otherwise.
  The driver code itself prints 1, if returned value is true and prints 0 if returned value is false.

  Expected Time Complexity: O(sum*N)
  Expected Auxiliary Space: O(sum*N)
  
  Constraints:
  1 <= N <= 100
  1<= arr[i] <= 100
  1<= sum <= 104
*/

import java.util.Arrays;

class Solution {

  // // MEMOIZED SOLUTION
  private static boolean find(int ind, int target, int[] arr, int[][] dp) {
    if (target == 0)
      return true;
    if (ind == 0)
      return arr[0] == target;

    if (dp[ind][target] != -1)
      return dp[ind][target] == 0 ? false : true;

    boolean notTaken = find(ind - 1, target, arr, dp);
    boolean taken = false;
    if (arr[ind] <= target)
      taken = find(ind - 1, target - arr[ind], arr, dp);

    dp[ind][target] = (taken || notTaken) ? 1 : 0;
    return taken || notTaken;
  }

  static Boolean isSubsetSum1(int N, int arr[], int sum) {
    // code here
    int[][] dp = new int[N][sum + 1];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(N - 1, sum, arr, dp);
  }


  
  // Tabulation method
  static Boolean isSubsetSum2(int N, int arr[], int sum) {
    // code here
    boolean[][] dp = new boolean[N][sum + 1];

    for (int i = 0; i < N; i++)
      dp[i][0] = true;

    if (arr[0] <= sum)
      dp[0][arr[0]] = true;

    for (int i = 1; i < N; i++) {
      for (int target = 1; target <= sum; target++) {
        boolean notTake = dp[i - 1][target];
        boolean take = false;
        if (arr[i] <= target)
          take = dp[i - 1][target - arr[i]];

        dp[i][target] = take || notTake;
      }
    }
    return dp[N - 1][sum];
  }

  // Space optimized tabulation solution
  static Boolean isSubsetSum3(int N, int arr[], int sum) {
    boolean[] dp = new boolean[sum + 1];

    dp[0] = true;

    if (arr[0] <= sum)
      dp[arr[0]] = true;

    for (int ind = 1; ind < N; ind++) {
      boolean[] curr = new boolean[sum + 1];
      curr[0] = true;
      for (int target = 1; target <= sum; target++) {
        boolean notTake = dp[target];
        boolean take = false;
        if (arr[ind] <= target)
          take = dp[target - arr[ind]];

        curr[target] = notTake || take;
      }
      dp = curr;
    }

    return dp[sum];
  }

}