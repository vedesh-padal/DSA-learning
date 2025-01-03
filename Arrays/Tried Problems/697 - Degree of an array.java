// EASY
// hashmap, arrays

/*
    Given a non-empty array of non-negative integers nums, the degree of this
    array is defined as the maximum frequency of any one of its elements.

    Your task is to find the smallest possible length of a (contiguous) subarray
    of nums, that has the same degree as nums.

    Example 1:
    Input: nums = [1,2,2,3,1]
    Output: 2
    Explanation:
    The input array has a degree of 2 because both elements 1 and 2 appear twice.
    Of the subarrays that have the same degree:
    [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
    The shortest length is 2. So return 2.

    Example 2:
    Input: nums = [1,2,2,3,1,4,2]
    Output: 6
    Explanation:
    The degree is 3 because the element 2 is repeated 3 times.
    So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.

    Constraints:
    nums.length will be between 1 and 50,000.
    nums[i] will be an integer between 0 and 49,999.
*/

import java.util.HashMap;

class Solution {
    public int findShortestSubArray(int[] nums) {
        // we will have two hashmaps, 
        // 1. to store the first occurence of an element, and it's index
        // 2. count / frequency of occurence of each element
        HashMap<Integer, Integer> first = new HashMap<>(), count = new HashMap<>();
        int res = -1, degree = 0;
        // res => represents the min.length of subarray with the highest degree
        // min. length of subarray is possible when:
        // - or also called the distance between the first and last occurence of that element

        for (int i = 0; i < nums.length; i++) {
            first.putIfAbsent(nums[i], i);
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);

            if (count.get(nums[i]) > degree) {
                degree = count.get(nums[i]);
                res = (i - first.get(nums[i]) + 1); // like of form: (n - i + 1) => to find the length
            }
            else if (count.get(nums[i]) == degree) {    // update the min. subarray length
                res = Math.min(res, i - first.get(nums[i]) + 1);
            }
        }
        return res;
    }
}