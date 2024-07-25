/*
  You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

  Connect: A cell is connected to adjacent cells horizontally or vertically.
  Region: To form a region connect every 'O' cell.
  Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
  A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.
 */


package graphs;

public class SurroundedRegions {

  private static void dfs(int row, int col, int[] drow, int[] dcol, boolean[][] vis, char[][] board) {
    vis[row][col] = true;
    int n = board.length;
    int m = board[0].length;

    for (int i = 0; i < 4; i++) {
      int nrow = row + drow[i];
      int ncol = col + dcol[i];

      if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
          vis[nrow][ncol] == false && board[nrow][ncol] == 'O') {
        dfs(nrow, ncol, drow, dcol, vis, board);
      }
    }
  }

  public void solve(char[][] board) {
    int n = board.length;
    int m = board[0].length;
    boolean[][] vis = new boolean[n][m];

    int[] drow = { -1, 0, 1, 0 };
    int[] dcol = { 0, 1, 0, -1 };

    // traverse first row and last row
    for (int j = 0; j < m; j++) {
      // first row
      if (board[0][j] == 'O' && vis[0][j] == false) {
        dfs(0, j, drow, dcol, vis, board);
      }

      // last row
      if (board[n - 1][j] == 'O' && vis[n - 1][j] == false) {
        dfs(n - 1, j, drow, dcol, vis, board);
      }
    }

    // traverse first col and last col
    for (int i = 0; i < n; i++) {
      // fist col
      if (board[i][0] == 'O' && vis[i][0] == false) {
        dfs(i, 0, drow, dcol, vis, board);
      }

      // last col
      if (board[i][m - 1] == 'O' && vis[i][m - 1] == false) {
        dfs(i, m - 1, drow, dcol, vis, board);
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == 'O' && vis[i][j] == false)
          board[i][j] = 'X'; // since in the question asked to replace
      }
    }
  }
}