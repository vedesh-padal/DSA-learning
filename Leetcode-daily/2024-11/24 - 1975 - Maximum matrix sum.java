// MEDIUM
// arrays, greedy, matrix

// You are given an n x n integer matrix. You can do the following operation any
// number of times:

// Choose any two adjacent elements of matrix and multiply each of them by -1.
// Two elements are considered adjacent if and only if they share a border.

// Your goal is to maximize the summation of the matrix's elements. Return the
// maximum sum of the matrix's elements using the operation mentioned above.

// Example 1:
// Input: matrix = [[1,-1],[-1,1]]
// Output: 4
// Explanation: We can follow the following steps to reach sum equals 4:
// - Multiply the 2 elements in the first row by -1.
// - Multiply the 2 elements in the first column by -1.

// Example 2:
// Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
// Output: 16
// Explanation: We can follow the following step to reach sum equals 16:
// - Multiply the 2 last elements in the second row by -1.

// Constraints:
// n == matrix.length == matrix[i].length
// 2 <= n <= 250
// -105 <= matrix[i][j] <= 105

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        // observation after looking through few examples:
        // if odd number of -ve elements in matrix, then in any way, we cannot remove
        // one -ve element, for our our matrix sum to be matrix, we have to make sure
        // that the one -ve element remaining is smallest abs number

        // if even no. of -ve elements, we can always propagate signs by multiplying
        // with -1
        // to two -ve elements as given in question

        int smallestAbsNum = Integer.MAX_VALUE;
        long totalSum = 0; // we will be taking all abs values, adding them
        // atlast, based on the count of -ve numbers, we subtract the smallest abs
        // number in matrix
        int cntNeg = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < 0)
                    cntNeg++;

                smallestAbsNum = Math.min(smallestAbsNum, Math.abs(matrix[i][j]));
                totalSum += Math.abs(matrix[i][j]);
            }
        }
        System.out.println(totalSum + " " + smallestAbsNum);
        return ((cntNeg & 1) == 1) ? (totalSum - 2 * smallestAbsNum) : totalSum;
    }

}