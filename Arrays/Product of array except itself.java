// EASY - MEDIUM (if SC: O(1))

// Given an integer array nums, return an array answer such that answer[i] is
// equal to the product of all the elements of nums except nums[i].

// The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

// You must write an algorithm that runs in O(n) time and without using the division operation.

// Example 1:
// Input: nums = [1,2,3,4]
// Output: [24,12,8,6]

// Example 2:
// Input: nums = [-1,1,0,-3,3]
// Output: [0,0,9,0,0]

// Constraints:
// 2 <= nums.length <= 105
// -30 <= nums[i] <= 30
// The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

// Follow up: Can you solve the problem in O(1) extra space complexity? (The
// output array does not count as extra space for space complexity analysis.)

class Solution {
  public int[] productExceptSelf(int[] nums) {

    // IDEA:
    // prefix product array
    // postfix product array
    // curr. index answer = prefix product at curr. index * suffix product at curr. index

    // instead  of using two different arrays to store the prefix product
    // and suffix product, we are doing it the following way

    // without consuming any extra auxilary space, this problem is solved

    // this step is not really required
    int[] ans = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      ans[i] = 1;
    }

    // finding the prefix product
    int curr = 1;
    for (int i = 0; i < nums.length; i++) {
      ans[i] *= curr;
      curr *= nums[i];
    }
    // finding the suffix product and at the same time multiplying with prefix
    // product to get the final ans
    curr = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      ans[i] *= curr;
      curr *= nums[i];
    }
    return ans;
  }
}