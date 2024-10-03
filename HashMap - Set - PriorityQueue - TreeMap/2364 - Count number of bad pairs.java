// You are given a 0-indexed integer array nums. A pair of indices (i, j) is a
// bad pair if i < j and j - i != nums[j] - nums[i].

// Return the total number of bad pairs in nums.

// Example 1:
// Input: nums = [4,1,3,3]
// Output: 5
// Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
// The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
// The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
// The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
// The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
// There are a total of 5 bad pairs, so we return 5.

// Example 2:
// Input: nums = [1,2,3,4,5]
// Output: 0
// Explanation: There are no bad pairs.

// Constraints:
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 109

import java.util.*;

class Solution {
  public long countBadPairs(int[] nums) {
    int n = nums.length;
    // total no. of pairs = nc2
    long totalPairs = (long) n * (n - 1) / 2;

    // BAD PAIR:
    // j - i != nums[j] - nums[i]
    // we can write it as:
    // nums[i] - i != nums[j] - j

    // now, there can be many bad pairs, why find soo many
    // just subtract good pairs from total pairs

    // so, GOOD pair:
    // nums[i] - i = nums[j] - i

    long goodPairs = 0;
    Map<Integer, Integer> hmap = new HashMap<>();

    for (int i = 0; i < n; i++) {
      int curr = nums[i] - i;

      // if such difference exists in hashmap,
      // then we found a pair
      goodPairs += (long) (hmap.getOrDefault(curr, 0));

      hmap.put(curr, hmap.getOrDefault(curr, 0) + 1);
    }
    return totalPairs - goodPairs;
  }
}