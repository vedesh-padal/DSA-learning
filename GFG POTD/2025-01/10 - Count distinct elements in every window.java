// EASY
// arrays, sliding-window, hash

/*
    Given an integer array arr[] and a number k. Find the count of distinct
    elements in every window of size k in the array.

    Examples:
    Input: arr[] = [1, 2, 1, 3, 4, 2, 3], k = 4
    Output:  [3, 4, 4, 3]
    Explanation: Window 1 of size k = 4 is 1 2 1 3. Number of distinct elements in this window are 3. 
    Window 2 of size k = 4 is 2 1 3 4. Number of distinct elements in this window are 4.
    Window 3 of size k = 4 is 1 3 4 2. Number of distinct elements in this window are 4.
    Window 4 of size k = 4 is 3 4 2 3. Number of distinct elements in this window are 3.

    Input: arr[] = [4, 1, 1], k = 2
    Output: [2, 1]
    Explanation: Window 1 of size k = 2 is 4 1. Number of distinct elements in this window are 2. 
    Window 2 of size k = 2 is 1 1. Number of distinct elements in this window is 1. 

    Input: arr[] = [1, 1, 1, 1, 1], k = 3
    Output: [1, 1, 1]

    Constraints:
    1 <= k <= arr.size() <= 10^5
    1 <= arr[i] <= 10^5
*/
import java.util.*;

class Solution {
    ArrayList<Integer> countDistinct(int arr[], int k) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        int n = arr.length;
        int l = 0;
        // num, count
        Map<Integer, Integer> hmap = new HashMap<>();
        Set<Integer> hset = new HashSet<>();    // will not exceed size k
        
        for (int r = 0; r < n; r++) {
            if ((r - l + 1) > k) {
                hmap.put(arr[l], hmap.get(arr[l]) - 1);
                if (hmap.get(arr[l]) == 0) {
                    hmap.remove(arr[l]);
                    hset.remove(arr[l]);
                }
                l++;
            }
            
            hmap.put(arr[r], hmap.getOrDefault(arr[r], 0) + 1);
            if (hmap.get(arr[r]) == 1 && !hset.contains(arr[r])) {
                hset.add(arr[r]);
            }
            if ((r - l + 1) == k) {
                res.add(hset.size());
            }
        }
        return res;
    }
}