/*
  You are given an array of words where each word consists of lowercase English letters.

  wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

  For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
  A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

  Return the length of the longest possible word chain with words chosen from the given list of words.

  Example 1:

  Input: words = ["a","b","ba","bca","bda","bdca"]
  Output: 4
  Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
  Example 2:

  Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
  Output: 5
  Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
  Example 3:

  Input: words = ["abcd","dbqca"]
  Output: 1
  Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
  ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
  

  Constraints:

  1 <= words.length <= 1000
  1 <= words[i].length <= 16
  words[i] only consists of lowercase English letters.
*/


import java.util.Arrays;
import java.util.Comparator;

class Solution {

  private static Comparator<String> comp = (a, b) -> a.length() - b.length();

  private static boolean compare(String s1, String s2)  {
    if (s1.length() != s2.length() + 1) 
      return false;

    int i = 0, j = 0;

    while (i < s1.length()) {
      if (j < s2.length() && s1.charAt(i) == s2.charAt(j))  {
        i++;
        j++;
      }
      else {
        i++;
      }
    }
    return i == s1.length() && j == s2.length();
  }

  public int longestStrChain(String[] words)  {
    
    // first sort the array of strings in asc order based on their length
    // Sort the words by length to process shorter words first and build up the longest chain efficiently
    // as we are trying to find the subsets instead of subsequences, we need to sort first inorder to build the longest string chain
    Arrays.sort(words, (a, b) -> a.length() - b.length());
    // OR
    // Arrays.sort(words, comp);

    int n = words.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int maxi = 1;

    for (int i=0; i<n; i++) {
      for (int prev = 0; prev < i; prev++)  {
        if (compare(words[i], words[prev]) && 1 + dp[prev] > dp[i]) {
          dp[i] = 1 + dp[prev];
        }
      }
      maxi = Math.max(maxi, dp[i]);
    }
    
    return maxi;
  }
}