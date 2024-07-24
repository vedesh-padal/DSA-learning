import java.util.*;

// returning the power set
// pick and not pick method
public class Subsets_Powerset {

  private void findSubsets(int ind, int[] nums, List<List<Integer>> ans, List<Integer> ds)    {
      if (ind == nums.length) {
          ans.add(new ArrayList<>(ds));
          return;
      }
      ds.add(nums[ind]);
      findSubsets(ind + 1, nums, ans, ds);
      ds.remove(ds.size() - 1);
      findSubsets(ind + 1, nums, ans, ds);
  }


  public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> ans = new ArrayList<>();
      findSubsets(0, nums, ans, new ArrayList<>());
      return ans;
  }
}