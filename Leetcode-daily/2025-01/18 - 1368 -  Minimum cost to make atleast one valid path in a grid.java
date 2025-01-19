// HARD
// backtracking, graph, matrix, dp, dfs

/*
    Given an m x n grid. Each cell of the grid has a sign pointing to the next
    cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:

    1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
    2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
    3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
    4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
    Notice that there could be some signs on the cells of the grid that point outside the grid.

    You will initially start at the upper left cell (0, 0). A valid path in the
    grid is a path that starts from the upper left cell (0, 0) and ends at the
    bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid
    path does not have to be the shortest.

    You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

    Return the minimum cost to make the grid have at least one valid path.

    Example 1:
    Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
    Output: 3
    Explanation: You will start at point (0, 0).
    The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3)
    change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) -->
    (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2,
    2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
    The total cost = 3.

    Example 2:
    Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
    Output: 0
    Explanation: You can follow the path from (0, 0) to (2, 2).

    Example 3:
    Input: grid = [[1,2],[4,3]]
    Output: 1

    Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 100
    1 <= grid[i][j] <= 4
*/
import java.util.*;

class Solution {
    int m, n;
    // Integer[][] dp;  // DP won't work, reason mentioned below

    // right, left, up, down
    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private boolean isValid(int i, int j) {
        return (i >= 0 && i < m && j >= 0 && j < n);
    }

    // USING DJIKSTRA'S ALGO
    // becoz, we need min. cost to reach from src to dest
    public int minCost(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int[][] res = new int[m][n];
        for (int[] row: res)
            Arrays.fill(row, Integer.MAX_VALUE);

        // minCost, to reach i, j from source (0, 0)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 0});
        res[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currCost = curr[0];
            int i = curr[1];
            int j = curr[2];

            // if already we can reach with less cost at that cell, 
            // then no need to perform next operations
            if (res[i][j] < currCost) {
                continue;
            }

            for (int k = 0; k < 4; k++) {
                int newI = i + dirs[k][0];
                int newJ = j + dirs[k][1];

                if (isValid(newI, newJ)) {
                    int gridDirection = grid[i][j];
                    int nextCost = currCost + (gridDirection - 1 != k ? 1 : 0);

                    if (nextCost < res[newI][newJ]) {
                        res[newI][newJ] = nextCost;
                        pq.offer(new int[]{nextCost, newI, newJ});
                    }
                }
            }
        }
        return res[m-1][n-1];
    }


    // ==================================
    // BRUTE FORCE - TLE - Backtracking DFS - try all paths
    // TC: 4^(m*n)
    private int dfs(int i, int j, int[][] grid, boolean[][] vis, int currCost) {
        if (i == m-1 && j == n-1) {
            return currCost;
        }

        vis[i][j] = true;
        int minCost = Integer.MAX_VALUE;
        for (int k = 0; k < 4; k++) {
            int newI = i + dirs[k][0];
            int newJ = j + dirs[k][1];

            if (isValid(newI, newJ) && vis[newI][newJ] == false) {
                int gridDirection = grid[i][j];   // 1-based direction
                // converted to 0-based to match our dirs[][]
                // if direction not matching, we add 1 cost
                int nextCost = currCost + ((gridDirection - 1 != k) ? 1 : 0);

                minCost = Math.min(minCost, dfs(newI, newJ, grid, vis, nextCost));
            }
        }

        vis[i][j] = false;
        return minCost;
    }

    // BRUTE FORCE
    public int minCost_BRUTE(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean[][] vis = new boolean[m][n];

        // one thing that comes to mind is explore all possiblities (4 directions at each cell)
        // and find the min. cost to reach (m-1, n-1) cell

        // i, j, grid, vis, currCost
        return dfs(0, 0, grid, vis, 0);

        // REALIZED -> DP solution won't work, becoz => 
        /*
            - In classic DP, we assume that if cell (i,j) is part of optimal solution, all cells in 
            that optimal path to (i,j) must also be optimal - but this problem violates that because 
            modifying a cell's direction might make a previously "non-optimal" path become optimal.

            - Therefore simple memoization with dp[i][j][direction] fails because optimal path from (i,j) 
            depends not just on "where we are", but on "what modifications we made to get here" - different arrow 
            modifications on the path to (i,j) can lead to different optimal solutions from (i,j).
        */
        // dp = new Integer[m][n];
        // return solve(0, 0, grid, vis, dp);
    }


    // dfs, this approach not working, have to go through LC editorial later
    @SuppressWarnings("unused")
    private int solve(int i, int j, int[][] grid, boolean[][] vis, Integer[][] dp) {
        if (i == m-1 && j == n-1)
            return 0;

        if (dp[i][j] != null) {
            return dp[i][j];
        }
        
        vis[i][j] = true;
        int minCost = Integer.MAX_VALUE;

        for (int k = 0; k < 4; k++) {
            int nr = i + dirs[k][0];
            int nc = j + dirs[k][1];

            if (isValid(nr, nc) && vis[nr][nc] == false) {
                int currCost = solve(nr, nc, grid, vis, dp);
                if (currCost != Integer.MAX_VALUE) {
                    if (grid[i][j] - 1 != k) {
                        currCost++;
                    }
                    minCost = Math.min(minCost, currCost);
                }
            }
        }

        vis[i][j] = false;
        return dp[i][j] = minCost;
    }
}