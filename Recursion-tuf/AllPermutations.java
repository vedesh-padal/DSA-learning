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