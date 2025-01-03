// HARD
// trees, graph, dfs, bfs

/*
    There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. 

    You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] 
    indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that 
    there is an edge between nodes ui and vi in the second tree.

    You must connect one node from the first tree with another node from the second tree with an edge.

    Return the minimum possible diameter of the resulting tree.

    The diameter of a tree is the length of the longest path between any two nodes in the tree.

    Example 1:
    Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
    Output: 3
    Explanation:
    We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.

    Example 2:
    Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
    Output: 5
    Explanation:
    We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.

    Constraints:
    1 <= n, m <= 105
    edges1.length == n - 1
    edges2.length == m - 1
    edges1[i].length == edges2[i].length == 2
    edges1[i] = [ai, bi]
    0 <= ai, bi < n
    edges2[i] = [ui, vi]
    0 <= ui, vi < m
    The input is generated such that edges1 and edges2 represent valid trees.
*/

import java.util.*;

class Solution {

    private Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        return graph;
    }

    private List<Integer> BFS(Map<Integer, List<Integer>> graph, int start) {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Boolean> vis = new HashMap<>();
        
        q.offer(start);
        vis.put(start, true);

        int maxDistance = 0;   // levels
        int farthestNode = start;   // sourceNode

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                farthestNode = curr;

                for (int neighbor: graph.getOrDefault(curr, new ArrayList<>())) {
                    if (!vis.getOrDefault(neighbor, false)) {
                        q.offer(neighbor);
                        vis.put(neighbor, true);
                    }
                }
            }
            // i was missing this - note:
            if (!q.isEmpty())
                maxDistance++;
        }

        return Arrays.asList(farthestNode, maxDistance);
    }

    private int findDiameter(Map<Integer, List<Integer>> graph) {
        // 1st BFS, to find the farthest node from any arbitrary node (ex: 0)
        
        // list becoz, we get: [farthestNode, distance]
        List<Integer> farthestNode = BFS(graph, 0);

        // 2nd BFS, from the farthest node to determine the other end of the diameter
        farthestNode = BFS(graph, farthestNode.get(0));

        return farthestNode.get(1);
    }

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        // graphs - represented as Adjacency Lists
        Map<Integer, List<Integer>> graph1 = buildGraph(edges1);
        Map<Integer, List<Integer>> graph2 = buildGraph(edges2);

        int d1 = findDiameter(graph1);
        int d2 = findDiameter(graph2);

        // when both graphs are connected the diameter would be
        // NOTE:
        // DIAMETER: The no. of edges in a longest path in that tree / graph
        // from any node to any other node
        
        // we take the ceil value after dividing by 2, so that from each graph we are picking the farthest node
        // so that in the resulted combined graph, we will be able to have correct diameter
        
        int combined = (d1 + 1) / 2 + (d2 + 1) / 2 + 1; // extra 1 becoz of the connection edge

        return Math.max(combined, Math.max(d1, d2));
    }




    // TRYING WITH TOPO SORT THING, WILL DO LATER
    /*
    Map<Integer, List<Integer>> graph1, graph2;

    private List<Integer> findMinHeightRoots(int[][] edges, boolean first) {
        int n = edges.length;
        int[] indeg = new int[n];
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            indeg[edge[0]]++;
            indeg[edge[1]]++;
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        if (first)
            graph1 = graph;
        else
            graph2 = graph;

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 1) {
                q.offer(i);
            }
        }

        int processed = 0;

        while (n - processed > 2) {
            int sz = q.size();
            processed += sz;
            for (int i = 0; i < sz; i++) {
                int curr = q.poll();
                for (int neighbor: graph.get(curr)) {
                    if (--indeg[neighbor] == 1) {
                        q.offer(neighbor);
                    }
                }
            }
        }
        
        List<Integer> roots = new ArrayList<>();
        roots.addAll(q);
        return roots;
    }


    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        List<Integer> roots1 = findMinHeightRoots(edges1, true);  // there can be either 1 or 2 roots
        List<Integer> roots2 = findMinHeightRoots(edges2, false);

        int root1 = roots1.get(0), root2 = roots2.get(0);

        // now we join them
        graph1.computeIfAbsent(root1, k -> new ArrayList<>()).add(root2);
        graph2.computeIfAbsent(root1, k -> new ArrayList<>()).add(root2);

    }

    */
}