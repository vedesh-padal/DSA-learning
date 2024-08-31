/*
  Given two strings s and t, determine if they are isomorphic.

  Two strings s and t are isomorphic if the characters in s can be replaced to get t.

  All occurrences of a character must be replaced with another character while preserving 
  the order of characters. No two characters may map to the same character, but a character may map to itself.

  Example 1:
  Input: s = "egg", t = "add"
  Output: true

  Explanation:
  The strings s and t can be made identical by:
  Mapping 'e' to 'a'.
  Mapping 'g' to 'd'.

  Example 2:
  Input: s = "foo", t = "bar"
  Output: false

  Explanation:
  The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.

  Example 3:
  Input: s = "paper", t = "title"
  Output: true

  Constraints:
  1 <= s.length <= 5 * 104
  t.length == s.length
  s and t consist of any valid ascii character.
*/


class Solution {
  public boolean isIsomorphic(String s, String t) {
    // stores index of chars in respective strings
    int[] indexS = new int[200]; // 200 becoz, approx num of ascii chars
    int[] indexT = new int[200];

    int len = s.length();

    for (int i = 0; i < len; i++) {
      if (indexS[s.charAt(i)] != indexT[t.charAt(i)])
        return false;

      indexS[s.charAt(i)] = i + 1;
      indexT[t.charAt(i)] = i + 1;
    }

    return true;
  }
}