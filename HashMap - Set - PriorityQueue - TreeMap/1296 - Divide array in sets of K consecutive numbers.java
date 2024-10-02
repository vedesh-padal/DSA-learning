// SAME AS HAND OF STRAIGHTS QUESTION

// Given an array of integers nums and a positive integer k, check whether it is
// possible to divide this array into sets of k consecutive numbers.

// Return true if it is possible. Otherwise, return false.

// Example 1:
// Input: nums = [1,2,3,3,4,4,5,6], k = 4
// Output: true
// Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].

// Example 2:
// Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
// Output: true
// Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and
// [9,10,11].

// Example 3:
// Input: nums = [1,2,3,4], k = 3
// Output: false
// Explanation: Each array should be divided in subarrays of size 3.

// Constraints:
// 1 <= k <= nums.length <= 105
// 1 <= nums[i] <= 109

import java.util.*;

class Solution {

  // OPTIMAL, using sorting and count in hashmap
  public boolean isPossibleDivide(int[] nums, int k) {
    if (nums.length % k != 0)
      return false;

    Arrays.sort(nums);

    Map<Integer, Integer> cntMap = new HashMap<>();
    for (int num : nums)
      cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);

    for (int num : nums) {
      int groupStart = num;

      // count exhausted, move to next sorted element
      if (cntMap.get(num) == 0)
        continue;

      for (int i = 0; i < k; i++) {
        // NOTE:
        // it is not index that we incrementing, but
        // adding a number to the start of the group
        int currEle = groupStart + i;

        // the next consecutive element is not present, due to:
        // - exhausted by prev. group
        // - OR, next sorted consecutive element is not present in the nums
        if (cntMap.getOrDefault(currEle, 0) == 0) {
          return false;
        }

        cntMap.put(currEle, cntMap.get(currEle) - 1);
      }
    }
    return true;
  }

  // USING MINHEAP
  public boolean isPossibleDivide_MINHEAP(int[] nums, int k) {
    if (nums.length % k != 0)
      return false;

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int num : nums)
      minHeap.add(num);

    while (!minHeap.isEmpty()) {
      int groupStart = minHeap.poll();

      for (int i = 1; i < k; i++) {
        int currEle = groupStart + i;

        // there is no next consecutive element
        if (!minHeap.remove(currEle))
          return false;
      }
    }
    return true;

  }

  // using TreeMap
  public boolean isPossibleDivide_TREEMAP(int[] nums, int k) {
    if (nums.length % k != 0)
      return false;

    TreeMap<Integer, Integer> tmap = new TreeMap<>();
    for (int num : nums)
      tmap.put(num, tmap.getOrDefault(num, 0) + 1);

    while (!tmap.isEmpty()) {
      int startGroup = tmap.firstEntry().getKey();

      for (int i = 0; i < k; i++) {
        int currEle = startGroup + i;
        if (!tmap.containsKey(currEle))
          return false;

        tmap.put(currEle, tmap.get(currEle) - 1);

        // if after decreasing the count, if that became 0
        // remove that element
        if (tmap.get(currEle) < 1)
          tmap.remove(currEle);
      }
    }
    return true;
  }
}