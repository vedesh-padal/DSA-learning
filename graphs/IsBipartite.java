package graphs;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

  private boolean checkUsingBFS(int node, int V, int[] color, int[][] graph) {
    Queue<Integer> q = new LinkedList<>();
    color[node] = 0;
    q.add(node);

    while (!q.isEmpty()) {
      int pnode = q.peek();
      q.remove();

      for (int it : graph[pnode]) {
        if (color[it] == -1) {
          // if unmarked, mark it with opposite color to it's adjacent node
          color[it] = 1 - color[pnode];
          q.add(it);
        } else if (color[it] == color[pnode]) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isBipartite(int[][] graph) {
    int V = graph.length;
    int[] color = new int[V];
    for (int i = 0; i < V; i++)
      color[i] = -1;

    for (int i = 0; i < V; i++) {
      if (color[i] == -1) {
        if (checkUsingBFS(i, V, color, graph) == false)
          return false;
      }
    }
    return true;
  }
}