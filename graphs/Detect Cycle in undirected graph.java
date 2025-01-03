package graphs;

import java.util.*;

class Solution {
    
    private boolean dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[node] = true;
        
        for (int neighbor: adj.get(node)) {
            if (vis[neighbor] == false) {
                if (dfs(neighbor, node, adj, vis) == true) {
                    return true;
                }
            }
            else if (parent != neighbor) {
                return true;    // there exists a cycle
            }
        }
        return false;
    }
    
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        Queue<int[]> q = new LinkedList<>();
        int v = adj.size();
        boolean[] vis = new boolean[v];
        
        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                if (dfs(i, -1, adj, vis)) {
                    return true;
                }
            }
        }
        return false;
    }
}