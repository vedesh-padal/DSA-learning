// A parentheses string is a non-empty string consisting only of '(' and ')'. 
// It is valid if any of the following conditions is true:

// It is ().
// It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
// It can be written as (A), where A is a valid parentheses string.
// You are given a parentheses string s and a string locked, both of length n.
// locked is a binary string consisting only of '0's and '1's. For each index i of locked,

// If locked[i] is '1', you cannot change s[i].
// But if locked[i] is '0', you can change s[i] to either '(' or ')'.
// Return true if you can make s a valid parentheses string. Otherwise, return false.
 
// Example 1:
// Input: s = "))()))", locked = "010100"
// Output: true
// Explanation: locked[1] == '1' and locked[3] == '1', so we cannot change s[1] or s[3].
// We change s[0] and s[4] to '(' while leaving s[2] and s[5] unchanged to make s valid.

// Example 2:
// Input: s = "()()", locked = "0000"
// Output: true
// Explanation: We do not need to make any changes because s is already valid.

// Example 3:
// Input: s = ")", locked = "0"
// Output: false
// Explanation: locked permits us to change s[0]. 
// Changing s[0] to either '(' or ')' will not make s valid.

// Constraints:
// n == s.length == locked.length
// 1 <= n <= 10^5
// s[i] is either '(' or ')'.
// locked[i] is either '0' or '1'.

import java.util.Stack;

class Solution {

    public boolean canBeValid(String s, String locked) {
        // valid paranthesis - striver's approach
        int n = s.length();
        if (n % 2 == 1) 
            return false;

        int min = 0, max = 0;
        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '0') {
                min--;
                max++;
            }
            else {
                if (s.charAt(i) == '(') {
                    min++;
                    max++;
                }
                else if (s.charAt(i) == ')') {
                    min--;
                    max--;
                }
            }
            // System.out.println("min: " + min + " max " + max);

            if (min < 0)
                min = 0;
            if (max < 0)
                return false;
        }
        return (min == 0);
    }   

    // ==============================================================
    // better intuition
    public boolean canBeValid_2Traversals(String s, String locked) {
        // without using Stack, but two traversals
        int n = s.length();
        if (n % 2 == 1)
            return false;

        // left to right checking
        // if we notice open < 0 => we return false
        int open = 0;
        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '0') {
                open++;
            }
            else if (s.charAt(i) == '(') {
                open++;
            }
            else if (s.charAt(i) == ')') {
                open--;
            }
            if (open < 0)
                return false;
        }

        // right to left checking
        // if we notice close < 0 => we return false
        int close = 0;
        for (int i = n-1; i >= 0; i--) {
            if (locked.charAt(i) == '0') {
                close++;
            }
            else if (s.charAt(i) == ')') {
                close++;
            }
            else if (s.charAt(i) == '(') {
                close--;
            }

            if (close < 0)
                return false;
        }
        return true;
    }

    // =====================================================================
    // VERY GOOD INTERVIEW QUESTION for explanation
    // best explanation can be given
    public boolean canBeValid_withStack(String s, String locked) {
        int n = s.length();
        // if odd length, we cannot pair and check if it is a valid paranthesis string
        if (n % 2 == 1)
            return false;

        Stack<Integer> open = new Stack<>();    // to keep track of open paranth, and locked[i] = 1
        Stack<Integer> openClose = new Stack<>();   // to keep track of locked[i] = 0 indices

        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '0') {
                openClose.push(i);
            }
            else if (s.charAt(i) == '(') {
                open.push(i);
            }
            else if (s.charAt(i) == ')') {
                if (!open.isEmpty()) {
                    open.pop();
                }
                else if (!openClose.isEmpty()) {
                    openClose.pop();
                }
                else {
                    return false;
                }
            }
        }

        // after all this, if there are still left
        // happens when the open and close are not adjancent
        // we check if the open paranthesis occured before, if not we return false;
        while (!open.isEmpty() && !openClose.isEmpty() && open.peek() < openClose.peek()) {
            open.pop();
            openClose.pop();
        }

        // now, since they will be cancelled out in pair
        // if `open` stack is empty, that means, we need not care about the openClose stack
        // if it is empty, becoz, they can be changed, and the size of it will be always even
        // so, we just check if `open` stack has become empty
        return open.isEmpty();
    }
}