// Given an array of positive integers nums, remove the smallest subarray
// (possibly empty) such that the sum of the remaining elements is divisible by
// p. It is not allowed to remove the whole array.

// Return the length of the smallest subarray that you need to remove, or -1 if
// it's impossible.

// A subarray is defined as a contiguous block of elements in the array.

// Example 1:
// Input: nums = [3,1,4,2], p = 6
// Output: 1
// Explanation: The sum of the elements in nums is 10, which is not divisible by
// 6. We can remove the subarray [4], and the sum of the remaining elements is
// 6, which is divisible by 6.

// Example 2:
// Input: nums = [6,3,5,2], p = 9
// Output: 2
// Explanation: We cannot remove a single element to get a sum divisible by 9.
// The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.

// Example 3:
// Input: nums = [1,2,3], p = 3
// Output: 0
// Explanation: Here the sum is 6. which is already divisible by 3. Thus we do
// not need to remove anything.

// Constraints:
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 109
// 1 <= p <= 109

import java.util.*;

class Solution {

  public int minSubarray(int[] nums, int p) {
    int n = nums.length;
    long totalSum = Arrays.stream(nums).mapToLong(i -> i).sum();

    // if the total sum is divisible by p, no subarray needs to be removed
    if (totalSum % p == 0)
      return 0;

    // if total sum is less than p, then it's impossible
    // to make it divisible by p
    if (totalSum < p)
      return -1;

    // target remainder that we need to remove
    // (as the sum of the elements in the subarray that needs to be removed)
    int target = (int) (totalSum % p);

    // to store the last seen index for each prefix sum remainder
    Map<Integer, Integer> lastSeen = new HashMap<>();

    // which represents the case where we remove a prefix from the start of the
    // array.
    lastSeen.put(0, -1);

    int minLen = n; // this is the max. that we might need to remove

    // running sum (modulo p) as we iterate through the array
    int currSum = 0;

    for (int i = 0; i < n; i++) {
      // update the curr. sum (modulo p)
      currSum = (currSum + nums[i]) % p;

      // calculate the complement remainder
      int complement = (currSum - target + p) % p;
      // + p, becoz incase currSum - target becomes -ve

      // if we've seen this complement before, update minLen
      if (lastSeen.containsKey(complement)) {
        minLen = Math.min(minLen, i - lastSeen.get(complement));
      }

      // update the last seen index of the current remainder
      lastSeen.put(currSum, i);
    }

    return minLen < n ? minLen : -1;
  }

  // ====================================================
  // HELP TAKEN
  public int minSubarray_HELP(int[] nums, int p) {
    int len = nums.length, res = len;
    long total = 0L, sum = 0L;
    for (int num : nums) {
      total += num;
    }
    if (total % p == 0) {
      return 0;
    }
    Map<Long, Integer> map = new HashMap<>();
    map.put(0L, -1);
    for (int i = 0; i < len; i++) {
      sum = (sum + nums[i]) % p;
      long key = ((sum - total) % p + p) % p;
      if (map.containsKey(key)) {
        res = Math.min(res, i - map.get(key));
      }
      map.put(sum, i);
    }
    return res != len ? res : -1;
  }

  // (sum - A) % p == 0;
  // sum % p == A % p;

  // ====================================================
  public int minSubarray_BRUTE(int[] nums, int p) {
    int n = nums.length;
    long totalSum = Arrays.stream(nums).mapToLong(i -> i).sum();
    if (totalSum < p)
      return -1;
    if (totalSum % p == 0)
      return 0;

    // remove sum of each sub array from totalsum and
    // keep track of the min len of subarray that on removal
    // from total sum is divisible by p
    int minLen = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      int sum = 0;
      for (int j = i; j < n; j++) {
        sum += nums[j];
        if ((totalSum - sum) % p == 0) {

          minLen = Math.min(j - i + 1, minLen);
        }
      }
    }
    return minLen < n ? minLen : -1;

  }

  // ====================================================
  // CRY CRY --- TOO COMPLICATED, giving TLE for this code, MY APPROACH
  public int minSubarray_MY_TRY(int[] nums, int p) {
    int n = nums.length;
    int sum = Arrays.stream(nums).sum();
    if (sum < p)
      return -1;

    // prefix sum
    int[] pre = new int[n];
    pre[0] = nums[0];
    for (int i = 1; i < n; i++)
      pre[i] = pre[i - 1] + nums[i];

    TreeMap<Integer, Pair> tmap = new TreeMap<>();
    // Map<Pair, Integer>

    // min. subarray len to remove
    int minLen = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      if (pre[i] >= p) {
        int rem = pre[i] % p;

        // if (hmap.contains)
        // boolean done = false;
        boolean canBeFound = true;

        while (rem != 0) {
          if (tmap.containsKey(rem)) {
            tmap.put(rem, new Pair(tmap.get(rem).cnt - 1, tmap.get(rem).ind));
            if (tmap.get(rem).cnt == 0)
              tmap.remove(rem);

            // we update min sub arr len to remove, when we have
            // the sum of the subarray to be divisible by p
            // and if that remainder is already present, either directly
            // as a value (if case) or as sum (else case)
            int remLen = n - tmap.get(rem).ind - 1;
            minLen = Math.min(minLen, remLen);
            break;
          } else {

            // if

            Iterator<Map.Entry<Integer, Pair>> it = tmap.entrySet().iterator();
            List<Integer> possible = new ArrayList<>();
            while (it.hasNext()) {
              Map.Entry<Integer, Pair> entry = it.next();
              if (entry.getKey() > rem) {
                canBeFound = false;
                break;
              } else {

                int currVal = entry.getKey();
                possible.add(currVal);
                rem = rem - currVal;
                // decrement count of it
                tmap.put(currVal, new Pair(tmap.get(currVal).cnt - 1, tmap.get(rem).ind));
                // if cnt becomes 0, remove from tmap
                if (tmap.get(rem).cnt == 0)
                  tmap.remove(rem);

              }

            }
            if (!canBeFound)
              break;
            // else {
            // int s = 0, e = possible.length() - 1;
            // while (s < e) {

            // }
            // }
          }
        }

      }
    }
    return minLen;
  }
}

class Pair {
  int cnt, ind;

  public Pair(int cnt, int ind) {
    this.cnt = cnt;
    this.ind = ind;
  }
}