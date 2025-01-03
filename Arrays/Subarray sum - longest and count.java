import java.util.ArrayList;
import java.util.HashMap;

class Solution {
  public int subarraySum1(int[] nums, int k) {
    int n = nums.length;

    // Approach - 1: 2 pointer method => optimal, but only when there are 0 and +ve integers
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
  // approach - 2: using hashmap => applicable when +ve and -ve integers, including 0
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

  // easy way and to understand:
  public ArrayList<Integer> subarraySum(int[] arr, int target) {
    // code here
    ArrayList<Integer> res = new ArrayList<>();
    int currSum = 0;
    int l = 0;

    for (int r = 0; r < arr.length; r++) {
      currSum += arr[r];

      while (currSum > target && l <= r) {
        currSum -= arr[l];
        l++;
      }
      if (currSum == target) {
        res.add(l + 1);
        res.add(r + 1);
        return res;
      }
    }

    res.add(-1);
    return res;
  }
}