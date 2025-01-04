// MEDIUM
// arrays, two-pointers

/*
    Given a sorted array arr[] and a target value, the task is to count triplets
    (i, j, k) of valid indices, such that arr[i] + arr[j] + arr[k] = target and i < j < k.

    Examples:
    Input: arr[] = [-3, -1, -1, 0, 1, 2], target = -2
    Output: 4
    Explanation: Two triplets that add up to -2 are:
    arr[0] + arr[3] + arr[4] = (-3) + 0 + (1) = -2
    arr[0] + arr[1] + arr[5] = (-3) + (-1) + (2) = -2
    arr[0] + arr[2] + arr[5] = (-3) + (-1) + (2) = -2
    arr[1] + arr[2] + arr[3] = (-1) + (-1) + (0) = -2

    Input: arr[] = [-2, 0, 1, 1, 5], target = 1
    Output: 0
    Explanation: There is no triplet whose sum is equal to 1.

    Constraints:
    3 ≤ arr.size() ≤ 10^3
    -105 ≤ arr[i], target ≤ 10^5
*/

class Solution {
    public int countTriplets(int[] arr, int target) {
        // Code Here
        // 3 sum problem with specific sum value
        // Arrays.sort(arr);    // becoz array already sorted
        int n = arr.length;
        int count = 0;
        
        for (int i = 0; i < n-2; i++) {
            // if (i != 0 && arr[i] == arr[i-1]) {
            //     continue;
            // }
            
            int j = i+1;
            int k = n-1;
            
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                if (sum < target) {
                    j++;
                }
                else if (sum > target) {
                    k--;
                }
                else {
                    // System.out.println(i + " " + j + " " + k);
                    int leftCnt = 1;
                    int rightCnt = 1;
                    
                    // count the duplicates on left
                    while (j+1 <= k && arr[j] == arr[j+1]) {
                        j++;
                        leftCnt++;
                        
                    }
                    // count the duplicates on right
                    while (j <= k-1 && arr[k] == arr[k-1]) {
                        k--;
                        rightCnt++;
                    }
                    // if both elements are same, then count no. of pairs:
                    // the no. of ways to choose 2 elements among leftCnt elements
                    if (arr[j] == arr[k]) {
                        count += (leftCnt * (leftCnt - 1)) / 2;
                    }
                    // if both elements are different, then no. of pairs:
                    // product of count of both
                    else {
                        count += (leftCnt * rightCnt);
                    } 
                    // now, that we have counted all the duplicates possibilites
                    // now, we increment j, decrement k to check if non-duplicates
                    // are forming the triplet with their sum equal to target
                    j++;
                    k--;
                }
            }
        }
        return count;
    }
}