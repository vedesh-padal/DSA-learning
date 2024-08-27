/*
  Given an integer array nums, find a subarray
  that has the largest product, and return the product.

  The test cases are generated so that the answer will fit in a 32-bit integer.

  Example 1:
  Input: nums = [2,3,-2,4]
  Output: 6
  Explanation: [2,3] has the largest product 6.

  Example 2:
  Input: nums = [-2,0,-1]
  Output: 0
  Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
  
  Constraints:
  1 <= nums.length <= 2 * 104
  -10 <= nums[i] <= 10
  The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
*/

class MaxProductSubarray {
  public int maxProductSubarray(int[] arr)  {
    
    int n = arr.length;
    int pre = 1, suff = 1;
    int maxi = Integer.MIN_VALUE;

    for (int i=0; i<n; i++) {
      if (pre == 0)  pre = 1;
      if (suff == 0)  suff = 1;

      pre *= arr[i];
      suff *= arr[n-i-1];

      maxi = Math.max(maxi, Math.max(pre, suff));

    }

    return maxi;
  }
}