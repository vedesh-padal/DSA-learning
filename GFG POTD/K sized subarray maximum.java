// MEDIUM - ARRAYS - QUEUE - SLIDING WINDOW
// FOR OPTIMAL APPROACH DEQUE

// Given an array arr[] and an integer k. Find the maximum for each 
// and every contiguous subarray of size k.

// Examples:

// Input: k = 3, arr[] = [1, 2, 3, 1, 4, 5, 2, 3, 6]
// Output: [3, 3, 4, 5, 5, 5, 6] 
// Explanation: 
// 1st contiguous subarray = [1 2 3] max = 3
// 2nd contiguous subarray = [2 3 1] max = 3
// 3rd contiguous subarray = [3 1 4] max = 4
// 4th contiguous subarray = [1 4 5] max = 5
// 5th contiguous subarray = [4 5 2] max = 5
// 6th contiguous subarray = [5 2 3] max = 5
// 7th contiguous subarray = [2 3 6] max = 6

// Input: k = 4, arr[] = [8, 5, 10, 7, 9, 4, 15, 12, 90, 13]
// Output: [10, 10, 10, 15, 15, 90, 90]
// Explanation: 
// 1st contiguous subarray = [8 5 10 7], max = 10
// 2nd contiguous subarray = [5 10 7 9], max = 10
// 3rd contiguous subarray = [10 7 9 4], max = 10
// 4th contiguous subarray = [7 9 4 15], max = 15
// 5th contiguous subarray = [9 4 15 12], max = 15
// 6th contiguous subarray = [4 15 12 90], max = 90
// 7th contiguous subarray = {15 12 90 13}, max = 90
// Expected Time Complexity: O(n)
// Expected Auxiliary Space: O(k)

// Constraints:
// 1 ≤ sizeof(arr) ≤ 106
// 1 ≤ k ≤ sizeof(arr)
// 0 ≤ arr[i] ≤ 109

import java.util.*;

class Solution {
  // Function to find maximum of each subarray of size k.
  public ArrayList<Integer> max_of_subarrays(int k, int arr[]) {
    // Your code here
    // ArrayList<Integer> al = new ArrayList<>();
    // Queue<Integer> q = new LinkedList<>();
    // Priority Queue??

    // int maxInSubArr = -1;
    // int l = 0, r = 0;
    // int n = arr.length;

    // for (int r=0; r < k; r++) {
    // maxInSubArr = Math.max(arr[r], maxInSubArr);
    // q.add(arr[r]);
    // }

    // l = 1;
    // while (r < n) {
    // int remEle = q.poll();
    // q.add()
    // }

    int n = arr.length;
    ArrayList<Integer> al = new ArrayList<>();
    Deque<Integer> dq = new ArrayDeque<>(); // we will be storing indices of the elements

    for (int i = 0; i < n; i++) {
      // we have reached the specified window size,
      // so remove the first element in the Deque

      // this condition is specifially useful when for first k elements
      // when traversing in array, and other case is when, we have less elements
      // compared to required k
      if (!dq.isEmpty() && dq.getFirst() == (i - k))
        dq.removeFirst();

      // if the incoming element in the window, is more than
      // the right most one, until we find those
      // and until dq is not empty, we will remove them
      while (!dq.isEmpty() && arr[dq.getLast()] <= arr[i])
        dq.removeLast();

      // now, we have found new incoming element
      // to be less than the right most in dequequ
      dq.add(i);

      // add the left most element in the dq
      // to the ans, as it is the max. element that u
      // have in the window

      // and we will add it only have the subarray of size k
      if (i >= (k - 1))
        al.add(arr[dq.getFirst()]);
    }

    return al;
  }
}