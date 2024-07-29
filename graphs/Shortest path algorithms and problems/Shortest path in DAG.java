/*
  Given a Directed Acyclic Graph of N vertices from 0 to N-1 and a 2D Integer array(or vector) edges[ ][ ] of length M, where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.

  Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that vertex.

  Example1:

  Input:
  N = 4, M = 2
  edge = [[0,1,2],[0,2,1]]
  Output:
  0 2 1 -1
  Explanation:
  Shortest path from 0 to 1 is 0->1 with edge weight 2. 
  Shortest path from 0 to 2 is 0->2 with edge weight 1.
  There is no way we can reach 3, so it's -1 for 3.
  Example2:

  Input:
  N = 6, M = 7
  edge = [[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
  Output:
  0 2 3 6 1 5
  Explanation:
  Shortest path from 0 to 1 is 0->1 with edge weight 2. 
  Shortest path from 0 to 2 is 0->4->2 with edge weight 1+2=3.
  Shortest path from 0 to 3 is 0->4->5->3 with edge weight 1+4+1=6.
  Shortest path from 0 to 4 is 0->4 with edge weight 1.
  Shortest path from 0 to 5 is 0->4->5 with edge weight 1+4=5.
 */

import java.util.*;

class Pair {
  int first, second;

  Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
}

class Solution {

  private void topoSort(int node, List<List<Pair>> adj, boolean[] vis, Stack<Integer> stk) {

    vis[node] = true;

    for (Pair it : adj.get(node)) {
      int v = it.first;
      if (vis[v] == false)
        topoSort(v, adj, vis, stk);
    }
    stk.push(node);

  }

  public int[] shortestPath(int N, int M, int[][] edges) {
    // Code here
    List<List<Pair>> adj = new ArrayList<>();
    for (int i = 0; i < N; i++)
      adj.add(new ArrayList<Pair>());

    for (int i = 0; i < M; i++) {
      int u = edges[i][0];
      int v = edges[i][1];
      int wt = edges[i][2];
      adj.get(u).add(new Pair(v, wt));
    }

    boolean[] vis = new boolean[N];
    Stack<Integer> stk = new Stack<>();
    for (int i = 0; i < N; i++) {
      if (vis[i] == false)
        topoSort(i, adj, vis, stk);
    }

    int[] dist = new int[N];
    // create a distance array, and initially put the distance from src node to this
    // curr node as infinity
    for (int i = 0; i < N; i++) {
      dist[i] = (int) (1e9); // to indicate that this particular node is not reachable from src node
    }

    // given to start from vertex 0
    // so mark the distance from src to src, i.e., 0
    dist[0] = 0;

    while (!stk.isEmpty()) {
      int node = stk.peek();
      stk.pop();

      for (Pair it : adj.get(node)) {
        int v = it.first;
        int wt = it.second;

        // if distance to reach this adjacent node (through the node above and wt. of
        // the edge)
        // is less than the direct distance to this adj. node, then update the distance
        // to reach
        // this adj node as: wt + dist[node]
        if (dist[node] + wt < dist[v]) {
          dist[v] = wt + dist[node];
        }
      }
    }

    for (int i = 0; i < dist.length; i++) {
      if (dist[i] == (int) (1e9))
        dist[i] = -1;   // acc. ot the problem statement requrirement if there is no path from src to the curr. node
    }

    return dist;
  }
}