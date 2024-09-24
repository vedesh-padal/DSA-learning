public class Trie {
  static class Node {
    Node[] links = new Node[26];
    boolean flag = false; // indicates if the nodes marks the end of a word

    boolean containsKey(char ch)  {
      return links[ch - 'a'] != null;
    }

    void put(char ch, Node node) {
      links[ch - 'a'] = node;
    }

    Node get(char ch) {
      return links[ch - 'a'];
    }

    void setEnd() {
      flag = true;
    }

    boolean isEnd() {
      return flag;
    }
  }

  // Trie class
  private Node root;

  public Trie() {
    root = new Node();  // Trie with an empty root
  }

  // insert a word into the Trie
  // O(len)  len = length of the word
  public void insert(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      // create a new node for the letter if not present
      if (!node.containsKey(word.charAt(i)))  {
        node.put(word.charAt(i), new Node());
      }
      // move to the next node
      node = node.get(word.charAt(i));
    }
    // mark the end of the word
    node.setEnd();
  }

  public boolean search(String word)  {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (!node.containsKey(word.charAt(i)))  {
        return false;
      }
      node = node.get(word.charAt(i));
    }
    return node.isEnd();
  }

  public boolean startsWith(String prefix)  {
    Node node = root;
    for (int i = 0; i < prefix.length(); i++) {
      // if a letter is not found, there is no word
      // with the given prefix
      if (!node.containsKey(prefix.charAt(i)))  {
        return false;
      }
      node = node.get(prefix.charAt(i));
    }
    return true;
  }
}