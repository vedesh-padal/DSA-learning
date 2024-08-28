
// We can easily observe that the number of rotations in an array is equal to the index(0-based index) of its minimum element.
// So, in order to solve this problem, we have to find the index of the minimum element.

class NumRotates {
  public int numTimesRotated(int[] arr) {
    int n = arr.length;
    int l = 0, h = n - 1;

    int mini = Integer.MAX_VALUE;
    int index = -1;

    while (l <= h)  {
      int mid = l + (h - l) / 2;
      
      // if the array is already sorted
      if (arr[l] <= arr[h]) {
        if (arr[l] < mini)  {
          mini = arr[l];
          index = l;
        }
      }

      // if the whole array is not sorted

      // if left half is sorted
      if (arr[l] <= arr[mid]) {
        // keep the minimum
        if (arr[l] < mini)  {
          mini = arr[l];
          index = l;
        }
        // eliminate left half, and search in right half if any min. element is present
        l = mid + 1;
      }
      else {
        if (arr[mid] < mini)  {
          mini = arr[mid];
          index = mid;
        }
        h = mid - 1;
      }
    }

    return index;
  }
}
