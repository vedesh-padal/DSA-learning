// EASY
// arrays, design, prefix-sum
/*
    Given an integer array nums, handle multiple queries of the following type:

    Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.

    Implement the NumArray class:
    - NumArray(int[] nums) Initializes the object with the integer array nums.
    - int sumRange(int left, int right) Returns the sum of the elements of nums
        between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).

    Example 1:
    Input
    ["NumArray", "sumRange", "sumRange", "sumRange"]
    [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]

    Output
    [null, 1, -1, -3]

    Explanation
    NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
    numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
    numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
    numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3

    Constraints:
    1 <= nums.length <= 10^4
    -10^5 <= nums[i] <= 10^5
    0 <= left <= right < nums.length
    At most 10^4 calls will be made to sumRange.
*/

// using Prefix Sum done below, can also be done using Segment Tree (but would be overkill)
class NumArray {
    @SuppressWarnings("unused")
    private int[] nums;
    private int[] preSum;

    public NumArray(int[] nums) {
        this.nums = nums.clone();

        buildPrefixArr(nums);
    }

    private void buildPrefixArr(int[] nums) {
        preSum = new int[nums.length];
        preSum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        
        int diff = preSum[right];
        if (left - 1 >= 0)
            diff -= preSum[left - 1];
        
        return diff;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */