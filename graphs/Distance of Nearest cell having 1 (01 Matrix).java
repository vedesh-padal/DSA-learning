// Example of Multi-source BFS

package graphs;
import java.util.*;

class Solution {
    
    private static final int[][] dirs = {{-1,0}, {0, 1}, {1, 0}, {0, -1}};
    
    //Function to find distance of nearest 1 in the grid for each cell.
    public int[][] nearest(int[][] grid)
    {
        // Code here
        // BFS
        int m = grid.length;
        int n = grid[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[m][n];
        boolean[][] vis = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // instead of enqueing only one node at the beginning as source node,
                    // we are enqueing multiple nodes at beginning as source nodes, before removing any
                    // Hence, called, Multi-source BFS
                    q.offer(new int[]{i, j, 0});
                    vis[i][j] = true;
                }
            }
        }
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], steps = curr[2];
            
            dist[r][c] = steps;
            
            for (int[] dir: dirs) {
                int nrow = dir[0] + r;
                int ncol = dir[1] + c;
                
                if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && vis[nrow][ncol] == false) {
                    q.offer(new int[]{nrow, ncol, steps + 1});
                    vis[nrow][ncol] = true;
                }
            }
        }
        return dist;
    }
}