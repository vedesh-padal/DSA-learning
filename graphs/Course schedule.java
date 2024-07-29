/*
  There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

  For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
  Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

  

  Example 1:

  Input: numCourses = 2, prerequisites = [[1,0]]
  Output: [0,1]
  Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
  Example 2:

  Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
  Output: [0,2,1,3]
  Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
  So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
  Example 3:

  Input: numCourses = 1, prerequisites = []
  Output: [0]
 */


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