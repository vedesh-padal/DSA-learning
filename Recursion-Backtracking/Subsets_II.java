/*
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */

import java.util.*;

// subsets without duplicates

public class Subsets_II {

  private void findNonDuplicateSubsets(int ind, int[] nums, List<List<Integer>> ans, List<Integer> ds) {
      ans.add(new ArrayList<>(ds));
      for (int i = ind; i < nums.length; i++) {
          if (i != ind && nums[i] == nums[i - 1]) continue;
          ds.add(nums[i]);
          findNonDuplicateSubsets(i + 1, nums, ans, ds);
          ds.remove(ds.size() - 1);
      }
  }


  public List<List<Integer>> subsetsWithDup(int[] nums) {
      List<List<Integer>> ans = new ArrayList<>();
      Arrays.sort(nums);
      findNonDuplicateSubsets(0, nums, ans, new ArrayList<>());
      return ans;
  }
}