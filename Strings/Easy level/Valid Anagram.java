/*
  Given two strings s and t, return true if t is an anagram of s, and false otherwise.

  An Anagram is a word or phrase formed by rearranging the letters of a different
  word or phrase, typically using all the original letters exactly once.

  Example 1:
  Input: s = "anagram", t = "nagaram"
  Output: true

  Example 2:
  Input: s = "rat", t = "car"
  Output: false
  
  Constraints:
  1 <= s.length, t.length <= 5 * 104
  s and t consist of lowercase English letters.
*/

class Solution {
  public boolean isAnagram1(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] chars1 = new int[26];
    int[] chars2 = new int[26];
    for (int i = 0; i < s.length(); i++) {
      chars1[s.charAt(i) - 97]++;
      chars2[t.charAt(i) - 97]++;
    }
    for (int i = 0; i < 26; i++) {
      if ((chars1[i] - chars2[i]) != 0) {
        return false;
      }
    }
    return true;
  }

  // ----------------------------------------
  // efficient and simple
  public boolean isAnagram2(String s, String t) {
    int len = s.length();
    if (len != t.length())
      return false;

    int[] count = new int[26];

    for (int i = 0; i < len; i++) {
      count[s.charAt(i) - 'a']++;
      count[t.charAt(i) - 'a']--;
    }

    for (int i = 0; i < 26; i++)
      if (count[i] != 0)
        return false;

    return true;
  }
}