// 827 - LC HARD

/*
  You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

  Return the size of the largest island in grid after applying this operation.

  An island is a 4-directionally connected group of 1s.

  Example 1:
  Input: grid = [[1,0],[0,1]]
  Output: 3
  Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
  
  Example 2:
  Input: grid = [[1,1],[1,0]]
  Output: 4
  Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

  Example 3:
  Input: grid = [[1,1],[1,1]]
  Output: 4
  Explanation: Can't change any 0 to 1, only one island with area = 4.
  
  Constraints:
  n == grid.length
  n == grid[i].length
  1 <= n <= 500
  grid[i][j] is either 0 or 1.
*/


import java.util.*;

class DisjointSet {
  List<Integer> rank = new ArrayList<>();
  List<Integer> parent = new ArrayList<>();
  List<Integer> size = new ArrayList<>();

  public DisjointSet(int n) {
    for (int i = 0; i <= n; i++) {
      rank.add(0);
      parent.add(i);
      size.add(1);
    }
  }

  public int findUltPar(int node) {
    if (node == parent.get(node))
      return node;

    int ulp = findUltPar(parent.get(node));
    parent.set(node, ulp);
    return parent.get(node);
  }

  public void findUnionByRank(int u, int v) {
    int ulp_u = findUltPar(u);
    int ulp_v = findUltPar(v);

    if (ulp_u == ulp_v)
      return;

    if (rank.get(ulp_u) == rank.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
    } else if (rank.get(ulp_v) < rank.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
    } else {
      parent.set(ulp_v, ulp_u);
      int rankU = rank.get(ulp_u);
      rank.set(ulp_u, rankU + 1);
    }
  }

  public void findUnionBySize(int u, int v) {
    int ulp_u = findUltPar(u);
    int ulp_v = findUltPar(v);

    if (ulp_u == ulp_v)
      return;

    if (size.get(ulp_u) < size.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
      size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
    }
    // for other cases, equiality also same result
    else {
      parent.set(ulp_v, ulp_u);
      size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
    }
  }

}

class Solution {

  private static boolean isValid(int x, int y, int n) {
    return x >= 0 && y >= 0 && x < n && y < n;
  }

  public int largestIsland(int[][] grid) {
    int n = grid.length; // n x n grid
    int[] drow = { -1, 0, 1, 0 };
    int[] dcol = { 0, 1, 0, -1 };

    DisjointSet ds = new DisjointSet(n * n);

    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        if (grid[row][col] == 0)
          continue;
        for (int i = 0; i < 4; i++) {
          int nrow = row + drow[i];
          int ncol = col + dcol[i];

          if (isValid(nrow, ncol, n) && grid[nrow][ncol] == 1) {
            int nodeNum = row * n + col;
            int adjNodeNum = nrow * n + ncol;
            ds.findUnionBySize(adjNodeNum, nodeNum);
          }
        }
      }
    }

    int maxi = 0;
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        if (grid[row][col] == 1)
          continue;

        HashSet<Integer> components = new HashSet<>();
        for (int i = 0; i < 4; i++) {
          int nrow = row + drow[i];
          int ncol = col + dcol[i];
          if (isValid(nrow, ncol, n) && grid[nrow][ncol] == 1) {
            components.add(ds.findUltPar(nrow * n + ncol));
          }
        }
        int totalSize = 0;
        for (int parent : components) {
          totalSize += ds.size.get(parent);
        }
        // totalSize + 1 becoz, we are including this cell too for counting the size of
        // the larger component
        maxi = Math.max(totalSize + 1, maxi);
      }
    }

    for (int cellNum = 0; cellNum < n * n; cellNum++) {
      maxi = Math.max(maxi, ds.size.get(ds.findUltPar(cellNum)));
    }
    return maxi;
  }
}