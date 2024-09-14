// Easy
/*
  Given an unsorted array arr containing both positive and negative numbers. 
  Your task is to create an array of alternate positive and negative numbers 
  without changing the relative order of positive and negative numbers.
  Note: Array should start with a positive number and 0 (zero) should be considered a positive element.

  Examples:

  Input: arr[] = [9, 4, -2, -1, 5, 0, -5, -3, 2]
  Output: 9 -2 4 -1 5 -5 0 -3 2
  Explanation: Positive elements : [9,4,5,0,2]
  Negative elements : [-2,-1,-5,-3]
  As we need to maintain the relative order of postive elements and negative elements 
  we will pick each element from the positive and negative and will store them. If any of 
  the positive and negative numbersare completed. we will continue with the remaining signed elements.
  The output is 9,-2,4,-1,5,-5,0,-3,2.

  Input: arr[] = [-5, -2, 5, 2, 4, 7, 1, 8, 0, -8]
  Output: 5 -5 2 -2 4 -8 7 1 8 0
  Explanation : Positive elements : [5,2,4,7,1,8,0]
  Negative elements : [-5,-2,-8]
  The output is 5, -5, 2, -2, 4, -8, 7, 1, 8, 0.
  
  Expected Time Complexity: O(n)
  Expected Auxiliary Space: O(n)

  Constraints:
  1 ≤ arr.size() ≤ 107
  -106 ≤ arr[i] ≤ 107
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
  void rearrange(ArrayList<Integer> arr) {
    // code here
    Queue<Integer> pos = new LinkedList<>();
    Queue<Integer> neg = new LinkedList<>();

    for (int num : arr) {
      if (num >= 0)
        pos.add(num);
      else
        neg.add(num);
    }
    int n = arr.size();
    int i = 0;
    while (i < n) {
      if (i % 2 == 0) {
        if (!pos.isEmpty())
          arr.set(i, pos.poll());
        else
          break;
      } else {
        if (!neg.isEmpty())
          arr.set(i, neg.poll());
        else
          break;
      }
      i++;
    }

    while (!pos.isEmpty() && i < n)
      arr.set(i++, pos.poll());

    while (!neg.isEmpty() && i < n)
      arr.set(i++, neg.poll());

  }

  // void rearrange(ArrayList<Integer> arr) {
  //   // code here
  //   int i = 0; // even index tracker
  //   int j = 1; // odd index tracker
  //   int n = arr.size();
  //   while (i < n && j < n) {
  //     // find the first wrong element (i.e., -ve element)
  //     while (i < n && arr.get(i) >= 0)
  //       i += 2;
  //     // find the first wrong element (i.e., +ve element)
  //     while (j < n && arr.get(j) < 0)
  //       j += 2;

  //     // if i, j are valid index positions, swap them
  //     // NOTE: IF WE DO SWAPPING: WE ARE DISTURBING THE RELATIVE ORDER
  //     // GIVEN IN Q: RELATIVE ORDER OF +VE AND -VE ELEMENTS SHOULD BE SAME AS IN THE
  //     // ORIG ARRAY
  //     // SO THIS METHOD WILL NOT RESULT IN CORRECT ANSWER
  //     // HENCE THE EXPECTED SPACE COMPLEXITY IN THE QUESTION IS GIVEN AS O(N)
  //     if (i < n && j < n) {
  //       int t = arr.get(i);
  //       arr.set(i, arr.get(j));
  //       arr.set(j, t);
  //     }
  //   }

  //   if (i < n) {
  //     for (int k = i; k < n; k++) {
  //       if (arr.get(k) >= 0)
  //         arr.set(i++, arr.get(k));
  //     }
  //   } else if (j < n) {
  //     for (int k = j; j < n; j++) {
  //       if (arr.get(k) < 0)
  //         arr.set(j++, arr.get(k));
  //     }
  //   }

  // }
}