/*
  Given an array arr of size n of non-negative integers and an integer sum, the task is to count all subsets of the given array with a sum equal to a given sum.

  Note: Answer can be very large, so, output answer modulo 109+7.

  Examples:

  Input: 
  n = 6, arr = [5, 2, 3, 10, 6, 8], sum = 10
  Output: 
  3
  Explanation: 
  {5, 2, 3}, {2, 8}, {10} are possible subsets.
  Input: 
  n = 5, arr = [2, 5, 1, 4, 3], sum = 10
  Output: 
  3
  Explanation: 
  {2, 1, 4, 3}, {5, 1, 4}, {2, 5, 3} are possible subsets.

  Expected Time Complexity: O(n*sum)
  Expected Auxiliary Space: O(n*sum)

  Constraints:
  1 ≤ n*sum ≤ 106
  0 ≤ arr[i] ≤ 106

*/


// space optimized tabulation solution (SIMILAR TO DP-14, just that, we are counting here)
class Solution {
  static int mod = (int) (1e9 + 7);

  public int perfectSum(int arr[], int n, int sum) {
    // Your code goes here
    int[] dp = new int[sum + 1];
    dp[0] = 1;

    if (arr[0] <= sum)
      dp[arr[0]] = 1;

    // special case if array contains 0 at first element
    if (arr[0] == 0)
      dp[0] = 2;

    for (int ind = 1; ind < n; ind++) {

      int[] curr = new int[sum + 1];
      curr[0] = 1;

      // target is starting form 0, because 
      // we have to tell totals subsets with sum= target so if we have had target=0 then you would have already stored the answer of dp[n-1][0]=1 
      // Which is correct if arr did not have any integer as 0 , in case 0 are there then this subset with 0 is also a valid answer hence we need 
      // to count it as well eg arr=[0,2 3] target=0 so total answer is 2 not 1,  so we recalculate the value of dp[i][0] to ensure 
      // if we are getting any more subsets with sum=0 apart from the empty subset.
      for (int target = 0; target <= sum; target++) {
        int notPick = dp[target] % mod;
        int pick = 0;
        if (arr[ind] <= target)
          pick = dp[target - arr[ind]] % mod;

        curr[target] = (pick + notPick) % mod;
      }
      dp = curr;
    }
    return dp[sum];
  }
}