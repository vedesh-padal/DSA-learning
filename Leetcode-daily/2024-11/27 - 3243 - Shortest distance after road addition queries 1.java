// MEDIUM
// graphs

// You are given an integer n and a 2D integer array queries.

// There are n cities numbered from 0 to n - 1. Initially, there is a
// unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.

// queries[i] = [ui, vi] represents the addition of a new unidirectional road
// from city ui to city vi. After each query, you need to find the length of the
// shortest path from city 0 to city n - 1.

// Return an array answer where for each i in the range [0, queries.length - 1],
// answer[i] is the length of the shortest path from city 0 to city n - 1 after
// processing the first i + 1 queries.

// Examples:
// Input: n = 5, queries = [[2,4],[0,2],[0,4]]
// Output: [3,2,1]

// Input: n = 4, queries = [[0,3],[0,2]]
// Output: [1,1]

// Constraints:
// 3 <= n <= 500
// 1 <= queries.length <= 500
// queries[i].length == 2
// 0 <= queries[i][0] < queries[i][1] < n
// 1 < queries[i][1] - queries[i][0]
// There are no repeated roads among the queries.

import java.util.*;

class Solution {

    private int bfs(int n, Map<Integer, List<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        boolean[] vis = new boolean[n];
        vis[0] = true;

        int steps = 0; // level -> becoz this guarantees the shortest path

        while (!q.isEmpty()) {
            int sz = q.size();

            for (int i = 0; i < sz; i++) {
                int city = q.poll();
                if (city == n - 1) {
                    return steps;
                }

                for (int neighbor : adj.get(city)) {
                    if (!vis[neighbor]) {
                        q.offer(neighbor);
                        vis[neighbor] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // adjancency graph
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            adj.put(i, new ArrayList<>());
            adj.get(i).add(i + 1);
        }

        int[] res = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            // after adding each query, perform BFS (level wise type of BFS)
            adj.get(queries[i][0]).add(queries[i][1]);

            res[i] = bfs(n, adj);

        }
        return res;
    }
}