/*
  You are given an integer array nums of size n.

  Consider a non-empty subarray from nums that has the maximum possible bitwise AND.

  In other words, let k be the maximum value of the bitwise AND of any subarray of nums. 
  Then, only subarrays with a bitwise AND equal to k should be considered.
  Return the length of the longest such subarray.

  The bitwise AND of an array is the bitwise AND of all the numbers in it.

  A subarray is a contiguous sequence of elements within an array.

  Example 1:
  Input: nums = [1,2,3,3,2,2]
  Output: 2
  Explanation:
  The maximum possible bitwise AND of a subarray is 3.
  The longest subarray with that value is [3,3], so we return 2.

  Example 2:
  Input: nums = [1,2,3,4]
  Output: 1
  Explanation:
  The maximum possible bitwise AND of a subarray is 4.
  The longest subarray with that value is [4], so we return 1.

  Constraints:
  1 <= nums.length <= 105
  1 <= nums[i] <= 106
*/


class Solution {
  // LOGIC / INTUITION:
  // max. bitwise AND subarray must include max. elements only
  // therefore, we need to find the longest subarray including MAX only
  public int longestSubarray(int[] nums) {
    int maxVal = 0; // to keep track of the max. number in the nums
    int maxLen = 0; // to keep track of the length of the longest subarray
    int curr = 0; // to count the number of times maxVal occured in the current subarray

    for (int num : nums) {
      // maxVal repeated, so we check if this is the longest length, 
      // if yes, we update the maxLen
      if (num == maxVal) {
        maxLen = Math.max(maxLen, ++curr);
      } else if (num > maxVal) { // we found even greater value, time to reset
        maxVal = num;
        maxLen = curr = 1;
      } else {
        // the curr. num is less than maxVal, so no subarray, so curr len of subarray is 0
        curr = 0; 
      }
    }
    return maxLen;
  }
}