import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
  
  // THIS IS INFACT OPTIMAL
  // somewhat better approach is to sort the array and then check if the
  // lastsmaller + 1 is the current element
  public int longestConsecutive1(int[] nums) {

    int n = nums.length;
    if (n == 0)
      return 0;

    Arrays.sort(nums);
    int lastSmaller = Integer.MIN_VALUE;
    int cnt = 0;
    int longest = 1;
    for (int i = 0; i < n; i++) {
      if (nums[i] - 1 == lastSmaller) {
        // a[i] is the next element of the current sequence
        cnt++;
        lastSmaller = nums[i];
      } else if (nums[i] != lastSmaller) {
        cnt = 1;
        lastSmaller = nums[i];
      }
      longest = Math.max(longest, cnt);
    }

    return longest;
  }

  // -----------------------
  // OPTIMAL APPROACH - meh - time and space consuming
  public int longestConsecutive2(int[] nums) {

    if (nums.length == 0)
      return 0;

    Set<Integer> set = new HashSet<>();

    for (int num : nums)
      set.add(num);

    int longest = 1;

    for (int it : set) {
      if (!set.contains(it - 1)) {
        int cnt = 1;
        int x = it;

        while (set.contains(x + 1)) {
          x = x + 1;
          cnt++;
        }

        longest = Math.max(cnt, longest);

      }
    }
    return longest;
  }

}