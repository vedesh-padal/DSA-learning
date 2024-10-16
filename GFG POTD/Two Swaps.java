// 16-10-2024
// EASY - MEDIUM - GFG
// arrays

// Given a permutation of some of the first natural numbers in an array arr[],
// determine if the array can be sorted in exactly two swaps. A swap can involve
// the same pair of indices twice.

// Return true if it is possible to sort the array with exactly two swaps,
// otherwise return false.

// Examples:
// Input: arr = [4, 3, 2, 1]
// Output: true
// Explanation: First, swap arr[0] and arr[3]. The array becomes [1, 3, 2, 4].
// Then, swap arr[1] and arr[2]. The array becomes [1, 2, 3, 4], which is
// sorted.

// Input: arr = [4, 3, 1, 2]
// Output: false
// Explanation: It is not possible to sort the array with exactly two swaps.

// Constraints:
// 1 ≤ arr.size() ≤ 106
// 1 ≤ arr[i] ≤ arr.size()
import java.util.List;

class Solution {

  public boolean checkSorted(List<Integer> arr) {
    int unsortedElementsCount = findUnsortedCount(arr);

    // if 3 => ex: [ 4, 1, 3, 2, 5 ] => a[0] - a[4] and a[1] - a[3]

    if (unsortedElementsCount == 0 || unsortedElementsCount == 3) {
      return true;
    }

    // swap the unsorted elements two times
    // and check if after this unsorted elements count == 0
    // if yes, return true
    if (unsortedElementsCount == 4) {
      swapOne(arr);
      swapOne(arr);

      return findUnsortedCount(arr) == 0;
    }
    // if there are more than 4 unsorted elements
    // then we cannot sort them in 2 swaps
    return false;
  }

  private int findUnsortedCount(List<Integer> arr) {
    int cnt = 0;
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i) == i + 1)
        continue;
      else
        cnt++;
    }
    return cnt;
  }

  private void swapOne(List<Integer> arr) {
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i) != i + 1) {
        int t = arr.get(i);
        arr.set(i, arr.get(t - 1));
        arr.set(t - 1, t);
        break;
      }
    }
  }
}