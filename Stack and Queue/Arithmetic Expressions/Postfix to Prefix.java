import java.util.Stack;

class Solution {
  public String postfixToPrefix(String s) {
    Stack<String> stk = new Stack<>();
    int n = s.length();

    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);
      if (Character.isLetterOrDigit(ch))  {
        stk.push(String.valueOf(ch));
      }
      else {
        String top1 = stk.pop();
        String top2 = stk.pop();

        // here ch means operator now
        String toPush = ch + top2 + top1;
        stk.push(toPush);
      }
    }
    return stk.pop(); // assuming given postfix expression is a valid one
  }
}