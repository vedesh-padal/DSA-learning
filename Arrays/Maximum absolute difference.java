// GFG POTD - 27 AUG 2024

/*
  Given an integer array arr of integers, the task is to find the maximum absolute difference 
  between the nearest left smaller element and the nearest right smaller element of every element
  in array arr. If for any component of the arr, the nearest smaller element doesn't exist then consider it as 0.

  Examples :

  Input: arr = [2, 1, 8]
  Output: 1
  Explanation: left smaller array ls = [0, 0, 1], right smaller array rs = [1, 0, 0]. Maximum Diff of abs(ls[i] - rs[i]) = 1
  
  Input: arr = [2, 4, 8, 7, 7, 9, 3]
  Output: 4
  Explanation: left smaller array ls = [0, 2, 4, 4, 4, 7, 2], right smaller rs = [0, 3, 7, 3, 3, 3, 0]. Maximum Diff of abs(ls[i] - rs[i]) = abs(7 - 3) = 4
  
  Expected Time Complexity: O(n)
  Expected Space Complexity: O(n)

  Constraints:
  1 <= arr.size() <= 106
  1<= arr[i] <=109
*/

class Solution {
  public int findMaxDiff(int[] arr) {
    // code here

    int n = arr.length;
    int[] l = new int[n];
    int[] r = new int[n];

    l[0] = 0;
    r[n - 1] = 0;

    for (int i = 1; i < n; i++) {
      int j = i - 1;
      while (j >= 0 && arr[j] >= arr[i]) {
        j--;
      }
      l[i] = (j >= 0) ? arr[j] : 0;

    }

    for (int i = n - 2; i >= 0; i--) {
      int j = i + 1;
      while (j < n && arr[j] >= arr[i]) {
        j++;
      }
      r[i] = (j < n) ? arr[j] : 0;
    }

    int maxi = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      maxi = Math.max(maxi, Math.abs(l[i] - r[i]));
    }

    return maxi;
  }
}