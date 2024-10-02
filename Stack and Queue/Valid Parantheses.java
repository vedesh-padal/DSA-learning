import java.util.Stack;

class Solution {
  public boolean isValid(String s)  {
    Stack<Character> stk = new Stack<>();
    for (char ch: s.toCharArray())  {
      if (ch == '(')
        stk.push(')');
      else if (ch == '[')
        stk.push(']');
      else if (ch == '{')
        stk.push('}');
      else if (stk.isEmpty() || ch != stk.pop())
        return false;
    }
    return stk.isEmpty();
  }
}