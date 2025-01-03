// EASY
// arrays, dp
/*
    You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
    Once you pay the cost, you can either climb one or two steps.

    You can either start from the step with index 0, or the step with index 1.

    Return the minimum cost to reach the top of the floor.

    Example 1:
    Input: cost = [10,15,20]
    Output: 15
    Explanation: You will start at index 1.
    - Pay 15 and climb two steps to reach the top.
    The total cost is 15.

    Example 2:
    Input: cost = [1,100,1,1,1,100,1,1,100,1]
    Output: 6
    Explanation: You will start at index 0.
    - Pay 1 and climb two steps to reach index 2.
    - Pay 1 and climb two steps to reach index 4.
    - Pay 1 and climb two steps to reach index 6.
    - Pay 1 and climb one step to reach index 7.
    - Pay 1 and climb two steps to reach index 9.
    - Pay 1 and climb one step to reach the top.
    The total cost is 6.

    Constraints:
    2 <= cost.length <= 1000
    0 <= cost[i] <= 999
*/

class Solution {


    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // if (n == 0) return 0;
        // if (n == 1) return cost[0];
        // if (n == 2) return Math.min(cost[0], cost[1]);

        int prevPrev = cost[0], prev = cost[1];

        for (int i = 2; i < n; i++) {
            int curr = cost[i] + Math.min(prevPrev, prev);
            prevPrev = prev;
            prev = curr;
        }
        return Math.min(prev, prevPrev);
    }

    private int solve(int ind, int[] cost, int[] dp) {
        // you have already reached the top of the floor, so not cost to be paid
        if (ind >= cost.length) {
            return 0;
        }
    
        if (dp[ind] != -1)
            return dp[ind];
        
        int oneStep = cost[ind] + solve(ind + 1, cost, dp);
        int twoSteps = cost[ind] + solve(ind + 2, cost, dp);

        return dp[ind] = Math.min(oneStep, twoSteps);
    }

    public int minCostClimbingStairs_topDown_memo(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        java.util.Arrays.fill(dp, -1);

        return Math.min(solve(0, cost, dp), solve(1, cost, dp));
    }
}