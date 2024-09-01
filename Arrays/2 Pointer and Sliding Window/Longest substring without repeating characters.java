/*

*/

import java.util.Arrays;

class Solution {
  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    int l = 0, r = 0;
    int maxLen = 0;
    int[] hash = new int[256];
    Arrays.fill(hash, -1);

    while (r < n) {
      if (hash[s.charAt(r)] != -1) {
        // if the repeating character is within the window
        // update the left pointer to the next position of where the repeating character appeared
        if (hash[s.charAt(r)] >= l) {
          l = hash[s.charAt(r)] + 1;
        }
      }
      maxLen = Math.max(maxLen, r - l + 1);
      hash[s.charAt(r)] = r; // updating the current char position
      r++;
    }
    return maxLen;
  }
}