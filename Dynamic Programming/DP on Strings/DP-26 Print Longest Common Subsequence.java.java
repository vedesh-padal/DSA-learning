
import java.util.*;

class Solution {

  // PRINT SINGLE LONGEST COMMON SUBSEQUENCE

  private static String print_longest_common_subsequence(String s, String t) {
    // Code here
    int n = s.length();
    int m = t.length();

    int[][] dp = new int[n + 1][m + 1];

    for (int i = 0; i <= n; i++)
      dp[i][0] = 0;
    for (int j = 0; j <= m; j++)
      dp[0][j] = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = 0 + Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    int i = n, j = n;
    int len = dp[n][m];
    StringBuilder str = new StringBuilder(""); // since we know that LCS length will be the same as dp[n][m]

    for (int k=0; k<len; k++)
      str.append("#");
    
    System.out.println(len);
    System.out.println("before printing LCS: " + str);
    System.out.println(str.capacity());
    System.out.println(str.length());

    int ind = len-1;

    while(i > 0 && j > 0) {
      if (s.charAt(i - 1) == t.charAt(j - 1)) {
        str.setCharAt(ind, s.charAt(i-1));
        // move diagonally in the DP table
        ind--;
        i--;
        j--;
      }
      else if (dp[i-1][j] > dp[i][j-1]) {
        i--;  // move up in the DP table
      }
      else {
        j--;  // move left in the DP table
      }

    }
    return str.toString();
    
  }

  public static void main(String[] args) {
    System.out.println(print_longest_common_subsequence("abcde", "bdgek"));
  }


  // ----------------------------------------------------------------------------------------

  // PRINT ALL LONGEST COMMON SUBSEQUENCES

  // since asked for all LCS, we need to solve it using backtracking, and TC can
  // be O(N^3)
  public List<String> all_longest_common_subsequences(String s, String t) {
    // Code here
    int n = s.length();
    int m = t.length();

    int[][] dp = new int[n + 1][m + 1];

    for (int i = 0; i <= n; i++)
      dp[i][0] = 0;
    for (int j = 0; j <= m; j++)
      dp[0][j] = 0;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = 0 + Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    Set<String> set = new HashSet<>();
    Map<String, List<String>> map = new HashMap<>();

    backtrack(dp, s, t, n, m, "", set, map);

    List<String> list = new ArrayList<>(set);
    Collections.sort(list);

    return list;
  }

  private static void backtrack(int[][] dp, String s, String t, int i, int j,
      String lcs, Set<String> res, Map<String, List<String>> memo) {

    if (i == 0 || j == 0) {
      res.add(new StringBuilder(lcs).reverse().toString());
      return;
    }

    String key = i + "," + j + "," + lcs;

    if (memo.containsKey(key)) {
      res.addAll(memo.get(key));
      return;
    }

    if (s.charAt(i - 1) == t.charAt(j - 1)) {
      // move diagonally
      backtrack(dp, s, t, i - 1, j - 1, lcs + s.charAt(i - 1), res, memo);

    } else {
      // we are moving in both directions
      // up and left in the dp array to check the length of the common subsequence
      // if the character representing for each of i, j, are different, we reach this
      // case
      // here is the difference,
      // in the tUf suggested solution, it assumes that there is only 1 LCS

      if (dp[i - 1][j] == dp[i][j]) { // compare with the upper element
        backtrack(dp, s, t, i - 1, j, lcs, res, memo);
      }
      if (dp[i][j - 1] == dp[i][j]) { // compare with the left side element
        backtrack(dp, s, t, i, j - 1, lcs, res, memo);
      }
    }

    List<String> lst = new ArrayList<>(res);
    memo.put(key, lst);

  }
}