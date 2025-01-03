// MEDIUM
// arrays, dynamic-programming

/*
    You are given two integer arrays energyDrinkA and energyDrinkB of the same
    length n by a futuristic sports scientist. These arrays represent the energy
    boosts per hour provided by two different energy drinks, A and B, respectively.

    You want to maximize your total energy boost by drinking one energy drink per
    hour. However, if you want to switch from consuming one energy drink to the
    other, you need to wait for one hour to cleanse your system (meaning you
    won't get any energy boost in that hour).

    Return the maximum total energy boost you can gain in the next n hours.

    Note that you can start consuming either of the two energy drinks.

    Example 1:
    Input: energyDrinkA = [1,3,1], energyDrinkB = [3,1,1]
    Output: 5
    Explanation:
    To gain an energy boost of 5, drink only the energy drink A (or only B).

    Example 2:
    Input: energyDrinkA = [4,1,1], energyDrinkB = [1,1,3]
    Output: 7
    Explanation:
    To gain an energy boost of 7:
    Drink the energy drink A for the first hour.
    Switch to the energy drink B and we lose the energy boost of the second hour.
    Gain the energy boost of the drink B in the third hour.

    Constraints:
    n == energyDrinkA.length == energyDrinkB.length
    3 <= n <= 10^5
    1 <= energyDrinkA[i], energyDrinkB[i] <= 10^5
*/

import java.util.Arrays;

class Solution {

    // SPACE OPTIMIZED
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;

        // slightly different approach, instead of the switching concept
        // we are just choosing who is getting to accure the max. energy at each hour

        // we need to keep track of the max. energy accured by consuming drinks
        // here, `a` variable means, ending at drinkA
        // `b` means, ending at drinkB

        long a0 = 0, a1 = 0, b0 = 0, b1 = 0;

        // a0 => previously noted max. energy in 1st hour ending with A
        // specifically:
        // a[i] => max. energy in (i + 1) hours ending with A

        for (int i = 0; i < n; i++) {
            a1 = Math.max(a0 + energyDrinkA[i], b0);
            b1 = Math.max(b0 + energyDrinkB[i], a0);

            a0 = a1;
            b0 = b1;
        }
        return Math.max(a1, b1);
    }


    // this slow, compare to the top-down approach
    // BOTTOM-UP - TABULATION method
    public long maxEnergyBoost_TABULATION(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long[][] dp = new long[2][n];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        
        // base case
        dp[0][n-1] = energyDrinkA[n-1];
        dp[1][n-1] = energyDrinkB[n-1];

        for (int hour = n-2; hour >= 0; hour--) {
            for (int drinkType = 0; drinkType <= 1; drinkType++) {
                
                long currEnergy = (drinkType == 0) ? energyDrinkA[hour] : energyDrinkB[hour];
                
                long noSwitchDrink = currEnergy + dp[drinkType][hour + 1];
                long switchDrink = currEnergy + ((hour + 2 < n) ? dp[1 - drinkType][hour + 2] : 0);

                dp[drinkType][hour] = Math.max(noSwitchDrink, switchDrink);
            }
        }
        return Math.max(dp[0][0], dp[1][0]);
    }

    private long solve(int hour, int[] drinkA, int[] drinkB, int drinkType, long[][] dp) {
        if (hour >= drinkA.length) {
            return 0;
        }

        if (dp[drinkType][hour] != -1) {
            return dp[drinkType][hour];
        }

        long currEnergy = (drinkType == 1) ? drinkA[hour] : drinkB[hour];
        
        long switchDrink = currEnergy + solve(hour + 2, drinkA, drinkB, 1 - drinkType, dp);
        long noSwitchDrink = currEnergy + solve(hour + 1, drinkA, drinkB, drinkType, dp);

        return dp[drinkType][hour] = Math.max(switchDrink, noSwitchDrink);
    }

    public long maxEnergyBoost_TopDown(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long[][] dp = new long[2][n];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);

        // hour, drinkA, drinkB, drinkType
        long startAtA = solve(0, energyDrinkA, energyDrinkB, 1, dp);
        long startAtB = solve(0, energyDrinkA, energyDrinkB, 0, dp);

        // return Math.max(dp[0][0], dp[1][0]);
        return Math.max(startAtA, startAtB);
    }
}