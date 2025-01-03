// A sorted array arr[] (may contain duplicates) is rotated at some unknown point, 
// the task is to find the minimum element in it. 

// Examples:

// Input: arr[] = [5, 6, 1, 2, 3, 4]
// Output: 1
// Explanation: 1 is the minimum element in the array.

// Input: arr[] = [3, 2, 2, 2]
// Output: 2
// Explanation: Here 2 is the minimum element.

// Input: arr[] = [4, 4, 4]
// Output: 4
// Explanation: Here 4 is the only minimum element.

// Constraints:
// 1 ≤ arr.size() ≤ 106
// 1 ≤ arr[i] ≤ 109

class Solution {
    public int findMin(int[] arr) {
        int l = 0, h = arr.length - 1;
        
        while (l <= h) {
            int mid = l + (h - l) / 2;
            
            // if already sorted
            if (arr[l] <= arr[h]) {
                return arr[l];
            }
            
            if (arr[mid] > arr[h]) {
                l = mid + 1;
            }
            else {
                h = mid;
            }
        }
        
        return arr[l];
    }
}