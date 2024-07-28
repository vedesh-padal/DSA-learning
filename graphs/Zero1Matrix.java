// Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

// The distance between two adjacent cells is 1.

package graphs;
import java.util.*;

class Pair {
  int first, second, steps;

  Pair(int first, int second, int steps) {
    this.first = first;
    this.second = second;
    this.steps = steps;
  }

}

  public class Zero1Matrix{

  public int[][] updateMatrix(int[][] mat) {
    int n = mat.length;
    int m = mat[0].length;
    boolean[][] vis = new boolean[n][m];
    int[][] dist = new int[n][m];

    Queue<Pair> q = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (mat[i][j] == 0) {
          q.add(new Pair(i, j, 0));
          vis[i][j] = true;
        } else {
          vis[i][j] = false;
        }
      }
    }

    int[] drow = { -1, 0, 1, 0 };
    int[] dcol = { 0, 1, 0, -1 };
    while (!q.isEmpty()) {
      int r = q.peek().first;
      int c = q.peek().second;
      int st = q.peek().steps;
      q.remove();
      dist[r][c] = st;

      for (int i = 0; i < 4; i++) {
        int nrow = r + drow[i];
        int ncol = c + dcol[i];

        if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == false) {
          vis[nrow][ncol] = true;
          q.add(new Pair(nrow, ncol, st + 1));
        }
      }
    }
    return dist;
  }
}