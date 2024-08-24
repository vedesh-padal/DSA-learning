package graphs;

/*
  "Bomb Enemy," involves a 2D grid where each cell can be one of three types: 
  a wall ('W'), an enemy ('E'), or an empty space ('0'). The goal is to determine
  the maximum number of enemies that can be killed by placing a bomb in one of the empty cells. 
  The bomb has the ability to kill all enemies in the same row and column until it hits a wall.
*/

class Solution {
  
  // recursive approach
  public int maxKilledEnemies1(char[][] grid)  {
    if (grid == null || grid.length == 0 || grid[0].length == 0)  
      return 0;

    int maxKills = 0;
    int rows = grid.length;
    int cols = grid[0].length;

    for (int r=0; r<rows; r++)  {
      for (int c=0; c<cols; c++)  {
        if (grid[r][c] == '0')  {
          int kills = countKills1(grid, r, c);
          maxKills = Math.max(kills, maxKills);
        }
      }
    }

    return maxKills;
  }


  private static int countKills1(char[][] grid, int r, int c)  {

    // base case out of bounds, or hit a wall
    if (r < 0 || r > grid.length || c < grid[0].length || c > grid[0].length || grid[r][c] == 'W')
      return 0;


    // if we hit an Enemy, count it and hit in all 4 directions
    if (grid[r][c] == 'E')  {
      return 1 + countKills1(grid, r+1, c) + countKills1(grid, r-1, c) 
               + countKills1(grid, r, c-1) + countKills1(grid, r, c+1);
    }

    // if we hit an empty space, continue checking in all 4 directions
    return countKills1(grid, r+1, c) + countKills1(grid, r-1, c) 
          + countKills1(grid, r, c-1) + countKills1(grid, r, c+1);

  }


  // iterative and optimal approach
  public int maxKilledEnemies2(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0)
      return 0;

    int rows = grid.length;
    int cols = grid[0].length;
    int maxKills = 0;

    for (int r=0; r<rows; r++)  {
      for (int c=0; c<cols; c++)  {
        if (grid[r][c] == '0')  {
          int kills = countKills2(grid, r, c);
          maxKills = Math.max(kills, maxKills);
        }
      }
    }

    return maxKills;
  }


  private static int countKills2(char[][] grid, int row, int col) {
    int kills = 0;

    // right
    for (int c=col+1; c < grid[0].length && grid[row][c] != 'W'; c++) {
      if (grid[row][c] == 'E')
        kills++;
    }

    // left
    for (int c=col-1; c >= 0 && grid[row][c] != 'W'; c--) {
      if (grid[row][c] == 'E')
        kills++;
    }

    // down
    for (int r=row+1; r < grid.length && grid[r][col] != 'W'; r++) {
      if (grid[r][col] == 'E')
        kills++;
    }

    // up
    for (int r=row-1; r >= 0 && grid[r][col] != 'W'; r--) {
      if (grid[r][col] == 'E')
        kills++;
    }

    return kills;
  }
}
