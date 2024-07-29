package graphs;

import java.util.*;


class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    // construct the graph
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numCourses; i++)
      adj.add(new ArrayList<>());

    for (int i = 0; i < prerequisites.length; i++) {
      adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
    }

    // find the indegree of each node
    int[] indegree = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      for (int it : adj.get(i)) {
        indegree[it]++;
      }
    }

    // add the nodes that have indegree 0 to the Queue [ they are safe node -> terminal nodes ]
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0)
        q.add(i);
    }

    // array of topological sort elements
    int[] topo = new int[numCourses];
    int i = 0;  // index to keep track of num of topo sort elements

    while (!q.isEmpty()) {
      int node = q.poll();
      topo[i++] = node;

      for (int it : adj.get(node)) {
        indegree[it]--;

        if (indegree[it] == 0)
          q.add(it);
      }
    }

    // if there is no cyclic dependency or all courses can be taken and completed, then return the toposort, else return []
    return (i == numCourses) ? topo : new int[] {};

  }
}