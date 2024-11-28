// MEDIUM
// arrays, kadane-type

// Given a circular integer array nums of length n, return the maximum possible
// sum of a non-empty subarray of nums.

// A circular array means the end of the array connects to the beginning of the
// array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the
// previous element of nums[i] is nums[(i - 1 + n) % n].

// A subarray may only include each element of the fixed buffer nums at most
// once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does
// not exist i <= k1, k2 <= j with k1 % n == k2 % n.

// Example 1:
// Input: nums = [1,-2,3,-2]
// Output: 3
// Explanation: Subarray [3] has maximum sum 3.

// Example 2:
// Input: nums = [5,-3,5]
// Output: 10
// Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

// Example 3:
// Input: nums = [-3,-2,-3]
// Output: -2
// Explanation: Subarray [-2] has maximum sum -2.

// Constraints:
// n == nums.length
// 1 <= n <= 3 * 104
// -3 * 104 <= nums[i] <= 3 * 104

class Solution {

    // CRAZY OPTIMAL SMALLER VERSION
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int currMaxSum = 0;
        int currMinSum = 0;

        for (int num : nums) {
            totalSum += num;
            currMaxSum = Math.max(currMaxSum + num, num);
            currMinSum = Math.min(currMinSum + num, num);
            minSum = Math.min(minSum, currMinSum);
            maxSum = Math.max(maxSum, currMaxSum);
        }

        return (maxSum < 0) ? maxSum : Math.max(maxSum, (totalSum - minSum));
    }

    // ==========================================
    // A Version of Kardane's algorithm
    // INTUITIVE - understandable
    public int maxSubarraySumCircular_INTUITIVE(int arr[]) {

        int totalSum = 0;

        for (int num : arr)
            totalSum += num;

        // CASE 1:
        // max. subarray sum is present without having to rotate
        // (or without having to find the cirular subarrray sum)
        int maxSum = kardaneMax(arr);

        // start of CASE 2:
        int minSum = kardaneMin(arr);
        // if you dont want to write seperate functions
        // you can invert the signs of elements in arr, and find max, and store in
        // minSum

        // CASE 2:
        // max. subarray sum is present in the circular array
        int circularMaxSubArrSum = totalSum - minSum;

        // this condition is for the edge case when arr = [-1, -1, -1]
        // totalSum = -3, minSum = -3, maxSum = -1;
        // circularMaxSubArrSum = -3 - (-3) = 0, but 0 is not present in arr[]
        // if we were to write Math.max(circularMaxSubArrSum, maxSum)
        // directly without the below if condition

        if (maxSum > 0) {
            return Math.max(circularMaxSubArrSum, maxSum);
        }

        return maxSum;
    }

    private int kardaneMax(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + arr[i], arr[i]);

            maxSoFar = Math.max(maxEndingHere, maxSoFar);
        }
        return maxSoFar;
    }

    private int kardaneMin(int[] arr) {
        // the above kardane max. is a low code type of writing kadane's algo
        // below is more understandable way:
        int mini = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            mini = Math.min(sum, mini);

            if (sum > 0) {
                sum = 0;
            }
        }
        return mini;
    }
}