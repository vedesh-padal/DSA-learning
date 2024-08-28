class BinarySearch {
  public int binarySearch(int[] arr, int target)  {
    int l = 0, r = arr.length - 1;
    
    while (l <= r)  {
      int mid = l + (r - l) / 2;
      if (arr[mid] == target)
        return mid;
      else if (target < arr[mid])
        r = mid - 1;
      else
        l = mid + 1;
    }

    return -1;  // no such target element is found, so -1
  }

  // ----------------------------------------------------------------------------------------------------------------------
  // The main difference between the lower and upper bound is in the condition. 
  // For the lower bound the condition was arr[ind] >= x and here, in the case of the upper bound, it is arr[ind] > x.
  
  // returns the index / position of lower bound(0-based indexing) in the array
  public int lowerBound(int[] arr, int x) {
    int n = arr.length - 1;
    int l = 0, h = n - 1;
    int ans = n;  // If we don’t find any index, we will return n

    while (l <= h)  {
      int mid = (l + h) / 2;
      if (arr[mid] >= x)  {
        ans = mid;
        h = mid - 1;
      }
      else 
        l = mid + 1;
    }

    return ans;
  }

  // ----------------------------------------------------------------------------------------------------------------------

  public int upperBound(int[] arr, int x) {
    int n = arr.length - 1;
    int l = 0, h = n - 1;
    int ans = n;  // If we don’t find any index, we will return n

    while (l <= h)  {
      int mid = (l + h) / 2;
      if (arr[mid] > x)  {
        ans = mid;
        h = mid - 1;
      }
      else 
        l = mid + 1;
    }

    return ans;
  }

  // ----------------------------------------------------------------------------------------------------------------------
  // SEARCH INSERT POSITION --- SAME AS LOWER BOUND
  //   Problem Statement: You are given a sorted array arr of distinct values and a target value x. You need to search for the index of the target value in the array.
  // If the value is present in the array, then return its index. Otherwise, determine the index where it would be inserted in the array while maintaining the sorted order.
  public static int searchInsert(int[] arr, int x) {
    int n = arr.length; // size of the array
    int low = 0, high = n - 1;
    int ans = n;

    while (low <= high) {
      int mid = (low + high) / 2;
      // maybe an answer
      if (arr[mid] >= x) {
        ans = mid;
        // look for smaller index on the left
        high = mid - 1;
      } else {
        low = mid + 1; // look on the right
      }
    }
    return ans;
  }

  


}