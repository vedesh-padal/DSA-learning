// Given an array of integers arr of even length n and an integer k.

// We want to divide the array into exactly n / 2 pairs such that the sum of
// each pair is divisible by k.

// Return true If you can find a way to do that or false otherwise.

// Example 1:

// Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
// Output: true
// Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
// Example 2:

// Input: arr = [1,2,3,4,5,6], k = 7
// Output: true
// Explanation: Pairs are (1,6),(2,5) and(3,4).
// Example 3:

// Input: arr = [1,2,3,4,5,6], k = 10
// Output: false
// Explanation: You can try all possible pairs to see that there is no way to
// divide arr into 3 pairs each with sum divisible by 10.

// Constraints:

// arr.length == n
// 1 <= n <= 105
// n is even.
// -109 <= arr[i] <= 109
// 1 <= k <= 105

import java.util.Arrays;

class Solution {

  // remainder and FREQUENCY approach
  public boolean canArrange(int[] arr, int k) {
    int[] freq = new int[k];
    for (int ele : arr) {
      ele = ele % k; // remainder when divided by k is stored in ele
      // and if the remainder is < 0, we add k to it to keep it +ve
      if (ele < 0)
        ele += k;

      freq[ele]++;
    }

    // elements when divided by k, their count
    // if it is not even, that means pairs cannot be formed
    // since it needs another number to form the pair
    if (freq[0] % 2 != 0)
      return false;

    // next, for remainder having elements, we check the frequency
    // of each remainder to it's pair remainder
    // EX: if k = 5, remainder 2's count should math with remainder 3's count
    // in order to form the pair
    // if at any point the freq. is not same, we say that pairs are not possible,
    // and return false
    for (int i = 1; i <= k / 2; i++) {
      if (freq[i] != freq[k - i])
        return false;
    }
    return true;
  }

  // ==================================================================
  // remainder and SORTING APPROACH
  public boolean canArrangeTHROUGH_SORTING(int[] arr, int k) {
    int n = arr.length;
    for (int i = 0; i < n; i++) {
      int rem = arr[i] % k;
      if (rem < 0)
        arr[i] = rem + k;
      else
        arr[i] = rem;
    }

    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr));

    int l = 0, r = n - 1;

    while (l < n && arr[l] == 0)
      l++;

    // each 0 - 0 indicates a pair
    // if the l was not an even number, then we say, we do not have pairs
    if (l % 2 == 1)
      return false;

    while (l < r) {
      if (arr[l] + arr[r] != k)
        return false;
      l++;
      r--;
    }
    return true;

    // MY TRY
    // Set<Integer> hset = new HashSet<>();

    // Map<Integer, ArrayList<Integer>> mapping = new HashMap<>();
    // mapping.put(1, new ArrayList<>(Arrays.asList(4, 9)));
    // mapping.put(2, new ArrayList<>(Arrays.asList(3, 8)));
    // mapping.put(3, new ArrayList<>(Arrays.asList(2, 7)));
    // mapping.put(4, new ArrayList<>(Arrays.asList(6, 1)));
    // mapping.put(5, new ArrayList<>(Arrays.asList(5, 0)));
    // mapping.put(6, new ArrayList<>(Arrays.asList(4)));
    // mapping.put(7, new ArrayList<>(Arrays.asList(3)));
    // mapping.put(8, new ArrayList<>(Arrays.asList(2)));
    // mapping.put(9, new ArrayList<>(Arrays.asList(1)));
    // mapping.put(0, new ArrayList<>(Arrays.asList(0)));

    // Map<Integer, ArrayList<Integer>> hmap = new HashMap<>();

    // for (int ele: arr) {
    // if (hmap.containsKey(mapping.get(ele % 10)).get(0) ||
    // hmap.containsKey(mapping.get(ele % 10)).get(1)) {
    // if (!hmap.get(mapping.get(ele % 10)).isEmpty()) {
    // cnt++;
    // hmap.get(mapping.get(ele % 10).remove(0));
    // }
    // }
    // hmap.put(mapping.get(ele))
    // }
  }
}