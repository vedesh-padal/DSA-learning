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


  // Naive approach is to:
  // stand at each index starting from left
  // and count no. of less than elements to the right
  // O(n^2) time, O(1) space


  // O(NlogN) time, O(n) space
  // INTUITITON:
  // We can use merge sort to count the inversions in an array, First, we divide
  // the array into two halves: a left half and a right half. Next, we recursively
  // count the inversions in both halves. While merging the two halves back
  // together, we also count how many elements from the left half array are
  // greater than elements from the right half array, as these represent cross
  // inversions (i.e, element from the left half of the array is greater than an
  // element from the right half during the merging process in the merge sort
  // algorithm). Finally, we sum the inversions from the left half, right half,
  // and the cross inversions to get the total number of inversions in the array.
  // This approach efficiently counts inversions while sorting the array.

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