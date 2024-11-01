// 19-10-2024
// EASY - MEDIUM
// arrays, string, mathematical

// A string str is given to represent a positive number. The task is to round str to the 
// nearest multiple of 10.  If you have two multiples equally apart from str, 
// choose the smallest element among them.

// Examples:
// Input: str = 29 
// Output: 30
// Explanation: Close multiples are 20 and 30, and 30 is the nearest to 29. 

// Input: str = 15
// Output: 10
// Explanation: 10 and 20 are equally distant multiples from 20. The smallest of the two is 10.

// Expected Time Complexity: O(n).
// Expected Auxiliary Space: O(1).

// Constraints:
// 1 <= str.size()<= 105

class Solution {
  String roundToNearest(String str) {
    char[] numArr = str.toCharArray();
    int n = str.length();

    int lastDigit = numArr[n-1] - '0';

    if (lastDigit <= 5) {
      numArr[n-1] = '0';
      return new String(numArr);
    }

    else {
      int i = n-2;
      while (i >= 0 && numArr[i] == '9')  {
        numArr[i] = '0';
        i--;
      }

      // which means all of them right side nums were 9
      // so we add a carry
      if (i < 0)  {
        return "1" + new String(numArr);
      }

      // increment the curr. digit by 1
      numArr[i]++;
      // and set the last digit to 0 (as we have rounded it to 10)
      numArr[n-1] = '0';
    }
    return new String(numArr);
  }
}