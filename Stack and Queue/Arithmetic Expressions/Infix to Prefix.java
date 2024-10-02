import java.util.Stack;

class Solution {

  private boolean isOperand(char ch) {
    return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
  }

  private int getPriority(char ch)  {
    switch (ch) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      case '^':
        return 3;
      default:
        return -1;
    }
  }

  private String reverse(String s)  {
    int start = 0, end = s.length();
    char[] chars = s.toCharArray();

    while (start < end) {
      char ch = chars[start];
      chars[start++] = chars[end];
      chars[end--] = ch;
    }
    return chars.toString();
  }

  public String infixToPrefix(String s) {
    // steps:
    // 1. reverse the given string which is reverse of infix expression
    //  also, replace ( with ) and ) with (
    // 2. convert this resultant to postfix expression
    // 3. reverse the resultant expression to get the prefix of the given original infix
    
    int n = s.length();
    
    // step 1
    s = reverse(s);
    char[] infix = s.toCharArray();
    
    for (int i = 0; i < n; i++) {
      if (infix[i] == '(')
        infix[i] = ')';
      else if (infix[i] == ')')
        infix[i] = '(';
    }

    // step 2
    String toReverse = infixToPostfix(infix.toString());

    return reverse(toReverse);

  }


  private String infixToPostfix(String s)  {
    Stack<Character> stk = new Stack<>();
    int i = 0;
    StringBuffer sb = new StringBuffer();
    int n = s.length();

    while (i < n) {
      char ch = s.charAt(i);
      if (isOperand(ch))  {
        sb.append(ch);
      }
      else if (ch == '(') {
        stk.add(ch);
      }
      else if (ch == ')') {
        while (!stk.isEmpty() && stk.peek() != '(') {
          sb.append(stk.pop());
        }
        // Pop the '(' from the stack
        if (!stk.isEmpty()) {
          stk.pop();
        }
      }
      else {  // which means it is an operator
        // we also make sure that the top of the stack is operator
        if (!isOperand(stk.peek())) {
          // we keep popping till we notice the top in the stack are 
          // of higher priority or SAME priority, and we also add to 
          // our resultant string
          while ( !stk.isEmpty() && (
             ( getPriority(ch) < getPriority(stk.peek()) ) ||
             ( getPriority(ch) <= getPriority(stk.peek()) && ch == '^'))
            )  {
            
            // becoz, acc. to question
            // Note: The order of precedence is: ^ greater than * equals to / greater than + equals to -. 
            // Ignore the right associativity of ^.
            // if (ch == '^' && stk.peek() == '^')
            //   break;
            sb.append(stk.pop());
          }
          // then we push the curr. character of the expression to the stack
          stk.push(ch); // .push() or .add() both are same in java for Stack
        }
      }
      i++;
    }

    // add the remaining elements [operators] to the resulting string
    while (!stk.isEmpty())
      sb.append(stk.pop());

    return sb.toString();
  }
}