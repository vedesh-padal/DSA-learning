// HARD
/*
  Given a string containing just the characters '(' and ')', return the 
  length of the longest valid (well-formed) parentheses substring.

  Example 1:
  Input: s = "(()"
  Output: 2
  Explanation: The longest valid parentheses substring is "()".

  Example 2:
  Input: s = ")()())"
  Output: 4
  Explanation: The longest valid parentheses substring is "()()".

  Example 3:
  Input: s = ""
  Output: 0
  
  Constraints:
  0 <= s.length <= 3 * 104
  s[i] is '(', or ')'.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {

  public int n;
  
  // ULTRA EFFICIENT APPROACH
  // CREATING OUR OWN STACK
  public int longestValidParentheses(String s)    {
      n = s.length();
      int max = 0;
      int[] stk = new int[n + 1]; // +1 becoz, we also have to accomodate -1
      int ind = -1;   // keeps track of the stack's top [ pointer kinda ]
      stk[++ind] = -1;  // push

      for (int i = 0; i < n; i++) {
          if (s.charAt(i) == '(')
              stk[++ind] = i;
          else {
              ind--;  // pop
              if (ind == -1)
                  stk[++ind] = i;
              
              max = Math.max(max, i - stk[ind]);
          }
      }
      return max;
  }


  // -----------------------------------------------------
  // EFFICIENT APPROACH: SINGLE PASS
  // MOST RECENT INVALID PARANTHESIS APPROACH
  public int longestValidParenthesesEff(String s)    {
      n = s.length();
      Stack<Integer> stk = new Stack<>();
      stk.push(-1);   // edge case: handles when beginning of the string can be part of the valid substring
      // For instance, if the string starts with a valid sequence of parentheses, 
      // the index of the last unmatched parenthesis is -1.
      
      int max = 0;

      for (int i = 0; i < n; i++) {
          char ch = s.charAt(i);
          if (ch == '(')
              stk.push(i);
          else {
              stk.pop();
              if (stk.isEmpty())
                  stk.push(i);
              // this time we are not doing -1, becoz i is the ending of valid paranthesis
              max = Math.max(max, i - stk.peek());
              // and stk.peek() is the Most Recent Invalid Paranthesis
          }
      }
      return max;
  }

  // ----------------------------------------------------------------------------------
  // BRUTE FORCE APPROACH
  // Intutition:
  // our valid parathesis substring is between two invalid paranthesis
  // and we need to maximize the substring length between these invalid paranthesis
  public int longestValidParenthesesBRUTE(String s) {
      // remove all valid paranthesis
      n = s.length();
      Stack<Integer> stk = new Stack<>();
      removeAllValidParanthesis(s, stk);

      // if the stack is empty, there were no invalid paranthesis. so, return the len of string
      if (stk.isEmpty())
          return n;
      
      // form list of invalid indices
      List<Integer> al = new ArrayList<>();
      formArrayOfInvalidIndices(stk, al);

      int max = 0;
      for (int i = 1; i < al.size(); i++) {
          max = Math.max(max, al.get(i) - al.get(i-1) - 1);   // we need length excluding the right invalid parantheis
      }
      return max;
  }

  private void removeAllValidParanthesis(String s, Stack<Integer> stk)    {
      for (int i = 0; i < n; i++) {
          if (s.charAt(i) == '(')
              stk.push(i);
          else {
              if (stk.isEmpty() || s.charAt(stk.peek()) == ')')
                  stk.push(i);
              else 
                  stk.pop();
          }
      }
  }

  private void formArrayOfInvalidIndices(Stack<Integer> stk, List<Integer> al)    {
      al.add(0, n);
      while (!stk.isEmpty())  {
          al.add(0, stk.pop());
      }
      al.add(0, -1);
  }
}