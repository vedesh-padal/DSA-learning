// 13-10-2024
// HARD
// arrays, list, heap, sorting

// You have k lists of sorted integers in non-decreasing order. Find the
// smallest range that includes at least one number from each of the k lists.

// We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a
// < c if b - a == d - c.

// Example 1:
// Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
// Output: [20,24]
// Explanation:
// List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
// List 2: [0, 9, 12, 20], 20 is in range [20,24].
// List 3: [5, 18, 22, 30], 22 is in range [20,24].

// Example 2:
// Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
// Output: [1,1]

// Constraints:
// nums.length == k
// 1 <= k <= 3500
// 1 <= nums[i].length <= 50
// -105 <= nums[i][j] <= 105
// nums[i] is sorted in non-decreasing order.

import java.util.*;

class Triplet {
  int val;
  int listInd;
  int ind;

  public Triplet(int val, int listInd, int ind) {
    this.val = val;
    this.listInd = listInd;
    this.ind = ind;
  }
}

class Solution {

  // OPTIMAL
  // use minHeap to extract the min. element from track[]
  // as the start of the range
  public int[] smallestRange(List<List<Integer>> nums) {
    int k = nums.size();

    PriorityQueue<Triplet> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));

    int maxEle = Integer.MIN_VALUE;

    // do one iteration to put k elements into the minHeap
    for (int i = 0; i < k; i++) {
      // pushing the first element of each list
      // val, listInd, index of the element pushed from that ith list
      pq.offer(new Triplet(nums.get(i).get(0), i, 0));
      maxEle = Math.max(maxEle, nums.get(i).get(0));
    }

    int[] resRange = new int[] { (int) (-1e6), (int) (1e6) };

    while (!pq.isEmpty()) {
      Triplet curr = pq.peek();
      pq.poll();

      int minEle = curr.val;
      int listInd = curr.listInd;
      int ind = curr.ind;

      if (maxEle - minEle < resRange[1] - resRange[0]) {
        resRange[0] = minEle;
        resRange[1] = maxEle;
      }

      // now, we need better answer
      // so decrease the range from minimum side

      // if after incrementing the index from the min. element
      // having list, if it is within bounds, we pick the next element of it
      if (ind + 1 < nums.get(listInd).size()) {
        int nextEle = nums.get(listInd).get(ind + 1);
        pq.offer(new Triplet(nextEle, listInd, ind + 1));
        maxEle = Math.max(maxEle, nextEle);
      } else {
        break;
      }
    }
    return resRange;
  }

  // ===================================================================
  // Kind of brute force, coz, traversing k size array (track) every time
  // to get the min. element from the track[]
  // O(n * k)
  public int[] smallestRange_BETTER(List<List<Integer>> nums) {
    int k = nums.size();

    int[] track = new int[k]; // { 0., 0, 0, ... }

    int[] resRange = new int[] { (int) (-1e5), (int) (1e5) }; // (a, b)

    while (true) {

      int minEle = Integer.MAX_VALUE;
      int maxEle = Integer.MIN_VALUE;
      int minEleListInd = 0;

      for (int i = 0; i < k; i++) {
        int listInd = i;
        int eleInd = track[i];
        int element = nums.get(listInd).get(eleInd); // nums[listInd][eleInd];

        if (element < minEle) {
          minEle = element;
          minEleListInd = listInd;
        }
        maxEle = Math.max(maxEle, element);
      }

      // now u have found the satisfying range (min. and max. in that track)
      // range: (minEle, maxEle)

      if (maxEle - minEle < resRange[1] - resRange[0]) {
        resRange[0] = minEle;
        resRange[1] = maxEle;
      }

      // now shorten this range by moving min. ele index
      int nextInd = track[minEleListInd] + 1;
      if (nextInd >= nums.get(minEleListInd).size()) {
        break;
      }

      track[minEleListInd] = nextInd; // correct next index, so update in track
    }
    return resRange;
  }
}