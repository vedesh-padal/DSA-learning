class SearchInRotatedSortedArray {
  public int search(int[] nums, int k) {
    int n = nums.length;
    int l = 0, h = n - 1;

    while (l <= h) {
      int mid = l + (h - l) / 2;

      if (nums[mid] == k)
        return mid;

      // if left half of the array is sorted
      if (nums[l] <= nums[mid]) {
        if (nums[l] <= k && k <= nums[mid]) {
          // element exists
          h = mid - 1;
        } else
          l = mid + 1;
      } else { // if right half of the array is sorted
        if (nums[mid] <= k && k <= nums[h]) {
          // element exists in right half
          l = mid + 1;
        } else
          h = mid - 1;
      }
    }

    return -1; // search element is not in array
  }
}


// if duplicates are present

class Solution {
  public boolean search(int[] nums, int target) {
    int low = 0, high = nums.length - 1;

    while (low <= high) {
      int mid = (low + high) / 2;

      // if mid points the target
      if (nums[mid] == target)
        return true;

      // EDGE CASE: if duplicates are present:
      if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
        low++;
        high--;
        continue;
      }

      // if left half of the array is sorted:
      if (nums[low] <= nums[mid]) {
        if (nums[low] <= target && target <= nums[mid]) {
          // element is present
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
      // right half of the array is sorted
      else {
        if (nums[mid] <= target && target <= nums[high]) {
          // element is present
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return false;
  }
}