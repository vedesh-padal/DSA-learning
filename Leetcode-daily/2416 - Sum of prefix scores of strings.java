class Trie {
  static class Node {
    Node[] links = new Node[26];
    int cntEndWith = 0;
    int cntPrefix = 0;

    boolean containsKey(char ch) {
      return links[ch - 'a'] != null;
    }

    Node get(char ch) {
      return links[ch - 'a'];
    }

    void put(char ch, Node node) {
      links[ch - 'a'] = node;
    }

    void increaseEnd() {
      cntEndWith++;
    }

    void increasePrefix() {
      cntPrefix++;
    }

    void deleteEnd() {
      cntEndWith--;
    }

    void reducePrefix() {
      cntPrefix--;
    }

    int getEnd() {
      return cntEndWith;
    }

    int getPrefix() {
      return cntPrefix;
    }
  }

  private Node root;

  public Trie() {
    root = new Node();
  }

  void insert(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if (!node.containsKey(ch)) {
        node.put(ch, new Node());
      }
      node = node.get(ch);
      node.increasePrefix();
    }
    node.increaseEnd();
  }

  int countWordsEqualTo(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if (node.containsKey(ch)) {
        node = node.get(ch);
      } else {
        return 0;
      }
    }
    return node.getEnd();
  }

  int countWordsStartingWith(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if (node.containsKey(ch)) {
        node = node.get(ch);
      } else {
        return 0;
      }
    }
    return node.getPrefix();
  }

  int countWordsStartingWithForAllSubstrings(String word) {
    Node node = root;
    int cnt = 0;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if (node.containsKey(ch)) {
        node = node.get(ch);
        cnt += node.getPrefix();
      } else {
        break;
      }
    }
    return cnt;
  }

  void erase(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if (node.containsKey(ch)) {
        node = node.get(ch);
        node.reducePrefix();
      } else {
        return;
      }
    }
    node.deleteEnd();
  }

}

class Solution {
  public int[] sumPrefixScores(String[] words) {
    int n = words.length;
    int[] res = new int[n];

    Trie trie = new Trie();

    for (String word : words) {
      trie.insert(word);
    }
    // now the trie is created with all the words
    // having the count of prefix till each character
    // and also the if the word ends there

    // now check every substring of word, and
    // count thek prefix occurrence in every word in the words array

    for (int i = 0; i < words.length; i++) {
      // int sum = 0;
      // for (int j = 1; j < words[i].length() + 1; j++) {
      // sum += trie.countWordsStartingWith(words[i].substring(0, j));
      // }

      res[i] = trie.countWordsStartingWithForAllSubstrings(words[i]);
    }
    return res;
  }
}