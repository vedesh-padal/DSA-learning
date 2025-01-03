// MEDIUM
// recursion, back-tracking

/*
    Given an array nums of distinct integers, return all the possible 
    permutations. You can return the answer in any order.

    
    Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

    Example 2:
    Input: nums = [0,1]
    Output: [[0,1],[1,0]]

    Example 3:
    Input: nums = [1]
    Output: [[1]]

    Constraints:
    1 <= nums.length <= 6
    -10 <= nums[i] <= 10
    All the integers of nums are unique.
*/

import java.util.*;

class Solution {
    List<List<Integer>> res;

    private void solve(int ind, int[] nums) {
        if (ind == nums.length) {
            List<Integer> al = new ArrayList<>();
            for (int num: nums)
                al.add(num);
            
            res.add(al);
            return;
        }

        for (int i = ind; i < nums.length; i++) {
            swap(i, ind, nums);
            solve(ind + 1, nums);
            swap(i, ind, nums);
        }
    }

    private void swap(int i, int j, int[] nums) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // BETTER APPROACH USING SWAPPING
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        // index => which element to be swapped with every element
        // well, not every element, becoz that would result in duplicate permutations
        // we start swapping at each level (draw tree diagram, it will be clear), starting from this index, not 0
        solve(0, nums);
        return res;
    }


    // ==================================================
    private void permute(int[] nums, List<Integer> al, Set<Integer> hset) {
        if (al.size() == nums.length) {
            res.add(new ArrayList<>(al));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!hset.contains(nums[i])) {
                al.add(nums[i]);
                hset.add(nums[i]);
                permute(nums, al, hset);
                al.remove(al.size() - 1);
                hset.remove(nums[i]);
            }
        }
    }

    // CHECKING EVERY POSSIBIBLITY, AND REQUIRES HASHSET
    public List<List<Integer>> permute_I(int[] nums) {
        res = new ArrayList<>();
        permute(nums, new ArrayList<>(), new HashSet<>());
        return res;
    }
}