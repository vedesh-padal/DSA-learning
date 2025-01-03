package graphs;
import java.util.*;

// Topological sort

class Solution {
  // DFS APPROACH
  static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, Stack<Integer> stk) {
    vis[node] = true;

    for (int it : adj.get(node)) {
      if (vis[it] == false)
        dfs(it, adj, vis, stk);
    }
    stk.push(node); // <==== main part
  }

  // Function to return list containing vertices in Topological order.
  static int[] topoSort_usingDFS(int V, ArrayList<ArrayList<Integer>> adj) {
    // add your code here
    boolean[] vis = new boolean[V];
    Stack<Integer> stk = new Stack<>();

    for (int i = 0; i < V; i++) {
      if (vis[i] == false)
        dfs(i, adj, vis, stk);
    }

    int[] ans = new int[V];
    int i = 0;
    while (!stk.isEmpty()) {
      ans[i++] = stk.peek();
      stk.pop();
    }
    return ans;
  }

  // =========================================================
  static int[] topoSort_usingBFS(int V, ArrayList<ArrayList<Integer>> adj) {
    int[] indegree = new int[V];

    for (int i = 0; i < V; i++) {
      for (int it: adj.get(i)) {
        indegree[it]++;
      }
    }

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < V; i++) {
      if (indegree[i] == 0) {
        q.offer(i);
      }
    }

    int j = 0;
    int[] topo = new int[V];

    while (!q.isEmpty()) {
      int node = q.poll();
      topo[j++] = node;

      for (int it: adj.get(node)) {
        indegree[it]--;
        if (indegree[it] == 0) {
          q.offer(it);
        }
      }
    }
    return topo;
  }
}