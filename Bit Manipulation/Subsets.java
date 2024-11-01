import java.util.List;
import java.util.ArrayList;

class Solution {

  public List<List<Integer>> subsets(int[] nums) {
    int n = nums.length;
    int numSubsets = 1 << n;
    List<List<Integer>> ans = new ArrayList<>();

    for (int num = 0; num < numSubsets; num++) {
      List<Integer> al = new ArrayList<>();

      for (int j = 0; j < n; j++) {
        if (((num >> j) & 1) == 1) { // if that bit is set, we pick that number
          al.add(nums[j]);
        }
      }
      ans.add(al);
    }
    return ans;
  }

  private void findSubsets(int ind, int[] nums, List<List<Integer>> ans, List<Integer> ds) {
    if (ind == nums.length) {
      ans.add(new ArrayList<>(ds));
      return;
    }
    ds.add(nums[ind]);
    findSubsets(ind + 1, nums, ans, ds);
    ds.remove(ds.size() - 1);
    findSubsets(ind + 1, nums, ans, ds);
  }

  public List<List<Integer>> subsets_RECURSION(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    findSubsets(0, nums, ans, new ArrayList<>());
    return ans;
  }

}