// You are given an m x n binary matrix grid.

// A move consists of choosing any row or column and toggling each value 
// in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).

// Every row of the matrix is interpreted as a binary number, and the 
// score of the matrix is the sum of these numbers.

// Return the highest possible score after making any number of moves (including zero moves).

class Solution {
    // with modifying the input
    // INTUITION:
    // 1. flip the row if the first element in that row is 0
    // 2. flip the column if the numZeros > numOnes in that column
    public int matrixScore_withInputModification(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // all of this in order to make the possible score highest
        // such that MSB are set in each row

        // row modification:
        // if first element in the row is 0, flip the row
        // set first column in each row
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }

        // column modification:
        // if no. of zeros is greater than no. of ones, then flip the column
        for (int j = 1; j < n; j++) {
            int numZeros = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 0)
                    numZeros++;
            }

            int numOnes = m - numZeros;
            // then only modify the column
            if (numZeros > numOnes) {
                for (int i = 0; i < m; i++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }

        int score = 0;

        // now, we count the row-wise bit number count
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    score += (1 << (n - j - 1));
            }
        }

        return score;
    }

    // ==================================================
    // without input modification
    public int matrixScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // MSB => 2^(n-1)
        // from observation, we will note that, we always want to
        // set the first column with 1s (that is MSB of all rows to be 1)
        int score = (1 * m << (n - 1));

        for (int j = 1; j < n; j++) {
            int sameBitsCnt = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == grid[i][0]) {
                    sameBitsCnt++;
                }
            }

            int numOnes = sameBitsCnt;
            int numZeros = m - numOnes;

            int ones = Math.max(numOnes, numZeros); // to set

            score += ((1 * ones) << (n - j - 1));
        }

        return score;
    }

}