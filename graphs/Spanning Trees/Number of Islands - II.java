/*
  You are given a n,m which means the row and column of the 2D matrix and an 
  array of  size k denoting the number of operations. Matrix elements is 0 if 
  there is water or 1 if there is land. Originally, the 2D matrix is all 0 
  which means there is no land in the matrix. The array has k operator(s) and 
  each operator has two integer A[i][0], A[i][1] means that you can change the cell 
  matrix[A[i][0]][A[i][1]] from sea to island. Return how many island are there in 
  the matrix after each operation.You need to return an array of size k.
  
  Note : An island means group of 1s such that they share a common side.
*/


// ONLINE QUERIES
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

  private static boolean isValid(int row, int col, int rows, int cols) {
    return row >= 0 && row < rows && col >= 0 && col < cols;
  }

  public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
    // Your code here
    DisjointSet ds = new DisjointSet(rows * cols);
    boolean[][] vis = new boolean[rows][cols];

    int cnt = 0;
    List<Integer> ans = new ArrayList<>();
    int len = operators.length;

    int[] drow = { -1, 0, 1, 0 };
    int[] dcol = { 0, 1, 0, -1 };

    for (int i = 0; i < len; i++) {
      int row = operators[i][0];
      int col = operators[i][1];
      // if the cell is already visited, we have nothing to do,
      // coz, the same number of groups remain
      if (vis[row][col]) {
        ans.add(cnt); // since we need to return the number of islands after each operation
        continue;
      }

      vis[row][col] = true;
      cnt++;

      // check in all 4 directions for current cell
      for (int j = 0; j < 4; j++) {
        int adjr = row + drow[j]; // adjacent row
        int adjc = col + dcol[j]; // adjacent col

        if (isValid(adjr, adjc, rows, cols) && vis[adjr][adjc] == true) {
          int nodeNum = row * cols + col;
          int adjNodeNum = adjr * cols + adjc;
          if (ds.findUltPar(nodeNum) != ds.findUltPar(adjNodeNum)) {
            cnt--;
            ds.findUnionBySize(nodeNum, adjNodeNum);
          }
        }
      }
      ans.add(cnt);
    }
    return ans;

  }

}