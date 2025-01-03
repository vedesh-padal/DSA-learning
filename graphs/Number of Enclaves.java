// Number of Enclaves
// You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

// A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

// Return the number of land cells (enclaves) in grid for which we cannot walk off the boundary of the grid in any number of moves.

package graphs;

import java.util.*;

class Pair {
  int first, second;

  Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
}

class Solution {
  public int numEnclaves(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    boolean[][] vis = new boolean[n][m];
    Queue<Pair> q = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
          if (grid[i][j] == 1) {
            q.add(new Pair(i, j));
            vis[i][j] = true;
          }
        }
      }
    }

    int[] drow = { -1, 0, 1, 0 };
    int[] dcol = { 0, 1, 0, -1 };
    while (!q.isEmpty()) {
      int row = q.peek().first;
      int col = q.peek().second;
      q.remove();

      for (int i = 0; i < 4; i++) {
        int nrow = row + drow[i];
        int ncol = col + dcol[i];

        if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
            vis[nrow][ncol] == false && grid[nrow][ncol] == 1) { // land and not viisted
          q.add(new Pair(nrow, ncol));
          vis[nrow][ncol] = true;
        }
      }
    }

    int cnt = 0;
    // now check and count the number of inner lands that are not surrounded by the
    // reachable boundary lands
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1 && vis[i][j] == false)
          cnt++;
      }
    }
    return cnt;
  }
}