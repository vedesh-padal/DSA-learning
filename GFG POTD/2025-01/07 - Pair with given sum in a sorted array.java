// EASY
// arrays, two-pointer
/*
    You are given an integer target and an array arr[]. You have to find number
    of pairs in arr[] which sums up to target. It is given that the elements of the arr[] are in sorted order.

    Note: pairs should have elements of distinct indexes.

    Examples :
    Input: arr[] = [-1, 1, 5, 5, 7], target = 6
    Output: 3
    Explanation: There are 2 pairs which sum up to 6 : {1, 5}, {1, 5} and {-1, 7}.

    Input: arr[] = [1, 1, 1, 1], target = 2
    Output: 6
    Explanation: There are 6 pairs which sum up to 2 : {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1} and {1, 1}.

    Input: arr[] = [-1, 10, 10, 12, 15], target = 125
    Output: 0
    Explanation: There is no such pair which sums up to 4.

    Constraints:
    -10^5 <= target <=10^5
    2 <= arr.size() <= 10^5
    -10^5 <= arr[i] <= 10^5
*/

class Solution {
    int countPairs(int arr[], int target) {
        int l = 0, r = arr.length - 1;
        int cnt = 0;
        
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum < target) {
                l++;
            }
            else if (sum > target) {
                r--;
            }
            else {
                int cnt1 = 0, cnt2 = 0;
                int ele1 = arr[l], ele2 = arr[r];
                
                while (l <= r && ele1 == arr[l]) {
                    l++;
                    cnt1++;
                }
                while (l <= r && ele2 == arr[r]) {
                    r--;
                    cnt2++;
                }
                
                if (ele1 == ele2) {
                    cnt += (cnt1 * (cnt1 - 1)) / 2;
                }
                else {
                    cnt += (cnt1 * cnt2);
                }
                
            }
        }
        return cnt;
    }
}