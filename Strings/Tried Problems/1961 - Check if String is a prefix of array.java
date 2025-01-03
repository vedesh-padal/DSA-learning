// EASY
// strings, two-pointer

// Given a string s and an array of strings words, determine whether s is a prefix string of words.

// A string s is a prefix string of words if s can be made by concatenating the first 
// k strings in words for some positive k no larger than words.length.

// Return true if s is a prefix string of words, or false otherwise.

// Example 1:

// Input: s = "iloveleetcode", words = ["i","love","leetcode","apples"]
// Output: true
// Explanation:
// s can be made by concatenating "i", "love", and "leetcode" together.

// Example 2:
// Input: s = "iloveleetcode", words = ["apples","i","love","leetcode"]
// Output: false
// Explanation:
// It is impossible to make s using a prefix of arr.

// Constraints:
// 1 <= words.length <= 100
// 1 <= words[i].length <= 20
// 1 <= s.length <= 1000
// words[i] and s consist of only lowercase English letters.

class Solution {
    public boolean isPrefixString(String s, String[] words) {
        
        // Two-pointer appraoch
        // int len = s.length();
        // int i = 0, j = 0;
        // int matchLen = 0;

        // for (String word: words) {
        //     int n = word.length();
        //     j = 0;
        //     while (j < n && i < len && s.charAt(i) == word.charAt(j)) {
        //         i++;
        //         j++;
        //         matchLen++;
        //     }

        //     if (j < n) {
        //         return false;
        //     }

        //     if (matchLen == len) {
        //         return true;
        //     }
        // }
        // return false;

        // =========================================
        // using StringBuilder

        StringBuilder sb = new StringBuilder();
        for (String word: words) {
            sb.append(word);
            if (s.equals(sb.toString())) {
                return true;
            }
            if (sb.length() > s.length())
                return false;
        }
        return false;

        // ====================================================
        // 340/349
        // return String.join("", words).indexOf(s) == 0;

        // =====================================
        // 344/349
        //s = "aaaaaaa"
        // words = ["a","a","a","a","a","a","a"]

        // int nextInd = 0;
        // for (int i = 0; i < words.length; i++) {
            
        //     String word = words[i];
        //     int ind = s.indexOf(word);
        //     if (ind == nextInd) {
        //         nextInd += word.length();
        //         // System.out.println(nextInd);
        //     }
        //     else {
        //         return false;
        //     }

        //     if (i == words.length - 1 || nextInd == s.length())
        //         break;
        // }
        // return true;
    }
}