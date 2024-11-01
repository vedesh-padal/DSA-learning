// You are given an integer array nums. A subsequence of nums is called a square
// streak if:

// The length of the subsequence is at least 2, and
// after sorting the subsequence, each element (except the first element) is the
// square of the previous number.
// Return the length of the longest square streak in nums, or return -1 if there
// is no square streak.

// A subsequence is an array that can be derived from another array by deleting
// some or no elements without changing the order of the remaining elements.

// Example 1:
// Input: nums = [4,3,6,16,8,2]
// Output: 3
// Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes
// [2,4,16].
// - 4 = 2 * 2.
// - 16 = 4 * 4.
// Therefore, [4,16,2] is a square streak.
// It can be shown that every subsequence of length 4 is not a square streak.

// Example 2:
// Input: nums = [2,3,5,6,7]
// Output: -1
// Explanation: There is no square streak in nums so return -1.

// Constraints:
// 2 <= nums.length <= 105
// 2 <= nums[i] <= 105

import java.util.*;

class Solution {

  public int longestSquareStreak(int[] nums) {
    // given that th
    Set<Integer> hset = new HashSet<>();
    int maxi = 0; // maxStreak

    // first add all of the nums into set
    for (int num : nums)
      hset.add(num);

    for (int num : nums) {

      // now, we check starting from each number
      // and if we find the squaring numbers in the set
      // we increase the streak, if not we break, and reset
      // and check starting from that number
      int streak = 0;
      long curr = num;

      // understanding why this while loop will be O(5) TC
      // lets take the best case (max. sq. streak increasing order)
      // by starting from the least numberand expecting the array is sorted
      // 2, 4, 16, 256, 65536, 2^32: 4294967296 (> 10^5) [ if written the if part
      // inside for loop ]
      // that means this while loop at the worst case (best case for the problem)
      // can run atmost 5 times
      while (hset.contains((int) curr)) {
        streak++;

        if (curr * curr > (int) (1e5))
          break;

        curr = curr * curr;
      }
      maxi = Math.max(maxi, streak);
    }
    return maxi < 2 ? -1 : maxi;
  }

  // ===========================================
  // first thought/approach
  public int longestSquareStreak_NLOGN(int[] nums) {

    Arrays.sort(nums); // but nlogn => TC
    // sorted, becoz, we will store in the hmap
    // the previous squares, so when sorted it will
    // be easier to notice and this is how this algo works

    Map<Integer, Integer> hmap = new HashMap<>();
    int maxi = 0; // maxStreak

    for (int num : nums) {
      int sqRt = (int) Math.sqrt(num);

      if (sqRt * sqRt == num && hmap.containsKey(sqRt)) {
        hmap.put(num, hmap.get(sqRt) + 1);
      } 
      else {
        hmap.put(num, 1);
      }
      maxi = Math.max(maxi, hmap.get(num));
    }
    // if maxStreak value is less than 2, we return -1 (as given in Q)
    return (maxi < 2) ? -1 : maxi;
  }
}