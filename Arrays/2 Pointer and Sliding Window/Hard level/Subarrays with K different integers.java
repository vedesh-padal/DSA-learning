/*
  LC - 992 - HARD
  Given an integer array nums and an integer k, return the number of good subarrays of nums.
  A good array is an array where the number of different integers in that array is exactly k.

  For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
  A subarray is a contiguous part of an array.

  Example 1:
  Input: nums = [1,2,1,2,3], k = 2
  Output: 7
  Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

  Example 2:
  Input: nums = [1,2,1,3,4], k = 3
  Output: 3
  Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

  Constraints:
  1 <= nums.length <= 2 * 104
  1 <= nums[i], k <= nums.length
*/


import java.util.HashMap;
import java.util.Map;

class Solution {

  // count number of subarrays with atmost k different integers
  private static int find(int[] nums, int k) {
    int n = nums.length;
    int l = 0, r = 0;
    int count = 0;
    Map<Integer, Integer> hmap = new HashMap<>(); // current number and it's frequency

    while (r < n) {
      hmap.put(nums[r], hmap.getOrDefault(nums[r], 0) + 1);
      while (hmap.size() > k) {
        int lval = hmap.get(nums[l]);
        hmap.put(nums[l], lval - 1);
        if (lval - 1 == 0)
          hmap.remove(nums[l]);
        l++;
      }
      count += (r - l + 1);
      r++;
    }
    return count;
  }

  public int subarraysWithKDistinct(int[] nums, int k) {

    // since it is kind of difficult to find number of subarrays with given
    // condition
    // hence we follow this approach

    // number of subarrays with sum <= k => the function call will return
    return (find(nums, k) - find(nums, k - 1));
  }
}