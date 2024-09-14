/*
  Given an undirected connected graph with V vertices and adjacency list adj. 
  ARTICULATION POINT DEFINITION:
  You are required to find all the vertices removing which (and edges through it) disconnects the graph 
  into 2 or more components and return it in sorted manner.
  Note: Indexing is zero-based i.e nodes numbering from (0 to V-1). There might be loops present in the graph.
*/

package graphs;

import java.util.ArrayList;

class Solution {
  private int timer = 1;

  private void dfs(int node, int parent, boolean[] vis, int[] tin, int[] low,
      boolean[] mark, ArrayList<ArrayList<Integer>> adj) {

    vis[node] = true;
    tin[node] = low[node] = timer;
    timer++;

    int child = 0;
    for (int it : adj.get(node)) {
      if (it == parent)
        continue;
      if (vis[it] == false) {
        dfs(it, node, vis, tin, low, mark, adj);
        low[node] = Math.min(low[node], low[it]);

        if (low[it] >= tin[node] && parent != -1)
          mark[node] = true;

        child++;
      } else {
        low[node] = Math.min(low[node], tin[it]);
      }
    }

    // seperate logic for the dfs start node (parent == -1)
    if (child > 1 && parent == -1)
      mark[node] = true;
  }

  // Function to return Breadth First Traversal of given graph.
  public ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
    // Code here
    boolean[] vis = new boolean[V];
    int[] tin = new int[V];
    int[] low = new int[V];
    boolean[] mark = new boolean[V];

    // since after each disconnection, there can be multiple components, we need
    // to do a DFS for each node
    for (int i = 0; i < V; i++) {
      if (vis[i] == false)
        dfs(i, -1, vis, tin, low, mark, adj);
    }

    ArrayList<Integer> artcPoints = new ArrayList<>();
    for (int i = 0; i < V; i++)
      if (mark[i] == true)
        artcPoints.add(i);

    if (artcPoints.size() == 0)
      artcPoints.add(-1);

    return artcPoints;
  }
}