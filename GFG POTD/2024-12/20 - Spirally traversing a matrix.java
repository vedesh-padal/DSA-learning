// EASY - MEDIUM
// arrays, matrix
/*
    You are given a rectangular matrix mat[][] of size n x m, and your task is 
    to return an array while traversing the matrix in spiral form.

    Examples:

    Input: mat[][] = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]]
    Output: [1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]

    Input: mat[][] = [[1, 2, 3, 4, 5, 6], [7, 8, 9, 10, 11, 12], [13, 14, 15, 16, 17, 18]]
    Output: [1, 2, 3, 4, 5, 6, 12, 18, 17, 16, 15, 14, 13, 7, 8, 9, 10, 11]

    Input: mat[][] = [[32, 44, 27, 23], [54, 28, 50, 62]]
    Output: [32, 44, 27, 23, 62, 50, 28, 54]

    Constraints:
    1 <= n, m <= 1000
    0 <= mat[i][j]<= 100
*/
import java.util.ArrayList;

class Solution {
    // Function to return a list of integers denoting spiral traversal of matrix.
    public ArrayList<Integer> spirallyTraverse(int mat[][]) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        int m = mat.length, n = mat[0].length;
        
        int top = 0, left = 0, right = n-1, bottom = m-1;
        
        while (top <= bottom && left <= right) {
            
            for (int i = left; i <= right; i++)
                res.add(mat[top][i]);
                
            top++;
            
            for (int i = top; i <= bottom; i++) 
                res.add(mat[i][right]);
                
            right--;
            
            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    res.add(mat[bottom][i]);
                
                bottom--;
            }
            
            if (left <= right) {
                for (int i = bottom; i >= top; i--) 
                    res.add(mat[i][left]);
                
                left++;
            }
        }
        
        return res;
        
    }
}
