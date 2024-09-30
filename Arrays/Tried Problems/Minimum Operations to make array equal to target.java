// LC 3229 - HARD - GREEDY
/*
  You are given two positive integer arrays nums and target, of the same length.

  In a single operation, you can select any subarray of nums and increment each 
  element within that subarray by 1 or decrement each element within that subarray by 1.

  Return the minimum number of operations required to make nums equal to the array target.

  Example 1:
  Input: nums = [3,5,1,2], target = [4,6,2,4]
  Output: 2
  Explanation:
  We will perform the following operations to make nums equal to target:
  - Increment nums[0..3] by 1, nums = [4,6,2,3].
  - Increment nums[3..3] by 1, nums = [4,6,2,4].

  Example 2:
  Input: nums = [1,3,2], target = [2,1,4]
  Output: 5
  Explanation:
  We will perform the following operations to make nums equal to target:
  - Increment nums[0..0] by 1, nums = [2,3,2].
  - Decrement nums[1..1] by 1, nums = [2,2,2].
  - Decrement nums[1..1] by 1, nums = [2,1,2].
  - Increment nums[2..2] by 1, nums = [2,1,3].
  - Increment nums[2..2] by 1, nums = [2,1,4].

  Constraints:
  1 <= nums.length == target.length <= 105
  1 <= nums[i], target[i] <= 108
 * 
 */

class Solution {
  // MORE INTUITIVE APPROACH --> bars of a graph approach
  public long minimumOperations_INTUITIVE(int[] nums, int[] target) {
    int n = nums.length;
    int[] diff = new int[n];
    for (int i = 0; i < n; i++)
      diff[i] = nums[i] - target[i];

    long ops = 0, incrOp = 0, decrOp = 0;

    for (int i = 0; i < n; i++) {
      // +ve bar
      if (diff[i] > 0) {
        if (incrOp < diff[i]) {
          long extra = diff[i] - incrOp;
          ops += extra;
        }
        incrOp = diff[i]; // put that bar equal to your curr diff, for next operation
        decrOp = 0; // be prepared for the next decrease operation coming ahead
      }
      // for -ve bar
      else if (diff[i] < 0) {
        if (decrOp < -diff[i]) {
          long extra = -diff[i] - decrOp;
          ops += extra;
        }
        decrOp = -diff[i];
        incrOp = 0;
      } else { // there curr. index in both nums and target is same, so no operation
        // but be prepared for next decrease or increase operation coming ahead
        decrOp = incrOp = 0;
      }
    }
    return ops;
  }

  // SIMPLE APPROACH
  public long minimumOperations_SIMPLE(int[] nums, int[] target) {
    int n = nums.length;
    int[] diff = new int[n];
    for (int i = 0; i < n; i++)
      diff[i] = nums[i] - target[i];

    long minOps = Math.abs(diff[0]);
    for (int i = 1; i < n; i++) {
      // if two adj. bars are of different signs
      if ((diff[i - 1] > 0 && diff[i] < 0) || (diff[i - 1] < 0 && diff[i] > 0)) {
        minOps += Math.abs(diff[i]);
      } else if (Math.abs(diff[i - 1]) < Math.abs(diff[i])) {
        minOps += Math.abs(diff[i] - diff[i - 1]);
      }
    }
    return minOps;
  }

  // EFFICIENT ONE:
  public long minimumOperations_SUPER_EFF(int[] nums, int[] target) {
    int n = nums.length;
    int prev = 0, curr = 0;
    long minOps = 0;
    for (int i = 0; i < n; i++) {
      curr = nums[i] - target[i];
      if ((curr > 0 && prev < 0) || (curr < 0 && prev > 0)) {
        minOps += Math.abs(curr);
      } else if (Math.abs(curr) > Math.abs(prev)) {
        minOps += Math.abs(curr - prev);
      }
      prev = curr;
    }
    return minOps;
  }
}