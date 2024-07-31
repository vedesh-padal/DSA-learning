// Given a weighted, undirected and connected graph of V vertices and an adjacency list adj where adj[i] is a list of
//  lists containing two integers where the first integer of each list j denotes there is edge between i and j , 
// second integers corresponds to the weight of that  edge . You are given the source vertex S and 
// You to Find the shortest distance of all the vertex's from the source vertex S. 
// You have to return a list of integers denoting shortest distance between each node and Source vertex S.
 

// Note: The Graph doesn't contain any negative weight cycle.

// Single source shortest path to each other node and store it in a distance array and return it

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Pair {
  int distance;
  int node;

  Pair(int first, int second) {
    this.distance = first;
    this.node = second;
  }
}

class Node {
  int value;
  int dist;

  Node(int first, int second) {
    this.value = first;
    this.dist = second;
  }
}

class Solution
{
  // Function to find the shortest distance of all the vertices
  // from the source vertex S.
  static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
    // Write your code here

    //// ===> USE THIS:
    // // Approach - 1: Using Priority Queue (specifically min-heap)
    // PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y) -> x.distance - y.distance);
    // int[] dist = new int[V];
    // for (int i = 0; i < V; i++)
    //   dist[i] = (int) (1e9);

    // dist[S] = 0;
    // pq.add(new Pair(0, S));

    // while (pq.size() != 0) {
    //   // int dis = pq.peek().distance;
    //   int node = pq.peek().node;
    //   pq.remove();

    //   for (int i = 0; i < adj.get(node).size(); i++) {
    //     int edgeWeight = adj.get(node).get(i).get(1);
    //     int adjNode = adj.get(node).get(i).get(0);

    //     // now, compare which distance is least
    //     if (dist[node] + edgeWeight < dist[adjNode]) {
    //       dist[adjNode] = dist[node] + edgeWeight;
    //       pq.add(new Pair(dist[adjNode], adjNode));
    //     }
    //   }
    // }
    // return dist;
        
        
    // using TreeSet - but not working properly
    // TreeSet<Pair> set = new TreeSet<>((x, y) -> (x.distance - y.distance));
    // int[] dist = new int[V];
    // for (int i=0; i<V; i++) 
    //     dist[i] = (int)(1e9);
        
    // dist[S] = 0;
    // set.add(new Pair(0, S));
    
    // while (set.size() != 0)  {
    //     Pair pair = set.pollFirst();
        
    //     for (ArrayList<Integer> adjNodes: adj.get(pair.node))    {
    //         int adjNode = adjNodes.get(0);
    //         int edgeWeight = adjNodes.get(1);
            
    //         // now, compare which distance is least
    //         if (dist[pair.node] + edgeWeight < dist[adjNode])    {
    //             dist[adjNode] = dist[pair.node] + edgeWeight;
    //             set.add(new Pair(dist[adjNode], adjNode));
    //         }
    //     }
    // }
    // return dist;

    TreeSet<Node> set = new TreeSet<>((node1, node2) -> {
      if (node1.value != node2.value && node1.dist == node2.dist) {
        return 1;
      }
      return node1.dist - node2.dist;
    });
    set.add(new Node(S, 0));
    int[] dist = new int[V];

    for (int i = 0; i < V; i++) {
      dist[i] = (int) 1e9;
    }
    dist[S] = 0;

    while (!set.isEmpty()) {
      Node node = set.pollFirst();

      for (ArrayList<Integer> adjNodes : adj.get(node.value)) {
        int adjNode = adjNodes.get(0);
        int adjNodeDist = adjNodes.get(1);

        if (dist[node.value] + adjNodeDist < dist[adjNode]) {
          set.add(new Node(adjNode, dist[node.value] + adjNodeDist));
          dist[adjNode] = dist[node.value] + adjNodeDist;
        }

      }
    }

    return dist;
  }
}
