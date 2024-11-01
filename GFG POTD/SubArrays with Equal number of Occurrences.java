// 22-10-2024
// MEDIUM

// Given an array arr[] and two integers say, x and y, find the number of
// sub-arrays in which the number of occurrences of x is equal to the number of
// occurrences of y.

// Examples:

// Input: arr[] = [1, 2, 1] , x= 1 , y = 2
// Output: 2
// Explanation: The possible sub-arrays have same equal number of occurrences of
// x and y are:
// 1) [1, 2], x and y have same occurrence(1).
// 2) [2, 1], x and y have same occurrence(1).

// Input: arr[] = [1, 2, 1] , x = 4 , y = 6
// Output: 6
// Explanation: The possible sub-arrays have same equal number of occurrences of
// x and y are:
// 1) [1], x and y have same occurrence(0).
// 2) [2], x and y have same occurrence(0).
// 3) [1], x and y have same occurrence(0).
// 4) [1, 2], x and y have same occurrence(0).
// 5) [2, 1], x and y have same occurrence(0).
// 6) [1, 2, 1], x and y have same occurrence(0).

// Input: arr[] = [1, 2, 1] , x= 1 , y = 4
// Output: 1
// Explanation: The possible sub-array have same equal number of occurrences of
// x and y is: [2], x and y have same occurrence(0)

// Constraints:
// 1 <= arr.size() <= 106
// 1 <= arr[i], x, y<=106

import java.util.HashMap;

class Solution {

  static int sameOccurrence(int[] arr, int x, int y) {
    int n = arr.length;
    int[] cntX = new int[n];
    int[] cntY = new int[n];

    // difference, no. of times occurred
    HashMap<Integer, Integer> hmap = new HashMap<>();

    for (int i = 0; i < n; i++) {
      if (arr[i] == x) {
        if (i != 0)
          cntX[i] = 1 + cntX[i - 1];
        else
          cntX[i] = 1;
      } 
      else {
        if (i != 0)
          cntX[i] = cntX[i - 1];
        else
          cntX[i] = 0;
      }

      if (arr[i] == y) {
        if (i != 0)
          cntY[i] = 1 + cntY[i - 1];
        else
          cntY[i] = 1;
      }
      else {
        if (i != 0)
          cntY[i] = cntY[i - 1];
        else
          cntY[i] = 0;
      }

      if (hmap.containsKey(cntX[i] - cntY[i]))
        hmap.put(cntX[i] - cntY[i], hmap.get(cntX[i] - cntY[i]) + 1);

      else
        hmap.put(cntX[i] - cntY[i], 1);

    }

    // first we have all the zero difference subarrays to include
    int res = hmap.getOrDefault(0, 0);

    // now, for each difference (in count of x and y), we stored that in hashmap
    // that means:
    // cntX[i] - cntY[i] = d
    // cntX[j] - cntY[j] = d
    // FROM ABOVE, WE CAN WRITE:
    // cntX[i] - cntX[j] = cntY[i] - cntY[j]
    // from the equation we can conclude that
    // subarrays with equal difference have equal no. of
    // occurrences of x and y

    // so, we can pick 1 x and 1 y from each of those
    // so, if there are n such subarrays with k as difference
    // then we can select Nc2 subarrays of size 2 (by picking 1 x and 1 y)
    // and we should do this for each difference

    // THE ABOVE METHOD IS CALLED:
    // HANDSHAKING METHOD -> learnt in GFG article

    for (HashMap.Entry<Integer, Integer> it : hmap.entrySet()) {
      res += (it.getValue() * (it.getValue() - 1)) / 2; // nc2 = n*(n-1) / 2
    }

    return res;
  }

  // BRUTE
  // 5 test cases => TLE
  static int sameOccurrence_BRUTE(int arr[], int x, int y) {
    // write code here
    int n = arr.length;
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      int cntX = 0, cntY = 0;
      for (int j = i; j < n; j++) {
        if (arr[j] == x)
          cntX++;
        else if (arr[j] == y)
          cntY++;

        if (cntX == cntY)
          cnt++;
      }
    }
    return cnt;
  }
}