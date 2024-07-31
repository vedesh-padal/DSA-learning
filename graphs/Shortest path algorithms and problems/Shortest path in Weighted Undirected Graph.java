// SHORTEST PATH IN WEIGHTED UNDIRECTED GRAPH

/*
  You are given a weighted undirected graph having n vertices numbered from 1 to n and m edges along with their weights. Find the shortest weight path between the vertex 1 and the vertex n,  if there exists a path, and return a list of integers whose first element is the weight of the path, and the rest consist of the nodes on that path. If no path exists, then return a list containing a single element -1.

  The input list of edges is as follows - {a, b, w}, denoting there is an edge between a and b, and w is the weight of that edge.

  Note: The driver code here will first check if the weight of the path returned is equal to the sum of the weights along the nodes on that path, if equal it will output the weight of the path, else -2. In case the list contains only a single element (-1) it will simply output -1. 

  Examples :

  Input: n = 5, m= 6, edges = [[1, 2, 2], [2, 5, 5], [2, 3, 4], [1, 4, 1], [4, 3, 3], [3, 5, 1]]
  Output: 5
  Explanation: Shortest path from 1 to n is by the path 1 4 3 5 whose weight is 5. 

  Input: n = 2, m= 1, edges = [[1, 2, 2]]
  Output: 2
  Explanation: Shortest path from 1 to 2 is by the path 1 2 whose weight is 2. 

  Input: n = 2, m= 0, edges = [ ]
  Output: -1
  Explanation: Since there are no edges, so no answer is possible.
  
  Expected Time Complexity: O(m* log(n))
  Expected Space Complexity: O(n+m)
*/


import java.util.*;

class Pair {
  int first, second;

  Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
}

class Solution {
  public List<Integer> shortestPath(int n, int m, int edges[][]) {
    // Code Here.
    // first, form the graph with adjacency list
    List<List<Pair>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) // i <= n becoz, 1-based indexing
      adj.add(new ArrayList<>());

    for (int i = 0; i < m; i++) {
      adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
      adj.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2]));
      // because this is an undirected graph, so two-way weights to be added
    }

    // forming a minimum heap.
    PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> (x.first - y.first));
    int[] dist = new int[n + 1];
    int[] parent = new int[n + 1]; // coz, 1-based indexing
    for (int i = 1; i <= n; i++) {
      dist[i] = (int) (1e9);
      parent[i] = i;
    }

    dist[1] = 0; // coz, 1 is the starting node acc. to question
    pq.add(new Pair(0, 1));
    while (pq.size() != 0) {
      int dis = pq.peek().first;
      int node = pq.peek().second;
      pq.remove();

      for (Pair iter : adj.get(node)) {
        int adjNode = iter.first;
        int edW = iter.second;

        if (dis + edW < dist[adjNode]) {
          dist[adjNode] = dis + edW;
          pq.add(new Pair(dist[adjNode], adjNode));
          parent[adjNode] = node;
        }
      }
    }

    List<Integer> path = new ArrayList<>();
    if (dist[n] == 1e9) {
      path.add(-1); // becoz, you didn't reach the destinationNode, which is node 'n'
      return path;
    }

    int node = n;
    while (parent[node] != node) {
      path.add(node);
      node = parent[node];
    }

    path.add(1); // becoz that while condition is satisfied and that means we are now at src
                 // node, and that is yet to be added in the path arraylist

    // now, we have to reverse the arraylist to actually get the path starting from
    // the src node
    Collections.reverse(path);

    // as mentioned in the problem, the first element should be the path cost to
    // reach destination
    // from source, and then the path from src to dest
    path.add(0, dist[n]);
    
    /////// to verify the result that would be printed
    // StringBuilder sb = new StringBuilder();
    // for (int i = 0; i < path.size(); i++) {
    // sb.append(path.get(i)); // Append Integer
    // if (i < path.size() - 1) {
    // sb.append(" "); // Add space between elements
    // }
    // }
    // String result = sb.toString();
    // System.out.println(result);

    ////// to verify the result that would be printed -- using stream and new java
    ////// version
    // String res = path.stream()
    // .map(String::valueOf) // Convert Integer to String
    // .collect(Collectors.joining(" ")); // Join with space
    // System.out.println(res);

    return path;
  }
}

