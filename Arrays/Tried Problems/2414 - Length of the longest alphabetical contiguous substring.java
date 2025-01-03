// MEDIUM - EASY
// string
/*
    An alphabetical continuous string is a string consisting of consecutive
    letters in the alphabet. In other words, it is any substring of the string "abcdefghijklmnopqrstuvwxyz".

    For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
    Given a string s consisting of lowercase letters only, return the length of
    the longest alphabetical continuous substring.

    Example 1:
    Input: s = "abacaba"
    Output: 2
    Explanation: There are 4 distinct continuous substrings: "a", "b", "c" and "ab".
    "ab" is the longest continuous substring.

    Example 2:
    Input: s = "abcde"
    Output: 5
    Explanation: "abcde" is the longest continuous substring.

    Constraints:
    1 <= s.length <= 105
    s consists of only English lowercase letters.
*/

class Solution {
    public int longestContinuousSubstring(String s) {
        int n = s.length();       
        int curr = 0, maxi = 0;
        if (s.charAt(0) == 'a') {
            curr = 1;
            maxi = 1;
        }
        @SuppressWarnings("unused")
        int l = 0, r = 1;

        while (r < n) {
            if (s.charAt(r) == s.charAt(r-1) + 1) {
                curr++;
                // maxi = Math.max(maxi, curr);
                if (curr > maxi)
                    maxi = curr;
            }
            else {
                l = r;
                curr = 1;
            }
            r++;
        }
        return (maxi == 0) ? 1 : maxi;
    }
}