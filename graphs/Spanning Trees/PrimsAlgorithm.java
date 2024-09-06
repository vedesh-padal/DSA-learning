import java.util.List;
import java.util.PriorityQueue;

class Pair {
  int node, distance;
  public Pair(int node, int distance) {
    this.node = node;
    this.distance = distance;
  }
}

public class PrimsAlgorithm {
  public static int spanningTree(int V, int E, List<List<int[]>> adj) {
    PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> (x.distance - y.distance));  // min heap
    pq.add(new Pair(0, 0)); // { node, distance }

    boolean[] vis = new boolean[V];
    int sum = 0;

    while (!pq.isEmpty()) {
      int node = pq.peek().node;
      int dist = pq.peek().distance;

      pq.remove();

      if (vis[node])  
        continue;
      
      vis[node] = true;
      sum += dist;

      for (int[] it: adj.get(node)) {
        int adjNode = it[0];
        int edW = it[1];

        if (!vis[adjNode])  {
          pq.add(new Pair(adjNode, edW));
        }
      }
    }
    return sum;
  }
}
