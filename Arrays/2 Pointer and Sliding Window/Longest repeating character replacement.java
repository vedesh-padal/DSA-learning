/*
  You are given a string s and an integer k. You can choose any character of the string and 
  change it to any other uppercase English character. You can perform this operation at most k times.

  Return the length of the longest substring containing the same letter you can get after performing the above operations.

  Example 1:
  Input: s = "ABAB", k = 2
  Output: 4
  Explanation: Replace the two 'A's with two 'B's or vice versa.
  
  Example 2:
  Input: s = "AABABBA", k = 1
  Output: 4
  Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
  The substring "BBBB" has the longest repeating letters, which is 4.
  There may exists other ways to achieve this answer too.

  Constraints:
  1 <= s.length <= 10^5
  s consists of only uppercase English letters.
  0 <= k <= s.length
*/

// IMPORTANT AND COVERS ALMOST ALL CONCEPTS and cases

class Solution {
  public int characterReplacement(String s, int k) {
    // sliding window approach
    int n = s.length();
    int l = 0, r = 0;
    int maxLen = 0; // len of the longest substring containing the same letter you can get after
                    // performing atmost k replacements
    // upper case english letters
    int[] freq = new int[26];
    int maxf = 0;

    while (r < n) {
      freq[s.charAt(r) - 'A']++;
      maxf = Math.max(maxf, freq[s.charAt(r) - 'A']);

      // the below condition will help in judging if num of replacements
      // to be made are within our possibility, if not we still shrink the window from
      // left

      // // also this while loop can be replaced with if condition becoz,
      // // we need to keep track of the longest substring
      // while ((r - l + 1) - maxf > k) {
      if ((r - l + 1) - maxf > k) {
        freq[s.charAt(l) - 'A']--;
        maxf = 0;
        // // we can ommit this since, the next maxf we get will be less than this,
        // // and it will not contribute in minimizing ( (r - l + 1) - maxf )
        // for (int i=0; i<26; i++)
        // maxf = Math.max(maxf, freq[i]);
        l++;
      }
      // num of replacements are within our range
      // so calculate the longest substring size
      if ((r - l + 1) - maxf <= k) {
        maxLen = Math.max(maxLen, (r - l + 1));
      }
      r++;
    }
    return maxLen;
  }
}