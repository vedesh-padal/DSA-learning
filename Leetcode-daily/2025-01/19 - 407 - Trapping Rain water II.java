// HARD
// bfs, matrix, heap

/*
    Given an m x n integer matrix heightMap representing the height of each unit
    cell in a 2D elevation map, return the volume of water it can trap after raining.
    
    Example 1:
    Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
    Output: 4
    Explanation: After the rain, water is trapped between the blocks.
    We have two small ponds 1 and 3 units trapped.
    The total volume of water trapped is 4.

    Example 2:
    Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
    Output: 10
    
    Constraints:
    m == heightMap.length
    n == heightMap[i].length
    1 <= m, n <= 200
    0 <= heightMap[i][j] <= 2 * 10^4
*/

import java.util.*;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        // Detailed explanation in notes
        int m = heightMap.length, n = heightMap[0].length;
        // SOME OBSERVATIONS:
        // - you cannot store water in boundary cells
        // - try to fill water in the neighbors of boundary cells 
        //      (starting from min. height boundary cells), and mark them visited
        // - the amount of water that can be stored in the neighbor depends on
        //      what is the height that it can gaurd, minus the neighbors height (positive only added)
        // - and the neighbor is marked visited, and the height it can gaurd is set as the 
        //      the the max. of curr. (neighbor) and it's neighbor (prev. one)


        boolean[][] vis = new boolean[m][n];
        // since we need to pick the min. height cell each time, so that water does not overflow
        // we use a MinHeap based on distance and along with it, also the associated cell co-ordinates
        // (height, i, j)
        PriorityQueue<int[]> boundaryCells = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // first, add all the boundary cells into the heap
        // left and right cols
        for (int i = 0; i < m; i++) {
            boundaryCells.offer(new int[]{ heightMap[i][0], i, 0 });
            vis[i][0] = true;
            boundaryCells.offer(new int[]{ heightMap[i][n-1], i, n-1 });
            vis[i][n-1] = true;
        }

        // first and last rows
        for (int j = 0; j < n; j++) {
            boundaryCells.offer(new int[]{ heightMap[0][j], 0, j });
            vis[0][j] = true;
            boundaryCells.offer(new int[]{ heightMap[m-1][j], m-1, j });
            vis[m-1][j] = true;
        }

        int water = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        while (!boundaryCells.isEmpty()) {
            int[] curr = boundaryCells.poll();
            int height = curr[0];
            int i = curr[1];
            int j = curr[2];

            for (int[] dir: dirs) {
                int newI = i + dir[0];
                int newJ = j + dir[1];

                if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && !vis[newI][newJ]) {
                    
                    water += Math.max(height - heightMap[newI][newJ], 0);
                    vis[newI][newJ] = true;
                    boundaryCells.offer(new int[]{ Math.max(height, heightMap[newI][newJ]), newI, newJ });
                }
            }
        }
        return water;
    }
}