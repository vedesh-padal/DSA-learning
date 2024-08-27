// Apply Dijkstra's algo, by taking MAX HEAP ( to first consider the node with high probability when removing from Priority Queue )

package graphs;
import java.util.*;

class Pair {
  int first;
  double second;

  Pair(int first, double second) {
    this.first = first;
    this.second = second;
  }
}

class Solution {
  public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
    List<Pair>[] adj = new ArrayList[n];
    for (int i = 0; i < n; i++)
      adj[i] = new ArrayList<>();

    for (int i = 0; i < edges.length; i++) {
      adj[edges[i][0]].add(new Pair(edges[i][1], succProb[i]));
      adj[edges[i][1]].add(new Pair(edges[i][0], succProb[i]));
    }

    double[] prob = new double[n];
    Arrays.fill(prob, 0);
    prob[start_node] = 1.0; // start node probability is 1

    // max Heap
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.second, a.second));
    pq.add(new Pair(start_node, 1.0));

    while (!pq.isEmpty()) {

      Pair current = pq.poll(); // get node with max. probability
      int u = current.first;
      double currProb = current.second;

      // if we reached the end node, return the probability
      if (u == end_node)
        return currProb;

      // explore neighbours
      for (Pair neighbour : adj[u]) {
        int v = neighbour.first;
        double edgeProb = neighbour.second;

        // calculate new probability to reach neighbour
        double newProb = currProb * edgeProb;

        // if new probability is greater than previously recorded probability to reach
        // this node
        if (newProb > prob[v]) {
          prob[v] = newProb;
          pq.add(new Pair(v, newProb));
        }
      }

    }

    return 0.0; // if we reach here, there is no path from start to end node

  }
}