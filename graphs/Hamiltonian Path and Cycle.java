package graphs;

// Pepcoding
// Hamiltonian Path: visit all nodes in the graph only once 
// (connecting all nodes in a graph without lifting pencil)
// If after going through entire path, are u able to reach the 
// starting vertex? => Hamiltonian Cycle

// this approach is specific to printing path
// if we found a hamiltonian path, we append the path with a .
// else if we found a hamiltonian cycle, we append the path with a *

import java.util.*;
import java.io.*;

class Hamiltonian {
  static class Edge {
    int src, nbr, wt;
    Edge(int src, int nbr, int wt)  {
      this.src = src;
      this.nbr = nbr;
      this.wt = wt;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int vtces = Integer.parseInt(br.readLine());
    ArrayList<Edge>[] graph = new ArrayList[vtces];

    for (int i = 0; i < vtces; i++)
      graph[i] = new ArrayList<>();

    int edges = Integer.parseInt(br.readLine());

    for (int i = 0; i < edges; i++)  {
      String[] parts = br.readLine().split(" ");
      int v1 = Integer.parseInt(parts[0]);
      int v2 = Integer.parseInt(parts[1]);
      int wt = Integer.parseInt(parts[2]);

      graph[v1].add(new Edge(v1, v2, wt));
      graph[v2].add(new Edge(v2, v1, wt));

    }

    int src = Integer.parseInt(br.readLine());

    // using hashset to avoid linear time complexity 
    // in checking if all nodes are visited
    HashSet<Integer> visited = new HashSet<>();
    hamiltonian(graph, src, visited, src + "", src);
  }

  // psf => path so far, osrc => original src
  private static void hamiltonian(ArrayList<Edge>[] graph, int src, 
                                  HashSet<Integer> visited, String psf, int osrc) {
    
    if (visited.size() == graph.length - 1) {
      System.out.print(psf);

      boolean closingEdgeFound = false;
      for (Edge e: graph[src])  {
        if (e.nbr == osrc)  {
          closingEdgeFound = true;
          break;
        }
        if (closingEdgeFound)
          System.out.println("*");
        else
          System.out.println(".");
      }
    }
    
    visited.add(src);
    for (Edge e: graph[src])  {
      if (visited.contains(e.nbr) == false) {
        hamiltonian(graph, e.nbr, visited, psf + e.nbr, osrc);
      }
    }
    visited.remove(src);  // backtracking
  }
}

