// HARD
// strings, arrays, dynamic-programming

/*
    You are given a list of strings of the same length words and a string target.

    Your task is to form target using the given words under the following rules:

    - target should be formed from left to right.
    - To form the ith character (0-indexed) of target, you can choose the kth
    character of the jth string in words if target[i] = words[j][k].

    - Once you use the kth character of the jth string of words, you can no longer
    use the xth character of any string in words where x <= k. In other words,
    all characters to the left of or at index k become unusuable for every string.
    - Repeat the process until you form the string target.
    Notice that you can use multiple characters from the same string in words provided the conditions above are met.

    Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.

    Example 1:
    Input: words = ["acca","bbbb","caca"], target = "aba"
    Output: 6
    Explanation: There are 6 ways to form target.
    "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
    "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
    "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
    "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
    "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
    "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")

    Example 2:
    Input: words = ["abba","baab"], target = "bab"
    Output: 4
    Explanation: There are 4 ways to form target.
    "bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
    "bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
    "bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
    "bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")

    Constraints:
    1 <= words.length <= 1000
    1 <= words[i].length <= 1000
    All strings in words have the same length.
    1 <= target.length <= 1000
    words[i] and target contain only lowercase English letters.
*/

class Solution {

    // SOME IMPORTANT OBSERVATIONS, and INTUITION:
    /*
        - all are same length words
        - target can be formed from left to right
        - we have to check all possibilites if the curr. char from the words can be picked or not,
            and recursive calls to be made
        - so, basically pick or notPick problem
        - if we pick a position from any of the words, all the chars 
            including that position and before it cannot be picked from all other words
        - NOTE: that target[i] can be present in many words, would you do recursive call to
            check for that same position in word in all words? NO
        - it is better if we store each char's occurence at each position of words 2D table
        - then when making recursive call, we just multiply it with frequency of that char. at that position
        - so, here changing variables in the recursive function calls are the 
            target index (i), and the word index (not words index, becoz we already have the freq. count)
    */

    private static final int MOD = (int)(1e9 + 7);

    private int solve(int i, int j, int m, int n, String target, int[][] freq, int[][] dp) {
        
        // all chars in the target completed/reached
        if (i == m) {
            return 1;
        }
        // the above condition is skipped, means target word could not be formed
        // and all chars of each word have been traversed, still didn't form the word, so return 0
        if (j == n) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        long notTake = solve(i, j+1, m, n, target, freq, dp) % MOD;

        int currCharFreq = freq[target.charAt(i) - 'a'][j];
        long take = 0L;
        if (currCharFreq > 0) {
            take = ( (long)currCharFreq * solve(i+1, j+1, m, n, target, freq, dp) ) % MOD;
        }

        return dp[i][j] = (int)(take + notTake) % MOD;

    }

    public int numWays_recursionMemoization(String[] words, String target) {
        // first, calculate freq. of each character at each position in dictionary word
        int m = target.length();
        int n = words.length;
        int len = words[0].length();

        int[][] freq = new int[26][len];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < len; j++) {
                freq[words[i].charAt(j) - 'a'][j]++;
            }
        }

        // for (int[] f: freq)
        //     System.out.println(Arrays.toString(f));

        // dp[i][j] = no. of ways the till target[i] can be reached after visiting j chars in each word
        int[][] dp = new int[m][len];
        for (int[] row: dp)
            java.util.Arrays.fill(row, -1);

        // targetIndex, wordIndex
        return solve(0, 0, m, len, target, freq, dp);

    }


    // ========================================================================================
    public int numWays(String[] words, String target) {
        int m = target.length();
        int n = words.length;
        int len = words[0].length();

        int[][] freq = new int[26][len];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < len; i++) {
                freq[words[j].charAt(i) - 'a'][i]++;
            }
        }

        long[][] dp = new long[m + 1][len + 1];
        dp[0][0] = 1;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= len; j++) {
                // not take
                if (j < len) {
                    dp[i][j+1] = (dp[i][j+1] + dp[i][j]) % MOD;
                }

                // take
                if (i < m && j < len) {
                    dp[i+1][j+1] = (dp[i+1][j+1] + freq[target.charAt(i) - 'a'][j] * dp[i][j]) % MOD;
                }
            }
        }

        return (int)dp[m][len];
    }

}