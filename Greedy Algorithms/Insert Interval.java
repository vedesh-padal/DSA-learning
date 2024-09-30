// You are given an array of non-overlapping intervals intervals where
// intervals[i] = [starti, endi] represent the start and the end of the ith
// interval and intervals is sorted in ascending order by starti. You are also
// given an interval newInterval = [start, end] that represents the start and
// end of another interval.

// Insert newInterval into intervals such that intervals is still sorted in
// ascending order by starti and intervals still does not have any overlapping
// intervals (merge overlapping intervals if necessary).

// Return intervals after the insertion.

// Note that you don't need to modify intervals in-place. You can make a new
// array and return it.

// Example 1:

// Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
// Output: [[1,5],[6,9]]
// Example 2:

// Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
// Output: [[1,2],[3,10],[12,16]]
// Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

// Constraints:

// 0 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti <= endi <= 105
// intervals is sorted by starti in ascending order.
// newInterval.length == 2
// 0 <= start <= end <= 105

import java.util.*;

class Solution {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> al = new ArrayList<>();
    int n = intervals.length;

    int i = 0;
    // left part (non-overlapping)
    while (i < n && intervals[i][1] < newInterval[0]) {
      al.add(new int[] { intervals[i][0], intervals[i][1] });
      i++;
    }

    // overlapping part
    while (i < n && intervals[i][0] <= newInterval[1]) {
      newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
      newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
      i++;
    }
    al.add(new int[] { newInterval[0], newInterval[1] });

    // right side - remaining non-overlapping part
    while (i < n) {
      al.add(new int[] { intervals[i][0], intervals[i][1] });
      i++;
    }

    return al.toArray(new int[al.size()][]);

    // // System.out.println(al);

    // int alSize = al.size();
    // // for (i = 0; i < alSize; i++) {
    // // System.out.println(al.get(i)[0] + " " + al.get(i)[1]);
    // // }
    // int[][] res = new int[alSize][2];

    // for (i = 0; i < alSize; i++) {
    // res[i][0] = al.get(i)[0];
    // res[i][1] = al.get(i)[1];
    // }

    // // System.gc();
    // return res;
  }
}