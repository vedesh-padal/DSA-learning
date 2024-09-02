/*
  Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

  Return the number of nice sub-arrays.

  Example 1:
  Input: nums = [1,1,2,1,1], k = 3
  Output: 2
  Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

  Example 2:
  Input: nums = [2,4,6], k = 1
  Output: 0
  Explanation: There are no odd numbers in the array.

  Example 3:
  Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
  Output: 16

  Constraints:
  1 <= nums.length <= 50000
  1 <= nums[i] <= 10^5
  1 <= k <= nums.length
*/

class Solution {
  // similar to the problem: count binary subarrays with sum

  public int find(int[] nums, int k) {
    if (k < 0)
      return 0;
    int n = nums.length;
    int l = 0, r = 0;
    int count = 0; // counts the num. of nice subarrays
    int sum = 0; // will count the number of odd numbers in the subarray, if equal to k, we will
                 // add all possible subarrays of that window

    while (r < n) {
      sum += nums[r] % 2; // we are adding 1 if the element at r is a odd number
      while (sum > k) {
        sum -= nums[l] % 2;
        l++;
      }
      count += (r - l + 1);
      r++;
    }
    return count;
  }

  public int numberOfSubarrays(int[] nums, int k) {
    return (find(nums, k) - find(nums, k - 1));
  }
}