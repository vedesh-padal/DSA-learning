import java.util.Stack;

class Solution {
  public String prefixToInfix(String s) {
    Stack<String> stk = new Stack<>();
    // since this is prefix to postfix, we travel from back
    int n = s.length();
    
    for (int i = n-1; i >= 0; i--)  {
      char ch = s.charAt(i);

      if (Character.isLetterOrDigit(ch))  {
        stk.push(String.valueOf(ch));
      }
      else {
        // pop two elements
        String top1 = stk.pop();
        String top2 = stk.pop();

        String toPush = "(" + top1 + ch + top2 + ")";
        stk.push(toPush);
      }
    }
    return stk.pop(); // assuming given prefix expression is valid
  }
}