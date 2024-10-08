// Given an array of integers heights representing the histogram's bar height
// where the width of each bar is 1, return the area of the largest rectangle in
// the histogram.

// Input: heights = [2,1,5,6,2,3]
// Output: 10
// Explanation: The above is a histogram where width of each bar is 1.
// The largest rectangle is shown in the red area, which has an area = 10 units.

// Input: heights = [2,4]
// Output: 4

import java.util.Stack;

class Solution {

  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    Stack<Integer> stk = new Stack<>();
    int maxArea = 0;

    // ~ O(N) logic
    // PSE code and popping time NSE logic cleverly
    for (int i = 0; i < n; i++) {
      while (!stk.isEmpty() && heights[stk.peek()] >= heights[i]) {
        int element = stk.peek();
        stk.pop();
        int nse = i;
        int pse = stk.isEmpty() ? -1 : stk.peek();

        maxArea = Math.max(maxArea, (nse - pse - 1) * heights[element]);
      }
      stk.push(i);
    }

    while (!stk.isEmpty()) {
      int nse = n;
      int element = stk.peek();
      stk.pop();
      int pse = stk.isEmpty() ? -1 : stk.peek();

      maxArea = Math.max(maxArea, (nse - pse - 1) * heights[element]);
    }

    return maxArea;

  }

  // ========================================================

  private int[] findPSE(int[] arr, int n) {
    Stack<Integer> stk = new Stack<>();
    int[] pse = new int[n];

    for (int i = 0; i < n; i++) {
      while (!stk.isEmpty() && arr[stk.peek()] >= arr[i])
        stk.pop();

      pse[i] = (stk.isEmpty()) ? -1 : stk.peek();
      stk.push(i);
    }
    return pse;
  }

  private int[] findNSE(int[] arr, int n) {
    Stack<Integer> stk = new Stack<>();
    int[] pse = new int[n];

    for (int i = n - 1; i >= 0; i--) {
      while (!stk.isEmpty() && arr[stk.peek()] >= arr[i])
        stk.pop();

      pse[i] = (stk.isEmpty()) ? n : stk.peek();
      stk.push(i);
    }
    return pse;
  }

  // TC: O(5N), SC: O(5N)
  public int largestRectangleArea_5N(int[] heights) {
    int n = heights.length;
    int[] pse = findPSE(heights, n);
    int[] nse = findNSE(heights, n);

    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      maxArea = Math.max(maxArea, (nse[i] - pse[i] - 1) * heights[i]);
    }
    return maxArea;
  }
}