// Given n non-negative integers representing an elevation map where the width
// of each bar is 1, compute how much water it can trap after raining.

// Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
// Explanation: The above elevation map (black section) is represented by array
// [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
// are being trapped.

// Example 2:
// Input: height = [4,2,0,3,2,5]
// Output: 9

// Constraints:
// n == height.length
// 1 <= n <= 2 * 104
// 0 <= height[i] <= 105

class Solution {
  public int trap(int[] height) {
    int n = height.length;
    int[] prefixMax = new int[n];
    int[] suffixMax = new int[n];

    prefixMax[0] = height[0];
    suffixMax[n - 1] = height[n - 1];

    for (int i = 1; i < n; i++) {
      prefixMax[i] = Math.max(prefixMax[i - 1], height[i]);

      suffixMax[n - i - 1] = Math.max(suffixMax[n - i], height[n - i - 1]);
    }

    int total = 0;
    for (int i = 0; i < n; i++) {
      int leftMax = prefixMax[i];
      int rightMax = suffixMax[i];

      if (height[i] < leftMax && height[i] < rightMax)
        total += Math.min(leftMax, rightMax) - height[i];
    }
    return total;
  }

  // ==============================================
  public int trap_spaceOptimized(int[] height) {
    int n = height.length;
    if (n == 0)
      return 0; // Handle edge case for empty array

    int left = 0, right = n - 1;
    int leftMax = 0, rightMax = 0;
    int totalWater = 0;

    while (left < right) {
      if (height[left] < height[right]) {
        if (height[left] >= leftMax) {
          leftMax = height[left]; // Update left max
        } else {
          totalWater += leftMax - height[left]; // Calculate trapped water
        }
        left++; // Move the left pointer
      } else {
        if (height[right] >= rightMax) {
          rightMax = height[right]; // Update right max
        } else {
          totalWater += rightMax - height[right]; // Calculate trapped water
        }
        right--; // Move the right pointer
      }
    }
    return totalWater;
  }

}

// can be space optimized to O(1)