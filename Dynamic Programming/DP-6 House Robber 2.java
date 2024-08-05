// this is similar to DP on subsequences and House Robber 1 problem
// but, this time another condition is added => circular, which means the robber cannot rob now 
// from both first and last house (as they are adjacent) at the same time

/*
  You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
   All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. 
   Meanwhile, adjacent houses have a security system connected, and it will automatically contact 
   the police if two adjacent houses were broken into on the same night.

  Given an integer array nums representing the amount of money of each house, 
  return the maximum amount of money you can rob tonight without alerting the police.

  Example 1:

  Input: nums = [2,3,2]
  Output: 3
  Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
  Example 2:

  Input: nums = [1,2,3,1]
  Output: 4
  Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
  Total amount you can rob = 1 + 3 = 4.
  Example 3:

  Input: nums = [1,2,3]
  Output: 3


*/


class Solution {

  // one way of doing, basic House robber code
  private static int find1(int[] nums) {
    int prev2 = 0, prev = nums[0];

    for (int i = 1; i < nums.length; i++) {
      int pick = nums[i];
      if (i > 1)
        pick += prev2;
      int notPick = 0 + prev;
      int curr = Math.max(pick, notPick);
      prev2 = prev;
      prev = curr;
    }
    return prev;
  }

  private static int find2(int[] nums, int s, int e) {
    int prev2 = 0, prev = 0;
    for (int i = s; i <= e; i++) {
      int curr = Math.max(nums[i] + prev2, prev);
      prev2 = prev;
      prev = curr;
    }
    return prev;
  }

  public int rob(int[] nums) {
    // this time arranged in circle

    int n = nums.length;
    if (n == 0)
      return 0;
    if (n == 1)
      return nums[0];


    //// kind of extra space consumption
    // int[] temp1 = new int[n], temp2 = new int[n];

    // for (int i = 0; i < n; i++) {
    //   if (i != 0)
    //     temp1[i] = nums[i];
    //   if (i != n - 1)
    //     temp2[i] = nums[i];
    // }

    // return Math.max(find1(temp1), find1(temp2));

    return Math.max(find2(nums, 0, n - 2), find2(nums, 1, n - 1));
  }
}