/*
  Given two distinct words startWord and targetWord, and a list denoting wordList of unique words of equal lengths. 
  => Find all shortest transformation sequence(s) from startWord to targetWord. You can return them in any order possible.
  
  -> Keep the following conditions in mind:

  - A word can only consist of lowercase characters.
  - Only one letter can be changed in each transformation.
  - Each transformed word must exist in the wordList including the targetWord.
  - startWord may or may not be part of the wordList.
  - Return an empty list if there is no such transformation sequence.
 */

import java.util.*;

class Solution {
  public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList) {

    Set<String> st = new HashSet<>();
    for (int i = 0; i < wordList.length; i++)
      st.add(wordList[i]);

    Queue<ArrayList<String>> q = new LinkedList<>();
    ArrayList<String> ls = new ArrayList<>();
    ls.add(startWord);
    q.add(ls);

    ArrayList<String> usedOnLevel = new ArrayList<>();
    usedOnLevel.add(startWord);

    int level = 0;
    ArrayList<ArrayList<String>> ans = new ArrayList<>();

    while (!q.isEmpty()) {
      ArrayList<String> vec = q.poll();

      // erase all words that have been used on previous level to transform
      if (vec.size() > level) {
        level++;
        for (String it : vec)
          st.remove(it);
        usedOnLevel.clear();
      }

      // pick the last word in the current polled sequence
      String word = vec.get(vec.size() - 1);
      if (word.equals(targetWord)) {
        if (ans.size() == 0)
          ans.add(vec);
        else if (ans.get(0).size() == vec.size())
          ans.add(vec);
      }

      for (int i = 0; i < word.length(); i++) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
          char[] replacedCharArray = word.toCharArray();
          replacedCharArray[i] = ch;
          String replacedWord = new String(replacedCharArray);

          if (st.contains(replacedWord)) {
            vec.add(replacedWord);
            ArrayList<String> temp = new ArrayList<>(vec);
            q.add(temp);
            usedOnLevel.add(replacedWord);
            vec.remove(vec.size() - 1);
          }
        }
      }
    }
    return ans;
  }
}