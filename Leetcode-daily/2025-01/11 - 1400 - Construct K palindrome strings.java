// MEDIUM
// arrays, string, counting, hashmap

/*
    Given a string s and an integer k, return true if you can use all the
    characters in s to construct k palindrome strings or false otherwise.

    Example 1:
    Input: s = "annabelle", k = 2
    Output: true
    Explanation: You can construct two palindromes using all characters in s.
    Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"

    Example 2:
    Input: s = "leetcode", k = 3
    Output: false
    Explanation: It is impossible to construct 3 palindromes using all the characters of s.

    Example 3:
    Input: s = "true", k = 4
    Output: true
    Explanation: The only possible solution is to put each character in a separate string.
    
    Constraints:
    1 <= s.length <= 10^5
    s consists of lowercase English letters.
    1 <= k <= 10^5
*/

class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (k > n)
            return false;
        if (k == n)
            return true;

        // key point:
        // even no. of count of char, can be evenly added to left and right ends
        // to form the palindrome
        // but, the main problem is with the odd freq. chars
        // when odd freq. chars are more than k in number, then we cannot satisfy the condition in the question

        int[] freq = new int[26];

        // Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
            freq[s.charAt(i) - 'a']++;
        }

        int oddCnt = 0;
        // for (char ch: freq.keySet()) {
        for (int cnt: freq) {
            // if (freq.get(ch) % 2 == 1) {
            if (cnt % 2 == 1) {
                oddCnt++;
                // should be <= k in order to be able to put in the middle of the even palindromes
                if (oddCnt > k) {
                    return false;
                }
            }
        }
        return true;
    }
}