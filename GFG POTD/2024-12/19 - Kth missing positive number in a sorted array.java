// EASY-MEDIUM
// arrays, binary-search

/*
    Given a sorted array of distinct positive integers arr[], we need to find the kth positive number that is missing from arr[].  

    Examples :
    Input: arr[] = [2, 3, 4, 7, 11], k = 5
    Output: 9
    Explanation: Missing are 1, 5, 6, 8, 9, 10… and 5th missing number is 9.

    Input: arr[] = [1, 2, 3], k = 2
    Output: 5
    Explanation: Missing are 4, 5, 6… and 2nd missing number is 5.
    
    Input: arr[] = [3, 5, 9, 10, 11, 12], k = 2
    Output: 2
    Explanation: Missing are 1, 2, 4, 6… and 2nd missing number is 2.

    Constraints:
    1 <= arr.size() <= 10^5
    1 <= k <= 10^5
    1 <= arr[i]<= 10^6
*/

class Solution {

    public int kthMissing_BinarySearch(int[] arr, int k) {
        int l = 0, h = arr.length - 1;
        int res = arr.length + k;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (arr[mid] > (mid + k)) {
                res = mid + k;
                h = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return res;
    }

    // ============================================================
    // simpler approach
    public int kthMissing_simpler_On(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] > (i + k)) {
                return (i + k);
            }
        }
        return (n + k);
    }

    // ============================================================
    // my try - linear complexity
    public int kthMissing_MY_TRY_LinearTC(int[] arr, int k) {
        int num = 1;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            while (num != arr[i]) {
                k--;
                if (k == 0)
                    return num;
            }
            num++;
        }

        if (k != 0) {
            num = arr[n-1];
            while (k != 0) {
                k--;
                num++;
            }
        }
        return num;
    }
}