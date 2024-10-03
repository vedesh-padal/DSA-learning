// The next greater element of some element x in an array is the first greater
// element that is to the right of x in the same array.

// You are given two distinct 0-indexed integer arrays nums1 and nums2, where
// nums1 is a subset of nums2.

// For each 0 <= i < nums1.length, find the index j such that nums1[i] ==
// nums2[j] and determine the next greater element of nums2[j] in nums2. If
// there is no next greater element, then the answer for this query is -1.

// Return an array ans of length nums1.length such that ans[i] is the next
// greater element as described above.

// Example 1:
// Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
// Output: [-1,3,-1]
// Explanation: The next greater element for each value of nums1 is as follows:
// - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so
// the answer is -1.
// - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
// - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so
// the answer is -1.

// Example 2:
// Input: nums1 = [2,4], nums2 = [1,2,3,4]
// Output: [3,-1]
// Explanation: The next greater element for each value of nums1 is as follows:
// - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
// - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so
// the answer is -1.

// Constraints:
// 1 <= nums1.length <= nums2.length <= 1000
// 0 <= nums1[i], nums2[i] <= 104
// All integers in nums1 and nums2 are unique.
// All the integers of nums1 also appear in nums2.

// Follow up: Could you find an O(nums1.length + nums2.length) solution?


import java.util.*;

class Pair {
  int val, org;

  public Pair(int val, int org) {
    this.val = val;
    this.org = org;
  }
}

class Solution {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    // Pair[] pairs = new Pair[nums2.length];
    int n = nums2.length;
    Map<Integer, Integer> hmap = new HashMap<>();
    for (int i = 0; i < n; i++)
      hmap.put(nums2[i], i);

    Stack<Integer> stk = new Stack<>();
    int[] nge = new int[n];

    // MONOTONIC STACK
    for (int i = n - 1; i >= 0; i--) {
      while (!stk.isEmpty() && stk.peek() <= nums2[i])
        stk.pop();

      if (stk.isEmpty())
        nge[i] = -1;
      else
        nge[i] = stk.peek();

      stk.push(nums2[i]);
    }

    int[] res = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      res[i] = nge[hmap.get(nums1[i])];
    }
    return res;
  }
}