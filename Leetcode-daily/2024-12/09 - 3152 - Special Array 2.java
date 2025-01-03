// MEDIUM
// arrays, prefix-sum, binary-search (should try later)

// An array is considered special if every pair of its adjacent elements contains two numbers with different parity.

// You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi] your task is to check that 
// subarray
//  nums[fromi..toi] is special or not.

// Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.

// Example 1:
// Input: nums = [3,4,1,2,6], queries = [[0,4]]
// Output: [false]
// Explanation:
// The subarray is [3,4,1,2,6]. 2 and 6 are both even.

// Example 2:
// Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]
// Output: [false,true]
// Explanation:
// The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
// The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the answer to this query is true.
 
// Constraints:
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 105
// 1 <= queries.length <= 105
// queries[i].length == 2
// 0 <= queries[i][0] <= queries[i][1] <= nums.length - 1

// good Arrays question

class Solution {
    
    // using Prefix sum - intuitive
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        
        // total count of violating indices till index i
        int[] cummSum = new int[n];
        cummSum[0] = 0;

        for (int i = 1; i < n; i++) {
            // two adjacent elements - pair - have same parity => violating indices
            if (nums[i] % 2 == nums[i-1] % 2) {
                cummSum[i] = cummSum[i-1] + 1;
            }
            else {
                cummSum[i] = cummSum[i-1];
            }
        }

        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int start = queries[i][0];
            int end = queries[i][1];

            if (cummSum[end] - cummSum[start] == 0) {
                res[i] = true;
            }
            else {
                res[i] = false;
            }
        }
        return res;
    }

    // ================================================================
    // TC: O(n*m)
    public boolean[] isArraySpecial_BRUTE(int[] nums, int[][] queries) {
        // int n = nums.length;
        int m = queries.length;
        boolean[] res = new boolean[m];

        for (int i = 0; i < m; i++) {
            int s = queries[i][0];
            int e = queries[i][1];

            boolean odd = ((nums[s] & 1) == 1);
            boolean not = false;
            for (int j = s; j <= e - 1; j++) {
                if (odd) {
                    if (((nums[j] & 1) == 1) && ((nums[j+1] & 1) == 0)) {
                        odd = false;
                        continue;
                    }
                    else {
                        not = true;
                        break;
                    }
                }
                else {
                    if (((nums[j] & 1) == 0) && ((nums[j+1] & 1) == 1)) {
                        odd = true;
                        continue;
                    }
                    else {
                        not = true;
                        break;
                    }
                }
            }
            if (not) 
                res[i] = false;
            else
                res[i] = true;
        }

        return res;
    }
}