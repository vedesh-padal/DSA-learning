import java.util.Stack;

class Solution {
  // Function to check if brackets are balanced or not.
  static boolean ispar(String x) {
    // add your code here
    Stack<Character> stk = new Stack<>();
    int n = x.length();
    for (int i = 0; i < n; i++) {
      char ch = x.charAt(i);
      if (ch == '{' || ch == '(' || ch == '[')
        stk.push(ch);
      else {
        if (stk.isEmpty())
          return false;
        else if (ch == ')' && stk.peek() == '(')
          stk.pop();
        else if (ch == '}' && stk.peek() == '{')
          stk.pop();
        else if (ch == ']' && stk.peek() == '[')
          stk.pop();
        else {
          return false;
        }
      }
    }
    return stk.isEmpty();
  }
}
