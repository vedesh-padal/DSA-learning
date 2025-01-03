/*
    Given an integer array arr, return the length of a maximum size turbulent subarray of arr.

    A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

    More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:

    For i <= k < j:
    arr[k] > arr[k + 1] when k is odd, and
    arr[k] < arr[k + 1] when k is even.
    Or, for i <= k < j:
    arr[k] > arr[k + 1] when k is even, and
    arr[k] < arr[k + 1] when k is odd.
    

    Example 1:
    Input: arr = [9,4,2,10,7,8,8,1,9]
    Output: 5
    Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]

    Example 2:
    Input: arr = [4,8,12,16]
    Output: 2

    Example 3:
    Input: arr = [100]
    Output: 1

    Constraints:
    1 <= arr.length <= 4 * 10^4
    0 <= arr[i] <= 10^9
*/

class Solution {
    // after 1hr of thinking --- crying
    // LEARNING -> think from basics first
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int cnt = 1;
        boolean type = false;   // increasing
        int maxLen = Integer.MIN_VALUE;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                if (type)
                    cnt++;
                else 
                    cnt = 2;    // i was takign cnt = 1 before, silly mistake
                type = false;
            }
            else if (arr[i] < arr[i + 1]) {
                if (!type) 
                    cnt++;
                else
                    cnt = 2;
                type = true;
            }
            else {  // when curr and next element are equal
                cnt = 1;
                type = false;   // actually shouldn't matter
            }
            maxLen = Math.max(maxLen, cnt);
        }
        return (maxLen == Integer.MIN_VALUE) ? 1 : maxLen;
    }
}