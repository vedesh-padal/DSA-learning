/*
  There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] 
  represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

  Return the city with the smallest number of cities that are reachable through some path and whose
  distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

  Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  // USING FLOYD WARSHALL ALGO SEEMS EFFICIENT
  public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    // first we need to find the shortest distance from each city / node to every
    // other city / node
    // we can either use floyd warshall algo or djikstra's algo (becoz, edge weights
    // are not -ve)

    int[][] dist = new int[n][n];
    for (int[] row : dist)
      Arrays.fill(row, Integer.MAX_VALUE);

    int m = edges.length;
    for (int i = 0; i < m; i++) {
      dist[edges[i][0]][edges[i][1]] = edges[i][2];
      dist[edges[i][1]][edges[i][0]] = edges[i][2];
    }

    for (int i = 0; i < n; i++)
      dist[i][i] = 0;

    // floyd warshall algo:
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE)
            continue;
          dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }

    // now, check for:
    // Return the city with the smallest number of cities that are
    // reachable through some path and whose distance is at most distanceThreshold,
    // If there are multiple such cities, return the city with the greatest number.

    int minCityCount = n; // initially max. number of cities possible at a threshold dist
    int cityNum = -1;

    for (int city = 0; city < n; city++) {
      int cnt = 0;
      for (int adjCity = 0; adjCity < n; adjCity++) {
        if (dist[city][adjCity] <= distanceThreshold)
          cnt++;
      }
      if (cnt <= minCityCount) {
        cityNum = city;
        minCityCount = cnt;
      }
    }
    return cityNum;
  }

  // ---------------------------------------------------------------
  // USING djikstras algo implementation:

  private static void djikstra(int n, List<int[]>[] adj, int[] dist, int src) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]); // [node, dist]
    pq.add(new int[] { src, 0 });

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int u = curr[0];
      int du = curr[1]; // edge weight / distance

      // interesting time saving way
      if (du > dist[u])
        continue;

      for (int[] nb : adj[u]) {
        int v = nb[0];
        int duv = nb[1];
        if (du + duv < dist[v]) {
          dist[v] = du + duv;
          pq.add(new int[] { v, dist[v] });
        }
      }
    }
  }

  public int findTheCity2(int n, int[][] edges, int distanceThreshold) {
    int INF = (int) (1e9 + 7);
    // better if adj list graph is considered, as it will be easier to do in
    // djikstras
    // CRAAAZY WAY TO REPRESENT -- SEEN IN LC SOLUTION
    @SuppressWarnings("unchecked")
    List<int[]>[] adj = new List[n];
    int[][] dist = new int[n][n];

    for (int i = 0; i < n; i++) {
      Arrays.fill(dist[i], INF);
      dist[i][i] = 0; // distance of the source node for djikstra has been adjusted here
    }

    for (int i = 0; i < n; i++)
      adj[i] = new ArrayList<>();

    for (int[] e : edges) {
      int u = e[0];
      int v = e[1];
      int d = e[2];

      adj[u].add(new int[] { v, d });
      adj[v].add(new int[] { u, d });
    }

    // applying djikstras to each node / city
    for (int i = 0; i < n; i++) {
      djikstra(n, adj, dist[i], i); // 'i' being the source node for djikstra
    }

    int minCity = -1;
    int minCount = n;

    for (int city = 0; city < n; city++) {
      int currCount = 0;
      for (int adjCity = 0; adjCity < n; adjCity++) {
        if (city == adjCity)
          continue;
        if (dist[city][adjCity] <= distanceThreshold)
          currCount++;
      }
      if (currCount <= minCount) {
        minCount = currCount;
        minCity = city;
      }
    }
    return minCity;
  }

}