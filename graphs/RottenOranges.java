package graphs;
import java.util.*;

class Pair {
  int row, col, tm;
  Pair(int _row, int _col, int _tm)   {
      this.row = _row;
      this.col = _col;
      this.tm = _tm;
  }
}

public class RottenOranges {
  public int orangesRotting(int[][] grid) {
      int n = grid.length;
      int m = grid[0].length;

      Queue<Pair> q = new LinkedList<>();
      int[][] vis = new int[n][m];
      int cntFresh = 0;

      for (int i=0; i<n; i++) {
          for (int j=0; j<m; j++) {
              if (grid[i][j] == 2) {  // check if rotted orange
                  q.add(new Pair(i, j, 0));
                  vis[i][j] = 2;
              }   else {  // if not rotten
                  vis[i][j] = 0;
              }
              if (grid[i][j] == 1)    cntFresh++;
          }
      }

      int[] drow = {-1, 0, 1, 0};
      int[] dcol = {0, 1, 0, -1};
      int cnt = 0;    // to check the count of fresh oranges being converted to rotten oranges
      int tm = 0;

      while (!q.isEmpty())    {
          int r = q.peek().row;
          int c = q.peek().col;
          int t = q.peek().tm;

          // update the time being taken to rot
          tm = Math.max(tm, t);
          q.remove();

          for (int i=0; i<4; i++) {
              // check if co-ordinates are correct and it is a fresh orange
              int nrow = r + drow[i];
              int ncol = c + dcol[i];

              if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                  vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1)  {
                      vis[nrow][ncol] = 2;
                      cnt++;
                      q.add(new Pair(nrow, ncol, t + 1));
                  }
          }
      }

      if (cnt != cntFresh)    return -1;

      return tm;
  }
}