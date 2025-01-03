// Given a string s, the task is to find the minimum characters to be added at the front to make the string palindrome.

// Note: A palindrome string is a sequence of characters that reads the same forward and backward.

// Examples:
// Input: s = "abc"
// Output: 2
// Explanation: Add 'b' and 'c' at front of above string to make it palindrome : "cbabc"

// Input: s = "aacecaaaa"
// Output: 2
// Explanation: Add 2 a's at front of above string to make it palindrome : "aaaacecaaaa"

// Constraints:
// 1 <= s.size() <= 106

class Solution {
    
    private static int[] computeLPS(String s) {
        int n = s.length();
        int[] lps = new int[n];
        
        int len = 0;
        lps[0] = 0;
        int i = 1;
        
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
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
    
    
    // BASED ON KMP Algorithm
    // specifically finding LPS array (Longest Prefix that is also a suffix)
    public static int minChar(String s) {
        // Write your code here
        
        // now, for us to find min. chars to be added at front to make it 
        // a palindrome,
        // we have to find the length of the longest prefix that is also a suffix
        // for the below concatednated string
        // WHY?
        // EX: s = "baabcd", r = "dcbaab"
        // concatenate with a special char:
        // concat = "baabcd$dcbaab"
        
        String rev = new StringBuilder(s).reverse().toString();
        
        // System.out.println(s + " " + rev);
        
        int[] lps = computeLPS(s + "$" + rev);
        // System.out.println(Arrays.toString(lps));
        
        return (s.length() - lps[lps.length - 1]);
    }
}