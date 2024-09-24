/*
 * Given an m x n grid of characters board and a string word, return true 
 * if word exists in the grid.

  The word can be constructed from letters of sequentially adjacent cells, 
  where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 */

package graphs;

class Solution {

  int[] drow = { -1, 0, 1, 0 };
  int[] dcol = { 0, 1, 0, -1 };
  int nc, nr;

  private boolean isValidPos(int r, int c) {
    return r >= 0 && r < nr && c >= 0 && c < nc;
  }

  private boolean dfs(char[][] board, int r, int c, String word, int pos, boolean[][] vis) {
    if (pos == word.length())
      return true;

    vis[r][c] = true;

    // we have to check in all 4 directions
    for (int i = 0; i < 4; i++) {
      int nrow = r + drow[i];
      int ncol = c + dcol[i];

      if (isValidPos(nrow, ncol) && vis[nrow][ncol] == false) {
        if (board[nrow][ncol] == word.charAt(pos)) {
          if (dfs(board, nrow, ncol, word, pos + 1, vis))
            return true;
        }
      }
    }
    // backtracking, unmarking this cell
    vis[r][c] = false;
    return false;
  }

  public boolean exist(char[][] board, String word) {
    nr = board.length;
    nc = board[0].length;
    boolean[][] vis = new boolean[nr][nc];

    // start DFS from every cell
    for (int i = 0; i < nr; i++) {
      for (int j = 0; j < nc; j++) {
        if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 1, vis))
          return true;
      }
    }
    return false;
  }
}