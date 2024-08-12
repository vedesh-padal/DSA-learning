class Solution {

  private static int findLCS(String s1, String s2) {
    int n = s1.length(), m = s2.length();

    int[] prev = new int[m + 1];

    for (int j = 0; j <= m; j++)
      prev[j] = 0;

    for (int i = 1; i <= n; i++) {
      int[] curr = new int[m + 1];
      for (int j = 1; j <= m; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1))
          curr[j] = 1 + prev[j - 1];
        else
          curr[j] = 0 + Math.max(prev[j], curr[j - 1]);
      }
      prev = curr;
    }
    return prev[m];
  }

  public int minDistance(String word1, String word2) {

    int lenLCS = findLCS(word1, word2); // length of longest common subsequence among two words

    int numDeletions = word1.length() - lenLCS; // deleting the chars that are not common from word1
    int numInsertions = word2.length() - lenLCS; // inserting those chars from word2 to word1 that are not common in both

    return (numDeletions + numInsertions);
  }
}