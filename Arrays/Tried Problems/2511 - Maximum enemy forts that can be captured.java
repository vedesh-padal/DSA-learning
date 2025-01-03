// EASY
// arrays, two-pointers

/*
    You are given a 0-indexed integer array forts of length n representing the
    positions of several forts. forts[i] can be -1, 0, or 1 where:

    -1 represents there is no fort at the ith position.
    0 indicates there is an enemy fort at the ith position.
    1 indicates the fort at the ith the position is under your command.

    Now you have decided to move your army from one of your forts at position i
    to an empty position j such that:
    0 <= i, j <= n - 1

    The army travels over enemy forts only. Formally, for all k where min(i,j) <
    k < max(i,j), forts[k] == 0.
    While moving the army, all the enemy forts that come in the way are captured.

    Return the maximum number of enemy forts that can be captured. In case it is
    impossible to move your army, or you do not have any fort under your command, return 0.

    Example 1:
    Input: forts = [1,0,0,-1,0,0,0,0,1]
    Output: 4
    Explanation:
    - Moving the army from position 0 to position 3 captures 2 enemy forts, at 1 and 2.
    - Moving the army from position 8 to position 3 captures 4 enemy forts.
    Since 4 is the maximum number of enemy forts that can be captured, we return 4.

    Example 2:
    Input: forts = [0,0,1,-1]
    Output: 0
    Explanation: Since no enemy fort can be captured, 0 is returned.

    Constraints:
    1 <= forts.length <= 1000
    -1 <= forts[i] <= 1
*/

class Solution {

    // will try with 2-pointers later

    // without two-pointers
    public int captureForts(int[] forts) {
        // the problem is simply finding how many consecutive 0s between 1 and -1 OR -1 and 1
        // we have to find the maximum among this
        int n = forts.length;
        boolean started = false;
        int maxi = 0, curr = 0; // curr => traversing count of no. of enemies between start and end positions

        // going from left to right:
        for (int i = 0; i < n; i++) {
            if (forts[i] == 1) {
                if (started)
                    curr = 0;
                started = true;
            }
            if (started && forts[i] == 0)
                curr++;
            if (started && forts[i] == -1) {    // reached an empty position / no fort
                maxi = Math.max(maxi, curr);
                curr = 0;
                started = false;
            }
        }

        int captFromLeft = maxi;

        // right to left traversal
        started = false; maxi = 0; curr = 0;
        for (int i = n-1; i >= 0; i--) {
            if (forts[i] == 1) {
                if (started)
                    curr = 0;
                started = true;
            }
            if (started && forts[i] == 0)
                curr++;
            if (started && forts[i] == -1) {
                maxi = Math.max(maxi, curr);
                started = false;
                curr = 0;
            }
            // System.out.println(i + " " + curr + " " + maxi);
        }
        // System.out.println(captFromLeft + " " + maxi);
        return Math.max(maxi, captFromLeft);
    }
}