// Given two strings s1 and s2, return true if s2 contains a
// permutation
// of s1, or false otherwise.

// In other words, return true if one of s1's permutations is the substring of
// s2.

// Example 1:
// Input: s1 = "ab", s2 = "eidbaooo"
// Output: true
// Explanation: s2 contains one permutation of s1 ("ba").

// Example 2:
// Input: s1 = "ab", s2 = "eidboaoo"
// Output: false

// Constraints:
// 1 <= s1.length, s2.length <= 104
// s1 and s2 consist of lowercase English letters.

class Solution {

  // OVER OPTIMIZED
  public boolean checkInclusion(String s1, String s2) {
    int n = s2.length(), m = s1.length();
    if (m > n)
      return false;

    int[] cnt1 = new int[26];
    int[] cnt2 = new int[26];

    for (int i = 0; i < m; i++) {
      cnt1[s1.charAt(i) - 'a']++;
      cnt2[s2.charAt(i) - 'a']++;
    }

    // keeps track of the number of character counts
    // matched in each window (comparing to s1)
    int matches = 0;
    // one time we traverse cnt1, cnt2 to udpate matches
    for (int i = 0; i < 26; i++) {
      if (cnt1[i] == cnt2[i])
        matches++;
    }

    // now, we update matches by comparing each window
    // below, we used to check whole cnt array, but here
    // we just increment right most and decrement left most
    // and update matches and check if all count is same

    int l = 0;
    for (int r = m; r < n; r++) {
      if (matches == 26)
        return true;

      // window expanded to right
      int ind = s2.charAt(r) - 'a';
      cnt2[ind]++;
      if (cnt1[ind] == cnt2[ind])
        matches++;
      // you disturbed the matching count
      else if (cnt1[ind] + 1 == cnt2[ind])
        matches--;

      // left side window contracted
      ind = s2.charAt(l) - 'a';
      cnt2[ind]--;
      if (cnt1[ind] == cnt2[ind])
        matches++;
      else if (cnt1[ind] - 1 == cnt2[ind])
        matches--;

      l++;
    }
    return matches == 26;
  }

  // ========================================
  // EASY UNDERSTANDABLE
  public boolean checkInclusion_O_26n(String s1, String s2) {
    // s1 windows size character count comparision
    // will take O(26*n) time complexity

    int n = s2.length(), m = s1.length();
    // base case
    if (m > n)
      return false;

    int[] cnt1 = new int[26];
    int[] cnt2 = new int[26];

    for (int i = 0; i < m; i++)
      cnt1[s1.charAt(i) - 'a']++;
    for (int i = 0; i < m; i++)
      cnt2[s2.charAt(i) - 'a']++;

    // checking if the first windows matches
    if (allEqual(cnt1, cnt2))
      return true;

    for (int i = m; i < n; i++) {
      // next window
      cnt2[s2.charAt(i) - 'a']++;
      cnt2[s2.charAt(i - m) - 'a']--;

      if (allEqual(cnt1, cnt2))
        return true;
    }
    return false;
  }

  private boolean allEqual(int[] cnt1, int[] cnt2) {
    for (int i = 0; i < 26; i++) {
      if (cnt1[i] != cnt2[i])
        return false;
    }
    return true;
  }
}