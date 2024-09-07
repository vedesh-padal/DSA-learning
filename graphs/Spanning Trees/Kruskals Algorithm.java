import java.util.*;

class Edge implements Comparable<Edge> {
  int src, dest, weight;

  public Edge(int _src, int _dest, int _weight) {
    this.src = _src;
    this.dest = _dest;
    this.weight = _weight;
  }

  public int compareTo(Edge compareEdge) {
    return this.weight - compareEdge.weight;
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

class Solution {
  // Using Kruskals Algorithm (with the help of Disjoint set)
  static int spanningTree(int V, int E, List<List<int[]>> adj) {
    List<Edge> edges = new ArrayList<Edge>();

    // TC: O(V + E)
    for (int i = 0; i < V; i++) {
      for (int j = 0; j < adj.get(i).size(); j++) {
        int adjNode = adj.get(i).get(j)[0];
        int wt = adj.get(i).get(j)[1];
        int node = i;
        edges.add(new Edge(node, adjNode, wt));
      }
    }

    // TC: O( M x logM )
    Collections.sort(edges); // sort the list of edges based on the weights (as implemented in comparable class)

    DisjointSet ds = new DisjointSet(V);

    int mstWt = 0;

    for (Edge it : edges) {
      int wt = it.weight;
      int u = it.src;
      int v = it.dest;

      if (ds.findUltPar(u) != ds.findUltPar(v)) {
        mstWt += wt;
        ds.unionBySize(u, v);
      }
    }

    return mstWt;
  }
}