/*
  You are given an integer array nums and an integer target.

  You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

  For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
  Return the number of different expressions that you can build, which evaluates to target.

  Example 1:

  Input: nums = [1,1,1,1,1], target = 3
  Output: 5
  Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
  -1 + 1 + 1 + 1 + 1 = 3
  +1 - 1 + 1 + 1 + 1 = 3
  +1 + 1 - 1 + 1 + 1 = 3
  +1 + 1 + 1 - 1 + 1 = 3
  +1 + 1 + 1 + 1 - 1 = 3
  Example 2:

  Input: nums = [1], target = 1
  Output: 1
  

  Constraints:

  1 <= nums.length <= 20
  0 <= nums[i] <= 1000
  0 <= sum(nums[i]) <= 1000
  -1000 <= target <= 1000
*/

// similar to count partitions with given difference
class Solution {

  private static int find(int[] nums, int target) {
    int[] prev = new int[target + 1];
    int n = nums.length;

    if (nums[0] == 0)
      prev[0] = 2;
    else
      prev[0] = 1;

    if (nums[0] != 0 && nums[0] <= target) {
      prev[nums[0]] = 1;
    }

    for (int ind = 1; ind < n; ind++) {
      int[] curr = new int[target + 1];
      for (int t = 0; t <= target; t++) {
        int notTake = prev[t];
        int take = 0;
        if (nums[ind] <= t)
          take = prev[t - nums[ind]];

        curr[t] = take + notTake; // since we want to COUNT how many such possible expressions exists
      }
      prev = curr;
    }
    return prev[target];  // finally return the possible count
  }

  public int findTargetSumWays(int[] nums, int target) {
    int totalSum = 0;
    for (int i : nums)
      totalSum += i;

    if (totalSum - target < 0 || (totalSum - target) % 2 == 1)
      return 0;

    int k = (totalSum - target) / 2;

    // the positives are considered as one subset, and the negatives as another subset, and the difference between the subsests is the target
    return find(nums, k);
  }
}