// Given an array of integers arr, replace each element with its rank.

// The rank represents how large the element is. The rank has the following
// rules:
// - Rank is an integer starting from 1.
// - The larger the element, the larger the rank. If two elements are equal, their
// rank must be the same.
// - Rank should be as small as possible.

// Example 1:
// Input: arr = [40,10,20,30]
// Output: [4,1,2,3]
// Explanation: 40 is the largest element. 10 is the smallest. 20 is the second
// smallest. 30 is the third smallest.

// Example 2:
// Input: arr = [100,100,100]
// Output: [1,1,1]
// Explanation: Same elements share the same rank.

// Example 3:
// Input: arr = [37,12,28,9,100,56,80,5,12]
// Output: [5,3,4,2,8,6,7,1,3]

// Constraints:
// 0 <= arr.length <= 105
// -109 <= arr[i] <= 109

import java.util.*;

class Pair {
  int val, ind, rank;
  public Pair(int val, int ind) {
    this.val = val;
    this.ind = ind;
  }
  public void setRank(int rank) {
    this.rank = rank;
  }
}

class Solution {

  // Optimal approach
  public int[] arrayRankTransform(int[] arr) {
    int n = arr.length;
    if (n == 0)
      return new int[] {};

    int[] nums = arr.clone(); // new thing learnt today
    Arrays.sort(nums);

    Map<Integer, Integer> hmap = new HashMap<>(); // ele, rank
    int j = 1;
    hmap.put(nums[0], j++);

    for (int i = 1; i < n; i++) {
      if (nums[i] == nums[i - 1])
        continue;
      else
        hmap.put(nums[i], j++);
    }

    for (int i = 0; i < n; i++)
      nums[i] = hmap.get(arr[i]);

    return nums;
  }

  // ====================================================
  // my initial approach - O(3n) + O(2nlogn) - INEFFICIENT
  public int[] arrayRankTransform_2SORTS(int[] arr) {
    int n = arr.length;

    if (n == 0)
      return new int[] {};

    Pair[] pairs = new Pair[n];

    for (int i = 0; i < n; i++)
      pairs[i] = new Pair(arr[i], i);

    Arrays.sort(pairs, (a, b) -> a.val - b.val);

    int rank = 1;
    pairs[0].setRank(1);
    for (int i = 1; i < n; i++) {
      if (pairs[i].val > pairs[i - 1].val)
        pairs[i].setRank(++rank);
      else
        pairs[i].setRank(rank);
    }

    Arrays.sort(pairs, (a, b) -> a.ind - b.ind);

    int[] res = new int[n];
    for (int i = 0; i < n; i++)
      res[i] = pairs[i].rank;

    return res;
  }
}