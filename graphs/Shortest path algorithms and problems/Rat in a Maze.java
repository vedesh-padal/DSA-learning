import java.util.ArrayList;

class Solution {

  static String directions = "URDL";

  static int[] drow = { -1, 0, 1, 0 };
  static int[] dcol = { 0, 1, 0, -1 };

  private static boolean isValid(int row, int col, int n, int[][] mat) {
    return (row >= 0 && row < n && col >= 0 && col < n && mat[row][col] == 1);
  }

  private static void findPath(int row, int col, int[][] mat, int n, StringBuilder currPath, ArrayList<String> paths) {
    if (row == n - 1 && col == n - 1) {
      paths.add(currPath.toString());
      return;
    }

    // you have visited this cell, so mark it as visted
    // that means cannot be visited again, so mark it with 0 => blocked -> cannot
    // visit again
    // 1 => can visit
    // 0 => cannot visit
    mat[row][col] = 0;

    for (int i = 0; i < 4; i++) {
      int nrow = row + drow[i];
      int ncol = col + dcol[i];

      if (isValid(nrow, ncol, n, mat)) {

        currPath.append(directions.charAt(i));

        // recursively find path for the next cell
        findPath(nrow, ncol, mat, n, currPath, paths);

        // remove the last direction when backtracking
        currPath.deleteCharAt(currPath.length() - 1);
      }
    }

    // mark the cell as unblocked
    mat[row][col] = 1;

  }

  public ArrayList<String> findPath(int[][] mat) {
    // Your code here
    // to store all possible paths to reach from (0, 0) to (n-1, n-1)
    ArrayList<String> paths = new ArrayList<>();
    // to store the current path
    StringBuilder currPath = new StringBuilder();
    int n = mat.length;

    if (mat[0][0] != 0 && mat[n - 1][n - 1] != 0) {
      findPath(0, 0, mat, n, currPath, paths);
    }
    return paths;
  }
}