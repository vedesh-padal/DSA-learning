// EASY - but good no. of edge cases, had to run 5 times
// strings

// Your friend is typing his name into a keyboard. Sometimes, when typing a
// character c, the key might get long pressed, and the character will be typed
// 1 or more times.

// You examine the typed characters of the keyboard. Return True if it is
// possible that it was your friends name, with some characters (possibly none)
// being long pressed.

// Example 1:
// Input: name = "alex", typed = "aaleex"
// Output: true
// Explanation: 'a' and 'e' in 'alex' were long pressed.

// Example 2:
// Input: name = "saeed", typed = "ssaaedd"
// Output: false
// Explanation: 'e' must have been pressed twice, but it was not in the typed
// output.

// Constraints:
// 1 <= name.length, typed.length <= 1000
// name and typed consist of only lowercase English letters.

class Solution {
  public boolean isLongPressedName(String name, String typed) {
    if (typed.length() < name.length())
      return false;

    int l = 0, r = 0;

    while (l < name.length() && r < typed.length()) {
      if (name.charAt(l) == typed.charAt(r)) {
        l++;
        r++;
      } else if (l >= 1 && name.charAt(l - 1) == typed.charAt(r)) {
        r++;
      } else {
        return false;
      }
    }

    if (l != name.length() && r == typed.length())
      return false;

    while (r < typed.length()) {
      if (typed.charAt(r) == name.charAt(l - 1))
        r++;
      else
        return false;
    }
    return r == typed.length();
  }
}