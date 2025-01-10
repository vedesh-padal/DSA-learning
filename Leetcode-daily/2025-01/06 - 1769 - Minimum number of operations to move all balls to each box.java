// MEDIUM
// arrays, prefix-sum, strings
/*
    You have n boxes. You are given a binary string boxes of length n, where
    boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.

    In one operation, you can move one ball from a box to an adjacent box. Box i
    is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may
    be more than one ball in some boxes.

    Return an array answer of size n, where answer[i] is the minimum number of
    operations needed to move all the balls to the ith box.

    Each answer[i] is calculated considering the initial state of the boxes.

    Example 1:
    Input: boxes = "110"
    Output: [1,1,3]
    Explanation: The answer for each box is as follows:
    1) First box: you will have to move one ball from the second box to the first box in one operation.
    2) Second box: you will have to move one ball from the first box to the second box in one operation.
    3) Third box: you will have to move one ball from the first box to the third
    box in two operations, and move one ball from the second box to the third box in one operation.

    Example 2:
    Input: boxes = "001011"
    Output: [11,8,5,4,3,4]

    Constraints:
    n == boxes.length
    1 <= n <= 2000
    boxes[i] is either '0' or '1'.
*/

class Solution {

    // OPTIMAL - VERY GOOD
    public int[] minOperations(String boxes) {
        // INTUITION:
        // we are keeping track of cummulative sum (of the non-empty boxes)
        // from the left and right, and also the for index i, the sum of the values of the cummulative values
        // i.e., for left to right: sum of cumm. sum / prefix sum values from 0 to (i-1)
        // and from right to left: sum of cumm. sum / suffix sum values from (i+1) to (n-1)

        int n = boxes.length();
        int[] res = new int[n];

        // left to right
        int cummSum = 0;
        int cummValSum = 0;
        for (int i = 0; i < n; i++) {
            res[i] = cummValSum;
            cummSum += (boxes.charAt(i) == '0') ? 0 : 1;
            cummValSum += cummSum;
        }

        // right to left
        cummSum = 0;
        cummValSum = 0;
        for (int i = n-1; i >= 0; i--) {
            res[i] += cummValSum;
            cummSum += (boxes.charAt(i) == '0') ? 0 : 1;
            cummValSum += cummSum;
        }
        return res;
    }


    // SPACE OPTIMIZED BRUTE FORCE --- still slow MEH
    // O(N^2) 
    public int[] minOperations_spaceOptBRUTE(String boxes) {
        // not storing indices of the non-empty boxes
        int n = boxes.length();
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            // search for non-empty boxes and calculate distance or operations
            for (int j = 0; j < n; j++) {
                if (j != i && boxes.charAt(j) == '1') {
                    res[i] += Math.abs(i - j);
                }
            }
        }
        return res;
    }

    // ===================================================
    // MY FIRST TRY -> BRUTE FORCE - with extra space
    // passed all testcases becoz low constraints
    public int[] minOperations_firstTry(String boxes) {
        // indices of the boxes where there is one ball
        // List<Integer> al = new ArrayList<>();
        int n = boxes.length();
        int o = 0;
        for (int i = 0; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                // al.add(i);
                o++;
            }
        }
        int[] ones = new int[o];
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                ones[k++] = i;
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            // for (int it: al) {
            for (int it: ones) {
                if (it != i) {
                    cnt += Math.abs(i - it);
                }
            }
            res[i] = cnt;
        }
        return res;
    }
}