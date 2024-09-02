/*
  Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
  A subarray is a contiguous part of the array.

  Example 1:
  Input: nums = [1,0,1,0,1], goal = 2
  Output: 4
  Explanation: The 4 subarrays are bolded and underlined below:
  [1,0,1,0,1]
  [1,0,1,0,1]
  [1,0,1,0,1]
  [1,0,1,0,1]

  Example 2:
  Input: nums = [0,0,0,0,0], goal = 0
  Output: 15
  
  Constraints:
  1 <= nums.length <= 3 * 104
  nums[i] is either 0 or 1.
  0 <= goal <= nums.length
*/

class Solution {

  private static int numSubArraysWithAtmostSum(int[] nums, int goal) {
    if (goal < 0)
      return 0;

    int l = 0, r = 0;
    int n = nums.length;
    int count = 0;
    int sum = 0;

    while (r < n) {
      sum += nums[r];
      while (sum > goal) {
        sum -= nums[l];
        l++;
      }
      // here (r - l + 1) represents number of subarrays with sum within the goal
      // ex: if window is [ 1, |0, 1, 1,| 0, 1]
      // the what we are adding is: (3 - 1 + 1) = 3 => (0), (0, 1), (0, 1, 1) =>
      // subarrays with sum within the goal
      count += (r - l + 1);
      r++;
    }
    return count;
  }

  public int numSubarraysWithSum(int[] nums, int goal) {
    return (numSubArraysWithAtmostSum(nums, goal) - numSubArraysWithAtmostSum(nums, goal - 1));
  }
}