/*
 * There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

  A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

  Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 */



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