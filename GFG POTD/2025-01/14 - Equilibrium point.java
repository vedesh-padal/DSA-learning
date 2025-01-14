// EASY
// ararys, prefix-sum, suffix-sum
/*
    Given an array of integers arr[], the task is to find the first equilibrium point in the array.

    The equilibrium point in an array is an index (0-based indexing) such that
    the sum of all elements before that index is the same as the sum of elements
    after it. Return -1 if no such point exists.

    Examples:
    Input: arr[] = [1, 2, 0, 3]
    Output: 2 
    Explanation: The sum of left of index 2 is 1 + 2 = 3 and sum on right of index 2 is 0 + 3 = 3.

    Input: arr[] = [1, 1, 1, 1]
    Output: -1
    Explanation: There is no equilibrium index in the array.

    Input: arr[] = [-7, 1, 5, 2, -4, 3, 0]
    Output: 3
    Explanation: The sum of left of index 3 is -7 + 1 + 5 = -1 and sum on right of index 3 is -4 + 3 + 0 = -1.

    Constraints:
    3 <= arr.size() <= 10^6
    0 <= arr[i] <= 10^9
*/

class Solution {
    // optimal
    public static int findEquilibrium(int arr[]) {
        int n = arr.length;
        int pre = arr[0];
        int suff = 0;
        for (int num: arr)
            suff += num;
            
        for (int i = 0; i < n-1; i++) {
            if (pre == suff)
                return i;
            
            suff -= arr[i];
            pre += arr[i+1];
        }
        return -1;
    }
    
    // Function to find equilibrium point in the array.
    public static int findEquilibrium_extraSpace(int arr[]) {
        // code here
        int n = arr.length;
        int[] pre = new int[n];
        int[] suff = new int[n];
        
        pre[0] = arr[0];
        suff[n-1] = arr[n-1];
        
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i-1] + arr[i];
            suff[n-i-1] = suff[n-i] + arr[n-i-1];
        }
        
        for (int i = 0; i < n; i++) {
            if (pre[i] == suff[i])
                return i;
        }
        return -1;
    }
}
