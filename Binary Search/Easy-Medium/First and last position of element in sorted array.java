class Solution {
  public int[] searchRange(int[] nums, int target) {
    int n = nums.length;
    int l = 0, h = n - 1;
    int first = -1, last = -1;

    while (l <= h) {
      int mid = l + (h - l) / 2;
      if (nums[mid] == target) {
        first = mid;
        last = mid;

        while (first > 0 && nums[first - 1] == target)
          first--;

        while (last < n - 1 && nums[last + 1] == target)
          last++;

        break;
      } else if (target < nums[mid])
        h = mid - 1;
      else
        l = mid + 1;
    }

    return new int[] { first, last };
  }
}