// Given string num representing a non-negative integer num, and an integer k,
// return the smallest possible integer after removing k digits from num.

// Example 1:
// Input: num = "1432219", k = 3
// Output: "1219"
// Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219
// which is the smallest.

// Example 2:
// Input: num = "10200", k = 1
// Output: "200"
// Explanation: Remove the leading 1 and the number is 200. Note that the output
// must not contain leading zeroes.

// Example 3:
// Input: num = "10", k = 2
// Output: "0"
// Explanation: Remove all the digits from the number and it is left with
// nothing which is 0.

// Constraints:
// 1 <= k <= num.length <= 105
// num consists of only digits.
// num does not have any leading zeros except for the zero itself.

import java.util.Stack;

class Solution {
  public String removeKdigits(String num, int k) {
    Stack<Character> stk = new Stack<>();

    // List<Character> stk = new ArrayList<>(); // can do this to avoid the reversal
    // time complexity
    // but every time have to write `stk.size() - 1` to get the top element, and
    // isEmpty thing
    int n = num.length();

    for (int i = 0; i < n; i++) {
      while ((!stk.isEmpty()) && (k > 0) && ((stk.peek() - '0') > (num.charAt(i) - '0'))) {
        stk.pop();
        k--;
      }

      stk.push(num.charAt(i));
    }

    // if there are still some of the k digits to be removed
    // remove them
    while (k > 0) {
      stk.pop();
      k--;
    }

    // add them to the string
    StringBuffer sb = new StringBuffer();
    while (!stk.isEmpty())
      sb.append(stk.pop());

    // there can be leading 0s, that are present at the end of the string
    // remove them
    while (sb.length() != 0 && sb.charAt(sb.length() - 1) == '0')
      sb.deleteCharAt(sb.length() - 1);

    if (sb.length() == 0)
      return "0";

    return sb.reverse().toString();

  }
}