import java.util.Arrays;

class Solution {
  public String longestCommonPrefix1(String[] strs) {
    int minLen = strs[0].length();
    for (String s : strs)
      minLen = Math.min(minLen, s.length());

    for (int i = 0; i < minLen; i++) {
      char c = strs[0].charAt(i);
      for (int j = 1; j < strs.length; j++) {
        if (strs[j].charAt(i) != c)
          return strs[0].substring(0, i);
      }
    }
    return strs[0].substring(0, minLen);
  }

  // method - 2
  // --------------------------------------------------------

  public String longestCommonPrefix2(String[] strs) {
    Arrays.sort(strs);
    int n = strs.length;
    String s1 = strs[0];
    String s2 = strs[n - 1];
    int i = 0;
    while (i < s2.length() && i < s1.length()) {
      if (s1.charAt(i) == s2.charAt(i))
        i++;
      else
        break;
    }
    return s1.substring(0, i);
  }
}