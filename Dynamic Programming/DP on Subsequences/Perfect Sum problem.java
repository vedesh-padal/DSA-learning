// DP ON Subsequences question

class Solution {

    static int MOD = (int)(1e9 + 7);

    private int solve(int[] nums, int ind, int target, Integer[][] dp) {
        if (ind < 0) {
            return (target == 0) ? 1 : 0;
        }

        if (dp[ind][target] != null) {
            return dp[ind][target];
        }

        int take = 0;
        int notTake = solve(nums, ind - 1, target, dp);

        if (nums[ind] <= target) {
            take = solve(nums, ind - 1, target - nums[ind], dp);
        }

        return dp[ind][target] = (take + notTake) % MOD;
    }

    public int perfectSum_memoized(int[] nums, int target) {
        int n = nums.length;
        Integer[][] dp = new Integer[n][target + 1];

        return solve(nums, n-1, target, dp);
    }


    // ==============================================
    public int perfectSum_tabulation(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n][target + 1];

        if (nums[0] <= target) 
            dp[0][0] = 1;
    
        // special case
        if (nums[0] == 0)
            dp[0][0] = 2;   
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                int notTake = dp[i-1][j];
                int take = 0;
                if (nums[i] <= j) {
                    take = dp[i-1][j - nums[i]];
                }
                dp[i][j] = (take + notTake) % MOD;
            }
        }

        return dp[n-1][target];
    }

    public int perfectSum_spaceOptimized(int[] nums, int target) {
        int n = nums.length;
        int[] prev = new int[target + 1];

        if (nums[0] <= target) {
            prev[0] = 1;
        }

        if (nums[0] == 0) {
            prev[0] = 2;
        }

        for (int i = 1; i < n; i++) {
            int[] curr = new int[target + 1];
            for (int j = 0; j <= target; j++) {
                int notTake = prev[j];
                int take = 0;
                if (nums[i] <= j) {
                    take = prev[j - nums[i]];
                }
                curr[j] = take + notTake;
            }
            prev = curr;
        }
        return prev[target];
    }
}