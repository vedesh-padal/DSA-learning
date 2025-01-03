// MEDIUM
// arrays, sliding-window

// You are given an array of integers nums of length n and a positive integer k.

// The power of an array is defined as:

// Its maximum element if all of its elements are consecutive and sorted in ascending order. -1 otherwise.
// You need to find the power of all 
// subarrays  of nums of size k.

// Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].

// Example 1:
// Input: nums = [1,2,3,4,3,2,5], k = 3
// Output: [3,4,-1,-1,-1]
// Explanation:
// There are 5 subarrays of nums of size 3:
// [1, 2, 3] with the maximum element 3.
// [2, 3, 4] with the maximum element 4.
// [3, 4, 3] whose elements are not consecutive.
// [4, 3, 2] whose elements are not sorted.
// [3, 2, 5] whose elements are not consecutive.

// Example 2:
// Input: nums = [2,2,2,2,2], k = 4
// Output: [-1,-1]

// Example 3:
// Input: nums = [3,2,3,2,3,2], k = 2
// Output: [-1,3,-1,3,-1]

// Constraints:
// 1 <= n == nums.length <= 500
// 1 <= nums[i] <= 105
// 1 <= k <= n

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {

  // clean code
  public int[] resultsArray(int[] nums, int k) {
    int n = nums.length;

    int[] res = new int[n - k + 1];
    Arrays.fill(res, -1); // this is what i missed and overcomplicated my try to solve this problem

    int cnt = 1; // no. of consecutive elements

    // pre-process the first window
    for (int i = 1; i < k; i++) {
      if (nums[i] == nums[i - 1] + 1) {
        cnt++;
      } 
      else {
        cnt = 1; // reset, becoz we noticed a number that broke the continuous flow
      }
    }

    if (cnt == k) {
      res[0] = nums[k - 1];
    }

    // now, next window
    int i = 1;
    int j = k;

    while (j < n) {
      if (nums[j] == nums[j - 1] + 1) {
        cnt++;
      } 
      else {
        cnt = 1;
      }

      if (cnt >= k) {
        res[i] = nums[j];
      }

      i++;
      j++;
    }

    return res;
  }

  // Deque Approach
  // SC: O(k)
  public int[] resultsArray_Deque(int[] nums, int k) {
    int len = nums.length;
    int[] res = new int[len - k + 1];
    int j = 0;

    Deque<Integer> dq = new ArrayDeque<>();

    for (int i = 0; i < len; i++) {
      if (dq.size() == k) {
        dq.removeFirst();
      }

      if (!dq.isEmpty() && dq.getLast() + 1 != nums[i]) {
        dq.clear();
      }

      dq.add(nums[i]);

      // only when we have window size
      if (i >= k-1) {
        if (dq.size() == k) {
          res[j] = dq.getLast();
        }
        else {
          res[j] = -1;
        }
        j++;
      }
    }

    return res;
  }
}
