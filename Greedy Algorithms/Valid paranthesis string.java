// Given a string s containing only three types of characters: '(', ')' and '*',
// return true if s is valid.

// The following rules define a valid string:

// Any left parenthesis '(' must have a corresponding right parenthesis ')'.
// Any right parenthesis ')' must have a corresponding left parenthesis '('.
// Left parenthesis '(' must go before the corresponding right parenthesis ')'.
// '*' could be treated as a single right parenthesis ')' or a single left
// parenthesis '(' or an empty string "".

// Example 1:

// Input: s = "()"
// Output: true
// Example 2:

// Input: s = "(*)"
// Output: true
// Example 3:

// Input: s = "(*))"
// Output: true

// Constraints:

// 1 <= s.length <= 100
// s[i] is '(', ')' or '*'.

class Solution {
  // recursive solution
  // trying out all the ways
  private Boolean[][] dp;

  private boolean solve(String s, int ind, int cnt) {
    if (cnt < 0)
      return false;

    if (ind == s.length())
      return (cnt == 0);

    if (dp[ind][cnt] != null)
      return dp[ind][cnt];

    if (s.charAt(ind) == '(')
      return solve(s, ind + 1, cnt + 1);
    if (s.charAt(ind) == ')')
      return solve(s, ind + 1, cnt - 1);

    // now we have *
    return dp[ind][cnt] = (solve(s, ind + 1, cnt - 1) || solve(s, ind + 1, cnt) || solve(s, ind + 1, cnt + 1));
  }

  public boolean checkValidStringRECURSIVE(String s) {
    // we maintain index and count
    // count increases when ( and decreases when )
    int n = s.length();
    dp = new Boolean[n][n];

    return solve(s, 0, 0);
  }
  
  // ==================================================================
  // GREEDY SOLUTION
  public boolean checkValidStringGREEDY(String s) {
    int min = 0, max = 0;
    int n = s.length();

    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);
      if (ch == '(') {
        min++;
        max++;
      } else if (ch == ')') {
        min--;
        max--;
      } else {
        min--;
        max++;
      }

      // if at any point reducing `min` reduced it below 0, reset it to 0
      if (min < 0)
        min = 0;

      // handles the case when string is starting with ")"
      if (max < 0)
        return false;
    }

    // it would be a valid string
    // if the range is from starts from 0
    // that is the first character is included
    return (min == 0);
  }

}