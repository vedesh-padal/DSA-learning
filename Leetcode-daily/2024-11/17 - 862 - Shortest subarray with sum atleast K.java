// HARD
// sliding-window, monotonic-stack, monotonic-queue, monotonic-deque, arrays

// Given an integer array nums and an integer k, return the length of the
// shortest non-empty subarray of nums with a sum of at least k. If there is no
// such subarray, return -1.

// A subarray is a contiguous part of an array.

// Example 1:
// Input: nums = [1], k = 1
// Output: 1
// Example 2:
// Input: nums = [1,2], k = 4
// Output: -1

// Example 3:
// Input: nums = [2,-1,2], k = 3
// Output: 3

// Constraints:
// 1 <= nums.length <= 105
// -105 <= nums[i] <= 105
// 1 <= k <= 109

// This problem is similar version of Leetcode 209
// minimum size subarray sum
// in that problem, all nums are +ve
// here, nums can be -ve too
// this is where just normal sliding window would not work:
// Ex:
// [ 84, -37, 37, 40, 95 ] , k = 167
// if you were to follow normal sliding window, 
// you would break at i = 1, and return minSize = 5

// BUT, the min. subarray size with sum >= k is actually 3 => [2, 4] indices

// For that, we need to use Monotonic Stack / Queue / Deque concept
// We need increasing nature becoz:
// when we find the preSum[] = [84, 47, 84, 124, 219]
// the 1st 84 that we noticed doesn't make any sense, as it is a dip
// and considering it will anyway make the subarray size we want greater and not contributing 
// to the smaller size
// so we remove it, and we will be using Deque (and storing in a monotonic fashion, as we want to avoid those dips)
// which will allow use to perform add and remove operations from both ends

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public int shortestSubarray(int[] nums, int k) {
    int n = nums.length;

    long[] cummSum = new long[n]; // cummulative sum

    // stores the indices of the elements that are in monotonically increasing order
    Deque<Integer> dq = new ArrayDeque<>();

    int minSize = Integer.MAX_VALUE;
    int j = 0;

    while (j < n) {
      if (j == 0) {
        cummSum[j] = nums[j];
      } 
      else {
        cummSum[j] = cummSum[j - 1] + nums[j];
      }

      if (cummSum[j] >= k) {
        minSize = Math.min(minSize, j + 1); // basically (j - i + 1)
        // but, since we are not keeping track of `i` variable and also
        // this possible only for the 0th index element
        // after that Deque takes care
      }

      // check if shrinking helps,
      // i.e., removing the first element of the monotonically increasing subarray
      while (!dq.isEmpty() && (cummSum[j] - cummSum[dq.getFirst()]) >= k) {
        // we update the minSize that is making this sum >= k possible
        minSize = Math.min(minSize, j - dq.getFirst()); // not +1, becoz we are removing first element of dq
        dq.removeFirst();
      }

      // now, check if the new element that is being considered, when expanding the
      // the subarray, is indeed in increasing order, if not, then remove the last
      // stored element in the Deque
      while (!dq.isEmpty() && cummSum[j] <= cummSum[dq.getLast()]) {
        dq.removeLast();
      }

      // now in the Deque, that we have all are in increasing order, now we can
      // actually add the incoming element index
      dq.add(j);
      j++;
    }

    return (minSize == Integer.MAX_VALUE) ? -1 : minSize;
  }
}