// You are given an array nums of non-negative integers and an integer k.

// An array is called special if the bitwise OR of all of its elements is at least k.

// Return the length of the shortest special non-empty subarray
//  of nums, or return -1 if no special subarray exists.

// Example 1:
// Input: nums = [1,2,3], k = 2
// Output: 1
// Explanation:
// The subarray [3] has OR value of 3. Hence, we return 1.

// Example 2:
// Input: nums = [2,1,8], k = 10
// Output: 3
// Explanation:
// The subarray [2,1,8] has OR value of 11. Hence, we return 3.

// Example 3:
// Input: nums = [1,2], k = 0
// Output: 1
// Explanation:
// The subarray [1] has OR value of 1. Hence, we return 1.

// Constraints:
// 1 <= nums.length <= 2 * 105
// 0 <= nums[i] <= 109
// 0 <= k <= 109

class Solution {

  private void updateBits(int[] bits, int n, int diff) {
    for (int i = 0; i < 32; i++) {
      // if the bit is set, update the bitCount at that bit position
      // either increment by 1 or decrement by 1 based on diff
      if ((n & (1 << i)) != 0)
        bits[i] += diff;
    }
  }

  private int findWindowOR(int[] bits) {
    int or = 0;
    for (int i = 0; i < 32; i++) {
      if (bits[i] != 0) {
        or = or | (1 << i);
      }
    }
    return or;
  }

  public int minimumSubarrayLength(int[] nums, int k) {
    int miniLen = Integer.MAX_VALUE;

    // tracks how many times each bit was set when considering
    // and not considering those numbers during sliding window
    int[] bits = new int[32];

    // sliding window
    int l = 0;
    for (int r = 0; r < nums.length; r++) {
      // here, 1, indicates, we are incrementing as we are expanding the window
      updateBits(bits, nums[r], 1);

      // now we find the XOR of the current window
      int currXOR = findWindowOR(bits);

      // we keep shrinking until we keep finding the xor of this
      // windows is atleast k
      while (l <= r && currXOR >= k) {

        miniLen = Math.min(miniLen, r - l + 1);

        // since we shrinking, we decrement the count of bits
        // that are set in nums[l]
        updateBits(bits, nums[l], -1);

        // update XOR after decrementing bit count after window shrink
        currXOR = findWindowOR(bits);

        l++;
      }

    }
    // System.out.println(miniLen);
    return miniLen == Integer.MAX_VALUE ? -1 : miniLen;
  }
}