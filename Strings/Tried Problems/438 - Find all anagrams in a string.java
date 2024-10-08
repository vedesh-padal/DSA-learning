// MEDIUM LEVEL
// SLIDING WINDOW APPROACH
// similar to String compression question

// Given two strings s and p, return an array of all the start indices of p's
// anagrams
// in s. You may return the answer in any order.

// Example 1:
// Input: s = "cbaebabacd", p = "abc"
// Output: [0,6]
// Explanation:
// The substring with start index = 0 is "cba", which is an anagram of "abc".
// The substring with start index = 6 is "bac", which is an anagram of "abc".

// Example 2:
// Input: s = "abab", p = "ab"
// Output: [0,1,2]
// Explanation:
// The substring with start index = 0 is "ab", which is an anagram of "ab".
// The substring with start index = 1 is "ba", which is an anagram of "ab".
// The substring with start index = 2 is "ab", which is an anagram of "ab".

// Constraints:
// 1 <= s.length, p.length <= 3 * 104
// s and p consist of lowercase English letters.
import java.util.*;

class Solution {
  public List<Integer> findAnagrams(String s, String p) {
    int n = s.length(), m = p.length();
    List<Integer> res = new ArrayList<>();

    if (n < m)
      return res;

    int[] cnt1 = new int[26];
    int[] cnt2 = new int[26];

    for (int i = 0; i < m; i++) {
      cnt1[s.charAt(i) - 'a']++;
      cnt2[p.charAt(i) - 'a']++;
    }

    int matches = 0;
    for (int i = 0; i < 26; i++) {
      if (cnt1[i] == cnt2[i])
        matches++;
    }

    int l = 0;
    for (int r = m; r < n; r++) {
      if (matches == 26)
        res.add(l);

      // window expanded towards right
      int ind = s.charAt(r) - 'a';
      cnt1[ind]++;

      if (cnt1[ind] == cnt2[ind])
        matches++;
      else if (cnt2[ind] + 1 == cnt1[ind])
        matches--;

      // left side window contracted
      ind = s.charAt(l) - 'a';
      cnt1[ind]--;

      if (cnt1[ind] == cnt2[ind])
        matches++;
      else if (cnt2[ind] - 1 == cnt1[ind])
        matches--;

      l++;
    }
    if (matches == 26)
      res.add(l);
    return res;
  }
}