// EASY
// arrays, counting, prefix-sum

/*
    You are given a 2D integer array logs where each logs[i] = [birthi, deathi]
    indicates the birth and death years of the ith person.

    The population of some year x is the number of people alive during that year.
    The ith person is counted in year x's population if x is in the inclusive
    range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.

    Return the earliest year with the maximum population.

    Example 1:
    Input: logs = [[1993,1999],[2000,2010]]
    Output: 1993
    Explanation: The maximum population is 1, and 1993 is the earliest year with this population.

    Example 2:
    Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
    Output: 1960
    Explanation:
    The maximum population is 2, and it had happened in years 1960 and 1970.
    The earlier year between them is 1960.

    Constraints:
    1 <= logs.length <= 100
    1950 <= birthi < deathi <= 2050
*/

class Solution {
    public int maximumPopulation(int[][] logs) {
        int miniYear = Integer.MAX_VALUE, maxiYear = Integer.MIN_VALUE;
        for (int[] log: logs) {
            miniYear = Math.min(log[0], miniYear);
            maxiYear = Math.max(log[1], maxiYear);
        }
        int totalYears = maxiYear - miniYear + 1;
        int[] diff = new int[totalYears];

        for (int[] log: logs) {
            int start = log[0] - miniYear;
            int end = log[1] - miniYear;
            diff[start]++;
            diff[end]--;    // becoz they don't live on the ending year, so not diff[end + 1]--;
        }
        // now, prefixSum, to find, in each year, how much population lived
        for (int i = 1; i < totalYears; i++) {
            diff[i] = diff[i-1] + diff[i];
        }

        int maxi = Integer.MIN_VALUE, index = -1;
        for (int i = 0; i < totalYears; i++) {
            if (diff[i] > maxi) {
                maxi = diff[i];
                index = i;
            }
        }
        return (index + miniYear);
    }
}