/*
  There are n servers numbered from 0 to n - 1 connected by undirected server-to-server 
  connections forming a network where connections[i] = [ai, bi] represents a connection 
  between servers ai and bi. Any server can reach other servers directly or indirectly 
  through the network.

  A critical connection is a connection that, if removed, will make some servers 
  unable to reach some other server.

  Return all critical connections in the network in any order.

  Example 1:
  Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
  Output: [[1,3]]
  Explanation: [[3,1]] is also accepted.
  
  Example 2:
  Input: n = 2, connections = [[0,1]]
  Output: [[0,1]]

  Constraints:
  2 <= n <= 105
  n - 1 <= connections.length <= 105
  0 <= ai, bi <= n - 1
  ai != bi
  There are no repeated connections.
*/
package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Solution {
	private int timer = 1; // 1 becoz, when you are calling dfs, that means one node / connection is
													// present

	private void dfs(int node, int parent, boolean[] vis, int[] tin,
			int[] low, List<List<Integer>> adj, List<List<Integer>> bridges) {
		vis[node] = true;
		tin[node] = low[node] = timer;
		timer++;

		for (int it : adj.get(node)) {
			if (it == parent)
				continue;
			if (vis[it] == false) {
				dfs(it, node, vis, tin, low, adj, bridges);
				// after a particular adj node is done with it's dfs,
				// check if the adj node's / child's low val is less than the curr node,
				// if so, update the curr node's low val
				low[node] = Math.min(low[node], low[it]);

				// then check if the edge between these nodes can form a bridge
				// node ------ it
				if (low[it] > tin[node]) {
					bridges.add(Arrays.asList(it, node));
				}
			} else {
				low[node] = Math.min(low[node], low[it]);
			}
		}

	}

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());

		for (List<Integer> it : connections) {
			int u = it.get(0);
			int v = it.get(1);
			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		boolean[] vis = new boolean[n];
		int[] tin = new int[n]; // DFS time of insertion
		int[] low = new int[n]; // min. lowest time of insertion of all adjacent nodes apart from parent

		List<List<Integer>> bridges = new ArrayList<>();
		dfs(0, -1, vis, tin, low, adj, bridges);
		return bridges;
	}
}