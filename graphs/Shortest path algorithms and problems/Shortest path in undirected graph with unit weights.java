// You are given an Undirected Graph having unit weight of the edges, 
// find the shortest path from src to all the vertex and if it is unreachable to reach any vertex, 
// then return -1 for that vertex.

import java.util.*;

class Solution {
    
  public int[] shortestPath(int[][] edges,int n,int m ,int src) {
      // Code here
      List<List<Integer>> adj = new ArrayList<>();
      for (int i=0; i<n; i++)
          adj.add(new ArrayList<>());
      
      for (int i=0; i<m; i++) {
          adj.get(edges[i][0]).add(edges[i][1]);
          adj.get(edges[i][1]).add(edges[i][0]);
      }
      
      int[] dist = new int[n];
      for (int i=0; i<n; i++)
          dist[i] = (int)(1e9);
      
      Queue<Integer> q = new LinkedList<>();
      dist[src] = 0;
      q.add(src);
      
      while (!q.isEmpty())    {
          int node = q.poll();
          for (int it: adj.get(node)) {
              if (dist[node] + 1 < dist[it])  {
                  dist[it] = 1 + dist[node];
                  q.add(it);
              }
          }
      }
      
      for (int i=0; i<n; i++) {
          if (dist[i] == (int)(1e9))
              dist[i] = -1;
      }
      
      return dist;
  }
}