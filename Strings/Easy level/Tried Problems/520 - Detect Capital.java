// EASY
// strings

/*
    We define the usage of capitals in a word to be right when one of the following cases holds:

    All letters in this word are capitals, like "USA".
    All letters in this word are not capitals, like "leetcode".
    Only the first letter in this word is capital, like "Google".
    Given a string word, return true if the usage of capitals in it is right.

    Example 1:
    Input: word = "USA"
    Output: true

    Example 2:
    Input: word = "FlaG"
    Output: false

    Constraints:
    1 <= word.length <= 100
    word consists of lowercase and uppercase English letters.
*/

class Solution {

    public boolean detectCapitalUse_Simple(String word) {
        int n = word.length();
        if (n == 0 || n == 1)
            return true;
        
        if (Character.isUpperCase(word.charAt(0))) {
            boolean prev = Character.isUpperCase(word.charAt(1));
            for (int i = 2; i < n; i++) {
                boolean curr = Character.isUpperCase(word.charAt(i));
                if (prev != curr)
                    return false;
            }
        }
        else {  // i.e., first char is small
            for (int i = 1; i < n; i++) {
                if (Character.isUpperCase(word.charAt(i)))
                    return false;
            }
        }
        return true;
    }

    // ================================================
    // first try
    public boolean detectCapitalUse(String word) {
        int capCnt = 0, smallCnt = 0;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char ch = word.charAt(i);
            if (Character.isUpperCase(ch))
                capCnt++;
            else 
                smallCnt++;

            if (Character.isUpperCase(word.charAt(0)) && capCnt == 1 && smallCnt == (i)) {
                continue;
            }
            else if (capCnt > 0 && smallCnt > 0) {
                // System.out.println(i);
                return false;
            }
            
            // if (capCnt > 0 && smallCnt > 0 && word.charAt(0))
            //     return false;
        }
        return (smallCnt == n || capCnt == n || (capCnt == 1 && smallCnt == n-1));
    }
}