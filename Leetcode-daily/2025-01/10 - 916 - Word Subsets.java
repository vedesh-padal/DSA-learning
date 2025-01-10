// MEDIUM
// arrays, strings, hashtable, counting

/*
    You are given two string arrays words1 and words2.
    A string b is a subset of string a if every letter in b occurs in a including multiplicity.

    For example, "wrr" is a subset of "warrior" but is not a subset of "world".
    A string a from words1 is universal if for every string b in words2, b is a subset of a.

    Return an array of all the universal strings in words1. You may return the answer in any order.

    Example 1:
    Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
    Output: ["facebook","google","leetcode"]

    Example 2:
    Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
    Output: ["apple","google","leetcode"]

    Constraints:
    1 <= words1.length, words2.length <= 10^4
    1 <= words1[i].length, words2[i].length <= 10
    words1[i] and words2[i] consist only of lowercase English letters.
    All the strings of words1 are unique.
*/
import java.util.*;

class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] freq = new int[26];
        // MAIN POINT:
        // if words1[i], to satisfy, it should have each character's count
        // atleast the max. count count of that char. among all words in words2

        for (String word: words2) {
            int[] curr = new int[26];
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                curr[ch - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                freq[i] = Math.max(freq[i], curr[i]);
            }
        }

        List<String> res = new ArrayList<>();

        for (String word: words1) {
            int[] curr = new int[26];
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                curr[ch - 'a']++;
            }

            boolean allPossible = true;
            for (int i = 0; i < 26; i++) {
                if (curr[i] < freq[i]) {
                    allPossible = false;
                    break;
                }
            }
            if (allPossible) {
                res.add(word);
            }
        }
        return res;
    }

    // first tried, counting each words2[i]'s count, but that was giving TLE,
    // then found the trick
}