// EASY
// arrays, matrix

/*
    Given a row-wise sorted 2D matrix mat[][] of size n x m and an integer x, find whether element x is present in the matrix.
    Note: In a row-wise sorted matrix, each row is sorted in itself, i.e. for any i, j within bounds, mat[i][j] <= mat[i][j+1].

    Examples :
    Input: mat[][] = [[3, 4, 9],[2, 5, 6],[9, 25, 27]], x = 9
    Output: true
    Explanation: 9 is present in the matrix, so the output is true.

    Input: mat[][] = [[19, 22, 27, 38, 55, 67]], x = 56
    Output: false
    Explanation: 56 is not present in the matrix, so the output is false.

    Input: mat[][] = [[1, 2, 9],[65, 69, 75]], x = 91
    Output: false
    Explanation: 91 is not present in the matrix.

    Constraints:
    1 <= n, m <= 1000
    1 <= mat[i][j] <= 10^5
    1 <= x <= 10^5
*/

class Solution {
    // Function to search a given number in row-column sorted matrix.
    public boolean searchRowMatrix(int[][] mat, int x) {
        // code here
        for (int[] row: mat) {
            if (row[0] <= x && x <= row[row.length - 1]) {
                int l = 0, h = row.length - 1;
                while (l <= h) {
                    int mid = l + (h - l) / 2;
                    if (row[mid] == x)
                        return true;
                    else if (row[mid] < x)
                        l = mid + 1;
                    else
                        h = mid - 1;
                }
            }
        }
        return false;
    }
}
