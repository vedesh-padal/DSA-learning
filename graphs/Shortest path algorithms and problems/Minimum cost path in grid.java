/*
  Given a square grid of size N, each cell of which contains an integer cost that represents 
  a cost to traverse through that cell, we need to find a path from the top left cell to the 
  bottom right cell by which the total cost incurred is minimum.
  From the cell (i,j) we can go (i,j-1), (i, j+1), (i-1, j), (i+1, j).  

  Examples :

  Input: grid = {{9,4,9,9},{6,7,6,4},{8,3,3,7},{7,4,9,10}}
  Output: 43
  Explanation: The grid is-
  9 4 9 9
  6 7 6 4
  8 3 3 7
  7 4 9 10
  The minimum cost is-
  9 + 4 + 7 + 3 + 3 + 7 + 10 = 43.

  Input: grid = {{4,4},{3,7}}
  Output: 14
  Explanation: The grid is-
  4 4
  3 7
  The minimum cost is- 4 + 3 + 7 = 14.
  Expected Time Complexity: O(n2*log(n))
  Expected Auxiliary Space: O(n2) 

  Constraints:
  1 ≤ n ≤ 500
  1 ≤ cost of cells ≤ 500

*/

// Using Djikstra's algorithm

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
  class Cell {
    int x;
    int y;
    int cost;

    public Cell(int x, int y, int cost) {
      this.x = x;
      this.y = y;
      this.cost = cost;
    }
  }

  private static final int[][] directions = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

  // Function to return the minimum cost to react at bottom
  // right cell from top left cell.
  public int minimumCostPath(int[][] grid) {
    // Code here
    int n = grid.length;

    // min heap = priority queue
    PriorityQueue<Cell> pq = new PriorityQueue<>((x, y) -> (x.cost - y.cost));
    pq.offer(new Cell(0, 0, grid[0][0])); // start from the top left

    int[][] minCost = new int[n][n];
    for (int[] row : minCost)
      Arrays.fill(row, -1);

    minCost[0][0] = grid[0][0];

    while (!pq.isEmpty()) {
      Cell current = pq.poll();

      if (current.x == n - 1 && current.y == n - 1)
        return current.cost;

      for (int[] direction : directions) {
        int newX = current.x + direction[0];
        int newY = current.y + direction[1];

        // check if it is a valid cell
        if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
          int newCost = current.cost + grid[newX][newY];

          if (newCost < minCost[newX][newY]) {
            minCost[newX][newY] = newCost;
            pq.offer(new Cell(newX, newY, newCost));
          }
        }
      }
    }
    return -1;
  }
}