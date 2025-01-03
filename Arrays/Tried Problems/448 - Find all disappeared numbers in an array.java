// EASY
// arrays, hashtable, logic

/*
    Given an array nums of n integers where nums[i] is in the range [1, n],
    return an array of all the integers in the range [1, n] that do not appear in nums.

    Example 1:
    Input: nums = [4,3,2,7,8,2,3,1]
    Output: [5,6]

    Example 2:
    Input: nums = [1,1]
    Output: [2]

    Constraints:
    n == nums.length
    1 <= n <= 105
    1 <= nums[i] <= n

    Follow up: Could you do it without extra space and in O(n) runtime? You may
    assume the returned list does not count as extra space.
*/

import java.util.List;
import java.util.ArrayList;

class Solution {

    // PRE-ORDER TRAVERSAL - DFS
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        
        // LOGIC:
        // if the curr. element exists in the array in it's sorted position
        // mark it that it exists, here, we are doing that by negative sign
        for (int i = 0; i < n; i++) {
            int absNum = nums[i];
            if (absNum < 0) {
                absNum = -absNum;
            }
            if (nums[absNum - 1] > 0) {
                nums[absNum - 1] = -nums[absNum - 1];
            }
        }
        
        // then we check the nums[i] which are still positive
        // that means that the sorted positions did not exist
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0)
                res.add(i + 1);
        }
        return res;
    }
    
    // ============================================
    // not optimal, can take many swaps
    public List<Integer> findDisappearedNumbers_usingCycliSort(int[] nums) {
        // using Cyclic sort
        // atmost n-1 swaps
        // without using hashtable

        int i = 0;
        while (i < nums.length) {
            // nums[i] <= nums.length => becoz: nums[i] is considered as an index and -1 is being done
            if (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int t = nums[i];
                nums[i] = nums[t - 1];
                nums[t - 1] = t;
            }
            else {
                i++;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                res.add(i+1);
            }
        }
        return res;
    }
}