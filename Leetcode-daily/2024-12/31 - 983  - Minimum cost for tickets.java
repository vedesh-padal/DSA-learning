// MEDIUM
// arrays, dynamic-programming

/*
    You have planned some train traveling one year in advance. The days of the
    year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.

    Train tickets are sold in three different ways:
    a 1-day pass is sold for costs[0] dollars,
    a 7-day pass is sold for costs[1] dollars, and
    a 30-day pass is sold for costs[2] dollars.
    The passes allow that many days of consecutive travel.

    For example, if we get a 7-day pass on day 2, then we can travel for 7 days:
    2, 3, 4, 5, 6, 7, and 8.
    Return the minimum number of dollars you need to travel every day in the given list of days.

    Example 1:
    Input: days = [1,4,6,7,8,20], costs = [2,7,15]
    Output: 11
    Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
    On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
    On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
    On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
    In total, you spent $11 and covered all the days of your travel.

    Example 2:
    Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
    Output: 17
    Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
    On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
    On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
    In total, you spent $17 and covered all the days of your travel.

    Constraints:
    1 <= days.length <= 365
    1 <= days[i] <= 365
    days is in strictly increasing order.
    costs.length == 3
    1 <= costs[i] <= 1000
*/
import java.util.*;

class Solution {
    Map<Integer, Integer> memo;


    public int mincostTickets(int[] days, int[] costs) {
        int maxDay = Integer.MIN_VALUE;
        
        // to easily check instead of iterating if this person is travelling on that day
        // we store these days of travel in a set
        Set<Integer> travelDays = new HashSet<>();

        for (int day: days) {
            maxDay = Math.max(maxDay, day);
            travelDays.add(day);
        }
        
        // dp[i] => min. cost to reach till days[i]
        int[] dp = new int[maxDay + 1];
        dp[0] = 0;  // there is no cost involved in travelling on day 0

        for (int i = 1; i <= maxDay; i++) {
            if (!travelDays.contains(i)) {
                dp[i] = dp[i-1];
            }
            else {
                // clever way to avoid 3 if statements to avoid index out of bounds
                // becoz, dp[0] is anyways 0, and 0 here represents the cost we spent 
                // to travel on that day
                // dp[i] = Integer.MAX_VALUE;
                
                int day1Pass = costs[0] + dp[Math.max(0, (i - 1))];
                int day7Pass = costs[1] + dp[Math.max(0, (i - 7))];
                int day30Pass = costs[2] + dp[Math.max(0, (i - 30))];
                
                dp[i] = Math.min(day1Pass, Math.min(day7Pass, day30Pass));
            }
        }
        return dp[maxDay];
    }

    // ==========================
    // TOP DOWN - MEMOIZATION
    private int solve(int ind, int n, int[] days, int[] costs) {
        
        if (ind >= n) {
            return 0;
        }

        if (memo.containsKey(ind)) {
            return memo.get(ind);
        }

        // we have 3 possibilities to explore
        // if 1 day pass is taken
        int pass1Day = costs[0] + solve(ind + 1, n, days, costs);

        // if 7 days pass is taken
        // first, find how far with that 7 days pass we can go
        int maxReach = days[ind] + 7;
        int j = ind;
        while (j < n && days[j] < maxReach) {
            j++;
        }
        // j => indicates, on which next day from `days` should we buy the pass on
        int pass7Days = costs[1] + solve(j, n, days, costs);

        // if 30 days pass is taken
        maxReach = days[ind] + 30;
        j = ind;
        while (j < n && days[j] < maxReach) {
            j++;
        }
        int pass30Days = costs[2] + solve(j, n, days, costs);

        int spend = Math.min(pass1Day, Math.min(pass7Days, pass30Days));
        memo.put(ind, spend);

        return spend;
    }


    public int mincostTickets_topDownMemo(int[] days, int[] costs) {
        memo = new HashMap<>();
        int n = days.length;

        // starting from days[0]
        return solve(0, n, days, costs);
    }
}