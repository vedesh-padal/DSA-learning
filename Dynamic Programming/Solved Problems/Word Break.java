import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  private boolean wb(String s, List<String> wordDict, int start, boolean[] memo) {
    // base case: current start index is end of the string
    if (start == s.length())
      return true;

    // base case: if memo has value for start index
    if (memo[start])
      return false;

    for (String word : wordDict) {
      // if s starts with word at index start AND substring starting from end is word
      // breakable
      if (s.startsWith(word, start) && wb(s, wordDict, start + word.length(), memo))
        return true;
    }
    memo[start] = true;
    return false;
  }

  public boolean wordBreak(String s, List<String> wordDict) {
    // recursively calling to break the string
    return wb(s, wordDict, 0, new boolean[s.length()]);
  }

  // ===============================================================
  int n;

  // ravi - iterating over string
  private boolean wbPossible(int pos, String s, List<String> wordDict, Boolean[] dp) {

    // it was able to reach till the end, that means word break is possible
    if (pos == n)
      return true;

    if (dp[pos] != null)
      return dp[pos];

    for (int i = pos; i < n; i++) {
      // if contains condition is true, that means we have one word possible
      // then only we proceed to explore other words
      if (wordDict.contains(s.substring(pos, i + 1)) && wbPossible(i + 1, s, wordDict, dp))
        return dp[pos] = true;
    }

    return dp[pos] = false;
  }

  public boolean wordBreak_tried(String s, List<String> wordDict) {
    n = s.length();
    Boolean[] dp = new Boolean[n];
    return wbPossible(0, s, wordDict, dp);
  }


  // ------------------------------------------------------------
  public boolean wordBreak_I_Tried(String s, List<String> wordDict) {
    Set<String> breakWords = new HashSet<>();

    for (String word : wordDict) {
      int ind = s.indexOf(word);
      if (ind == -1)
        continue;
      else {
        int wordLen = word.length();
        breakWords.add(s.substring(ind, ind + wordLen));

        int sLen = s.length();
        System.out.println(s.length());
        if (ind + wordLen < sLen)
          s = s.substring(0, ind) + s.substring(ind + sLen);
        else
          s = s.substring(0, ind);

        System.out.println(s);
      }
    }

    System.out.println();
    if (wordDict.size() == 5) {
      for (String str : breakWords)
        System.out.println(str);
    }
    for (String breakWord : breakWords) {
      if (!wordDict.contains(breakWord))
        return false;
    }
    return true;
  }
}