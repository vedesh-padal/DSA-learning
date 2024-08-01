/*
  You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, 
  where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), 
  and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). 
  You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

  A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

  Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

  Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
  Output: 2
  Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
  This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

  https://leetcode.com/problems/path-with-minimum-effort/description/

 */


import java.util.PriorityQueue;

class Triplet {
  int diff, row, col;

  Triplet(int first, int second, int third) {
    this.diff = first;
    this.row = second;
    this.col = third;
  }
}

class Solution {
  public int minimumEffortPath(int[][] heights) {
    int n = heights.length;
    int m = heights[0].length;

    PriorityQueue<Triplet> pq = new PriorityQueue<>((x, y) -> (x.diff - y.diff)); // min. heap
    int[][] diff = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        diff[i][j] = (int) (1e9);
      }
    }

    diff[0][0] = 0;
    pq.add(new Triplet(0, 0, 0));
    int[] drow = { -1, 0, 1, 0 };
    int[] dcol = { 0, 1, 0, -1 };

    while (!pq.isEmpty()) {
      Triplet trp = pq.poll();
      int df = trp.diff;
      int r = trp.row;
      int c = trp.col;

      if (r == n - 1 && c == m - 1)
        return df;

      for (int i = 0; i < 4; i++) {
        int nrow = r + drow[i];
        int ncol = c + dcol[i];

        if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m) {
          int newEffort = Math.max(Math.abs(heights[r][c] - heights[nrow][ncol]), df);
          if (newEffort < diff[nrow][ncol]) {
            diff[nrow][ncol] = newEffort;
            pq.add(new Triplet(newEffort, nrow, ncol));
          }
        }
      }
    }
    return 0;
  }
}