/*
Given a String str, reverse the string without reversing its individual words. Words are separated by dots.

Note: The last character has not been '.'. 

Examples :

Input: str = i.like.this.program.very.much
Output: much.very.program.this.like.i
Explanation: After reversing the whole string(not individual words), the input string becomes much.very.program.this.like.i
Input: str = pqr.mno
Output: mno.pqr
Explanation: After reversing the whole string , the input string becomes mno.pqr
Expected Time Complexity: O(|str|)
Expected Auxiliary Space: O(|str|)

Constraints:
1 <= |str| <= 105
 */

class Solution {
  // Function to reverse words in a given string.
  String reverseWords(String str) {
    // code here
    String[] strArr = str.split("\\.");
    int n = strArr.length;
    StringBuffer sb = new StringBuffer();

    for (int i = n - 1; i >= 1; i--) {
      sb.append(strArr[i] + ".");
    }

    sb.append(strArr[0]);

    return sb.toString();
  }
}