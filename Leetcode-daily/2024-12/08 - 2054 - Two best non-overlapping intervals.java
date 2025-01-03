// MEDIUM
// array, binary-search, sorting, heap

// You are given a 0-indexed 2D integer array of events where events[i] =
// [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends
// at endTimei, and if you attend this event, you will receive a value of
// valuei. You can choose at most two non-overlapping events to attend such that
// the sum of their values is maximized.

// Return this maximum sum.

// Note that the start time and end time is inclusive: that is, you cannot
// attend two events where one of them starts and the other ends at the same
// time. More specifically, if you attend an event with end time t, the next
// event must start at or after t + 1.

// Input: events = [[1,3,2],[4,5,2],[2,4,3]]
// Output: 4
// Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.

// Input: events = [[1,3,2],[4,5,2],[1,5,5]]
// Output: 5
// Explanation: Choose event 2 for a sum of 5.

// Input: events = [[1,5,3],[1,5,1],[6,6,5]]
// Output: 8
// Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.

// Constraints:
// 2 <= events.length <= 105
// events[i].length == 3
// 1 <= startTimei <= endTimei <= 109
// 1 <= valuei <= 106

// SHOULD - try for Heap approach

import java.util.Arrays;

class Solution {

    // OPTIMAL - from codestoryWithMIK
    // NICE ONE - BINARY SEARCH
    private int n;
    private int[][] dp = new int[100001][3];

    private int binarySearch(int[][] events, int endTime) {
        int l = 0, r = n-1;
        int result = n;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (events[mid][0] > endTime) {
                result = mid;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        
        return result;
    }

    private int solve(int[][] events, int i, int count) {
        if (count == 2 || i >= n) {
            return 0;
        }

        if (dp[i][count] != -1) {
            return dp[i][count];
        }

        // next valid event index
        int nextValidIndex = binarySearch(events, events[i][1]);
        int take = events[i][2] + solve(events, nextValidIndex, count + 1);
        int notTake = solve(events, i + 1, count);

        dp[i][count] = Math.max(take, notTake);

        return dp[i][count];
    }

    public int maxTwoEvents(int[][] events) {
        n = events.length;
        // sort based on start time
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        for (int[] row: dp) {
            Arrays.fill(row, -1);
        }

        return solve(events, 0, 0);
    }

    // ==========================================================

    // brute force
    public int maxTwoEvents_BRUTE(int[][] events) {
        int n = events.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            // for the case when the first element in the iteration itself
            // is giving max val.
            res = Math.max(res, events[i][2]);

            int val = events[i][2];
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;

                // checking for overlapping events
                if (events[j][0] <= events[i][1] && events[j][1] >= events[i][0]) {
                    continue;
                }

                res = Math.max(res, val + events[j][2]);
            }
        }

        return res;
    }
}