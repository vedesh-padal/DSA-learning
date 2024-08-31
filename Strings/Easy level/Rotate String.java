/*
  Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.

  A shift on s consists of moving the leftmost character of s to the rightmost position.

  For example, if s = "abcde", then it will be "bcdea" after one shift.

  Example 1:
  Input: s = "abcde", goal = "cdeab"
  Output: true

  Example 2:
  Input: s = "abcde", goal = "abced"
  Output: false

  Constraints:
  1 <= s.length, goal.length <= 100
  s and goal consist of lowercase English letters.
*/
class Solution {

  public boolean rotateString1(String s, String goal) {
    return (s.length() == goal.length()) && (s + s).contains(goal);
  }

  // ----------------------------------------------------
  // this is actually an optimal one
  public boolean rotateString2(String s, String goal) {
    // you can rotate atmost length times
    int len = s.length();
    if (len != goal.length())
      return false;

    for (int i = 0; i <= len; i++) {
      if (rotateString(s, goal, i))
        return true;
    }
    return false;
  }

  private static boolean rotateString(String s, String goal, int times) {
    int len = s.length();
    for (int i = 0; i < len; i++) {
      if (s.charAt(i) != goal.charAt((i + times) % len))
        return false;
    }
    return true;
  }
}