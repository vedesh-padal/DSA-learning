class Solution {

  // LINEAR SEARCH approach with conditions, can help if there are multiple peaks
  // and if u want to find the highest peak
  public int findPeakElement1(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      if ((i == 0 || nums[i - 1] < nums[i]) && (i == n - 1 || nums[i] > nums[i + 1]))
        return i;
    }
    return -1;
  }

  // --------------------------------------------------------------------
  // BINARY SEARCH approach
  public int findPeakElement2(int[] nums) {
    int n = nums.length;

    if (n == 1)
      return 0;

    // since we can find any of the peaks:
    if (nums[0] > nums[1])
      return 0;
    if (nums[n - 1] > nums[n - 2])
      return n - 1;

    int l = 1, h = n - 2;

    while (l <= h) {
      int mid = l + (h - l) / 2;

      // if arr[mid] is the peak
      if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1])
        return mid;

      // if we are in the left
      if (nums[mid - 1] < nums[mid])
        l = mid + 1;

      // if we are in the right
      // or arr[mid] is the common point
      else
        h = mid - 1;
    }

    // dummy return as, we will definitely reach here
    return -1;
    // as there will exists atleast a peak
  }
}