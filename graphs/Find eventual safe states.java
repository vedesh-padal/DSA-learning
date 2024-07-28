package graphs;

import java.util.List;
import java.util.ArrayList;

class Solution {

  private static boolean dfsCheck(int node, int[][] graph, boolean[] vis, boolean[] pathVis, boolean[] check) {
    vis[node] = true;
    pathVis[node] = true;
    check[node] = false; // to track the safe node

    // traverse adj nodes
    for (int it : graph[node]) {
      if (vis[it] == false) {
        if (dfsCheck(it, graph, vis, pathVis, check) == true)
          return true;
      } else if (pathVis[it] == true)
        return true;
    }

    check[node] = true;
    pathVis[node] = false;
    return false;
  }

  public List<Integer> eventualSafeNodes(int[][] graph) {
    int V = graph.length;
    boolean[] vis = new boolean[V];
    boolean[] pathVis = new boolean[V];
    boolean[] check = new boolean[V];
    for (int i = 0; i < V; i++) {
      if (vis[i] == false) {
        dfsCheck(i, graph, vis, pathVis, check);
      }
    }
    List<Integer> safeNodes = new ArrayList<>();
    for (int i = 0; i < check.length; i++) {
      if (check[i] == true)
        safeNodes.add(i);
    }
    return safeNodes;
  }
}