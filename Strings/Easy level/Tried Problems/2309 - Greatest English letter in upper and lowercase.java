// EASY
// strings
/*
    Given a string of English letters s, return the greatest English letter which
    occurs as both a lowercase and uppercase letter in s. The returned letter
    should be in uppercase. If no such letter exists, return an empty string.

    An English letter b is greater than another letter a if b appears after a in
    the English alphabet.

    Example 1:
    Input: s = "lEeTcOdE"
    Output: "E"
    Explanation:
    The letter 'E' is the only letter to appear in both lower and upper case.

    Example 2:
    Input: s = "arRAzFif"
    Output: "R"
    Explanation:
    The letter 'R' is the greatest letter to appear in both lower and upper case.
    Note that 'A' and 'F' also appear in both lower and upper case, but 'R' is greater than 'F' or 'A'.

    Example 3:
    Input: s = "AbCdEfGhIjK"
    Output: ""
    Explanation:
    There is no letter that appears in both lower and upper case.
    
    Constraints:
    1 <= s.length <= 1000
    s consists of lowercase and uppercase English letters.
*/

class Solution {
    // NOICE, without extra space
    public String greatestLetter(String s) {
        for (char cap = 'Z'; cap >= 'A'; cap--) {
            char sm = (char)(cap + 32);
            if (s.indexOf(cap) >= 0 && s.indexOf(sm) >= 0) {
                return String.valueOf(cap);
            }
        }
        return "";
    }

    public String greatestLetter_withExtraSpace(String s) {
        int[] small = new int[26];
        int[] cap = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isUpperCase(ch)) 
                cap[ch - 'A']++;
            else
                small[ch - 'a']++;
        }

        for (int i = 25; i >= 0; i--) {
            if (small[i] > 0 && cap[i] > 0)
                return Character.toString(i + 'A');
        }
        return "";
    }
}