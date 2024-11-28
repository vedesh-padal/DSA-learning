// EASY

// Given an array arr of integers, find all the elements that occur more than
// once in the array. Return the result in ascending order. If no element
// repeats, return an empty array.

// Examples:
// Input: arr[] = [2, 3, 1, 2, 3]
// Output: [2, 3]
// Explanation: 2 and 3 occur more than once in the given array.

// Input: arr[] = [0, 3, 1, 2]
// Output: []
// Explanation: There is no repeating element in the array, so the output is empty.

// Input: arr[] = [2]
// Output: []
// Explanation: There is single element in the array. Therefore output is empty.

// Constraints:
// 1 <= arr.size() <= 106
// 0 <= arr[i] <= 106

import java.util.*;

class Solution {

    // public List<Integer> findDuplicates(int[] arr) {

    // List<Integer> res = new ArrayList<>();

    // for (int i = 0; i < arr.length; i++) {
    // int ind = Math.abs(arr[i]);
    // if (arr[ind] <= 0) {
    // res.add(Math.abs(arr[i]));
    // }
    // arr[ind] = -arr[ind];
    // }
    // Collections.sort(res);
    // return res;
    // }

    public List<Integer> findDuplicates(int[] arr) {
        List<Integer> res = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int ind = arr[i] % n;
            arr[ind] += n;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] / n >= 2) {
                res.add(i);
            }
        }
        return res;
    }

    public List<Integer> findDuplicates_brute(int[] arr) {
        // code here
        Arrays.sort(arr);
        int n = arr.length;
        int[] cnt = new int[n];

        for (int i = 0; i < n; i++) {
            cnt[arr[i]]++;
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (cnt[i] > 1) {
                res.add(i);
            }
        }
        return res;
    }
}
