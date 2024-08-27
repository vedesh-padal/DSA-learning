/*
  Given an array of integers. Find the Inversion Count in the array.  
  Two array elements arr[i] and arr[j] form an inversion if arr[i] > arr[j] and i < j.

  Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. 
  If the array is already sorted then the inversion count is 0.
  If an array is sorted in the reverse order then the inversion count is the maximum. 

  Examples:

  Input: n = 5, arr[] = {2, 4, 1, 3, 5}
  Output: 3
  Explanation: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
  Input: n = 5, arr[] = {2, 3, 4, 5, 6}
  Output: 0
  Explanation: As the sequence is already sorted so there is no inversion count.
  Input: n = 3, arr[] = {10, 10, 10}
  Output: 0
  Explanation: As all the elements of array are same, so there is no inversion count.
  Expected Time Complexity: O(nLogn).
  Expected Auxiliary Space: O(n).

  Constraints:
  1 ≤ n ≤ 5*105
  1 ≤ arr[i] ≤ 1018
*/

import java.util.ArrayList;

class Solution {

  private static long merge(long[] arr, int low, int mid, int high) {
    ArrayList<Long> temp = new ArrayList<>();

    int left = low;
    int right = mid + 1;

    long cnt = 0;

    while (left <= mid && right <= high) {
      if (arr[left] <= arr[right]) {
        temp.add(arr[left]);
        left++;
      } else {
        temp.add(arr[right]);
        cnt += (mid - left + 1);
        right++;
      }
    }

    while (left <= mid) {
      temp.add(arr[left]);
      left++;
    }

    while (right <= high) {
      temp.add(arr[right]);
      right++;
    }

    for (int i = low; i <= high; i++) {
      arr[i] = temp.get(i - low);
    }

    return cnt;
  }

  private static long mergeSort(long[] arr, int low, int high) {

    long cnt = 0;
    if (low >= high)
      return cnt;

    int mid = low + (high - low) / 2;

    cnt += mergeSort(arr, low, mid);
    cnt += mergeSort(arr, mid + 1, high);
    cnt += merge(arr, low, mid, high);

    return cnt;
  }

  // arr[]: Input Array
  // N : Size of the Array arr[]
  // Function to count inversions in the array.
  static long inversionCount(long arr[], int n) {
    // Your Code Here
    return mergeSort(arr, 0, n - 1);
  }
}