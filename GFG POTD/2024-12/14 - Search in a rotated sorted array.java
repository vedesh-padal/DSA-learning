// Given a sorted and rotated array arr[] of distinct elements, the task is to 
// find the index of a target key. Return -1 if the key is not found.

// Examples :
// Input: arr[] = [5, 6, 7, 8, 9, 10, 1, 2, 3], key = 3
// Output: 8
// Explanation: 3 is found at index 8.

// Input: arr[] = [3, 5, 1, 2], key = 6
// Output: -1
// Explanation: There is no element that has value 6.

// Input: arr[] = [33, 42, 72, 99], key = 42
// Output: 1
// Explanation: 42 is found at index 1.

// Constraints:
// 1 ≤ arr.size() ≤ 106
// 0 ≤ arr[i] ≤ 106
// 1 ≤ key ≤ 106

class Solution {
    int search(int[] arr, int key) {
        // Complete this function
        
        // using Binary search twice: - OPTIMAL 1
        // find the pivot index => min. val index
        // to find the index where key is present
        // check if it is zero: binary search from 0 to n-1
        // if <= key: bs from 0 to pivot - 1
        // else bs from pivot + 1 to n-1
        
        
        // using Binary search (using only once)
        // OPTIMAL - 2  
        int l = 0, h = arr.length - 1;
        
        while (l <= h) {
            int mid = l + (h - l) / 2;
            
            if (arr[mid] == key)
                return mid;
                
            // left part is sorted
            if (arr[l] <= arr[mid]) {
                if (arr[l] <= key && key < arr[mid]) {
                    h = mid - 1;
                }
                else {
                    l = mid + 1;
                }
            }
            else {
                if (key > arr[mid] && key <= arr[h]) {
                    l = mid + 1;
                }
                else {
                    h = mid - 1;
                }
            }
        }
        
        return -1;
    }
}