package trees.bst;

class Solution {

    private TreeNode create(TreeNode root, int[] nums, int l, int r) {
        if (l > r)
            return null;
        
        int mid = l + (r - l) / 2;

        TreeNode t = new TreeNode(nums[mid]);
        
        t.left = create(t, nums, l, mid-1);
        t.right = create(t, nums, mid+1, r);

        return t;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 1)
            return new TreeNode(nums[0]);
        
        return create(null, nums, 0, nums.length - 1);
    }
}