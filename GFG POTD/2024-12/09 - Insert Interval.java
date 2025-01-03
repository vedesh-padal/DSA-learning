// MEDIUM
// arrays, merging

// Geek has an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
// represent the start and the end of the ith event and intervals
// is sorted in ascending order by starti. He wants to add a new interval
// newInterval= [newStart, newEnd] where newStart and newEnd represent the start
// and end of this interval.

// Help Geek to insert newInterval into intervals such that intervals is still
// sorted in ascending order by starti and intervals still does not have any
// overlapping intervals (merge overlapping intervals if necessary).

// Examples:
// Input: intervals = [[1,3], [4,5], [6,7], [8,10]], newInterval = [5,6]
// Output: [[1,3], [4,7], [8,10]]
// Explanation: The newInterval [5,6] overlaps with [4,5] and [6,7].

// Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,9]
// Output: [[1,2], [3,10], [12,16]]
// Explanation: The new interval [4,9] overlaps with [3,5],[6,7],[8,10].

// Constraints:
// 1 ≤ intervals.size() ≤ 105
// 0 ≤ start[i], end[i] ≤ 109

import java.util.ArrayList;

class Solution {
    static ArrayList<int[]> insertInterval(int[][] intervals, int[] newInterval) {
        // code here
        ArrayList<int[]> res = new ArrayList<>();
        
        int nStart = newInterval[0];
        int nEnd = newInterval[1];
        
        for (int[] interval: intervals) {
            int start = interval[0];
            int end = interval[1];
            
            // overlapping not yet occurred, so add the current interval to res
            if (end < nStart) {
                res.add(new int[]{start, end});
            }
            
            // check if there is a overlapping possibility,
            // if yes, update the newInterval start and end, inorder to merge the intervals
            else if (start <= nEnd) {
                nStart = Math.min(start, nStart);
                nEnd = Math.max(end, nEnd);
            }
            
            // current interval will start after the merged interval
            else {
                res.add(new int[]{nStart, nEnd});
                nStart = start;
                nEnd = end;
            }
        }
        
        // we are yet to add the last merged interval
        res.add(new int[]{nStart, nEnd});
        
        return res;
        
    }
}