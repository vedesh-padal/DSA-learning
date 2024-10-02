// MONOTONIC STACK BASED PROBLEM

// Given an array of integers temperatures represents the daily temperatures,
// return an array answer such that answer[i] is the number of days you have to
// wait after the ith day to get a warmer temperature. If there is no future day
// for which this is possible, keep answer[i] == 0 instead.

// Example 1:
// Input: temperatures = [73,74,75,71,69,72,76,73]
// Output: [1,1,4,2,1,1,0,0]

// Example 2:
// Input: temperatures = [30,40,50,60]
// Output: [1,1,1,0]

// Example 3:
// Input: temperatures = [30,60,90]
// Output: [1,1,0]

// Constraints:
// 1 <= temperatures.length <= 105
// 30 <= temperatures[i] <= 100

import java.util.Stack;

class Pair {
  int val, ind;
  public Pair(int val, int ind) {
    this.val = val;
    this.ind = ind;
  }
}

class Solution {
  public int[] dailyTemperatures(int[] temperatures) {
    Stack<Pair> stk = new Stack<>();
    int n = temperatures.length;
    int[] ans = new int[n];

    for (int i = n - 1; i >= 0; i--) {
      while (!stk.isEmpty() && stk.peek().val <= temperatures[i]) {
        stk.pop();
      }

      if (stk.isEmpty())
        ans[i] = 0;
      else {
        Pair peek = stk.peek();
        ans[i] = peek.ind - i;
      }

      stk.push(new Pair(temperatures[i], i));
    }
    return ans;
  }
}