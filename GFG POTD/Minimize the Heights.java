// MEDIUM

/*
  Given an array arr[] denoting heights of N towers and a positive integer K, 
  you have to modify the height of each tower either by increasing or decreasing them by K only once.
  Find out what could be the possible minimum difference of the height of 
  shortest and longest towers after you have modified each tower.
  Note: Assume that height of the tower can be negative.
  A slight modification of the problem can be found here. 

  Example 1:
  Input:
  K = 2, N = 4
  Arr[] = {1, 5, 8, 10}
  Output:
  5
  Explanation:
  The array can be modified as 
  {3, 3, 6, 8}. The difference between 
  the largest and the smallest is 8-3 = 5.

  Example 2:
  Input:
  K = 3, N = 5
  Arr[] = {3, 9, 12, 16, 20}
  Output:
  11
  Explanation:
  The array can be modified as
  {6, 12, 9, 13, 17}. The difference between 
  the largest and the smallest is 17-6 = 11. 

  Your Task:
  You don't need to read input or print anything. Your task is to complete the function 
  getMinDiff() which takes the arr[], n and k as input parameters and returns an integer 
  denoting the minimum difference.

  Expected Time Complexity: O(N*logN)
  Expected Auxiliary Space: O(N)

  Constraints
  1 ≤ K ≤ 104
  1 ≤ N ≤ 105
  1 ≤ Arr[i] ≤ 105
*/


// MINIMIZE THE HEIGHTS - I
import java.util.Arrays;

class Solution {
  int getMinDiff(int[] arr, int n, int k) {
    // code here
    // first we need to sort the array (obviously)
    Arrays.sort(arr);
    // to store the min. difference between the largest and shortest towers
    int ans = arr[n - 1] - arr[0];

    // when we want to find the min. difference,
    // we increase the height of the smallest tower by k
    // and decrease the height of the largeest tower by k
    int smallest = arr[0] + k;
    int largest = arr[n - 1] - k;

    // If smallest exceeds largest, swap them
    if (smallest > largest) {
      int temp = smallest;
      smallest = largest;
      largest = temp;
    }

    // represent the curr. min. and max. heights of towers after modifications
    int mini, maxi;

    // we repeat the same process by comparing
    // adjacent elements and updating smallest and largest
    // then updating the min. diff (ans) if it is small
    for (int i = 0; i < n - 1; i++) {
      // right side elements decrease height by k
      mini = Math.min(smallest, arr[i + 1] - k);
      // left side elements increase height by k
      maxi = Math.max(largest, arr[i] + k);

      // becoz, this will result in higher number when done (maxi - mini)
      // so, better skip, doesn't matter though
      // if (mini < 0)
      // continue;
      ans = Math.min(ans, maxi - mini);
    }
    return ans;
  }

  // ----------------------------------------------------------------------------------------
  // GFG POTD QUESTION:
  // VARIATION - 2:
  // Note: It is compulsory to increase or decrease the height by K for each tower. 
  // AFTER EACH OPERATION, THE RESULTANT ARRAY SHOULD NOT CONTAIN ANY NEGATIVE INTEGER
  int getMinDiff2(int[] arr, int k) {
    // code here
    Arrays.sort(arr);
    int n = arr.length;

    int ans = arr[n - 1] - arr[0];
    int smallest = arr[0] + k;
    int largest = arr[n - 1] - k;
    if (smallest > largest) {
      int t = smallest;
      smallest = largest;
      largest = t;
    }
    for (int i = 0; i < n - 1; i++) {
      int mini = Math.min(smallest, arr[i + 1] - k);
      int maxi = Math.max(largest, arr[i] + k);

      if (mini < 0)
        continue;

      ans = Math.min(ans, maxi - mini);
    }
    return ans;
  }
}