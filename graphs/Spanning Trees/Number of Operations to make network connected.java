import java.util.*;

// DisjointSet Data Structure useful when there are Dynamic connections happening
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
  public int makeConnected(int n, int[][] connections) {
    int numConns = connections.length;
    DisjointSet ds = new DisjointSet(n);
    int numExtraEdges = 0;

    for (int i = 0; i < numConns; i++) {
      int u = connections[i][0];
      int v = connections[i][1];

      // counting number of extra edges
      // an edge is extra in a component, if two nodes have same ultimate edge
      // we are able to say this becoz, we dynamically created the graph using
      // DisjointSet DS
      if (ds.findUltPar(u) == ds.findUltPar(v))
        numExtraEdges++;
      else
        ds.unionBySize(u, v);
    }

    // counting number of connected components
    // if the ultimate parent of a node is itself, we say that we have a connected
    // component - BEST WAY
    // other way can be, counting number of distinct ultimate parents among for all
    // nodes
    int numConnComps = 0;
    for (int i = 0; i < ds.parent.size(); i++) {
      if (i == ds.parent.get(i))
        numConnComps++;
    }

    int minConnCompsReq = numConnComps - 1;
    if (numExtraEdges >= minConnCompsReq)
      return minConnCompsReq;
    else
      return -1;
  }
}