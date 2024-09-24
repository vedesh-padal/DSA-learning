import java.util.List;
import java.util.ArrayList;

class Solution {
  int n;

  private void find(String s, List<String> wordDict, int pos, String curr, List<String> res) {

    if (pos == n) {
      res.add(curr.trim());
      return;
    }

    StringBuffer sb = new StringBuffer();
    for (int i = pos; i < n; i++) {
      sb.append(s.charAt(i));
      if (wordDict.contains(sb.toString()))
        find(s, wordDict, i + 1, curr + " " + sb.toString(), res);

    }

  }

  public List<String> wordBreak(String s, List<String> wordDict) {
    List<String> res = new ArrayList<>();
    n = s.length();
    find(s, wordDict, 0, "", res);
    return res;
  }
}