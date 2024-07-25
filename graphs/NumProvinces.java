package graphs;

import java.util.ArrayList;

public class NumProvinces {

  // with arraylist:
  // private static void dfs(int node, ArrayList<ArrayList<Integer>> adj,
  // boolean[] vis) {
  // vis[node] = true;
  // for (Integer it: adj.get(node)) {
  // if (vis[it] == false) {
  // dfs(it, adj, vis);
  // }
  // }
  // }

  // directly with adj matrix
  private static void dfs(int ind, int[][] adj, boolean[] vis) {
    vis[ind] = true;
    for (int i = 0; i < adj.length; i++) {
      if (adj[ind][i] == 1 && !vis[i]) {
        vis[i] = true;
        dfs(i, adj, vis);
      }
    }
  }

  public int findCircleNum(int[][] isConnected) {
    int V = isConnected.length;

    // unnecessary - copying to an arraylist and doing
    // ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    // for (int i=0; i < V; i++) {
    // adj.add(new ArrayList<>());
    // }

    // for (int i=0; i<V; i++) {
    // for (int j=0; j<V; j++) {
    // if (isConnected[i][j] == 1 && i != j) {
    // adj.get(i).add(j);
    // adj.get(j).add(i);
    // }
    // }
    // }

    boolean[] vis = new boolean[V];
    int cnt = 0; // basically finding how many traversals are possible starting from each node,
                 // to identify the no. of provinces
    for (int i = 0; i < V; i++) {
      if (vis[i] == false) {
        // dfs(i, adj, vis);
        dfs(i, isConnected, vis);
        cnt++;
      }
    }
    return cnt;
  }
  

  // GFG
  private static void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {
    vis[node] = true;
    for (int i = 0; i < adj.get(0).size(); i++) {
      if (adj.get(node).get(i) == 1 && !vis[i]) {
        dfs(i, vis, adj);
      }
    }
  }

  static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
    // code here
    int cnt = 0;
    boolean[] vis = new boolean[V];
    for (int i = 0; i < V; i++) {
      if (!vis[i]) {
        dfs(i, vis, adj);
        cnt++;
      }
    }
    return cnt;
  }
}