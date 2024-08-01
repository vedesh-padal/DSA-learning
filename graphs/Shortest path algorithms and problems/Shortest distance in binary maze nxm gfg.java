/*
  Given a n * m matrix grid where each element can either be 0 or 1. You need to find the shortest distance between a given source cell to a destination cell. The path can only be created out of a cell if its value is 1. 

  If the path is not possible between source cell and destination cell, then return -1.

  Note : You can move into an adjacent cell if that adjacent cell is filled with element 1. 
  Two cells are adjacent if they share a side. In other words, you can move in one of the four directions, 
  Up, Down, Left and Right. The source and destination cell are based on the zero based indexing. The destination cell should be 1.

  Example 1:

  Input:
  grid[][] = {{1, 1, 1, 1},
              {1, 1, 0, 1},
              {1, 1, 1, 1},
              {1, 1, 0, 0},
              {1, 0, 0, 1}}
  source = {0, 1}
  destination = {2, 2}
  Output:
  3
  Explanation:
  1 1 1 1
  1 1 0 1
  1 1 1 1
  1 1 0 0
  1 0 0 1
  The highlighted part in the matrix denotes the 
  shortest path from source to destination cell.
  Example 2:

  Input:
  grid[][] = {{1, 1, 1, 1, 1},
              {1, 1, 1, 1, 1},
              {1, 1, 1, 1, 0},
              {1, 0, 1, 0, 1}}
  source = {0, 0}
  destination = {3, 4}
  Output:
  -1
  Explanation:
  The path is not possible between source and 
  destination, hence return -1.
  Your Task:

  You don't need to read or print anything. Your task is to complete the function shortestPath() which takes 
  the a 2D integer array grid, source cell and destination cell as an input parameters and returns the 
  shortest distance between source and destination cell.

  Expected Time Complexity: O(n * m)
  Expected Space Complexity: O(n * m)

  Constraints:

  1 ≤ n, m ≤ 500
  grid[i][j] == 0 or grid[i][j] == 1
  The source and destination cells are always inside the given matrix.
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

  int shortestPath(int[][] grid, int[] source, int[] destination) {

    // Your code here
    int n = grid.length;
    int m = grid[0].length;
    if (source[0] == destination[0] && source[1] == destination[1])
      return 0;
    int[][] dist = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++)
        dist[i][j] = (int) (1e9);
    }
    dist[source[0]][source[1]] = 0;
    // Priority Queue is not required here for the dijkstra's algo being applied
    // here
    // becoz, at any point, we are just incrementing the distance by 1 to check
    Queue<Triplet> q = new LinkedList<>();
    q.add(new Triplet(0, source[0], source[1]));
    int[] drow = { -1, 0, 1, 0 };
    int[] dcol = { 0, 1, 0, -1 };

    while (!q.isEmpty()) {
      Triplet trp = q.poll();
      int dis = trp.dist;
      int r = trp.row;
      int c = trp.col;

      // check all 4 directions from the current cell
      for (int i = 0; i < 4; i++) {
        int nrow = r + drow[i];
        int ncol = c + dcol[i];

        if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
            && grid[nrow][ncol] == 1
            && dis + 1 < dist[nrow][ncol]) {
          dist[nrow][ncol] = dis + 1;
          if (nrow == destination[0] && ncol == destination[1])
            return 1 + dis;
          q.add(new Triplet(1 + dis, nrow, ncol));
        }
      }
    }
    return -1;
  }
}
