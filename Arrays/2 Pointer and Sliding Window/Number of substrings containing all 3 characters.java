/*
  Given a string s consisting only of characters a, b and c.

  Return the number of substrings containing at least one occurrence of all these characters a, b and c.

  Example 1:
  Input: s = "abcabc"
  Output: 10
  Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", 
  "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 
  
  Example 2:
  Input: s = "aaacb"
  Output: 3
  Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

  Example 3:
  Input: s = "abc"
  Output: 1
  
  Constraints:
  3 <= s.length <= 5 x 10^4
  s only consists of a, b or c characters.
*/

class Solution {
  public int numberOfSubstrings(String s) {
    int n = s.length();
    int[] lastIndex = { -1, -1, -1 };
    int count = 0;
    int i = 0;
    while (i < n) {
      lastIndex[s.charAt(i) - 'a'] = i;
      if (lastIndex[0] != -1 && lastIndex[1] != -1 && lastIndex[2] != -1)
        count = count + (1 + Math.min(lastIndex[0], Math.min(lastIndex[1], lastIndex[2])));
      i++;
    }
    return count;
  }
}