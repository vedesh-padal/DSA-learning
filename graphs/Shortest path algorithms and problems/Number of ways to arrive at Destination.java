/*
  You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads 
  between some intersections. The inputs are generated such that you can reach any intersection from any 
  other intersection and that there is at most one road between any two intersections.

  You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that 
  there is a road between intersections ui and vi that takes timei minutes to travel. You want to know
  in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

  Return the number of ways you can arrive at your destination in the shortest amount of time. 
  Since the answer may be large, return it modulo 109 + 7.

  Example 1:
  Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
  Output: 4
  Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
  The four ways to get there in 7 minutes are:
  - 0 ➝ 6
  - 0 ➝ 4 ➝ 6
  - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
  - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6

  Example 2:
  Input: n = 2, roads = [[1,0,10]]
  Output: 1
  Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.

  Constraints:
  1 <= n <= 200
  n - 1 <= roads.length <= n * (n - 1) / 2
  roads[i].length == 3
  0 <= ui, vi <= n - 1
  1 <= timei <= 109
  ui != vi
  There is at most one road connecting any two intersections.
  You can reach any intersection from any other intersection.
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
  public int countPaths(int n, int[][] roads) {
      // first we will create the graph with adj list
      List<List<Pair>> adj = new ArrayList<>();
      // n is number of intersections
      for (int i=0; i<n; i++)
          adj.add(new ArrayList<>());
      
      int m = roads.length;
      for (int i=0; i<m; i++) {
          adj.get(roads[i][0]).add(new Pair(roads[i][1], roads[i][2]));
          adj.get(roads[i][1]).add(new Pair(roads[i][0], roads[i][2])); // since told they are bi-directional roads with intersection
      }

      PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> (x.first - y.first));    // min heap, based on distance (rather here, time / cost)
      long[] dist = new long[n];    // basically time, but naming it as dist
      long[] ways = new long[n];
      Arrays.fill(dist, (long)(1e18));
      Arrays.fill(ways, 0);

      // starting from 0th intersection to (n-1)th intersection
      dist[0] = 0;
      ways[0] = 1;    // there is only one way in which you can reach the starting node
      long mod = (long)(1e9 + 7);
      pq.add(new Pair(0, 0));

      while (!pq.isEmpty())   {
          long dis = (long)pq.peek().first;
          int node = pq.peek().second;
          pq.remove();

          for (Pair it: adj.get(node)) {
              int adjNode = it.first;
              int edW = it.second;

              if (dis + edW < dist[adjNode])   {
                  dist[adjNode] = dis + edW;
                  pq.add(new Pair((int)(dis + edW), adjNode));
                  ways[adjNode] = ways[node]; // ways of the adj node is same was from where it is being reached
                  // becoz, distance to reach here from it's predecessor is minimum compared to what was already present
              }
              else if (dis + edW == dist[adjNode])   {
                  ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                  // we are adding becoz, it has the same min. distance when reached from other path, so this way of reaching should
                  // also be added to the adjNode
              }
          }
      }
      return (int)(ways[n-1] % mod); // number of ways to reach the destination in the shortest amount of time
      // here dist is nothing but time, and since the number of paths can be many we are returning modulo with some num
  }
}