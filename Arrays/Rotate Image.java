class Solution {
  
  // approach - 1 : but need another identical size matrix
  // take the first row of the matrix and put it in the last column of the dummy matrix,
  // take the second row of the matrix, and put it in the second last column of the matrix and so on..
  public void rotate1(int[][] matrix) {
    int n = matrix.length;
    int[][] res = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        res[j][n - i - 1] = matrix[i][j];
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = res[i][j];
      }
    }
  }

  // approach - 2 with O(1) space
  // OBSERVATION:
  // first column of the original matrix is the reverse of the first row of the
  // rotated matrix
  public void rotate2(int[][] matrix) {

    int n = matrix.length;

    // Transpose the matrix
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }

    // Reverse the rows in the transposed matrix
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n / 2; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][n - 1 - j];
        matrix[i][n - 1 - j] = temp;
      }
    }
  }
}