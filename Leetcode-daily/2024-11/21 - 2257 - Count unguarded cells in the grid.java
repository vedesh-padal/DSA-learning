// MEDIUM
// arrays, matrix, simulation, graph

// You are given two integers m and n representing a 0-indexed m x n grid. You
// are also given two 2D integer arrays guards and walls where guards[i] =
// [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith
// guard and jth wall respectively.

// A guard can see every cell in the four cardinal directions (north, east,
// south, or west) starting from their position unless obstructed by a wall or
// another guard. A cell is guarded if there is at least one guard that can see it.

// Return the number of unoccupied cells that are not guarded.

// Constraints:
// 1 <= m, n <= 105
// 2 <= m * n <= 105
// 1 <= guards.length, walls.length <= 5 * 104
// 2 <= guards.length + walls.length <= m * n
// guards[i].length == walls[j].length == 2
// 0 <= rowi, rowj < m
// 0 <= coli, colj < n
// All the positions in guards and walls are unique.

class Solution {

    private boolean isValid(int i, int j, int m, int n) {
        return ( i < m && i >= 0 && j < n && j >= 0 );
    }

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // first, we shall make the grid
        int[][] grid = new int[m][n];

        for (int i = 0; i < guards.length; i++) 
            grid[guards[i][0]][guards[i][1]] = -1;

        for (int i = 0; i < walls.length; i++)
            grid[walls[i][0]][walls[i][1]] = -2;

        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int[] guard: guards) {
            for (int k = 0; k < 4; k++) {
                int nrow = guard[0] + dirs[k][0];
                int ncol = guard[1] + dirs[k][1];

                // move in this specific direction till you meet
                // sight of guard or wall
                while (isValid(nrow, ncol, m, n) && grid[nrow][ncol] != -1 && grid[nrow][ncol] != -2) {
                    grid[nrow][ncol] = 1;
                    nrow += dirs[k][0];
                    ncol += dirs[k][1];
                }
            }
        }
        
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}