/*
  Given an array of positive integers. Find the maximum length of Bitonic subsequence. 
  A subsequence of array is called Bitonic if it is first strictly increasing, then strictly decreasing. Return the maximum length of bitonic subsequence.
  
  ** ==> Note : A strictly increasing or a strictly decreasing sequence should not be considered as a bitonic sequence

  Examples :
  Input: n = 5, nums[] = [1, 2, 5, 3, 2]
  Output: 5
  Explanation: The sequence {1, 2, 5} is increasing and the sequence {3, 2} is decreasing so merging both we will get length 5.

  Input: n = 8, nums[] = [1, 11, 2, 10, 4, 5, 2, 1]
  Output: 6
  Explanation: The bitonic sequence {1, 2, 10, 4, 2, 1} has length 6.
  Expected Time Complexity: O(n2)
  Expected Space Complexity: O(n)
  
  Constraints:
  1 ≤ length of array ≤ 103
  1 ≤ arr[i] ≤ 104
*/

import java.util.Arrays;

class Solution {
  public static int LongestBitonicSequence(int n, int[] nums) {
    // code here
    int[] dp1 = new int[n]; // increasing
    int[] dp2 = new int[n]; // decreasing -- reverse
    Arrays.fill(dp1, 1);
    Arrays.fill(dp2, 1);

    // filling dp1 for forward increasing LIS
    for (int i = 0; i < n; i++) {
      for (int prev = 0; prev < i; prev++) {
        if (nums[i] > nums[prev] && 1 + dp1[prev] > dp1[i])
          dp1[i] = dp1[prev] + 1;
      }
    }

    // filling dp2 for backward increasing LIS
    for (int i = n - 1; i >= 0; i--) {
      for (int prev = n - 1; prev > i; prev--) {
        if (nums[i] > nums[prev] && 1 + dp2[prev] > dp2[i])
          dp2[i] = 1 + dp2[prev];
      }
    }

    int maxi = 0;
    for (int i = 0; i < n; i++) {
      // acc. to question: Bitonic means, should be increasing AND decreasing, so add the following if condition
      if (dp1[i] != 1 && dp2[i] != 1)
        maxi = Math.max(dp1[i] + dp2[i] - 1, maxi);
    }

    return maxi;
  }
}