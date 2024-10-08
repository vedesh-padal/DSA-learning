// Given a rows x cols binary matrix filled with 0's and 1's, find the largest
// rectangle containing only 1's and return its area.

import java.util.Stack;

class Solution {

  private int largestHistogram(int[] arr) {
    Stack<Integer> stk = new Stack<>();
    int n = arr.length;
    int maxArea = 0;

    for (int i = 0; i < n; i++) {
      while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
        int element = stk.peek();
        stk.pop();
        int nse = i;
        int pse = stk.isEmpty() ? -1 : stk.peek();

        maxArea = Math.max(maxArea, (nse - pse - 1) * arr[element]);
      }
      stk.push(i);
    }

    while (!stk.isEmpty()) {
      int element = stk.peek();
      stk.pop();
      int nse = n;
      int pse = stk.isEmpty() ? -1 : stk.peek();

      maxArea = Math.max(maxArea, (nse - pse - 1) * arr[element]);
    }
    return maxArea;
  }

  public int maximalRectangle(char[][] matrix) {
    int n = matrix.length;
    int m = matrix[0].length;

    int[][] prefixSum = new int[n][m];

    for (int j = 0; j < m; j++) {
      int sum = 0;
      for (int i = 0; i < n; i++) {
        sum += (matrix[i][j] == '1') ? 1 : 0;
        if (matrix[i][j] == '0')
          sum = 0;
        prefixSum[i][j] = sum;
      }
    }

    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      maxArea = Math.max(maxArea, largestHistogram(prefixSum[i]));
    }
    return maxArea;
  }
}