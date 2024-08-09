/*
  Given an array arr, partition it into two subsets(possibly empty) such that each element must belong to only one subset. Let the sum of the elements of these two subsets be S1 and S2. 
  Given a difference d, count the number of partitions in which S1 is greater than or equal to S2 and the difference between S1 and S2 is equal to d. Since the answer may be large return it modulo 109 + 7.

  Example 1:

  Input:
  n = 4
  d = 3
  arr[] =  { 5, 2, 6, 4}
  Output: 1
  Explanation:
  There is only one possible partition of this array. Partition : {6, 4}, {5, 2}. The subset difference between subset sum is: (6 + 4) - (5 + 2) = 3.
  Example 2:

  Input:
  n = 4
  d = 0 
  arr[] = {1, 1, 1, 1} 
  Output: 6 
  Explanation:
  we can choose two 1's from indices {0,1}, {0,2}, {0,3}, {1,2}, {1,3}, {2,3} and put them in S1 and remaning two 1's in S2.
  Thus there are total 6 ways for partition the array arr. 
  Your Task:
  You don't have to read input or print anything. Your task is to complete the function countPartitions() which takes the integer n and d and array arr and returns the count of partitions.

  Expected Time Complexity: O( n*sum(arr))
  Expected Space Complexity: O( sum(arr))

  Constraint:
  1 <= n <= 500
  0 <= d  <= 25000
  0 <= arr[i] <= 50
*/

import java.util.Arrays;

class Solution {

  final static int mod = (int) (1e9 + 7);

  private static int find(int ind, int sum, int[] arr, int[][] dp) {

    if (ind == 0) { // there are two cases
      if (arr[0] == 0 && sum == 0)
        return 2;
      if (sum == 0 || arr[0] == sum)
        return 1;
      return 0;
    }

    if (dp[ind][sum] != -1)
      return dp[ind][sum];

    int notTake = find(ind - 1, sum, arr, dp);
    int take = 0;
    if (arr[ind] <= sum)
      take = find(ind - 1, sum - arr[ind], arr, dp);

    return dp[ind][sum] = (take + notTake) % mod;

  }

  // MEMOIZED SOLUTION
  public static int countPartitions1(int n, int d, int[] arr) {
    // code here

    int totalSum = 0;
    for (int i : arr)
      totalSum += i;

    // S1 - S2 = d S2 = TOTALSUM - S2 => S2 = (TOTALSUM - D) / 2
    // SO S2 IS THE target that should be equal to the sum of the subset

    // and it should satisfy the below two conditions:
    // - should not be less than 0
    // - and the target that we set should be an integer, since when divided by 2,
    // it should not be a decimal

    if ((totalSum - d) < 0 || (totalSum - d) % 2 != 0)
      return 0;

    int k = (totalSum - d) / 2; // this is our target, with which we are trying to find subsets sum
    int[][] dp = new int[n][k + 1];

    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(n - 1, k, arr, dp);
  }


  // -----------------------------------------------------------------------
  // TABULATION METHOD
  public static int countPartitions2(int n, int d, int[] arr) {
    // code here
    int totalSum = 0;
    for (int i : arr)
      totalSum += i;

    if ((totalSum - d) < 0 || (totalSum - d) % 2 == 1)
      return 0;

    int k = (totalSum - d) / 2; // this is our target, with which we are trying to find subsets sum
    int[][] dp = new int[n][k + 1];

    if (arr[0] == 0) // two cases, pick and not pick
      dp[0][0] = 2;
    else
      dp[0][0] = 1; // 1 case not pick

    if (arr[0] != 0 && arr[0] <= k)
      dp[0][arr[0]] = 1;

    for (int i = 1; i < n; i++) {
      for (int target = 0; target <= k; target++) {
        int notTake = dp[i - 1][target];
        int take = 0;
        if (arr[i] <= target)
          take = dp[i - 1][target - arr[i]];

        dp[i][target] = (take + notTake) % mod;
      }
    }
    return dp[n - 1][k];
  }


  // -----------------------------------------------------------------------
  // TABULATION METHOD with SPACE OPTIMIZATION
  public static int countPartitions(int n, int d, int[] arr) {
    // code here
    int totalSum = 0;
    for (int i : arr)
      totalSum += i;

    if (totalSum - d < 0 || (totalSum - d) % 2 == 1)
      return 0;

    int k = (totalSum - d) / 2;

    int[] dp = new int[k + 1];

    if (arr[0] == 0)
      dp[0] = 2;
    else
      dp[0] = 1;

    if (arr[0] != 0 && arr[0] <= k)
      dp[arr[0]] = 1;

    for (int i = 1; i < n; i++) {
      int[] curr = new int[k + 1];
      for (int target = 0; target <= k; target++) {
        int notPick = dp[target];
        int pick = 0;
        if (arr[i] <= target)
          pick = dp[target - arr[i]];

        curr[target] = (pick + notPick) % mod;
      }
      dp = curr;
    }
    return dp[k];
  }
}