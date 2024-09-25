public class Trie2 {
  static class Node {
    Node[] links = new Node[26];
    int cntEndWith = 0;
    int cntPrefix = 0;

    boolean containsKey(char ch)  {
      return (links[ch - 'a'] != null);
    }  

    Node get(char ch) {
      return links[ch - 'a'];
    }

    void put(char ch, Node node)  {
      links[ch - 'a'] = node;
    }

    void increaseEnd() {
      cntEndWith++;
    }

    void increasePrefix() {
      cntPrefix++;
    }

    void deleteEnd()  {
      cntEndWith--;
    }

    void reducePrefix() {
      cntPrefix--;
    }

    int getEnd()  {
      return cntEndWith;
    }

    int getPrefix() {
      return cntPrefix;
    }

  }

  private Node root;

  public Trie2() {
    root = new Node();
  }

  void insert(String word)  {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (!node.containsKey(word.charAt(i)))  {
        node.put(word.charAt(i), new Node());
      }
      node = node.get(word.charAt(i));
      node.increasePrefix();
    }
    node.increaseEnd();
  }

  int countWordsEqualTo(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (node.containsKey(word.charAt(i))) {
        node = node.get(word.charAt(i));
      }
      else {
        return 0;
      }
    }
    return node.getEnd();
  }
  
  int countWordsStartingWith(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (node.containsKey(word.charAt(i))) {
        node = node.get(word.charAt(i));
      }
      else {
        return 0;
      }
    }
    return node.getPrefix();
  }

  // we are assuming that this word exists,
  // since it has been asked to erase
  void erase(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (node.containsKey(word.charAt(i))) {
        node = node.get(word.charAt(i));
        node.reducePrefix();
      }
      else {
        // that is why we are just returning
        return;
      }
    }
    node.deleteEnd();
  }

}