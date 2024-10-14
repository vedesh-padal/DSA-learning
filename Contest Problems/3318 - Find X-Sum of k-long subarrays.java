// easy
// hashmap, arrays

// You are given an array nums of n integers and two integers k and x.
// The x-sum of an array is calculated by the following procedure:

// Count the occurrences of all elements in the array.
// Keep only the occurrences of the top x most frequent elements. If two
// elements have the same number of occurrences, the element with the bigger
// value is considered more frequent.
// Calculate the sum of the resulting array.
// Note that if an array has less than x distinct elements, its x-sum is the sum
// of the array.

// Return an integer array answer of length n - k + 1 where answer[i] is the
// x-sum of the
// subarray
// nums[i..i + k - 1].

// Example 1:
// Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2
// Output: [6,10,12]
// Explanation:
// For subarray [1, 1, 2, 2, 3, 4], only elements 1 and 2 will be kept in the
// resulting array. Hence, answer[0] = 1 + 1 + 2 + 2.
// For subarray [1, 2, 2, 3, 4, 2], only elements 2 and 4 will be kept in the
// resulting array. Hence, answer[1] = 2 + 2 + 2 + 4. Note that 4 is kept in the
// array since it is bigger than 3 and 1 which occur the same number of times.
// For subarray [2, 2, 3, 4, 2, 3], only elements 2 and 3 are kept in the
// resulting array. Hence, answer[2] = 2 + 2 + 2 + 3 + 3.

// Example 2:
// Input: nums = [3,8,7,8,7,5], k = 2, x = 2
// Output: [11,15,15,15,12]
// Explanation:
// Since k == x, answer[i] is equal to the sum of the subarray nums[i..i + k - 1].

// Constraints:
// 1 <= n == nums.length <= 50
// 1 <= nums[i] <= 50
// 1 <= x <= k <= nums.length

import java.util.*;

class Pair {
  int val, ind;
  public Pair(int val, int ind) {
    this.val = val;
    this.ind = ind;
  }
}

class Solution {
  public int[] findXSum(int[] nums, int k, int x) {
    int n = nums.length;
    int[] ans = new int[n - k + 1];

    for (int i = 0; i <= n - k; i++) {

      Map<Integer, Integer> fMap = new HashMap<>();

      for (int j = i; j < i + k; j++) {
        fMap.put(nums[j], fMap.getOrDefault(nums[j], 0) + 1);
      }

      List<Pair> al = new ArrayList<>();
      for (Map.Entry<Integer, Integer> entry : fMap.entrySet()) {
        al.add(new Pair(entry.getValue(), entry.getKey()));
      }

      Collections.sort(al, (a, b) -> (a.val == b.val) ? b.ind - a.ind : b.val - a.val);

      // calculate x-sum
      int sum = 0;
      for (int j = 0; j < Math.min(x, al.size()); j++) {
        sum += al.get(j).ind * al.get(j).val;
      }
      ans[i] = sum;
    }

    return ans;

  }
}