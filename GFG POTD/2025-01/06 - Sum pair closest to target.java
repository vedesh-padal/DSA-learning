// EASY
// arrays, sorting, binary-search

/*
    Given an array arr[] and a number target, find a pair of elements (a, b) in
    arr[], where a<=b whose sum is closest to target.

    Note: Return the pair in sorted order and if there are multiple such pairs
    return the pair with maximum absolute difference. If no such pair exists return an empty array.

    Examples:
    Input: arr[] = [10, 30, 20, 5], target = 25
    Output: [5, 20]
    Explanation: As 5 + 20 = 25 is closest to 25.

    Input: arr[] = [5, 2, 7, 1, 4], target = 10
    Output: [2, 7]
    Explanation: As (4, 7) and (2, 7) both are closest to 10, but absolute
    difference of (2, 7) is 5 and (4, 7) is 3. Hence, [2, 7] has maximum absolute difference and closest to target.

    Input: arr[] = [10], target = 10
    Output: []
    Explanation: As the input array has only 1 element, return an empty array.

    Constraints:
    1 <= arr.size() <= 2*10^5
    0 <= target<= 2*10^5
    0 <= arr[i] <= 10^5
*/
import java.util.*;

class Solution {
    public List<Integer> sumClosest(int[] arr, int target) {
        // code here
        List<Integer> res = new ArrayList<>();
        int a = -1, b = -1;
        int minDiff = Integer.MAX_VALUE;
        
        Arrays.sort(arr);
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int sum = arr[l] + arr[r];
            int diff = Math.abs(target - sum);
            if (diff < minDiff) {
                a = arr[l];
                b =  arr[r];
                minDiff = diff;
            }
            if (sum < target) {
                l++;   
            }
            else if (sum > target) {
                r--;
            }
            else {  // found equal, directly return, becoz we need the max. difference
                res.add(a);
                res.add(b);
                return res;
            }
        }
        if (a == -1 || b == -1)
            return res;
        
        res.add(a);
        res.add(b);
        return res;
    }
}