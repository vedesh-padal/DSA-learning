package graphs;

// Pepcoding
// Eulerian Path:
// Starting from any node, can you visit all EDGES in the graph exactly one time?
// you can visit same vertex more than once also -> no constraint on vertices

// Eulerian Circuit:
// you should reach the starting vertex at the end
// start and end vertex should be same

// ==> eulerian circuit => eulerian path -> present

// FOR UNDIRECTED GRAPH:
//  EC: degree of all nodes should be even
//  EP: (n-2) nodes degree should be even => 2 nodes can be odd

// FOR DIRECTED GRAPH:
// EC: indegree == outdegree
// EP: (n-2) nodes should have indegree == outdegree || 
//      other two nodes two cases:
//        1. indegree = outdegree + 1
//        2. outdegree = indegree + 1

// also revised from codeStoryWithMik - 30-11-2024

import java.util.*;

class Solution{
    
    private void DFS(int u, List<Integer>[] adj, boolean[] vis) {
        vis[u] = true;
        
        for (int neighbor: adj[u]) {
            if (vis[neighbor] == false) {
                DFS(neighbor, adj, vis);
            }
        }
    }
    
    
    private boolean isConnected(int V, List<Integer>[] adj) {
        
        int nonZeroDegreeVert = -1;
        
        for (int i = 0; i < adj.length; i++) {
            // non-zero degree vertex
            if (adj[i].size() != 0) {
                nonZeroDegreeVert = i;
                break;
            }
        }
        
        // if there was no non-zero degree vertex, that means
        // all nodes are single ones, then we can directly return true from here only
        // BUT, THERE WILL BE DEFINITELY A NON-ZERO VERTEX, SO NOT NEEDED
        // just have put that info here
        
        boolean[] vis = new boolean[V];
        
        // now, DFS from this non-zero vertex
        DFS(nonZeroDegreeVert, adj, vis);
        
        // check if all non-zero vertices are visited, if not we return that 
        // the graph is not connected => EP, EC not possible
        for (int i = 0; i < V; i++) {
            if (vis[i] == false && adj[i].size() > 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public int isEulerCircuit(int V, List<Integer>[] adj) {
        // case when there are components in the graph
        if (isConnected(V, adj) == false)
            return 0;
            
        // now, check the degrees
        int oddDegCnt = 0;
        for (int i = 0; i < V; i++) {
            if ((adj[i].size() & 1) == 1) {
                oddDegCnt++;
            }
        }
        
        if (oddDegCnt > 2)
            return 0;   // NONE
            
        if (oddDegCnt == 2)
            return 1;
            
        return 2;
        
    }
}