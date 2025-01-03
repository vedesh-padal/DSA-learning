// EASY
// searching, arrays, matrix

/*
    Given a 2D integer matrix mat[][] of size n x m, where every row and column
    is sorted in increasing order and a number x, the task is to find whether
    element x is present in the matrix.

    Examples:
    Input: mat[][] = [[3, 30, 38],[20, 52, 54],[35, 60, 69]], x = 62
    Output: false
    Explanation: 62 is not present in the matrix, so output is false.

    Input: mat[][] = [[18, 21, 27],[38, 55, 67]], x = 55
    Output: true
    Explanation: 55 is present in the matrix.

    Input: mat[][] = [[1, 2, 3],[4, 5, 6],[7, 8, 9]], x = 3
    Output: true
    Explanation: 3 is present in the matrix.

    Constraints:
    1 <= n, m <=1000
    1 <= mat[i][j] <= 10^9
    1<= x <= 10^9
*/

class Solution {

    // OPTIMAL:
    // WE CAN REDUCE THE NO. OF BINARY SEARCHES TO 1
    public boolean searchMatrix(int[][] mat, int x) {
        int m = mat.length, n = mat[0].length;
        int l = 0, r = (m*n - 1);
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int row = mid / n;
            int col = mid % n;
            
            if (mat[row][col] == x) {
                return true;
            }
            else if (mat[row][col] < x) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return false;
    }
    
    // =======================================================================
    // 2 days later same POTD, but MEDIUM marked => gives TLE
    // expected TC: O(logN + logM) [ solved myself ]
    // THIS APPROACH IS REQUIRING TWO BINARY SEARCHES
    public boolean searchMatrix_2BinarySearches(int[][] mat, int x) {
        // code here
        int m = mat.length, n = mat[0].length;
        
        // first do a binary search to find the row in which x would fit in
        
        int l = 0, r = m-1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mat[mid][0] <= x && x <= mat[mid][n-1]) {
                int a = 0, b = n-1;
                // search within this row, if our x is present
                while (a <= b) {
                    int mid2 = a + (b - a) / 2;
                    if (mat[mid][mid2] == x)
                        return true;
                    else if (mat[mid][mid2] < x)
                        a = mid2 + 1;
                    else
                        b = mid2 - 1;
                }
            }
            else if (mat[mid][0] > x) { // go up
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        
        return false;
    }
    
    // =======================================================================
    // BETTER: (depends though, based on constraints sometimes)
    // TC: O(n + m)
    public static boolean matSearch(int mat[][], int x) {
        int n = mat.length, m = mat[0].length;
        int i = 0;  // row tracker
        int j = m-1;
        
        while (i < n && j >= 0) {
            if (x > mat[i][j])
                i++;
            else if (x < mat[i][j])
                j--;
            else
                return true;
        }
        return false;
    }
    
    
    // if nxm, TC: O(n*log(m))
    // OKAYISH APPROACH
    public static boolean matSearch_firsTry(int mat[][], int x) {
        // your code here
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