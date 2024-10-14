// 12-10-2024
// MEDIUM
// array, hash-table, heap

// You are given a 2D integer array intervals where intervals[i] = [lefti,
// righti] represents the inclusive interval [lefti, righti].

// You have to divide the intervals into one or more groups such that each
// interval is in exactly one group, and no two intervals that are in the same
// group intersect each other.

// Return the minimum number of groups you need to make.

// Two intervals intersect if there is at least one common number between them.
// For example, the intervals [1, 5] and [5, 8] intersect.

// Example 1:
// Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
// Output: 3
// Explanation: We can divide the intervals into the following groups:
// - Group 1: [1, 5], [6, 8].
// - Group 2: [2, 3], [5, 10].
// - Group 3: [1, 10].
// It can be proven that it is not possible to divide the intervals into fewer
// than 3 groups.

// Example 2:
// Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
// Output: 1
// Explanation: None of the intervals overlap, so we can put all of them in one
// group.

// Constraints:
// 1 <= intervals.length <= 105
// intervals[i].length == 2
// 1 <= lefti <= righti <= 106

import java.util.*;

class Solution {
  public int minGroups(int[][] intervals) {
    // sort the intervals based on the left (asc. order)
    Arrays.sort(intervals, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);

    // Min Heap to keep track of the ending time (right)
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int n = intervals.length;

    for (int i = 0; i < n; i++) {
      if (!pq.isEmpty() && pq.peek() < intervals[i][0]) {
        pq.poll();
      }

      // if the min heap was not empty, and
      // if we would have FOUND a group (i.e., min. ending time)
      // less than the curr. interval, then we can form a group
      // SO, UPDATE that group's ending time, by adding it to the Min. Heap

      pq.offer(intervals[i][1]);
    }

    // the size of the min. heap is the no. of groups that we have
    return pq.size();
  }
}