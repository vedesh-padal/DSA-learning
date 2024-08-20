/*
  Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

  answer[i] % answer[j] == 0, or
  answer[j] % answer[i] == 0
  If there are multiple solutions, return any of them.

  Example 1:
  Input: nums = [1,2,3]
  Output: [1,2]
  Explanation: [1,3] is also accepted.

  Example 2:
  Input: nums = [1,2,4,8]
  Output: [1,2,4,8]
  
  Constraints:

  1 <= nums.length <= 1000
  1 <= nums[i] <= 2 * 109
  All the integers in nums are unique.
*/

import java.util.*;

class Solution {
  public List<Integer> largestDivisibleSubset(int[] nums) {
    int n = nums.length;
    // to keep track of the LIS till that index
    int[] dp = new int[n];
    Arrays.fill(dp, 1); // init with 1, since, atleast this element is the only one LIS

    int[] hash = new int[n];

    int maxi = 1; // to keep track of the max. LIS length
    int lastIndex = 0; // to keep track of the last index of the till now LIS having ending index

    Arrays.sort(nums); // we are sorting becoz, when we find that the prev. element divides the curr.
                       // element, then the prev. prev. also divides the curr. element
    // and we need the array to be sorted for this reason

    for (int i = 0; i < n; i++) {
      hash[i] = i;
      for (int prev = 0; prev < i; prev++) {
        if (nums[i] % nums[prev] == 0 && 1 + dp[prev] > dp[i]) {
          dp[i] = 1 + dp[prev];
          hash[i] = prev;
        }
      }
      if (maxi < dp[i]) {
        maxi = dp[i];
        lastIndex = i;
      }
    }

    List<Integer> al = new ArrayList<>();
    al.add(nums[lastIndex]);

    while (hash[lastIndex] != lastIndex) {
      lastIndex = hash[lastIndex];
      al.add(nums[lastIndex]);
    }

    Collections.reverse(al);

    return al;
  }
}