/*
  Given a weighted and directed graph of V vertices and E edges, Find the shortest distance 
  of all the vertex's from the source vertex S. If a vertices can't be reach from the S then 
  mark the distance as 10^8. Note: If the Graph contains a negative cycle then return an array 
  consisting of only -1.

  E = [[0,1,5],[1,0,3],[1,2,-1],[2,0,1]]
  S = 2
  Output:
  1 6 0
  Explanation:
  For nodes 2 to 0, we can follow the path-
  2-0. This has a distance of 1.
  For nodes 2 to 1, we cam follow the path-
  2-0-1, which has a distance of 1+5 = 6,
  
  Expected Time Complexity: O(V*E).
  Expected Auxiliary Space: O(V).

  Constraints:
  1 ≤ V ≤ 500
  1 ≤ E ≤ V*(V-1)
  -1000 ≤ adj[i][j] ≤ 1000
  0 ≤ S < V
*/

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
  static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
    // Write your code here
    int[] dist = new int[V];
    Arrays.fill(dist, (int) (1e8));
    dist[S] = 0;

    // N - 1 iterations => bellman ford algo
    // (O (N x E))
    for (int i = 0; i < V - 1; i++) {
      // relaxing edges
      for (ArrayList<Integer> it : edges) {
        int u = it.get(0);
        int v = it.get(1);
        int wt = it.get(2);

        if (dist[u] != (int) (1e8) && dist[u] + wt < dist[v]) {
          dist[v] = dist[u] + wt;
        }
      }
    }

    // do Nth iteration to check if there exists a negative cycle
    // if negative cycle exists, distance in the nth iteration will further reduce,
    // that means negative cycle exists
    for (ArrayList<Integer> it : edges) {
      int u = it.get(0);
      int v = it.get(1);
      int wt = it.get(2);

      if (dist[u] != (int) (1e8) && dist[u] + wt < dist[v]) {
        return new int[] { -1 };
      }
    }
    return dist;
  }
}
