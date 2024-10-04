// Given an array of N integers and Q queries of indices. Return a list NGEs[]
// where NGEs[i] stores the count of elements strictly greater than the current
// element (arr[indices[i]]) to the right of indices[i].

// Examples :

// Input: arr[] = [3, 4, 2, 7, 5, 8, 10, 6]
// queries = 2
// indices[] = [0, 5]
// Output: 6, 1
// Explanation: The next greater elements to the right of 3(index 0) are
// 4,7,5,8,10,6. The next greater elements to the right of 8(index 5) is only
// 10.

// Input: arr[] = [1, 2, 3, 4, 1]
// queries = 2
// indices[] = [0, 3]
// Output: 3, 0
// Explanation: The count of numbers to the right of index 0 which are greater
// than arr[0] is 3 i.e. (2, 3, 4). Similarly, the count of numbers to the right
// of index 3 which are greater than arr[3] is 0, since there are no greater
// elements than 4 to the right of the array.

// Expected Time Complexity: O(N * queries).
// Expected Auxiliary Space: O(queries).

// Constraints:
// 1 <= N <= 104
// 1 <= arr[i] <= 105
// 1 <= queries <= 100

import java.util.*;

class Pair {
  int val, ind;

  public Pair(int val, int ind) {
    this.val = val;
    this.ind = ind;
  }
}

class Solution {

  private static int[] findNGE(int N, int arr[], int queries, int indices[]) {
    Stack<Pair> stk = new Stack<>();
    int[] nge = new int[N];

    for (int i = N - 1; i >= 0; i--) {
      while (!stk.isEmpty() && stk.peek().val <= arr[i])
        stk.pop();

      nge[i] = (stk.isEmpty()) ? -1 : stk.peek().ind;

      stk.push(new Pair(arr[i], i));
    }
    return nge;
  }

  private static int findCount(int[] arr, int m, int ind) {
    int count = 0;
    if (ind == -1)
      return count;

    for (int i = ind; i < arr.length; i++) {
      if (arr[i] > m)
        count++;
    }
    return count;
  }

  public static int[] count_NGEs(int N, int arr[], int queries, int indices[]) {
    int[] res = new int[queries];

    // gets the index of the NGE for each element
    int[] nge = findNGE(N, arr, queries, indices);

    for (int i = 0; i < queries; i++)
      res[i] = findCount(arr, arr[indices[i]], nge[indices[i]]);

    return res;
  }

  // =================================================================
  // was trying to do in O(k) space, but seems that is impossible
  // or complicates the solution
  public static int[] count_NGEs_SPACE_optimized_NotWorking(int N, int arr[], int queries, int indices[]) {
    // code here

    int[] res = new int[queries];

    Stack<Pair> stk = new Stack<>();
    int j = 0; // to keep track of queries

    for (int i = N - 1; i >= 0; i--) {
      while (!stk.isEmpty() && stk.peek().val <= arr[i])
        stk.pop();

      if (j < queries && i == indices[j]) {
        int ngeIndex = (stk.isEmpty()) ? -1 : stk.peek().ind;

        int cnt = 0;

        if (ngeIndex != -1) {
          for (int k = ngeIndex; k < N; k++) { // Start from ngeIndex + 1
            if (arr[k] > arr[i]) {
              cnt++;
            }
          }
        }

        res[j++] = cnt; // Store count in result array

      }

      stk.push(new Pair(arr[i], i));

    }
    return res;
  }
}