// MEDIUM
// arrays, dynamic-programming

/*
    You are given an integer array nums and an integer target.

    You want to build an expression out of nums by adding one of the symbols '+' and '-' 
    before each integer in nums and then concatenate all the integers.

    For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 
    1 and concatenate them to build the expression "+2-1".
    Return the number of different expressions that you can build, which evaluates to target.

    Example 1:
    Input: nums = [1,1,1,1,1], target = 3
    Output: 5
    Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
    -1 + 1 + 1 + 1 + 1 = 3
    +1 - 1 + 1 + 1 + 1 = 3
    +1 + 1 - 1 + 1 + 1 = 3
    +1 + 1 + 1 - 1 + 1 = 3
    +1 + 1 + 1 + 1 - 1 = 3

    Example 2:
    Input: nums = [1], target = 1
    Output: 1

    Constraints:
    1 <= nums.length <= 20
    0 <= nums[i] <= 1000
    0 <= sum(nums[i]) <= 1000
    -1000 <= target <= 1000
*/

import java.util.Arrays;

// 3 WAYS OF DOING

class Solution {
    private int totalSum;

    private int find(int[] nums, int target) {
        int n = nums.length;
        int[] prev = new int[target + 1];
        
        prev[0] = (nums[0] == 0) ? 2 : 1;

        // adding one way of reaching target, from the 0th index
        // to the num in the 0th index if the at that place the num is <= target
        if (nums[0] != 0 && nums[0] <= target) {
            prev[nums[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            int[] curr = new int[target + 1];
            for (int sum = 0; sum <= target; sum++) {
                // take or notTake concept
                int notTake = prev[sum];
                int take = 0;
                if (nums[i] <= sum) {
                    take = prev[sum - nums[i]];
                }
                curr[sum] = take + notTake;
            }
            prev = curr;
        }

        return prev[target];
    }

    // THE OG SOLUTION - STRIVER : DP ON SUBSEQUENCES
    // but, a bit tricky to understand
    // based on subsequences and difference between totalSum and target
    // and reducing the space by half
    public int findTargetSumWays(int[] nums, int target) {
        totalSum = Arrays.stream(nums).sum();

        // Two subsets such that:
        // s1 + s2 = totalSum
        // s1 - s2 = target
        // we can derive:
        // s2 = (totalSum - target) / 2
        // inorder for us to have a proper s2, the above expression must be perfectly divisible by 2
        // if not, we cannot form such subset

        // % 2 => to check 
        if ((totalSum - target) < 0 || (totalSum - target) % 2 == 1) {
            return 0;
        }

        int newTarget = (totalSum - target) / 2;

        return find(nums, newTarget);
    }


    // SPACE Optimized Tabulation
    public int findTargetSumWays_spaceOptTab(int[] nums, int target) {
        totalSum = Arrays.stream(nums).sum();
        if (Math.abs(target) > totalSum)
            return 0;

        int n = nums.length;
        // just 1D DP is needed here, becoz we are depending on just the previous state
        int[] dp = new int[2*totalSum + 1];

        dp[nums[0] + totalSum] = 1;
        dp[-nums[0] + totalSum] += 1;

        // // the above thing can be reduced to simply:
        // dp[nums[0] + totalSum] = (nums[0] == 0) ? 2 : 1;

        for (int i = 1; i < n; i++) {
            int[] curr = new int[2*totalSum + 1];
            for (int sum = -totalSum; sum <= totalSum; sum++) {
                if (dp[sum + totalSum] > 0) {
                    curr[nums[i] + sum + totalSum] += dp[sum + totalSum];
                    curr[-nums[i] + sum + totalSum] += dp[sum + totalSum];
                }
            }
            dp = curr;
        }
        return dp[totalSum + target];
    }


    // ========================
    // 2D DP - Tabulation
    public int findTargetSumWays_Tabulation(int[] nums, int target) {
        totalSum = Arrays.stream(nums).sum();

        // base case, if the sum of the nums is already less than target
        // then, in no way, we can achieve the target
        // this hold true, becoz: 0 <= nums[i] <= 1000 [ positive nums only ]
        if (Math.abs(target) > totalSum)
            return 0;
        
        int n = nums.length;

        // No. of ways to reach the sum using the first index numbers
        int[][] dp = new int[n][2*totalSum + 1];

        // base cases:
        dp[0][nums[0] + totalSum] = 1;
        // here we are doing +1, to handle the edge case when nums[0] = 0
        dp[0][-nums[0] + totalSum] += 1;

        for (int i = 1; i < n; i++) {
            for (int sum = -totalSum; sum <= totalSum; sum++) {
                // if there are existing combinations of numbers that can yield
                // this sum using the previous elements, then only update the curr. state
                if (dp[i-1][sum + totalSum] > 0) {
                    // adding the curr. index number
                    dp[i][nums[i] + sum + totalSum] += dp[i-1][sum + totalSum];
                    
                    // subtracting
                    dp[i][-nums[i] + sum + totalSum] += dp[i-1][sum + totalSum];
                }
            }
        }
        
        return dp[n-1][target + totalSum];
    }

    private int solve(int ind, int currSum, int target, int[] nums, int[][] dp) {
        if (ind == nums.length) {
            return (currSum == target) ? 1 : 0;
        }

        // `currSum + totalSum` => to avoid the -ve index
        if (dp[ind][currSum + totalSum] != Integer.MIN_VALUE) {
            return dp[ind][currSum + totalSum];
        }

        // else, we have 2 possibilites, either we add or subtract
        int add = solve(ind + 1, currSum + nums[ind], target, nums, dp);
        int subtract = solve(ind + 1, currSum - nums[ind], target, nums, dp);

        return dp[ind][currSum + totalSum] = add + subtract;
    }


    // RECURSION WITH MEMOIZATION
    // but, not very much suited when constraints are high
    public int findTargetSumWays_memoizedRecursion(int[] nums, int target) {
        totalSum = 0;
        for (int num: nums)
            totalSum += num;

        // our solution is dependent on the `index` and `sum` at each index
        // that sum can be in the range of [-totalSum, totalSum]
        // since we can't have -ve indices in dp array, we init as follows

        // No. of ways to reach the target from the index with the current sum
        int[][] dp = new int[nums.length][2*totalSum + 1];  // +1 => to count 0
        for (int[] row: dp)
            Arrays.fill(row, Integer.MIN_VALUE);

        // index, currSum
        return solve(0, 0, target, nums, dp);   // calculating all the ways
    }
}