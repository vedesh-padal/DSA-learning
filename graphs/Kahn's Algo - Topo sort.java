package graphs;
import java.util.*;

// Topological sort

class Solution {
  // BFS APPROACH
  static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, Stack<Integer> stk) {
    vis[node] = true;

    for (int it : adj.get(node)) {
      if (vis[it] == false)
        dfs(it, adj, vis, stk);
    }
    stk.push(node);
  }

  // Function to return list containing vertices in Topological order.
  static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
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

}