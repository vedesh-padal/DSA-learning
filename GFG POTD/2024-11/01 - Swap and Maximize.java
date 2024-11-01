// EASY
// arrays

// Given an array arr[ ] of positive elements. Consider the array as a circular
// array, meaning the element after the last element is the first element of the
// array. The task is to find the maximum sum of the absolute differences
// between consecutive elements with shuffling of array elements allowed i.e.
// shuffle the array elements and make [a1..an] such order that |a1 – a2| + |a2– a3|
// + …… + |an-1 – an| + |an – a1| is maximized.

// Examples:

// Input: arr[] = [4, 2, 1, 8]
// Output: 18
// Explanation: After Shuffling, we get [1, 8, 2, 4]. Sum of absolute difference
// between consecutive elements after rearrangement = |1 - 8| + |8 - 2| + |2 - 4| 
// + |4 - 1| = 7 + 6 + 2 + 3 = 18.

// Input: arr[] = [10, 12]
// Output: 4
// Explanation: No need of rearrangement. Sum of absolute difference between
// consecutive elements = |10 - 12| + |12 - 10| = 2 + 2 = 4.

// Constraints:
// 2 ≤ arr.size()≤ 105
// 1 <= arr[i] <= 105

import java.util.Arrays;

class Solution {

  public long maxSum(Long[] arr) {
    int n = arr.length;
    Arrays.sort(arr);

    long sum = 0;

    for (int i = 0; i < n / 2; i++) {
      sum -= 2 * arr[i];
      sum += 2 * arr[n - i - 1];
    }
    return sum;
  }

  public long maxSum_MY_TRY(Long[] arr) {
    // code here
    Arrays.sort(arr);

    long[] res = new long[arr.length];

    int i = 0;
    int j = arr.length - 1;
    int k = 0;

    while (i < j) {
      res[k++] = arr[i++];
      res[k++] = arr[j--];
    }

    if (i == j)
      res[k++] = arr[i];

    // System.out.println(Arrays.toString(res));

    int sum = 0;
    for (i = 0; i < arr.length - 1; i++) {
      sum += Math.abs(res[i] - res[i + 1]);
    }
    return sum + Math.abs(res[arr.length - 1] - res[0]);
  }
}