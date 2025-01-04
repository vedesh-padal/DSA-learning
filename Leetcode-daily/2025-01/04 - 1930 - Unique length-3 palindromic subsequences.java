// MEDIUM
// hashset, strings, prefix-sum
/*
    Given a string s, return the number of unique palindromes of length three that are a subsequence of s.

    Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.

    A palindrome is a string that reads the same forwards and backwards.

    A subsequence of a string is a new string generated from the original string
    with some characters (can be none) deleted without changing the relative order of the remaining characters.

    For example, "ace" is a subsequence of "abcde".

    Example 1:
    Input: s = "aabca"
    Output: 3
    Explanation: The 3 palindromic subsequences of length 3 are:
    - "aba" (subsequence of "aabca")
    - "aaa" (subsequence of "aabca")
    - "aca" (subsequence of "aabca")

    Example 2:
    Input: s = "adc"
    Output: 0
    Explanation: There are no palindromic subsequences of length 3 in "adc".

    Example 3:
    Input: s = "bbcbaba"
    Output: 4
    Explanation: The 4 palindromic subsequences of length 3 are:
    - "bbb" (subsequence of "bbcbaba")
    - "bcb" (subsequence of "bbcbaba")
    - "bab" (subsequence of "bbcbaba")
    - "aba" (subsequence of "bbcbaba")

    Constraints:
    3 <= s.length <= 10^5
    s consists of only lowercase English letters.
*/

import java.util.*;

class Solution {
    // pre-compute leftMost and rightMost index
    // BETTER WAY, by avoiding the extra work
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[][] indices = new int[26][2];   // O(26) => O(1)
        for (int[] row: indices)
            Arrays.fill(row, -1);
        
        // TC: O(n)
        for (int i = 0; i < n; i++) {
            int ind = s.charAt(i) - 'a';
            if (indices[ind][0] == -1) {
                indices[ind][0] = i;
            }
            indices[ind][1] = i;    // updating the rightmost index at every occurence
        }

        int res = 0;
        // TC: O(26 * n) => O(n)
        for (int i = 0; i < 26; i++) {
            // i = 0 ===> 'a'
            int leftInd = indices[i][0];
            int rightInd = indices[i][1];

            if (leftInd == -1) {
                continue;
            }

            Set<Character> hset = new HashSet<>();
            for (int middle = leftInd + 1; middle <= rightInd - 1; middle++) {
                hset.add(s.charAt(middle));
            }
            res += hset.size();
        }
        return res;
    }

    // ==========================================================================
    // UNDERSTANDABLE
    public int countPalindromicSubsequence_UNDERSTANDABLE(String s) {
        int n = s.length();
        // INTUITION:
        // since only 3 length string and palindrome
        // first and last char, is same
        // to find all possibilities, we find for each character (that occurs atleast twice in string)
        // and first their first and last occurence (index) in the string
        // and between then find the unique characters between those indices

        Set<Character> letters = new HashSet<>();
        for (int i = 0; i < n; i++) 
            letters.add(s.charAt(i));
    
        int res = 0;
        // TC: O(26 x n) => O(n)
        for (char letter: letters) {
            // first / leftmost index
            int firstIdx = -1;
            int lastIdx = -1;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == letter) {
                    if (firstIdx == -1)
                        firstIdx = i;
                    lastIdx = i;
                }
            }

            // unique chars in-between
            Set<Character> hset = new HashSet<>();
            for (int middle = firstIdx + 1; middle <= lastIdx - 1; middle++) {
                hset.add(s.charAt(middle));
            }

            res += hset.size();
        }
        return res;
    }

    // ===========================================
    // WHY AM I EVEN THINKING ABOUT DP???
    // look at the constraints broooo
    Set<String> unique;
    private int solve(int ind, int cnt, String curr, String s) {
        if (cnt == 0) {
            // System.out.println(curr);
            if (curr.charAt(0) == curr.charAt(2)) {
                // System.out.println(curr + " correct");
                if (!unique.contains(curr)) {
                    // System.out.println("considering " + curr);
                    unique.add(curr);
                    return 1;
                }
                // System.out.println("did not consider, already present: " + curr);
                return 0;
            }
            return 0;
        }

        if (cnt < 0 || ind >= s.length()) {
            return 0;
        }

        // pick
        int pick = solve(ind + 1, cnt-1, curr + s.charAt(ind), s);
        // not pick
        int notPick = solve(ind + 1, cnt, curr, s);

        return (pick + notPick);
    }

    public int countPalindromicSubsequence_firstTry(String s) {
        unique = new HashSet<>();
        return solve(0, 3, "", s);
    }
}