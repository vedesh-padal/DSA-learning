class Solution {

  //// strangely this is taking less memory / beating more people by 6% margin -- not sure if this is leetcode thing
  // // to avoid TLE with normal recursion, we can use memoization (with hashmap) with recursion
  // // using recursion => top-down approach
  // static private int climb(int n, Map<Integer, Integer> memo) {
  // if (n == 0 || n == 1)
  // return 1;

  // if (!memo.containsKey(n)) {
  // memo.put(n, climb(n-1, memo) + climb(n-2, memo));
  // }

  // return memo.get(n);
  // }

  public int climbStairs(int n) {

    // // top-down recursion with memoization
    // Map<Integer, Integer> memo = new HashMap<>();
    // return climb(n, memo);

    // space optimized tabulation method:
    if (n == 0 || n == 1) {
      return 1;
    }

    int prev = 1, curr = 1; // initialised to 1 becoz, there is only 1 way to reach at 0 and 1 step
    for (int i = 2; i <= n; i++) {
      int temp = curr;
      curr = prev + curr;
      prev = temp;
    }

    return curr;

  }

}