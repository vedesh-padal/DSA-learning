// MEDIUM
// sliding-window, arrays, deque, monotonic-queue, ordered-map

/*
    You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:

    Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of indices 
    i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
    Return the total number of continuous subarrays.

    A subarray is a contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [5,4,2,4]
    Output: 8
    Explanation: 
    Continuous subarray of size 1: [5], [4], [2], [4].
    Continuous subarray of size 2: [5,4], [4,2], [2,4].
    Continuous subarray of size 3: [4,2,4].
    Thereare no subarrys of size 4.
    Total continuous subarrays = 4 + 3 + 1 = 8.
    It can be shown that there are no more continuous subarrays.
    
    Example 2:
    Input: nums = [1,2,3]
    Output: 6
    Explanation: 
    Continuous subarray of size 1: [1], [2], [3].
    Continuous subarray of size 2: [1,2], [2,3].
    Continuous subarray of size 3: [1,2,3].
    Total continuous subarrays = 3 + 2 + 1 = 6.
    
    Constraints:
    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^9
*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

class Solution {

    // using Monotonic Queue concept
    // using Deque
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long count = 0;

        Deque<Integer> min = new ArrayDeque<>();
        Deque<Integer> max = new ArrayDeque<>();

        int i = 0, j = 0;
        while (j < n) {
            while (!min.isEmpty() && nums[min.peekLast()] > nums[j]) {
                min.pollLast();
            }
            min.addLast(j);

            while (!max.isEmpty() && nums[max.peekLast()] < nums[j]) {
                max.pollLast();
            }
            max.addLast(j);

            while (!min.isEmpty() && !max.isEmpty() && nums[max.peekFirst()] - nums[min.peekFirst()] > 2) {
                if (min.peekFirst() == i) {
                    min.pollFirst();
                }
                if (max.peekFirst() == i) {
                    max.pollFirst();
                }
                i++;
            }

            count += (j - i + 1);
            j++;
        }

        return count;
    }

    // ================================================================================================
    // using TreeMap
    public long continuousSubarrays_usingTreeMap(int[] nums) {
        int n = nums.length;
        long count = 0;
        TreeMap<Integer, Integer> tmap = new TreeMap<>();
        int i = 0, j = 0;

        while (j < n) {
            // expand the window, and increase the count of the new num
            // since there can be duplicates, we are using TreeMap instead of TreeSet
            // and Ordered one (Tree...), becoz, we want to obtain the minimum
            // and maximum in that window without having to do entire traversal of that
            // window to find the mini and maxi among it
            tmap.put(nums[j], tmap.getOrDefault(nums[j], 0) + 1);

            // here, the key point is, we just need the mini and maxi in the window
            // becoz, with them itself we can easily come to a conclusion if the window (subarray)
            // is `continuous`, without having to compare each pair in that window

            // if, violating the continuous condition, shrink the window
            while (Math.abs(tmap.lastKey() - tmap.firstKey()) > 2) {
                tmap.put(nums[i], tmap.get(nums[i]) - 1);
                if (tmap.get(nums[i]) == 0) {
                    tmap.remove(nums[i]);
                }
                i++;
            }

            // this represents the number of new subarrays possible due to 
            // this window expansion (or even the current window)
            // do a dry run, you will understand
            count += (j - i + 1);
            j++;
        }

        return count;
    }
}