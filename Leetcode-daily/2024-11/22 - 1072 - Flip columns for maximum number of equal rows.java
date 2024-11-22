// MEDIUM
// hash-map, arrays, matrix

// You are given an m x n binary matrix matrix.

// You can choose any number of columns in the matrix and flip every cell in
// that column (i.e., Change the value of the cell from 0 to 1 or vice versa).

// Return the maximum number of rows that have all values equal after some number of flips.

// Example 1:
// Input: matrix = [[0,1],[1,1]]
// Output: 1
// Explanation: After flipping no values, 1 row has all values equal.

// Example 2:
// Input: matrix = [[0,1],[1,0]]
// Output: 2
// Explanation: After flipping values in the first column, both rows have equal values.

// Example 3:
// Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
// Output: 2
// Explanation: After flipping values in the first two columns, the last two
// rows have equal values.

// Constraints:
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 300
// matrix[i][j] is either 0 or 1.

import java.util.*;

class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // storing each row as a pattern, and counting the repeating pattern
        // among all rows, and returning the max. count

        // no. of rows having the same pattern
        Map<String, Integer> hmap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder("");

            for (int j = 0; j < n; j++) {
                if (matrix[i][0] == matrix[i][j]) {
                    sb.append("s"); // same
                } else {
                    sb.append("d"); // different
                }
            }

            String rowAsPattern = sb.toString();
            hmap.put(rowAsPattern, hmap.getOrDefault(rowAsPattern, 0) + 1);
        }

        int maxRows = 0;
        // max. repeating pattern
        for (int freq : hmap.values()) {
            maxRows = Math.max(maxRows, freq);
        }
        return maxRows;
    }
}