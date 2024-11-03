// EASY
// string

// Given two strings s and goal, return true if and only if s can become goal
// after some number of shifts on s.

// A shift on s consists of moving the leftmost character of s to the rightmost position.

// For example, if s = "abcde", then it will be "bcdea" after one shift.

// Example 1:
// Input: s = "abcde", goal = "cdeab"
// Output: true

// Example 2:
// Input: s = "abcde", goal = "abced"
// Output: false

// Constraints:
// 1 <= s.length, goal.length <= 100
// s and goal consist of lowercase English letters.

class Solution {
  public boolean rotateString_simple(String s, String goal) {
    if (s.length() != goal.length())
      return false;

    String doubleS = s + s;
    // Ex: abcde + abcde = abcdeabcde => contains cdeab
    return doubleS.contains(goal);
  }

  // ==============================================
  public boolean rotateString(String s, String goal) {
    if (s.length() != goal.length())
      return false;

    for (int i = 0; i <= s.length(); i++) {
      if (rotate(s, goal, i))
        return true;
    }
    return false;
  }

  private boolean rotate(String s, String goal, int times) {
    // `times` here indicates no. of times rotated
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != goal.charAt((i + times) % s.length()))
        return false;
    }
    return true;
  }

}