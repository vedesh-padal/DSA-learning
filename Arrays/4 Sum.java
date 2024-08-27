import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {

    int n = nums.length;
    List<List<Integer>> ans = new ArrayList<>();

    if (nums == null || n < 4)
      return ans;

    Arrays.sort(nums);

    // Early exit if the smallest possible sum is greater than target
    // or the largest possible sum is less than target
    if (4L * nums[0] > target || 4L * nums[n - 1] < target) {
      return ans;
    }

    for (int i = 0; i < n; i++) {
      if (i > 0 && nums[i] == nums[i - 1])
        continue;

      for (int j = i + 1; j < n; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1])
          continue;

        int k = j + 1;
        int l = n - 1;

        while (k < l) {
          long sum = nums[i] + nums[j] + nums[k] + nums[l];

          if (sum == target) {
            ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));

            k++;
            l--;

            // skip duplicates
            while (k < l && nums[k] == nums[k - 1])
              k++;
            while (k < l && nums[l] == nums[l + 1])
              l--;
          } else if (sum < target)
            k++;
          else
            l--;
        }
      }
    }

    return ans;
  }
}