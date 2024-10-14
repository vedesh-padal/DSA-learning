// Given a string s, check if it can be constructed by taking a substring of it
// and appending multiple copies of the substring together.

// Example 1:
// Input: s = "abab"
// Output: true
// Explanation: It is the substring "ab" twice.

// Example 2:
// Input: s = "aba"
// Output: false

// Example 3:
// Input: s = "abcabcabcabc"
// Output: true
// Explanation: It is the substring "abc" four times or the substring "abcabc"
// twice.

// Constraints:
// 1 <= s.length <= 104
// s consists of lowercase English letters.

class Solution {
  public boolean repeatedSubstringPattern(String s) {
    int n = s.length();
    // NOTE:
    // 1. A substring can be repeated to form s, only if n % l == 0
    // 2. no. of times such substr can be repeated = n / l
    // 3. if after repeating, if that is equal to s, then we return true;

    // there must be one repitition, meaning that:
    // the first half is atleast repeated once, if that repeat is not matched
    // we decrease the length by 1 from n/2 to n/2 - 1 and check the above 3 conditions
    
    for (int i = n / 2; i >= 1; i--) {
      if (n % i == 0) {
        int times = n / i;
        String subStr = s.substring(0, i);
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < times; j++) {
          sb.append(subStr);
        }
        if (sb.toString().equals(s))
          return true;
      }
    }
    return false;
  }
}