// EASY
// hashmap, arrays, sorting, binary-search, hashset

// Given an array arr of integers, check if there exist two indices i and j such that :

// i != j
// 0 <= i, j < arr.length
// arr[i] == 2 * arr[j]
 

// Example 1:
// Input: arr = [10,2,5,3]
// Output: true
// Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]

// Example 2:
// Input: arr = [3,1,7,11]
// Output: false
// Explanation: There is no i and j that satisfy the conditions.

// Constraints:
// 2 <= arr.length <= 500
// -10^3 <= arr[i] <= 10^3

import java.util.*;

class Solution {

    // simple using hashset
    // don't overcomplicate things, start from basics first
    public boolean checkIfExist(int[] arr) {
        Set<Integer> hset = new HashSet<>();
        for (int num: arr) {
            if (hset.contains(num * 2))
                return true;

            if (((num & 1) == 0) && hset.contains(num / 2)) {
                return true;
            }
            hset.add(num);
        }
        return false;
    }


    // ======================================
    private int binarySearch(int[] arr, int s, int e, int tar) {
        while (s <= e) {
            int mid = s + (e - e) / 2;
            if (arr[mid] == tar) {
                return mid;
            }
            else if (arr[mid] < tar) {
                s = mid + 1;
            }
            else {
                e = mid - 1;
            }
        }
        return -1;
    }

    // over-optimized, not suitable for small constraint problems, hashmap is easy
    public boolean checkIfExist_BinarySearchApproach(int[] arr) {
        int n = arr.length;
        boolean allZeros = true;
        for (int num: arr) {
            if (num != 0) {
                allZeros = false;
                break;
            }
        }
        if (allZeros) {
            return true;
        }
        Arrays.sort(arr);

        for (int i = 0; i < n-1; i++) {
            int doubleIndex = binarySearch(arr, 0, n-1, 2*arr[i]);
            // System.out.println(doubleIndex);
            if (doubleIndex != -1 && doubleIndex != i) {
                return true;
            }
        }
        return false;
    }
    

    // tried set, hashmap, hashmap with priorityQueue, none worked
    // the last one had one testcase failing, then put that 2 vars and then back to 105/111
    // GOOD Question actually
    // can be solved using sorting and binary search actually, later thought this and solving
    public boolean checkIfExist_initTry(int[] arr) {
        Map<Integer, PriorityQueue<Integer>> hmap = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            hmap.computeIfAbsent(arr[i], k -> new PriorityQueue<>()).offer(i);
        }

        @SuppressWarnings("unused")
        int num = -1;
        int doubleAt = -1;
        int numAt = -1;

        // now check for the double number
        for (int i = 0; i < n; i++) {
            if (hmap.containsKey(2*arr[i])) {
                while (!hmap.get(2*arr[i]).isEmpty() && hmap.get(2*arr[i]).peek() != i) {
                    hmap.get(2*arr[i]).poll();
                }
                // System.out.println(hmap.get(2*arr[i]));
                System.out.println("found for: " + arr[i] + " at " + hmap.get(2*arr[i]));

                // if (!hmap.get(2*arr[i]).isEmpty() && hmap.get(2*arr[i]).peek() != i)
                
                num = arr[i];
                numAt = i;
                Integer polledEle = hmap.get(2*arr[i]).poll();
                doubleAt = (polledEle == null) ? -1 : polledEle;
                
            }
        }

        if (doubleAt == numAt)
            return false;
        else 
            return true;


        // for (int i = 0; i < arr.length; i++) {
        //     if (hmap.containsKey(2*arr[i]) && hmap.get(2*arr[i]) != i) {
        //         System.out.println("found for: " + arr[i] + " at " + hmap.get(2*arr[i]));
        //         return true;
        //     }
        //     hmap.put(arr[i], i);
        // }
        // System.out.println(hmap);
        // if (hmap.containsKey(2*arr[0]) && hmap.get(2*arr[0]) != 0) {
        //     System.out.println("found for: " + arr[0] + " at " + hmap.get(2*arr[0]));
        //     return true;
        // }
        // return false;
    }
}