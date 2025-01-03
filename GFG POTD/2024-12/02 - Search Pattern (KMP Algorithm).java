// MEDIUM - HARD
// strings

// Given two strings, one is a text string txt and the other is a pattern string
// pat. The task is to print the indexes of all the occurrences of the pattern
// string in the text string. Use 0-based indexing while returning the indices.
// Note: Return an empty list in case of no occurrences of pattern.

// Examples :
// Input: txt = "abcab", pat = "ab"
// Output: [0, 3]
// Explanation: The string "ab" occurs twice in txt, one starts at index 0 and
// the other at index 3.

// Input: txt = "abesdu", pat = "edu"
// Output: []
// Explanation: There's no substring "edu" present in txt.

// Input: txt = "aabaacaadaabaaba", pat = "aaba"
// Output: [0, 9, 12]

// Constraints:
// 1 ≤ txt.size() ≤ 106
// 1 ≤ pat.size() < txt.size()
// Both the strings consist of lowercase English alphabets.

import java.util.ArrayList;

class Solution {


    private int[] computeLPS(String pat) {
        int m = pat.length();
        int[] lps = new int[m];
        
        int len = 0;    // length of the previous longest prefix and suffix
        lps[0] = 0; // becoz, there is no proper prefix and suffix of pat[0..0]
        
        int i = 1;
        
        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if (len != 0) {
                    len = lps[len - 1];
                }
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }


    // Knoth Morris Patt Algorithm (KMP) - Search pattern algorithm
    // TC: O(m + n)
    // SC: O(m), where m: length of pattern, n: length of text
    ArrayList<Integer> search(String pat, String txt) {
        int n = txt.length(), m = pat.length();
        
        ArrayList<Integer> res = new ArrayList<>();
        
        
        // create an LPS (Longest Prefix Suffix) array to store
        // the proper longest prefix which is also a suffix
        // where, lps[j] = the longest prefix of pat[0...j] which is also a suffix of pat[0...j]
        int[] lps = computeLPS(pat);
        
        // System.out.println(Arrays.toString(lps));
        
        int i = 0;  // index of txt
        int j = 0;  // index of pat
        
        while (i < n) {
            if (j < m && txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == m) {
                res.add(i - j); // 0-based indexing
                j = lps[j-1];
            }
            else if (i < n && (txt.charAt(i) != pat.charAt(j))) {
                if (j != 0) {
                    j = lps[j-1];
                }
                else {
                    i++;
                }
            }
        }
        return res;
    }

    // =============================================================================

    // BRUTE FORCE: TC: O(n*m)
    // try with every possible index at i and iterate on patern and keep trying
    ArrayList<Integer> search_BRUTE(String pat, String txt) {
        // your code here
        int i = 0;
        int j = 0;
        int n = txt.length(), m = pat.length();
        
        ArrayList<Integer> res = new ArrayList<>();
        
        int prev = i;
        while (i < n) {
            prev = i;
            j = 0;
            
            while (i < n && j < m && txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == m) {
                res.add(i - j);
            }
            i = prev + 1;
            j = 0;
        }
        
        return res;
    }
}