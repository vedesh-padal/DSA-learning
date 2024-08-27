
// // INTUITION:
// Now let’s say we know that the sum of subarray(i, j) = S, 
// and we also know that the sum of subarray(i, x) = S where i < x < j. 
// We can conclude that the sum of subarray(x+1, j) = 0.

// So in this problem, we’ll store the prefix sum of every element, and if we observe that 2 elements
// have the same prefix sum, we can conclude that the 2nd part of this subarray sums to zero

import java.util.HashMap;

class Solution {

  public static int maxLen(int A[], int n) {
    // Your code here
    HashMap<Integer, Integer> mpp = new HashMap<Integer, Integer>();

    int maxi = 0;
    int sum = 0;

    for (int i = 0; i < n; i++) {

      sum += A[i];

      if (sum == 0) {
        maxi = i + 1;
      } else {
        if (mpp.get(sum) != null) {

          maxi = Math.max(maxi, i - mpp.get(sum));
        } else {

          mpp.put(sum, i);
        }
      }
    }
    return maxi;
  }

}
