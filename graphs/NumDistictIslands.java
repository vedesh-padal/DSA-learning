package graphs;

import java.util.*;

class Pair {
  int first;
  int second;

  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
}

public class NumDistictIslands {
  private void dfs(int ro, int co, boolean[][] vis, char[][] grid) {
    vis[ro][co] = true;
    int n = grid.length, m = grid[0].length;
    Queue<Pair> q = new LinkedList<>();
    q.add(new Pair(ro, co));

    while (!q.isEmpty()) {
      int row = q.peek().first;
      int col = q.peek().second;
      q.remove();

      for (int delRow = -1; delRow <= 1; delRow++) {
        for (int delCol = -1; delCol <= 1; delCol++) {
          int nrow = row + delRow;
          int ncol = col + delCol;

          if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
              vis[nrow][ncol] == false && grid[nrow][ncol] == '1') {
            vis[nrow][ncol] = true;
            q.add(new Pair(nrow, ncol));
          }
        }
      }
    }

  }

  // Function to find the number of islands.
  public int numIslands(char[][] grid) {
    // Code here
    int n = grid.length, m = grid[0].length;
    boolean[][] vis = new boolean[n][m];

    int cnt = 0;
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < m; col++) {
        if (vis[row][col] == false && grid[row][col] == '1') { // if the current position is not visited before and if it is a land
          cnt++;
          dfs(row, col, vis, grid);
        }
      }
    }
    return cnt;
  }
}
