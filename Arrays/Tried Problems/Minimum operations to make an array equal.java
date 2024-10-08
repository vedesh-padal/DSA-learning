/*
 * You have an array arr of length n where arr[i] = (2 * i) + 1 for all
 *  valid values of i (i.e., 0 <= i < n).

  In one operation, you can select two indices x and y where 0 <= x, y < n 
  and subtract 1 from arr[x] and add 1 to arr[y] (i.e., perform arr[x] -=1 and arr[y] += 1). 
  The goal is to make all the elements of the array equal. It is guaranteed that all 
  the elements of the array can be made equal using some operations.

  Given an integer n, the length of the array, return the minimum number of 
  operations needed to make all the elements of arr equal.


  Input: n = 3
  Output: 2
  Explanation: arr = [1, 3, 5]
  First operation choose x = 2 and y = 0, this leads arr to be [2, 3, 4]
  In the second operation choose x = 2 and y = 0 again, thus arr = [3, 3, 3].

  Example 2:
  Input: n = 6
  Output: 9

  Constraints:
  1 <= n <= 104
 */

class Solution {
  public int minOperations(int n) {
      // Ex: odd number: n = 5: [1, 3, 5, 7, 9]
      // same element in array: 5  =>  subtract each element by 5:
      // [-4, -2, 0, 2, 4]
      // so, number of operations = 4 + 2  = 6 => 2 * sum(till mid) => 2 * (mid * (mid + 1) / 2) = mid * (mid + 1)
      // 2 * (1 + 2) = [ above ]
      // here mid = 2  [ middle index ]

      // lly for even number: n = 6 => [1, 3, 5, 7, 9, 11]
      // [-5, -3, -1, 1, 3, 5]  => 1 + 3 + 5 => arth progression => n/2 * (2a + (n-1)*d) => 9 => mid*mid

      // therefore it boils down to:
      int mid = n / 2;
      return (n % 2 == 0) ? mid * mid : (mid) * (mid + 1);
  }
}