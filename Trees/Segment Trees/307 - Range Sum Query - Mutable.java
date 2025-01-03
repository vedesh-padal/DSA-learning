// MEDIUM
// arrays, design, binary-indexed-tree, segment-tree

/*
    Given an integer array nums, handle multiple queries of the following types:

    1. Update the value of an element in nums.
    2. Calculate the sum of the elements of nums between indices left and right
    inclusive where left <= right.

    Implement the NumArray class:
    - NumArray(int[] nums) Initializes the object with the integer array nums.
    - void update(int index, int val) Updates the value of nums[index] to be val.
    - int sumRange(int left, int right) Returns the sum of the elements of nums
        between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).

    Example 1:
    Input
    ["NumArray", "sumRange", "update", "sumRange"]
    [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
    Output
    [null, 9, null, 8]
    Explanation
    NumArray numArray = new NumArray([1, 3, 5]);
    numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
    numArray.update(1, 2); // nums = [1, 2, 5]
    numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8

    Constraints:
    1 <= nums.length <= 3 * 10^4
    -100 <= nums[i] <= 100
    0 <= index < nums.length
    -100 <= val <= 100
    0 <= left <= right < nums.length
    At most 3 * 10^4 calls will be made to update and sumRange.
*/

class NumArray {
    private int[] segTree;
    private int[] nums;

    public NumArray(int[] nums) {
        int n = nums.length;
        this.nums = nums.clone();
        segTree = new int[4*n];

        buildSegTree(0, 0, n-1, nums, segTree);
    }

    private void buildSegTree(int i, int l, int r, int[] nums, int[] segTree) {
        if (l == r) {   // leaf node
            segTree[i] = nums[l];
            return;
        }
        int mid = l + (r - l) / 2;
        buildSegTree(2*i + 1, l, mid, nums, segTree);
        buildSegTree(2*i + 2, mid + 1, r, nums, segTree);

        segTree[i] = segTree[2*i + 1] + segTree[2*i + 2];
    }
    
    // ==============
    public void update(int index, int val) {
        update(0, 0, nums.length - 1, index, val);
    }

    // TC: O(2*log(N))
    private void update(int i, int l, int r, int index, int val) {
        if (l == r) {
            segTree[i] = val;
            return;
        }
        int mid = l + (r - l) / 2;
        if (index <= mid) { // left child
            update(2*i + 1, l, mid, index, val);
        }
        else {  // right child
            update(2*i + 2, mid + 1, r, index, val);
        }
        segTree[i] = segTree[2*i + 1] + segTree[2*i + 2];
    }
    
    // ==============
    public int sumRange(int left, int right) {
        return sumRange(left, right, 0, 0, nums.length - 1);
    }

    private int sumRange(int start, int end, int i, int l, int r) {
        // totally out of range
        if (end < l || r < start) {
            return 0;
        }

        // [l, r] range completely present in the [start, end] range completely included
        if (start <= l && r <= end) {
            return segTree[i];
        }

        // overlapping 
        int mid = l + (r - l) / 2;

        return sumRange(start, end, 2*i + 1, l, mid) + sumRange(start, end, 2*i + 2, mid + 1, r);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */