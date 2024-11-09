// MEDIUM
// strings, arrays
// Google

// Given an array arr[] such that each element is in the range [0 - 9], find the
// minimum possible sum of two numbers formed using the elements of the array.
// All digits in the given array must be used to form the two numbers. Return a
// string without leading zeroes.

// Examples :
// Input: arr[] = [6, 8, 4, 5, 2, 3]
// Output: 604
// Explanation: The minimum sum is formed by numbers 358 and 246.

// Input: arr[] = [5, 3, 0, 7, 4]
// Output: 82
// Explanation: The minimum sum is formed by numbers 35 and 047.

// Input: arr[] = [9, 4]
// Output: 13
// Explanation: The minimum sum is formed by numbers 9 and 4.

// Constraints:
// 1 ≤ arr.size() ≤ 106
// 0 ≤ arr[i] ≤ 9

import java.util.Arrays;

class Solution {
  String minSum(int[] arr) {
    // code here
    Arrays.sort(arr);
    int n = arr.length;

    // since `n` can be very large, we can't do it in normal int or long
    // so we use string here

    // sorting and picking alternate digits will
    // result in min. digits being considered in both numbers
    StringBuilder s1 = new StringBuilder();
    StringBuilder s2 = new StringBuilder();

    for (int i = 0; i < n; i++) {
      if (i % 2 == 0) {
        s1.append(String.valueOf(arr[i]));
      } else {
        s2.append(String.valueOf(arr[i]));
      }
    }

    // Ex: [1,2,3,4,5]
    // we now have:
    // s1 = "135", s2 = "24"

    // what find sum does:
    // calculates the sum of two numbers which are in string form

    return findSum(s1.toString(), s2.toString());
  }

  private String findSum(String s1, String s2) {
    int i = s1.length() - 1;
    int j = s2.length() - 1;

    StringBuilder sum = new StringBuilder();
    int carry = 0;

    // summing two numbers but when the numbers are strings
    while (i >= 0 && j >= 0) {
      int d1 = s1.charAt(i) - '0';
      int d2 = s2.charAt(j) - '0';

      // add the new digit to the front becoz we are taking
      // i, j from reverse (from one digit to right)

      sum = sum.append(String.valueOf((d1 + d2 + carry) % 10));

      carry = (d1 + d2 + carry) / 10;

      i--;
      j--;
    }

    // if two nums are of not same length, we would have remaining digits:

    while (i >= 0) {
      int d1 = s1.charAt(i) - '0';
      sum = sum.append(String.valueOf((d1 + carry) % 10));
      carry = (d1 + carry) / 10;
      i--;
    }

    while (j >= 0) {
      int d2 = s2.charAt(j) - '0';
      sum = sum.append(String.valueOf((d2 + carry) % 10));
      carry = (d2 + carry) / 10;
      j--;
    }

    // if we still have the carry, we add it

    if (carry != 0)
      sum = sum.append(String.valueOf(carry));

    // System.out.println(sum);

    // since we have done sum.append(digitSum), instead of digitSum + sum
    // [ becoz we used StringBuffer ]
    // we have to reverse it
    sum.reverse();

    // we will not consider the leading zeros, if any
    // and return the string
    for (int k = 0; k < sum.length(); k++) {
      if (sum.charAt(k) != '0') {
        String ans = sum.substring(k).toString();
        // System.out.println(ans);
        return ans;
      }
    }

    return "0";
  }
}
