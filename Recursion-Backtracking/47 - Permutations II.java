// MEDIUM
// recursion, back-tracking

/*
    Given a collection of numbers, nums, that MIGHT CONTAIN DUPLICATES,
    return all possible unique permutations in any order.

    Example 1:
    Input: nums = [1,1,2]
    Output:
    [[1,1,2],
    [1,2,1],
    [2,1,1]]

    Example 2:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    
    Constraints:
    1 <= nums.length <= 8
    -10 <= nums[i] <= 10
*/

import java.util.*;

class Solution {
    private List<List<Integer>> res;

    // APPROACH - 2, same time and space complexity as approach 1
    // using Set at each level, so that duplicate element can't be picked to swapped
    // with other elements in that level
    private void solve(int ind, int[] nums) {
        if (ind == nums.length) {
            List<Integer> al = new ArrayList<>();
            for (int num: nums)
                al.add(num);
            res.add(al);
            return;
        }

        Set<Integer> uniqueSet = new HashSet<>();
        for (int i = ind; i < nums.length; i++) {
            if (!uniqueSet.contains(nums[i])) {
                uniqueSet.add(nums[i]);
                swap(i, ind, nums);
                solve(ind + 1, nums);
                swap(i, ind, nums);
            }       
        }
    }

    private void swap(int i, int j, int[] nums) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        solve(0, nums);
        return res;
    }


    ///// ========= APPROACH 1 ====================
    // TC: O(n x n!)
    // SC: O(n) + O(n) [rec. stack space - auxillary]
    private void solve(int[] nums, List<Integer> al, Map<Integer, Integer> hmap) {

        if (al.size() == nums.length) {
            res.add(new ArrayList<>(al));   // TC: O(N) -> becoz copying all elements
            return;
        }

        for (int num: hmap.keySet()) {
            if (hmap.get(num) > 0) {
                // DO SOMETHING
                al.add(num);
                hmap.put(num, hmap.get(num) - 1);
                
                // EXPLORE
                solve(nums, al, hmap);

                // UNDO THAT SOMETHING
                al.remove(al.size() - 1);
                hmap.put(num, hmap.get(num) + 1);
            }
        }
    }

    public List<List<Integer>> permuteUnique_I(int[] nums) {
        // Difference between Permutations 1 and this one, is there can be duplicates
        // so instead of Set, we need to maintain a Map, to maintain count of each unique element
        // so that we can consider all elements in the recursive calls
        res = new ArrayList<>();
        Map<Integer, Integer> hmap = new HashMap<>();
        for (int num: nums)
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        
        solve(nums, new ArrayList<>(), hmap);
        return res;
    }
}