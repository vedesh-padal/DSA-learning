// Given an integer array nums and an integer k, return the number of non-empty
// subarrays that have a sum divisible by k.

// A subarray is a contiguous part of an array.

// Example 1:

// Input: nums = [4,5,0,-2,-3,1], k = 5
// Output: 7
// Explanation: There are 7 subarrays with a sum divisible by k = 5:
// [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
// Example 2:

// Input: nums = [5], k = 9
// Output: 0

// Constraints:

// 1 <= nums.length <= 3 * 104
// -104 <= nums[i] <= 104
// 2 <= k <= 104
import java.util.HashMap;

class Solution {
  public int subarraysDivByK(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int prefixSum = 0, count = 0;
    for (int num : nums) {
      prefixSum = (prefixSum + num) % k;
      if (prefixSum < 0)
        prefixSum += k; // becoz, ex: -2 % 5 = -2, but we need positive mod 3

      count += map.getOrDefault(prefixSum, 0);
      map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
    }
    return count;
  }

  // ====================================
  // intuitive explanation
  public int subarraysDivByK_explanation(int[] nums, int k) {
    int count = 0;
    int prefixSum = 0;
    int[] remainderFrequency = new int[k];

    // There's initially one subarray (empty subarray) with remainder 0
    remainderFrequency[0] = 1;

    for (int num : nums) {
      prefixSum += num;

      // Calculate remainder of current prefix sum when divided by k
      int remainder = prefixSum % k;

      // Adjust remainder if it's negative to be in the range [0, k-1]
      if (remainder < 0) {
        remainder += k;
      }

      // If we have seen this remainder before, add the count of such remainders
      count += remainderFrequency[remainder];

      // Update the frequency of this remainder
      remainderFrequency[remainder]++;
    }

    return count;
  }
}