class Trie {
  static class Node {
    Node[] links = new Node[26];
    boolean flag = false;

    boolean containsKey(char ch) {
      return links[ch - '0'] != null;
    }

    void put(char ch, Node node) {
      links[ch - '0'] = node;
    }

    Node get(char ch) {
      return links[ch - '0'];
    }

    void setEnd() {
      flag = false;
    }

    boolean isEnd() {
      return flag;
    }

  }

  private Node root;

  public Trie() {
    root = new Node();
  }

  public void insert(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (!node.containsKey(word.charAt(i))) {
        node.put(word.charAt(i), new Node());
      }
      node = node.get(word.charAt(i));
    }
    node.setEnd();
  }

  public boolean search(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (!node.containsKey(word.charAt(i))) {
        return false;
      }
      node = node.get(word.charAt(i));
    }
    return node.isEnd();
  }

  public boolean startsWith(String prefix) {
    Node node = root;
    for (int i = 0; i < prefix.length(); i++) {
      if (!node.containsKey(prefix.charAt(i))) {
        return false;
      }
      node = node.get(prefix.charAt(i));
    }
    return true;
  }

  public int getPrefixLen(String prefix) {
    Node node = root;
    int cnt = 0;
    for (int i = 0; i < prefix.length(); i++) {
      if (!node.containsKey(prefix.charAt(i))) {
        return cnt;
      }
      node = node.get(prefix.charAt(i));
      cnt++;
    }
    return cnt;
  }
}

class Solution {

  // using Trie data structure
  public int longestCommonPrefix(int[] arr1, int[] arr2) {
    int lcpLen = 0;

    Trie trie = new Trie();

    for (int num : arr2)
      trie.insert(Integer.toString(num));

    for (int num : arr1) {
      int currPreLen = trie.getPrefixLen(Integer.toString(num));
      lcpLen = Math.max(currPreLen, lcpLen);
    }
    return lcpLen;
  }

  // ========================================================
  // BRUTE FORCE - ofc, TLE
  public int longestCommonPrefix_BRUTE(int[] arr1, int[] arr2) {
    int n1 = arr1.length, n2 = arr2.length;

    int maxCommonLen = 0;

    for (int i = 0; i < n1; i++) {
      String a = Integer.toString(arr1[i]);
      for (int j = 0; j < n2; j++) {
        String b = Integer.toString(arr2[j]);
        int minLen = Math.min(a.length(), b.length());
        int commonLen = 0;
        for (int k = 0; k < minLen; k++) {
          if (a.charAt(k) == b.charAt(k)) {
            commonLen++;
            maxCommonLen = Math.max(commonLen, maxCommonLen);
          } else
            break;
        }
      }
    }

    return maxCommonLen;
  }
}