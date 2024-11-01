// 20-10-2024
// string, stack, recursion
// A boolean expression is an expression that evaluates to either true or false.
// It can be in one of the following shapes:

// 't' that evaluates to true.
// 'f' that evaluates to false.
// '!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
// '&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of
// the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
// '|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of
// the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
// Given a string expression that represents a boolean expression, return the
// evaluation of that expression.

// It is guaranteed that the given expression is valid and follows the given rules.

// Example 1:

// Input: expression = "&(|(f))"
// Output: false
// Explanation:
// First, evaluate |(f) --> f. The expression is now "&(f)".
// Then, evaluate &(f) --> f. The expression is now "f".
// Finally, return false.

// Example 2:
// Input: expression = "|(f,f,f,t)"
// Output: true
// Explanation: The evaluation of (false OR false OR false OR true) is true.

// Example 3:
// Input: expression = "!(&(f,t))"
// Output: true
// Explanation:
// First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression
// is now "!(f)".
// Then, evaluate !(f) --> NOT false --> true. We return true.

// Constraints:
// 1 <= expression.length <= 2 * 104
// expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.

import java.util.*;

class Solution {

  private boolean isOperator(char ch) {
      return ch == '&' || ch == '|' || ch == '!';
  }

  public boolean parseBoolExpr(String expression) {
      Stack<Character> stk = new Stack<>();
      int n = expression.length();
      Stack<Character> op = new Stack<>();

      Map<Character, Boolean> hmap = new HashMap<>();
      hmap.put('t', true);
      hmap.put('f', false);

      for (int i = 0; i < n; i++) {
          char ch = expression.charAt(i);
          if (isOperator(ch))
              op.push(ch);
          else if (ch == '(' || ch == 'f' || ch == 't')    {
              stk.push(ch);
          }
          else if (ch == ',')
              continue;

          else if (ch == ')') {

              List<Character> values = new ArrayList<>();
              while (!stk.isEmpty() && stk.peek() != '(') {
                  values.add(stk.pop());
              }
              stk.pop();  // remove '('

              if (!op.isEmpty())  {
                  char oper = op.pop();
                  char result;

                  // evaluation
                  if (oper == '!')    {
                      boolean val = hmap.get(values.get(0));
                      // since given that input string will always be valid
                      // therefore, when we have this char, there will be only one boolean char
                      result = (!val) ? 't' : 'f';
                  }
                  else {
                      boolean boolRes = (oper == '&'); // start with true for AND, false for OR
                      for (char val: values)  {
                          boolean boolVal = hmap.get(val);
                          if (oper == '&')
                              boolRes = boolRes && boolVal;
                          else
                              boolRes = boolRes || boolVal;
                      }
                      result = boolRes ? 't' : 'f';
                  }
                  
                  // now, we have the result
                  stk.push(result);

              }
          }
      }
      return stk.pop() == 't';
  }
}
      


      // HAHA --> Tried to Over-optimize
      // by not using extra space by store nested elements in a list for evaluation
      // turns out, is is too much, then used List to store and it worked (as above)

      // else if (ch == ')') // my failing part
          // MY TRY: 56/75 -> failing for nested loops (as per claude)
          /*
              Input:
              expression = "!(&(&(!(&(f)),&(t),|(f,f,t)),&(t),&(t,t,f)))"
              Stdout
                  stk: [f]
                  op: [!, &, &, !]
              Output
                  false
              Expected
                  true
          */
          // else if (ch == ')') {
          //     while (stk.size() != 1 && stk.peek() != '(')   {
          //         // pop recent two
          //         char oper;
          //         char second = stk.pop();
          //         char first = stk.pop();
          //         if (!op.isEmpty())
          //             oper = op.peek();
          //         else 
          //             break;

          //         if (first == '!')   {
          //             // oper.pop(); // doubt
          //             if (second == 'f')
          //                 stk.push('t');
          //             else if (second == 't')
          //                 stk.push('f');
                      
          //             break;
          //         }
          //         else if (first == '(' || first == '&' || first == '|')  {
          //             stk.push(second);
          //             break;
          //         }
          //         // both recent pops are boolean chars
          //         else {
          //             if (oper == '&')    {
          //                 boolean opRes = hmap.get(first) && hmap.get(second);
          //                 stk.push(opRes ? 't' : 'f');
          //             }
          //             else if (oper == '|')   {
          //                 boolean opRes = hmap.get(first) || hmap.get(second);
          //                 stk.push(opRes ? 't' : 'f');
          //             }
          //         }
          //     }
          //     if (op.peek() != '!')
          //         op.pop();
          // }
      // }
      // System.out.println(stk);
      // System.out.println(op);

      // if (op.size() == 1 && op.pop() == '!')  {
      //     return stk.pop() == 'f' ? !false : true;
      // }

      // return stk.pop() == 't' ? true : false;