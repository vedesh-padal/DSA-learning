// You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:

// A cell containing a thief if grid[r][c] = 1
// An empty cell if grid[r][c] = 0
// You are initially positioned at cell (0, 0). In one move, you can move to any 
// adjacent cell in the grid, including cells containing thieves.

// The safeness factor of a path on the grid is defined as the minimum manhattan
//  distance from any cell in the path to any thief in the grid.

// Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).

// An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), 
// (r + 1, c) and (r - 1, c) if it exists.

// The Manhattan distance between two cells (a, b) and (x, y) is equal to 
// |a - x| + |b - y|, where |val| denotes the absolute value of val.

// Very Good Queestion
// Actually should be marked as hard, multiple concepts involved
// BFS, Multi-source BFS, Array, Matrix, Binary Search

// INTUITION:
// 1. collect all the thieves in a Queue, and do multi-source BFS
//      we are doing multi source BFS, becoz, if we go on doing BFS from each
//      cell for finding safeness factor (based on min. manhattan distance from
//      any cell in the path to any thief in the grid) at each cell from from nearest theives,
//      it would result it too much time complexity 

// 2. now, we will have safeness factor of each cell due to multi-source bfs
// 3. Now, inorder to find the Max. safeness factor, instead of starting a BFS
//      from each cell and doing this for all cell to find the max. safeness factor
//      instead we can apply Binary Search
//          we are applying binary search starting with l = 0, h = max, BECOZ
//          we want to find the max. safeness factor, we will first pick the mid-value
//          from the entire range, and check if this as the target, are we able to find
//          a path where when doing bfs starting from (0,0) we were able to find
//          safeness factor along the path >= what we choose from the mid-val of binary search
//          We are mentioning >= becoz, we want max. safeness factor among all paths starting from (0, 0)
//      If the mid chosen and passed as target for BFS starting from (0, 0), didnt find any such path
//      that reached (n-1, n-1), we pick the `h = mid - 1`, and repeat, and if found, we will
//      track the max. Safeness factor, and return this after the binary search

import java.util.*;

class Pair {
  int row;
  int col;

  public Pair(int row, int col) {
    this.row = row;
    this.col = col;
  }
}

class Solution {
  private int n;
  // up, right, down, left DIRECTIONS
  private int[][] dirs = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

  private boolean check(int[][] distNearestThief, int sf) {
    Queue<Pair> q = new LinkedList<>();
    boolean[][] visited = new boolean[n][n];

    q.add(new Pair(0, 0));
    visited[0][0] = true;

    if (distNearestThief[0][0] < sf) {
      return false;
    }

    while (!q.isEmpty()) {
      int r = q.peek().row;
      int c = q.peek().col;
      q.poll();

      if (r == n - 1 && c == n - 1)
        return true;

      for (int i = 0; i < 4; i++) {
        int nrow = r + dirs[i][0];
        int ncol = c + dirs[i][1];

        if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < n && visited[nrow][ncol] == false) {
          // reject this cell, becoz it has sf less than
          // our assumed threshold of sf
          if (distNearestThief[nrow][ncol] < sf) {
            continue;
          } else {
            q.add(new Pair(nrow, ncol));
            visited[nrow][ncol] = true;
          }
        }

      }
    }
    return false;
  }

  public int maximumSafenessFactor(List<List<Integer>> grid) {
    n = grid.size();

    // multi-source bfs to find the safeness factor of each cell
    int[][] distNearestThief = new int[n][n];
    // for (int[] row: distNearestThief)
    // Arrays.fill(row, -1);

    boolean[][] visited = new boolean[n][n];

    Queue<Pair> msQ = new LinkedList<>();
    // first we add all the thieves into the msQ
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (grid.get(i).get(j) == 1) {
          msQ.add(new Pair(i, j));
          visited[i][j] = true;
        }
      }
    }

    // now we apply Multi-source BFS
    int level = 0;
    while (!msQ.isEmpty()) {
      int size = msQ.size();

      for (int k = 0; k < size; k++) {
        int currI = msQ.peek().row;
        int currJ = msQ.peek().col;
        msQ.poll();

        // here it is level wise like traversal
        // so, we need not calculate manhattan distance
        // becoz, we are not calculating distance form empty cell to thief
        // BUT, rather, we are starting from all the thieves at a time
        // and moving one step at a time, and thus calculating the safeness factor
        // at a time with much reduced time complexity, as we are moving in all 4
        // directions
        // and one step only (level wise)
        distNearestThief[currI][currJ] = level;

        // move all 4 directions
        for (int i = 0; i < 4; i++) {
          int nrow = currI + dirs[i][0];
          int ncol = currJ + dirs[i][1];

          if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < n && visited[nrow][ncol] == false) {
            msQ.add(new Pair(nrow, ncol));
            visited[nrow][ncol] = true;
          }
        }
      }
      level++;
    }

    // Now, apply Binary Search
    int l = 0, r = n * 2;
    int res = 0;
    while (l <= r) {
      int mid_sf = l + (r - l) / 2;
      // found, but i want to be greedy to find the max. sf
      if (check(distNearestThief, mid_sf)) {
        res = mid_sf;
        l = mid_sf + 1;
      } else {
        r = mid_sf - 1;
      }
    }
    return res;
  }
}