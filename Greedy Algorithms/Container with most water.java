// You are given an integer array height of length n. There are n vertical lines
// drawn such that the two endpoints of the ith line are (i, 0) and (i,
// height[i]).

// Find two lines that together with the x-axis form a container, such that the
// container contains the most water.

// Return the maximum amount of water a container can store.

// Notice that you may not slant the container.

// Input: height = [1,8,6,2,5,4,8,3,7]
// Output: 49
// Explanation: The above vertical lines are represented by array
// [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the
// container can contain is 49.
// Example 2:

// Input: height = [1,1]
// Output: 1

// Constraints:

// n == height.length
// 2 <= n <= 105
// 0 <= height[i] <= 104


class Solution {
  // ALSO, GFG POTD 13-01-2025
  public int maxArea(int[] height) {
    int l = 0, r = height.length - 1;
    int maxWater = Integer.MIN_VALUE; // if n lower bound is 1, then we have to init with 0, so that we dont return INT_MIN

    while (l < r) {
      int water = (r - l) * Math.min(height[l], height[r]);
      maxWater = Math.max(maxWater, water);

      if (height[l] < height[r]) {
        l++;
      } else {
        r--;
      }
    }
    return maxWater;
  }
}