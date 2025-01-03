// MEDIUM
// arrays, binary-search
/*
    You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer maxOperations.

    You can perform the following operation at most maxOperations times:

    Take any bag of balls and divide it into two new bags with a positive number of balls.
    For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
    Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.

    Return the minimum possible penalty after performing the operations.

    Example 1:
    Input: nums = [9], maxOperations = 2
    Output: 3
    Explanation: 
    - Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
    - Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
    The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.

    Example 2:
    Input: nums = [2,4,8,2], maxOperations = 4
    Output: 2
    Explanation:
    - Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
    - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
    - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
    - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
    The bag with the most number of balls has 2 balls, so your penalty is 2, and you should return 2.
    
    Constraints:
    1 <= nums.length <= 10^5
    1 <= maxOperations, nums[i] <= 10^9
*/

class Solution {

    private boolean isPossible(int[] nums, int mid, int maxOps) {
        int numOps = 0;

        for (int num: nums) {
            int ops = num / mid;

            // if the num is exactly divisible, then ops will have one extra operation, so we subtract it
            if (num % mid == 0) {
                ops -= 1;
            }
            numOps += ops;
        }
        return (numOps <= maxOps);
    }


    // Based on the concept:
    // Binary Search on Answer
    // like Koko eating bananas
    public int minimumSize(int[] nums, int maxOperations) {
        int l = 1;  // best case of dividing each num in nums with
        int r = nums[0];
        for (int num: nums) {
            r = Math.max(num, r);
        }

        int result = r;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (isPossible(nums, mid, maxOperations)) {
                // we will be greedy and check if further smaller number division is possible
                result = mid;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return result;
    }
}