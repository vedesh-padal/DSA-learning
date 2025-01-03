// EASY
// arrays, matrix

/*
    Given a square matrix mat[][] of size n x n. The task is to rotate it by 90 degrees 
    in an anti-clockwise direction without using any extra space. 

    Examples:

    Input: mat[][] = [[1, 2, 3],
                    [4, 5, 6]
                    [7, 8, 9]]
    Output: Rotated Matrix:
    [3, 6, 9]
    [2, 5, 8]
    [1, 4, 7]

    Input: mat[][] = [[1, 2],
                    [3, 4]]
    Output: Rotated Matrix:
    [2, 4]
    [1, 3]

    Constraints:
    1 ≤ n ≤ 102
    0 <= mat[i][j] <= 103
*/

class Solution {
    // Function to rotate matrix anticlockwise by 90 degrees.
    static void rotateby90(int mat[][]) {
        int m = mat.length, n = mat[0].length;
        
        // 1. reverse row wise
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n/2; j++) {
                int t = mat[i][j];
                mat[i][j] = mat[i][n-j-1];
                mat[i][n-j-1] = t;
            }
        }
        
        // 2. transpose matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                int t = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = t;
            }
        }
    }
}