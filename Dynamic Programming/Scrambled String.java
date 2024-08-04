import java.util.*;


class Solution {

  // this approach uses memoization, DP
  // since we are using DP, we should declare the hashMap globally
  static HashMap<String, Boolean> hmap = new HashMap<String, Boolean>();

  static boolean isScramble(String S1, String S2) {
    // code here
    if (S1.length() != S2.length())
      return false;

    int n = S1.length();
    if (n == 0)
      return true;

    if (S1.equals(S2))
      return true;

    String s1Copy = S1, s2Copy = S2;
    char[] t1 = s1Copy.toCharArray();
    char[] t2 = s2Copy.toCharArray();

    Arrays.sort(t1);
    Arrays.sort(t2);

    s1Copy = new String(t1);
    s2Copy = new String(t2);

    if (!s1Copy.equals(s2Copy))
      return false;

    String key = S1 + " " + S2;

    if (hmap.containsKey(key))
      return hmap.get(key);

    // declaring flag variable to store the result
    boolean flag = false;

    for (int i = 1; i < n; i++) {
      if (isScramble(S1.substring(0, i), S2.substring(0, i)) &&
          isScramble(S1.substring(i, n), S2.substring(i, n))) {
        flag = true;
        hmap.put(key, flag);
        return true;
      }

      if (isScramble(S1.substring(0, i), S2.substring(n - i, n)) &&
          isScramble(S1.substring(i, n), S2.substring(0, n - i))) {
        flag = true;
        hmap.put(key, flag);
        return true;
      }

      hmap.put(key, flag);
    }

    return false;
  }
}
