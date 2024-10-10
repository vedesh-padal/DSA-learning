// medium
// monotonic stack, arrays, greedy

// A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i]
// <= nums[j]. The width of such a ramp is j - i.

// Given an integer array nums, return the maximum width of a ramp in nums. If
// there is no ramp in nums, return 0.

// Example 1:
// Input: nums = [6,0,8,2,1,5]
// Output: 4
// Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] =
// 0 and nums[5] = 5.

// Example 2:
// Input: nums = [9,8,1,0,1,9,4,0,4,1]
// Output: 7
// Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] =
// 1 and nums[9] = 1.

// Constraints:
// 2 <= nums.length <= 5 * 104
// 0 <= nums[i] <= 5 * 104
import java.util.Stack;

class Solution {
  // 2 POINTER APPROACH - kind of difficult
  public int maxWidthRamp(int[] nums) {
    int n = nums.length;
    int[] maxOnRight = new int[n];
    maxOnRight[n - 1] = nums[n - 1];

    // this helps us quickly know the maximum value
    // to the right of any index.
    for (int i = n - 2; i >= 0; i--) {
      maxOnRight[i] = Math.max(maxOnRight[i + 1], nums[i]);
    }

    int maxi = 0;
    int l = 0, r = 0;
    while (r < n) {
      // that means if we have the window, or left pointer i
      // to be here, then to the right of r including r
      // we cannot find any element greater than nums[l]
      // so, we move left forward
      while (l <= r && nums[l] > maxOnRight[r]) {
        l++;
      }
      // now, we have l <= r and nums[l] <= maxOnRight[r]
      maxi = Math.max(maxi, r - l);
      r++;
    }
    return maxi;
  }

  // USING MONOTONIC STACK - more intuitive
  public int maxWidthRamp_MONOTONIC_STACK(int[] nums) {
    // to store the indices of the greater elements
    // increasing monotonic stack
    Stack<Integer> stk = new Stack<>();
    int n = nums.length;

    // from the back store only greater elements than the previously encountered
    System.out.println("Being pushed in stack: ");
    for (int i = n - 1; i >= 0; i--) {
      if (stk.isEmpty() || nums[i] >= nums[stk.peek()]) {
        // System.out.println("ind: " + i + "\tval: " + nums[i]);
        stk.push(i);
      }
    }

    int maxi = Integer.MIN_VALUE;
    System.out.println("being popped out: ");
    for (int i = 0; i < n; i++) {
      while (!stk.isEmpty() && nums[i] <= nums[stk.peek()]) {
        // System.out.println("i: " + i + "\tnums[i]: " + nums[i] + "\tstk.peek(): " +
        // stk.peek());
        // System.out.println("diff: " + (stk.peek() - i));
        // System.out.println("maxi here: " + maxi);
        maxi = Math.max(stk.peek() - i, maxi);
        stk.pop();
      }
      if (stk.isEmpty())
        break;
    }
    return maxi;
  }
}

/*
 * Input: nums = [9,8,1,0,1,9,4,0,4,1]
 * Stdout::
 * 
 * Being pushed in stack:
 * ind: 9 val: 1
 * ind: 8 val: 4
 * ind: 6 val: 4
 * ind: 5 val: 9
 * ind: 0 val: 9
 * being popped out:
 * i: 0 nums[i]: 9 stk.peek(): 0
 * diff: 0
 * maxi here: -2147483648
 * i: 0 nums[i]: 9 stk.peek(): 5
 * diff: 5
 * maxi here: 0
 * i: 2 nums[i]: 1 stk.peek(): 6
 * diff: 4
 * maxi here: 5
 * i: 2 nums[i]: 1 stk.peek(): 8
 * diff: 6
 * maxi here: 5
 * i: 2 nums[i]: 1 stk.peek(): 9
 * diff: 7
 * maxi here: 6
 * 
 * Output: 7
 * Expected: 7
 */
