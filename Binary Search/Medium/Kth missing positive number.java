
// A very good one, requires lot of understanding and Math

class Solution {

  // O(n) simple approach
  // The main idea is to shift k by 1 step if the current element is
  // smaller or equal to k. And whenever we get a number > k, we can conclude that
  // k is the missing number.
  public int findKthPositive1(int[] arr, int k) {
    for (int i : arr) {
      if (i <= k)
        k++;
      else
        break;
    }
    return k;
  }

  // -------------------------------------------------

  public int findKthPositive2(int[] arr, int k) {
    int n = arr.length;
    int l = 0, h = n - 1;

    while (l <= h) {
      int mid = (l + h) / 2;
      int missingNumsTillIndex = arr[mid] - (mid + 1); // mid + 1, becoz 1...N ( for number at that index )
      if (missingNumsTillIndex < k)
        l = mid + 1;
      else
        h = mid - 1;
    }

    // now once l > h
    // h is pointing to the starting range, and l is pointing to the ending range
    // where k will lie
    // the below nums representing num of missing nums till that index
    // ex: 2 3 7 11 l is at 11, h is at 3
    // return arr[h] + k - (numMissingTillIndex)
    // return arr[h] + k - ( arr[h] - (h + 1) )
    // return arr[h] + k - arr[h] + h + 1
    // return h + 1 + k;
    // return l + k;

    return h + 1 + k;

  }
}