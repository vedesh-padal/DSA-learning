import java.util.HashMap;
import java.util.Map;

class Solution {
  public static int totalFruits(Integer[] arr) {
    // code here
    int l = 0, r = 0, maxLen = 0;
    int n = arr.length;
    Map<Integer, Integer> hmap = new HashMap<>();

    while (r < n) {
      hmap.put(arr[r], hmap.getOrDefault(arr[r], 0) + 1);
      // only two types of fruits allowed to be hold in our basekets (one type of
      // fruit in each basket)
      if (hmap.size() > 2) { // O(N)
        // while (hmap.size() > 2) { // O (N + N)
        int lval = hmap.get(arr[l]);
        hmap.put(arr[l], lval - 1);
        if (lval - 1 == 0)
          hmap.remove(arr[l]);
        l++;
        // }
      }
      if (hmap.size() <= 2) {
        maxLen = Math.max(maxLen, r - l + 1);
      }
      r++;
    }
    return maxLen;
  }
}