/*
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

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

import java.util.ArrayList;
import java.util.List;

public class AllPermutations {

  private void recurPermuteUsinSwap(int ind, int[] nums, List<List<Integer>> ans)  {
      if (ind == nums.length) {
          ArrayList<Integer> al = new ArrayList<>();
          for (int n: nums)   
              al.add(n);
          ans.add(al);
          return;
      }
      for (int i = ind; i < nums.length; i++) {
          swap(i, ind, nums);
          recurPermuteUsinSwap(ind + 1, nums, ans);
          swap(i, ind, nums);
      }
  }

  private void swap(int i, int j, int[] nums) {
      int t = nums[i];
      nums[i] = nums[j];
      nums[j] = t;
  }


  // private void recursivePermute(int[] nums, boolean[] freq, List<List<Integer>> ans, List<Integer> ds)    {
  //     if (ds.size() == nums.length)   {
  //         ans.add(new ArrayList<>(ds));
  //         return;
  //     }
  //     for (int i=0; i < nums.length; i++) {
  //         if (!freq[i])   {
  //             freq[i] = true;
  //             ds.add(nums[i]);
  //             recursivePermute(nums, freq, ans, ds);
  //             ds.remove(ds.size() - 1);
  //             freq[i] = false;
  //         }
  //     }
  // }

  public List<List<Integer>> permute(int[] nums) {
      // List<List<Integer>> ans = new ArrayList<>();
      // boolean[] freq = new boolean[nums.length];
      // recursivePermute(nums, freq, ans, new ArrayList<>());
      // return ans;

      List<List<Integer>> ans = new ArrayList<>();
      recurPermuteUsinSwap(0, nums, ans);
      return ans;
  }
}