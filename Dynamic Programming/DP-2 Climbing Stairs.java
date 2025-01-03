class Solution {

  private int solve(int steps, int[] dp) {
    if (steps == 0)
      return 1;
    if (steps < 0)
      return 0;
    if (dp[steps] != -1)
      return dp[steps];

    return dp[steps] = solve(steps - 1, dp) + solve(steps - 2, dp);
  }

  public int climbStairs_TopDown(int n) {
    int[] dp = new int[n + 1];
    java.util.Arrays.fill(dp, -1);

    return solve(n, dp);
  }
  // ===============
  public int climbStairs_Tabulation(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }
  // ===============
  public int climbStairs(int n) {
    int prevPrev = 1, prev = 1;
    for (int i = 2; i <= n; i++) {
      int curr = prevPrev + prev;
      prevPrev = prev;
      prev = curr;
    }
    return prev;
  }

  // ==================================================================================================================
  // strangely this is taking less memory / beating more people by 6% margin --
  // not sure if this is leetcode thing
  // to avoid TLE with normal recursion, we can use memoization (with hashmap)
  // with recursion
  // using recursion => top-down approach
  @SuppressWarnings("unused")
  static private int climb(int n, java.util.Map<Integer, Integer> memo) {
    if (n == 0 || n == 1)
      return 1;

    if (!memo.containsKey(n)) {
      memo.put(n, climb(n - 1, memo) + climb(n - 2, memo));
    }
    return memo.get(n);
  }

  public int climbStairs_usingHashMap(int n) {

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