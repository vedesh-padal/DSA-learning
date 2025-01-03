// MEDIUM - HARD
// bfs, djikstras, priority-queue, graph

// You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:

// 0 represents an empty cell,
// 1 represents an obstacle that may be removed.
// You can move up, down, left, or right from and to an empty cell.

// Return the minimum number of obstacles to remove so you can move from the upper 
// left corner (0, 0) to the lower right corner (m - 1, n - 1).

// Examples:
// Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
// Output: 2
// Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
// It can be shown that we need to remove at least 2 obstacles, so we return 2.
// Note that there may be other ways to remove 2 obstacles to create a path.

// Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
// Output: 0
// Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.

// Constraints:
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 105
// 2 <= m * n <= 105
// grid[i][j] is either 0 or 1.
// grid[0][0] == grid[m - 1][n - 1] == 0

// SIMILAR QUESTIONS:
// 3341. Find Minimum Time to Reach Last Room I
// 1631. Path With Minimum Effort
// 3286. Find a Safe Walk Through a Grid
// 2290. Minimum Obstacle Removal to Reach Corner
// 1293. Shortest Path in a Grid with Obstacles Elimination
// 864. Shortest Path to Get All Keys

import java.util.*;

class Solution {

    private boolean isValid(int i, int j, int m, int n) {
        return (i >= 0 && i < m && j >= 0 && j < n);
    }

    int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    // USING DJIKSTRAS ALGO:
    // space efficient, not as runtime efficient as deque implementation
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // priority Queue
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.offer(new int[] { 0, 0, 0 });

        int[][] res = new int[m][n];
        for (int[] row : res)
            Arrays.fill(row, Integer.MAX_VALUE);

        res[0][0] = 0;

        while (!pq.isEmpty()) {
            int obstCnt = pq.peek()[0];
            int r = pq.peek()[1];
            int c = pq.peek()[2];
            pq.poll();

            for (int[] dir : dirs) {
                int nrow = r + dir[0];
                int ncol = c + dir[1];

                if (isValid(nrow, ncol, m, n) && res[nrow][ncol] == Integer.MAX_VALUE) {
                    int nObstCnt = obstCnt + grid[nrow][ncol];
                    if (nObstCnt < res[nrow][ncol]) {
                        res[nrow][ncol] = nObstCnt;
                        pq.offer(new int[] { nObstCnt, nrow, ncol });
                    }
                }
            }
        }
        return res[m - 1][n - 1];
    }

    // ==============================================

    // the below approaches are BFS ones, this works, becoz there are no varying weights

    // USING DEQUE (that is optimal compared to pure BFS) runtime efficient
    public int minimumObstacles_usingDeque(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] { 0, 0, 0 }); // obstCnt, i, j

        int[][] res = new int[m][n];
        for (int[] row : res)
            Arrays.fill(row, Integer.MAX_VALUE);

        res[0][0] = 0;

        while (!dq.isEmpty()) {
            int obstCnt = dq.peek()[0];
            int r = dq.peek()[1];
            int c = dq.peek()[2];
            dq.poll();

            for (int[] dir : dirs) {
                int nrow = r + dir[0];
                int ncol = c + dir[1];

                if (isValid(nrow, ncol, m, n) && res[nrow][ncol] == Integer.MAX_VALUE) {
                    if (grid[nrow][ncol] == 1) {
                        res[nrow][ncol] += 1;
                        dq.offerLast(new int[] { obstCnt + 1, nrow, ncol });
                    } else {
                        res[nrow][ncol] = obstCnt;
                        dq.offerFirst(new int[] { obstCnt, nrow, ncol });
                    }
                }
            }
        }
        return res[m - 1][n - 1];
    }

    // ===================================
    // using pure BFS:
    public int minimumObstacles_usingPureBFS(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0 });

        int[][] res = new int[m][n];
        for (int[] row : res)
            Arrays.fill(row, Integer.MAX_VALUE);

        res[0][0] = 0;

        while (!q.isEmpty()) {
            int sz = q.size();

            for (int i = 0; i < sz; i++) {
                int r = q.peek()[0];
                int c = q.peek()[1];

                q.poll();

                for (int[] dir : dirs) {
                    int nrow = r + dir[0];
                    int ncol = c + dir[1];

                    if (isValid(nrow, ncol, m, n)) {
                        int obstCnt = res[r][c] + grid[nrow][ncol];

                        // if this path has fewer obstacles than previously recorded
                        if (obstCnt < res[nrow][ncol]) {
                            res[nrow][ncol] = obstCnt;
                            q.offer(new int[] { nrow, ncol });
                        }
                    }
                }
            }
        }

        return res[m - 1][n - 1];
    }

}