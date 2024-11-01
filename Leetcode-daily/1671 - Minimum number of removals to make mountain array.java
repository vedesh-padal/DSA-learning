// You may recall that an array arr is a mountain array if and only if:
// arr.length >= 3

// There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
// arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
// arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
// Given an integer array nums​​​, return the minimum number of elements to
// remove to make nums​​​ a mountain array.

// Example 1:
// Input: nums = [1,3,1]
// Output: 0
// Explanation: The array itself is a mountain array so we do not need to remove
// any elements.

// Example 2:
// Input: nums = [2,1,1,5,6,2,3,1]
// Output: 3
// Explanation: One solution is to remove the elements at indices 0, 1, and 5,
// making the array nums = [1,5,6,3,1].

// Constraints:
// 3 <= nums.length <= 1000
// 1 <= nums[i] <= 109
// It is guaranteed that you can make a mountain array out of nums.


class Solution {
  public int minimumMountainRemovals(int[] nums) {
    // inorder to make a mountain array
    // it should satisfy one of this:
    // 1. increasing, peak, decreasing
    // 2. increasing only
    // 3. decreasing only

    // it is like the "longest bitonic subsequence problem"
    // first, we traverse from the start till the end and find the LIS array
    // then from the back
    // then we sum up each index. specific ones and subtract 1 from the sum
    // inorder to get the longest bitonic subsequence array
    // and when calculating this, we keep track of the max. of these
    // and susbtract this max. from the size of the array, which would be our ans
    // this works becoz, to have a mountain array, everything should be increasing
    // and decreasing OR only one way

    int n = nums.length;
    int[] dpF = new int[n];
    int[] dpB = new int[n];

    for (int i = 0; i < n; i++) {
      dpF[i] = 1;
      dpB[i] = 1;
    }

    // from front LIS
    for (int curr = 0; curr < n; curr++) {
      for (int prev = 0; prev < curr; prev++) {
        if (nums[prev] < nums[curr]) {
          dpF[curr] = Math.max(dpF[curr], 1 + dpF[prev]);
        }
      }
    }

    // from the back LIS
    for (int curr = n - 1; curr >= 0; curr--) {
      for (int prev = n - 1; prev > curr; prev--) {
        if (nums[prev] < nums[curr]) {
          dpB[curr] = Math.max(dpB[curr], 1 + dpB[prev]);
        }
      }
    }

    // indicating peak of the mountain
    int maxi = 0;
    for (int i = 0; i < n; i++) {
      if (dpF[i] > 1 && dpB[i] > 1)
        maxi = Math.max(maxi, dpF[i] + dpB[i] - 1);
    }

    return (n - maxi);
  }
}