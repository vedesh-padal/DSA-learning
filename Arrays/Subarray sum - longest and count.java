import java.util.HashMap;

class Solution {
  public int subarraySum1(int[] nums, int k) {
    int n = nums.length;

    // Approach - 1: 2 pointer method
    int l = 0, r = 0;
    int sum = nums[0];
    int count = 0; // to keep track of the subarrays of sum = k
    // int maxLen = 0;
    while (r < n) {
      if (l <= r && sum > k) {
        sum -= nums[l];
        l++;
      }
      if (sum == k) {
        count++;
        // if the problem is about finding the maxLen of such subarray:
        // maxLen = Math.max(maxLen, r - l + 1);
      }

      r++;
      if (r < n && sum < k)
        sum += nums[r];
    }
    return count;
  }

  // ------------------------------------
  // approach - 2: using hashmap
  public int subarraySum2(int[] nums, int k) {
    int n = nums.length;
    // Approach - 2: prefix sum:
    int sum = 0, count = 0;
    HashMap<Integer, Integer> hmap = new HashMap<>();
    hmap.put(0, 1); // for the cases, when you have sum == k, and it should be considered,
    // and no. of those subarrays should also be counted

    for (int i = 0; i < n; i++) {
      sum += nums[i];
      if (hmap.containsKey(sum - k))
        count += hmap.get(sum - k);

      // or just:
      // count += hmap.getOrDefault(sum-k, 0);

      hmap.put(sum, hmap.getOrDefault(sum, 0) + 1);
    }
    return count;
  }
}