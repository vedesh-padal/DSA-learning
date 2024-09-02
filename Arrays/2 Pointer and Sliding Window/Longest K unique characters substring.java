/*
  Given a string you need to print the size of the longest possible substring that has
  exactly K unique characters. If there is no possible substring then print -1.

  Example 1:

  Input:
  S = "aabacbebebe", K = 3
  Output: 
  7
  Explanation: 
  "cbebebe" is the longest substring with 3 distinct characters.
  Example 2:

  Input: 
  S = "aaaa", K = 2
  Output: -1
  Explanation: 
  There's no substring with 2 distinct characters.
  Your Task:
  You don't need to read input or print anything. Your task is to complete the function longestKSubstr() 
  which takes the string S and an integer K as input and returns the length of the longest substring 
  with exactly K distinct characters. If there is no substring with exactly K distinct characters then return -1.

  Expected Time Complexity: O(|S|).
  Expected Auxiliary Space: O(|S|).

  Constraints:
  1 ≤ |S| ≤ 105
  1 ≤ K ≤ 26
  All characters are lowercase latin characters.


*/

class Solution {
  public int longestkSubstr(String s, int k) {
    // code here
    int n = s.length();
    int l = 0, r = 0, maxLen = -1;
    int distinct = 0;
    int[] freq = new int[26];

    while (r < n) {
      if (freq[s.charAt(r) - 'a'] == 0)
        distinct++;
      freq[s.charAt(r) - 'a']++;

      if (distinct > k) {
        freq[s.charAt(l) - 'a']--;
        if (freq[s.charAt(l) - 'a'] == 0)
          distinct--;
        l++;
      }

      if (distinct == k)
        maxLen = Math.max(maxLen, r - l + 1);
      r++;
    }
    return maxLen;
  }
}