import java.util.*;

class Solution {
  int n;

  // NOT VERY EFFICIENT WAY TO SOLVE
  // efficient way would be to use Trie Data Structure - yet to learn and solve
  private int find(int ind, String s, Set<String> dict, int[] memo) {
    if (ind == n)
      return 0;

    if (memo[ind] != Integer.MAX_VALUE)
      return memo[ind];

    int minExtraChars = Integer.MAX_VALUE;
    StringBuilder sb = new StringBuilder();
    for (int i = ind; i < n; i++) {
      sb.append(s.charAt(i));
      int extraChars = 0;
      if (!dict.contains(sb.toString()))
        extraChars = sb.length();

      int currExtraChars = extraChars + find(i + 1, s, dict, memo);
      minExtraChars = Math.min(currExtraChars, minExtraChars);
    }
    return memo[ind] = minExtraChars;
  }

  public int minExtraChar(String s, String[] dictionary) {
    Set<String> hset = new HashSet<>(Arrays.asList(dictionary));
    n = s.length();
    int[] memo = new int[n];
    Arrays.fill(memo, Integer.MAX_VALUE);

    return find(0, s, hset, memo);
  }


  // DIDNT WORK:
  // public int minExtraChar(String s, String[] dictionary) {
  //   String str = new String(s);
  //   int n = dictionary.length;
  //   for (int i = 0; i < n; i++) {
  //     int ind = str.indexOf(dictionary[i]);
  //     if (ind == -1)
  //       continue;
  //     else {
  //       int len = dictionary[i].length();
  //       int strLen = str.length();

  //       if (ind + len < strLen)
  //         str = str.substring(0, ind) + str.substring(ind + len);
  //       else
  //         str = str.substring(0, ind);
  //     }
  //   }
  //   return str.length();
  // }
}