// MEDIUM
// arrays, prefix-sum, binary-search

// You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].

// Each queries[i] represents the following action on nums:

// Decrement the value at each index in the range [li, ri] in nums by at most vali.
// The amount by which each value is decremented can be chosen independently for each index.
// A Zero Array is an array with all its elements equal to 0.

// Return the minimum possible non-negative value of k, such that after processing the 
// first k queries in sequence, nums becomes a Zero Array. If no such k exists, return -1.

// Example 1:
// Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]
// Output: 2
// Explanation:
// For i = 0 (l = 0, r = 2, val = 1):
// Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
// The array will become [1, 0, 1].
// For i = 1 (l = 0, r = 2, val = 1):
// Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
// The array will become [0, 0, 0], which is a Zero Array. Therefore, the minimum value of k is 2.

// Example 2:
// Input: nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]
// Output: -1
// Explanation:
// For i = 0 (l = 1, r = 3, val = 2):
// Decrement values at indices [1, 2, 3] by [2, 2, 1] respectively.
// The array will become [4, 1, 0, 0].
// For i = 1 (l = 0, r = 2, val = 1):
// Decrement values at indices [0, 1, 2] by [1, 1, 0] respectively.
// The array will become [3, 0, 0, 0], which is not a Zero Array.

// Constraints:
// 1 <= nums.length <= 105
// 0 <= nums[i] <= 5 * 105
// 1 <= queries.length <= 105
// queries[i].length == 3
// 0 <= li <= ri < nums.length
// 1 <= vali <= 5

class Solution {
    // BINARY SEARCH APPROACH -- avoids TLE
    private boolean canMakeZero(int[] nums, int[][] queries, int k) {
        int n = nums.length;

        int[] diff = new int[n + 1];

        for (int i = 0; i < k; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];

            diff[l] += val;
            diff[r + 1] -= val;
        }

        // prefix sum of diff is also handled here
        int currDiff = 0;
        for (int i = 0; i < n; i++) {
            // since nums[] is passed to this function, you wouldnt want to modify it
            // by decrementing it, as it will be needed for next iteration
            currDiff += diff[i];
            if (nums[i] > currDiff) {
                return false;
            }
        }
        return true;
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int m = queries.length;

        // we apply binary search to check how many queries to consider
        // `mid` will represent how many queries to be considered among m queries
        // if if that `mid` return that it is possible to find such Zero array transformation (I question code)
        // we will be greedy now and try finding even less mid
        int l = 0;
        int r = m;

        int res = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (canMakeZero(nums, queries, mid)) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }

    // =============================================================================
    // checking each from beginning
    // can be improved with binarySearch
    public int minZeroArray_allCheckingFromStart(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;

        boolean all0s = true;
        // edge case, if all nums[i] are already 0s, then we return 0
        for (int num : nums) {
            if (num != 0) {
                all0s = false;
                break;
            }
        }

        if (all0s)
            return 0;

        int[] diff = new int[n];

        for (int i = 0; i < m; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            diff[l] += queries[i][2];
            if (r + 1 < n)
                diff[r + 1] -= queries[i][2];

            // to avoid prefix sum of diff array, we are doing that on the go
            boolean allZeros = true;
            int curr = 0;
            for (int j = 0; j < n; j++) {
                curr += diff[j];

                // nums[j] -= curr; => we dont subtract becoz, next iteration needs nums[] to be same as original
                if (nums[j] > curr) {
                    allZeros = false;
                    break;
                }

            }
            if (allZeros) {
                return (i + 1);
            }
        }
        return -1;
    }
}