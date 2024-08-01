/*
  Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

  A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

  All the visited cells of the path are 0.
  All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
  The length of a clear path is the number of visited cells of this path.

 */


import java.util.LinkedList;
import java.util.Queue;

class Triplet {
  int dist, row, col;

  Triplet(int first, int second, int third) {
    this.dist = first;
    this.row = second;
    this.col = third;
  }
}

class Solution {
  public int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;

    if (grid.length == 1 && grid[0].length == 1 && grid[0][0] == 0)
      return 1;

    if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
      return -1;

    int[][] dist = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++)
        dist[i][j] = Integer.MAX_VALUE;
    }
    dist[0][0] = 1;
    // Priority Queue is not required here for the dijkstra's algo being applied
    // here
    // becoz, at any point, we are just incrementing the distance by 1 to check
    Queue<Triplet> q = new LinkedList<>();
    q.add(new Triplet(1, 0, 0));
    int[] drow = { -1, -1, 0, 1, 1, 1, 0, -1 };
    int[] dcol = { 0, 1, 1, 1, 0, -1, -1, -1 };

    while (!q.isEmpty()) {
      Triplet trp = q.poll();
      int dis = trp.dist;
      int r = trp.row;
      int c = trp.col;

      // check all 8 directions from the current cell
      for (int i = 0; i < 8; i++) {
        int nrow = r + drow[i];
        int ncol = c + dcol[i];

        if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n
            && grid[nrow][ncol] == 0 // 0 is part of the path, not 1
            && dis + 1 < dist[nrow][ncol]) {
          dist[nrow][ncol] = dis + 1;
          if (nrow == n - 1 && ncol == n - 1)
            return 1 + dis;
          q.add(new Triplet(dis + 1, nrow, ncol));
        }
      }
    }
    return -1;
  }
}