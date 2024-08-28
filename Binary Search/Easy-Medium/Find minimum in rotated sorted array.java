class Solution {
  public int findMin(int[] nums) {
    // finding minimum in rotated sorted array
    int low = 0, high = nums.length - 1;
    int min = Integer.MAX_VALUE;

    while (low <= high) {
      int mid = (low + high) / 2;

      // if left half is sorted
      if (nums[low] <= nums[mid]) {
        // pick the left most from left sorted - becoz min. and neglect the left half
        // and search for right half of the array
        min = Math.min(nums[low], min); 
        low = mid + 1;
      }
      // if right half is sorted
      else {
        min = Math.min(nums[mid], min);
        high = mid - 1;
      }
    }
    return min;
  }
}