/*
  Given an array A[] of size N. Find the number of pairs (i, j) such that
  Ai XOR Aj = 0, and 1 ≤ i < j ≤ N.

  Example 1:

  â€‹Input : arr[ ] = {1, 3, 4, 1, 4}
  Output : 2
  Explanation:
  Index( 0, 3 ) and (2 , 4 ) are only pairs 
  whose xors is zero so count is 2.

  â€‹Example 2:

  Input : arr[ ] = {2, 2, 2} 
  Output :  3

  Your Task:
  This is a function problem. The input is already taken care of by the driver code. 
  You only need to complete the function calculate() that takes an array (arr), sizeOfArray (n), 
  and return the count of Zeros Xor's Pairs. The driver code takes care of the printing.

  Expected Time Complexity: O(N*Log(N)).
  Expected Auxiliary Space: O(1).

  Output:
  For each test case, output a single integer i.e counts of Zeros Xors Pairs

  Constraints
  2 ≤ N ≤ 10^5
  1 ≤ A[i] ≤ 10^5
*/


import java.util.Arrays;

class Complete {

  // Function for finding maximum and value pair
  public static long calculate(int arr[], int n) {
    // Complete the function
    Arrays.sort(arr);
    long cnt = 0;
    int i = 0;

    while (i < n) {
      int count = 1; // count the occurences of i

      // count how many times arr[i] appears consecutively
      while (i + 1 < n && arr[i] == arr[i + 1]) {
        count++;
        i++;
      }

      // if there are 'count' occurences of the same element
      // then, we can form, count * (count - 1) / 2 num of pairs
      if (count > 1)
        cnt += (long) count * (count - 1) / 2;

      i++; // move to the next unique element
    }

    return cnt;
  }

}
