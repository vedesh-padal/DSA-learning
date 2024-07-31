/*
  A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

  Every adjacent pair of words differs by a single letter.
  Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
  sk == endWord
  Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence 
  from beginWord to endWord, or 0 if no such sequence exists.

  Example 1:

  Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
  Output: 5
  Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

  Example 2:

  Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
  Output: 0
  Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
*/

// return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

import java.util.*;

class Pair {
  String first;
  int second;

  Pair(String word, int steps) {
    this.first = word;
    this.second = steps;
  }
}

class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Queue<Pair> q = new LinkedList<>();
    q.add(new Pair(beginWord, 1));

    Set<String> st = new HashSet<>();
    for (int i = 0; i < wordList.size(); i++)
      st.add(wordList.get(i));

    st.remove(beginWord);

    // T.C.: N x word.length x 26 x logN
    while (!q.isEmpty()) {
      String word = q.peek().first;
      int steps = q.peek().second;
      q.remove();

      if (word.equals(endWord) == true)
        return steps;

      for (int i = 0; i < word.length(); i++) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
          char[] replacedCharArray = word.toCharArray();
          replacedCharArray[i] = ch;

          String replaceWord = new String(replacedCharArray);

          // if this words exists in the set, to avoid re-counting and to actually stop
          if (st.contains(replaceWord)) {
            st.remove(replaceWord);
            q.add(new Pair(replaceWord, steps + 1));
          }
        }
      }
    }
    return 0; // if not able to reach the endWord, we return 0 acc. to the problem statement
  }
}