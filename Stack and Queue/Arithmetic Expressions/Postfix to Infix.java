import java.util.Stack;

class Solution {

  public String postfixToInfix(String s)  {
    Stack<String> stk = new Stack<>();
    int n = s.length();

    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);
      // is operand
      if (Character.isLetterOrDigit(ch))  {
        stk.push(String.valueOf(ch));
      }
      else {
        String top1 = stk.pop();
        String top2 = stk.pop();

        String toPush = "(" + top2 + ch + top1 + ")";
        stk.push(toPush);
      }
    }

    return stk.pop(); // given that the given postfix expression is a valid one
  }
}