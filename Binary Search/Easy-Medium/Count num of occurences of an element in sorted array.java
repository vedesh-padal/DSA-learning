class Solution {
  public int[] searchRange(int[] nums, int target) {
    int n = nums.length;
    int l = 0, h = n - 1;
    int first = -1, last = -1;

    while (l <= h) {
      int mid = l + (h - l) / 2;
      if (nums[mid] == target) {
        // once the target is found, decrement first until we are finding target, and
        // increment target until we are finding target
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

  // ----------------------------------------------------------
  // similar, count num of occurences:

  int count(int[] arr, int n, int x) {
    // code here
    int l = 0, h = n - 1;

    int first = -1, last = -1;

    // find first occurrence
    while (l <= h) {
      int mid = l + (h - l) / 2;
      if (arr[mid] == x) {
        first = mid;
        h = mid - 1;
      } else if (arr[mid] > x)
        h = mid - 1;
      else
        l = mid + 1;
    }

    if (first == -1)
      return 0;

    // find last occurrence
    l = 0;
    h = n - 1;

    while (l <= h) {
      int mid = l + (h - l) / 2;
      if (arr[mid] == x) {
        last = mid;
        l = mid + 1;
      } else if (arr[mid] > x)
        h = mid - 1;
      else
        l = mid + 1;
    }

    // return count of x
    return last - first + 1;
  }
}