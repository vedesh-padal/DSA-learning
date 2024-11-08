// MEDIUM
// prefix-sum, arrays

// Given an array, arr[], determine if arr can be split into three consecutive
// parts such that the sum of each part is equal. If possible, return any index
// pair(i, j) in an array such that sum(arr[0..i]) = sum(arr[i+1..j]) =
// sum(arr[j+1..n-1]), otherwise return an array {-1,-1}.

// Note: Driver code will print true if arr can be split into three equal sum
// subarrays, otherwise, it is false.

// Examples :
// Input: arr[] = [1, 3, 4, 0, 4]
// Output: true
// Explanation: [1, 2] is valid pair as sum of subarray arr[0..1] is equal to
// sum of subarray arr[2..3] and also to sum of subarray arr[4..4]. The sum is 4.

// Input: arr[] = [2, 3, 4]
// Output: false
// Explanation: No three subarrays exist which have equal sum.

// Input: arr[] = [0, 1, 1]
// Output: false

// Constraints:
// 3 ≤ arr.size() ≤ 106
// 0 ≤ arr[i] ≤ 106

import java.util.*;

class Solution {
  // Function to determine if array arr can be split into three equal sum sets.
  public List<Integer> findSplit(int[] arr) {
    // code here
    List<Integer> res = new ArrayList<>();

    int totalSum = 0;

    for (int num : arr)
      totalSum += num;

    if (totalSum % 3 != 0)
      return List.of(-1, -1);

    int eqSum = totalSum / 3;
    int currSum = 0;
    int firstIndex = -1;

    for (int i = 0; i < arr.length; i++) {
      currSum += arr[i];
      if (currSum == eqSum && firstIndex == -1) {
        firstIndex = i;
      } 
      else if (currSum == 2 * eqSum && firstIndex != -1) {
        res.add(firstIndex);
        res.add(i);
        return res;
      }
    }
    return List.of(-1, -1);
  }
}