package graphs;

import java.util.*;

class Solution {

  private static void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj,
      Stack<Integer> stk, boolean stackNeeded) {

    vis[node] = true;

    for (int it : adj.get(node)) {
      if (vis[it] == false) {
        dfs(it, vis, adj, stk, stackNeeded);
      }
    }

    // pushing the current node when backtracking
    if (stackNeeded)
      stk.push(node);
  }

  // Function to find number of strongly connected components in the graph.
  public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
    // code here
    // step - 1:sort the edges according to finishing time
    boolean[] vis = new boolean[V];
    Stack<Integer> stk = new Stack<>(); // to store the node when backtracking after all visits from that node
    for (int i = 0; i < V; i++) {
      if (vis[i] == false)
        dfs(i, vis, adj, stk, true);
    }

    // step - 2: reverse the graph
    ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
    for (int i = 0; i < V; i++)
      adjT.add(new ArrayList<>());

    for (int i = 0; i < V; i++) {
      vis[i] = false;
      for (int it : adj.get(i)) {
        adjT.get(it).add(i); // reversing the connected edge
      }
    }

    // step - 3: perform one more dfs on the reversed graph, and count the number
    // of times DFS was done
    int scc = 0; // number of strongly connected components
    while (!stk.isEmpty()) {
      int node = stk.peek();
      stk.pop();
      if (vis[node] == false) {
        scc++;
        dfs(node, vis, adjT, stk, false);
      }
    }
    return scc;
  }
}
