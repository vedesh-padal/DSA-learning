// MEDIUM
// array, two-pointer, string

// You are given two strings sentence1 and sentence2, each representing a
// sentence composed of words. A sentence is a list of words that are separated
// by a single space with no leading or trailing spaces. Each word consists of
// only uppercase and lowercase English characters.

// Two sentences s1 and s2 are considered similar if it is possible to insert an
// arbitrary sentence (possibly empty) inside one of these sentences such that
// the two sentences become equal. Note that the inserted sentence must be
// separated from existing words by spaces.

// For example,
// s1 = "Hello Jane" and s2 = "Hello my name is Jane" can be made equal by
// inserting "my name is" between "Hello" and "Jane" in s1.
// s1 = "Frog cool" and s2 = "Frogs are cool" are not similar, since although
// there is a sentence "s are" inserted into s1, it is not separated from "Frog"
// by a space.
// Given two sentences sentence1 and sentence2, return true if sentence1 and
// sentence2 are similar. Otherwise, return false.

// Example 1:
// Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
// Output: true
// Explanation:
// sentence2 can be turned to sentence1 by inserting "name is" between "My" and
// "Haley".

// Example 2:
// Input: sentence1 = "of", sentence2 = "A lot of words"
// Output: false

// Explanation:
// No single sentence can be inserted inside one of the sentences to make it
// equal to the other.

// Example 3:
// Input: sentence1 = "Eating right now", sentence2 = "Eating"
// Output: true
// Explanation:
// sentence2 can be turned to sentence1 by inserting "right now" at the end of
// the sentence.

// Constraints:
// 1 <= sentence1.length, sentence2.length <= 100
// sentence1 and sentence2 consist of lowercase and uppercase English letters
// and spaces.
// The words in sentence1 and sentence2 are separated by a single space.

class Solution {

  // two pointer approach
  // prefix string and suffix string kind of approach
  public boolean areSentencesSimilar(String sentence1, String sentence2) {
    if (sentence1.length() < sentence2.length()) {
      String t = sentence1;
      sentence1 = sentence2;
      sentence2 = t;
    }

    String[] s1 = sentence1.split(" ");
    String[] s2 = sentence2.split(" ");

    // i, k => front pointers
    // j, l => back pointers
    int i = 0, j = s1.length - 1;
    int k = 0, l = s2.length - 1;

    while (k < s2.length && i < s1.length && s1[i].equals(s2[k])) {
      i++;
      k++;
    }

    while (l >= k && s1[j].equals(s2[l])) {
      j--;
      l--;
    }

    return l < k;
  }

  // ===== MY TRY ========
  private boolean find(String sentence1, String sentence2) {
    int n = sentence1.length(), m = sentence2.length();

    int i = 0;
    while (i < m && sentence1.charAt(i) == sentence2.charAt(i)) {
      i++;
    }

    // doubt if can be inserted at the beginning too
    // INFACT YES, TESTCASES WERE FAILING FOR
    // sentence1 = "xD iP tqchblXgqvNVdi"
    // sentence2 = "FmtdCzv Gp YZf UYJ xD iP tqchblXgqvNVdi"
    // Output false
    // Expected true

    if (i == 0) {
      int k = 0;
      while (sentence2.charAt(k) != sentence1.charAt(i)) {
        k++;
      }

      if (sentence1.substring(i).equals(sentence2.substring(k)))
        return true;
      else
        return false;
    }

    if (i == m)
      return true;

    int j = i;
    while (sentence1.charAt(j) != sentence2.charAt(i)) {
      j++;
    }

    if (j == n)
      return false;

    // System.out.println(i + " " + j);
    // String insertionSentence = sentence1.substring(i, j);
    // System.out.println(insertionSentence.length());

    // System.out.println(Arrays.toString(insertionSentence.split(" ", -1)));

    int numWordsInInsertionSent = sentence1.substring(i, j).split(" ", -1).length;
    System.out.println(numWordsInInsertionSent);

    if (numWordsInInsertionSent < 2)
      return false;

    // while
    if (sentence1.substring(j).equals(sentence2.substring(i)))
      return true;
    else
      return false;

  }

  public boolean areSentencesSimilar_MY_TRY(String sentence1, String sentence2) {
    if (sentence1.length() > sentence2.length()) {
      return find(sentence1, sentence2);
    } else {
      return find(sentence2, sentence1);
    }

  }
}