package graphs;

import java.util.*;

public class NumProvinces {

  // with arraylist:
  // private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
  //   vis[node] = true;
  //   for (Integer it : adj.get(node)) {
  //     if (vis[it] == false) {
  //       dfs(it, adj, vis);
  //     }
  //   }
  // }

  // directly with adj matrix
  // private static void dfs(int ind, int[][] adj, boolean[] vis) {
  //   vis[ind] = true;
  //   for (int i = 0; i < adj.length; i++) {
  //     if (adj[ind][i] == 1 && !vis[i]) {
  //       vis[i] = true;
  //       dfs(i, adj, vis);
  //     }
  //   }
  // }

  public int findCircleNum(int[][] isConnected) {
    int V = isConnected.length;

    // unnecessary - copying to an arraylist and doing
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }

    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        if (isConnected[i][j] == 1 && i != j) {
          adj.get(i).add(j);
          adj.get(j).add(i);
        }
      }
    }

    boolean[] vis = new boolean[V];
    int cnt = 0; // basically finding how many traversals are possible starting from each node,
                 // to identify the no. of provinces
    for (int i = 0; i < V; i++) {
      if (vis[i] == false) {
        // dfs(i, adj, vis);
        dfs(i, vis, adj);
        cnt++;
      }
    }
    return cnt;
  }
  // ----------------------------------------------------------------------------------------
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

  // USING DISJOINT SET
  static int numProvinces2(ArrayList<ArrayList<Integer>> adj, int V) {
    // code here

    DisjointSet ds = new DisjointSet(V);

    for (int i = 0; i < adj.size(); i++) {
      for (int j = 0; j < adj.get(i).size(); j++) {
        if (adj.get(i).get(j) == 1)
          ds.unionBySize(i, j);
      }
    }

    int cnt = 0;
    for (int i = 0; i < ds.parent.size(); i++) {
      if (i == ds.parent.get(i))
        cnt++;
    }
    return cnt;
  }
}

class DisjointSet {
  List<Integer> parent = new ArrayList<>();
  List<Integer> rank = new ArrayList<>();
  List<Integer> size = new ArrayList<>();

  public DisjointSet(int n) {
    for (int i = 0; i < n; i++) {
      parent.add(i);
      rank.add(0);
      size.add(1);
    }
  }

  public int findUltPar(int node) {
    if (node == parent.get(node))
      return node;

    int ulp = findUltPar(parent.get(node));
    parent.set(node, ulp);
    return ulp; // or parent.get(node); // both will give same
  }

  public void unionByRank(int u, int v) {
    int ulp_u = findUltPar(u);
    int ulp_v = findUltPar(v);

    if (ulp_u == ulp_v)
      return;

    if (rank.get(ulp_u) == rank.get(ulp_v))
      parent.set(ulp_u, ulp_v);

    else if (rank.get(ulp_v) < rank.get(ulp_u))
      parent.set(ulp_v, ulp_u);

    else {
      parent.set(ulp_v, ulp_u); // attach v's ult parent to u's ult parent
      int rankU = rank.get(ulp_u);
      rank.set(ulp_u, rankU + 1);
    }
  }

  public void unionBySize(int u, int v) {
    int ulp_u = findUltPar(u);
    int ulp_v = findUltPar(v);

    if (ulp_u == ulp_v)
      return;

    if (size.get(ulp_u) < size.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
      size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
    } else { // for == also we add size
      parent.set(ulp_v, ulp_u);
      size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
    }
  }
}
