// You are given an array of integers nums, there is a sliding window of size k
// which is moving from the very left of the array to the very right. You can
// only see the k numbers in the window. Each time the sliding window moves
// right by one position.

// Return the max sliding window.

// Example 1:
// Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
// Output: [3,3,5,5,6,7]
// Explanation:
// Window position Max
// --------------- -----
// [1 3 -1] -3 5 3 6 7 3
// 1 [3 -1 -3] 5 3 6 7 3
// 1 3 [-1 -3 5] 3 6 7 5
// 1 3 -1 [-3 5 3] 6 7 5
// 1 3 -1 -3 [5 3 6] 7 6
// 1 3 -1 -3 5 [3 6 7] 7

// Example 2:
// Input: nums = [1], k = 1
// Output: [1]

// Constraints:
// 1 <= nums.length <= 105
// -104 <= nums[i] <= 104
// 1 <= k <= nums.length

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    // no. of windows that we can have = (n - k + 1)
    int[] res = new int[n - k + 1];
    int ind = 0; // ptr to insert in the res

    // this Deque stores atmost k elements
    Deque<Integer> dq = new ArrayDeque<>(); // stores the indices

    for (int i = 0; i < n; i++) {
      // first check if the front of the Deque
      // is beyond (left beyond) the start of the window
      // if yes, we remove the front of the Deque
      // here, (i - k) means the start of the curr. window
      // i indicates the end of the curr. window
      if (!dq.isEmpty() && dq.getFirst() <= (i - k))
        dq.removeFirst();

      // we need to maintain greater elements
      // decreasing monotonic stack, in the form of Deque
      while (!dq.isEmpty() && nums[dq.getLast()] <= nums[i]) {
        dq.removeLast();
      }

      // push the index
      dq.add(i);

      // we have to add the max. element in the window
      // to the resultant list, only when we have traversed
      // the window size, that is when i = k-1
      if (i >= (k - 1))
        res[ind++] = nums[dq.getFirst()];
    }
    return res;
  }
}