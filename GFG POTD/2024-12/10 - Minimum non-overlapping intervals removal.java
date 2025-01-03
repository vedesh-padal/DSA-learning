// MEDIUM - EASY
// arrays, greedy, sorting

// Given a 2D array intervals[][] of representing intervals where intervals [i] = [starti, endi ]. 
// Return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

// Examples:
// Input: intervals[][] = [[1, 2], [2, 3], [3, 4], [1, 3]]
// Output: 1
// Explanation: [1, 3] can be removed and the rest of the intervals are non-overlapping.

// Input: intervals[][] = [[1, 3], [1, 3], [1, 3]]
// Output: 2
// Explanation: You need to remove two [1, 3] to make the rest of the intervals non-overlapping.

// Input: intervals[][] = [[1, 2], [5, 10], [18, 35], [40, 45]]
// Output: 0
// Explanation: All ranges are already non overlapping.

// Constraints:
// 1 ≤ intervals.size() ≤ 105
// |intervalsi| == 2
// 0 ≤ starti < endi <=5*104

import java.util.Arrays;

class Solution {
    static int minRemoval(int intervals[][]) {
        // code here
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int minEnd = intervals[0][1];
        int count = 0;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < minEnd) {
                count++;
                minEnd = Math.min(minEnd, intervals[i][1]);
            } else {
                minEnd = intervals[i][1];
            }
        }
        return count;
    }
}