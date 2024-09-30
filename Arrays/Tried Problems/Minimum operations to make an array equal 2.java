/*
 * You are given two integer arrays nums1 and nums2 of equal length n 
 * and an integer k. You can perform the following operation on nums1:

  Choose two indexes i and j and increment nums1[i] by k and decrement
   nums1[j] by k. In other words, nums1[i] = nums1[i] + k and nums1[j] = nums1[j] - k.
  nums1 is said to be equal to nums2 if for all indices i such that 
  0 <= i < n, nums1[i] == nums2[i].

  Return the minimum number of operations required to make nums1 equal 
  to nums2. If it is impossible to make them equal, return -1.

  Example 1:
  Input: nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
  Output: 2
  Explanation: In 2 operations, we can transform nums1 to nums2.
  1st operation: i = 2, j = 0. After applying the operation, nums1 = [1,3,4,4].
  2nd operation: i = 2, j = 3. After applying the operation, nums1 = [1,3,7,1].
  One can prove that it is impossible to make arrays equal in fewer operations.
  
  Example 2:
  Input: nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
  Output: -1
  Explanation: It can be proved that it is impossible to make the two arrays equal.

  Constraints:
  n == nums1.length == nums2.length
  2 <= n <= 105
  0 <= nums1[i], nums2[j] <= 109
  0 <= k <= 105
 */

import java.util.Arrays;

class Solution {
  public long minOperations(int[] nums1, int[] nums2, int k) {

    // we cant increment or decrement,
    // in this case all the elements in arr1 should be equal to arr2
    if (k == 0) {
      return Arrays.equals(nums1, nums2) ? 0 : -1;
    }

    // to check later, if the number of increment values
    // are equal to number of decrement values
    long incr = 0;
    long decr = 0;
    int n = nums1.length;

    for (int i = 0; i < n; i++) {
      if (nums1[i] == nums2[i])
        continue;

      int diff = Math.abs(nums1[i] - nums2[i]);
      if (diff % k != 0)
        return -1;

      if (nums1[i] < nums2[i])
        incr += (long) diff / k;
      else
        decr += (long) diff / k;
    }

    // if (incr == decr)
    // return incr; // number of operations

    // return -1;

    return (incr == decr) ? incr : -1;
  }
}