// 24-10-2024
// EASY
// arrays

// Given an array arr. Return the modified array in such a way that if the
// current and next numbers are valid numbers and are equal then double the
// current number value and replace the next number with 0. After the
// modification, rearrange the array such that all 0's are shifted to the end.

// Note:
// Assume ‘0’ as the invalid number and all others as a valid number.
// The sequence of the valid numbers is present in the same order.

// Example:
// Input: arr[] = [2, 2, 0, 4, 0, 8]
// Output: [4, 4, 8, 0, 0, 0]
// Explanation: At index 0 and 1 both the elements are the same. So, we will
// change the element at index 0 to 4 and the element at index 1 is 0 then we
// will shift all the zeros to the end of the array. So, the array will become
// [4, 4, 8, 0, 0, 0].

// Input: arr[] = [0, 2, 2, 2, 0, 6, 6, 0, 0, 8]
// Output: [4, 2, 12, 8, 0, 0, 0, 0, 0, 0]
// Explanation: At index 5 and 6 both the elements are the same. So, we will
// change the element at index 5 to 12 and the element at index 6 is 0. We will
// change the element at index 1 to 4 and the element at index 2 is 0. Then we
// shift all the zeros to the end of the array. So, array will become [4, 2, 12,
// 8, 0, 0, 0, 0, 0, 0].

// Expected Time Complexity: O(n)
// Expected Auxiliary Space: O(n)

import java.util.ArrayList;

class Solution {
  static ArrayList<Integer> modifyAndRearrangeArr(int arr[]) {
    // Complete the function
    ArrayList<Integer> res = new ArrayList<>();

    int i = 0;
    while (i + 1 < arr.length) {
      if (arr[i] == arr[i + 1]) {
        arr[i] = arr[i] * 2;
        arr[i + 1] = 0;
        i += 2;
      } else {
        i++;
      }
    }
    for (int num : arr) {
      if (num != 0)
        res.add(num);
    }
    int k = res.size();
    for (i = 0; i < arr.length - k; i++)
      res.add(0);

    return res;
  }
}
