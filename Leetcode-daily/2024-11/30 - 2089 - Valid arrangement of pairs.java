// HARD
// graphs, eulerian-path, dfs

/*
    You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi].
    An arrangement of pairs is valid if for every index i where 1 <= i <
    pairs.length, we have endi-1 == starti.

    Return any valid arrangement of pairs.

    Note: The inputs will be generated such that there exists a valid arrangement of pairs.

    Example 1:
    Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
    Output: [[11,9],[9,4],[4,5],[5,1]]
    Explanation:
    This is a valid arrangement since endi-1 always equals starti.
    end0 = 9 == 9 = start1
    end1 = 4 == 4 = start2
    end2 = 5 == 5 = start3

    Example 2:
    Input: pairs = [[1,3],[3,2],[2,1]]
    Output: [[1,3],[3,2],[2,1]]
    Explanation:
    This is a valid arrangement since endi-1 always equals starti.
    end0 = 3 == 3 = start1
    end1 = 2 == 2 = start2
    The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.

    Example 3:
    Input: pairs = [[1,2],[1,3],[2,1]]
    Output: [[1,2],[2,1],[1,3]]
    Explanation:
    This is a valid arrangement since endi-1 always equals starti.
    end0 = 2 == 2 = start1
    end1 = 1 == 1 = start2

    Constraints:
    1 <= pairs.length <= 105
    pairs[i].length == 2
    0 <= starti, endi <= 109
    starti != endi
    No two pairs are exactly the same.
    There exists a valid arrangement of pairs.
 */

import java.util.*;

class Solution {
    // basically, Eulerian Path problem - Hierholzer's Algorithm
    // what does the algo. say?
    // 1. build an adjacency list
    // 2. find indegree and outdegree arrays
    // 3. find the start node of Eulerian Path
    //      startNode: is the `node` which satisfies: outdegree[node] - indegree[node] = 1
    //      endNode:   is the `node` which satisfies: indegree[node] - outdegree[node] = 1
    // 4. then, do a DFS from startNode of the Eulerian Path

    public int[][] validArrangement(int[][] pairs) {
        // build adj. list
        Map<Integer, List<Integer>> adj = new HashMap<>();

        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Integer> outdegree = new HashMap<>();

        for (int[] pair: pairs) {
            int u = pair[0];
            int v = pair[1];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);

            indegree.put(v, indegree.getOrDefault(v, 0) + 1);
            outdegree.put(u, outdegree.getOrDefault(u, 0) + 1);
        }

        // find the startNode of the Euler path
        int startNode = pairs[0][0];
        for (int[] pair: pairs) {
            // source node
            int node = pair[0];
            if (outdegree.getOrDefault(node, 0) - indegree.getOrDefault(node, 0) == 1) {
                startNode = pair[0];    // here, pair[0] represents from, i.e., u in u --> v
                break;
            }
        }

        // now, do a DFS from this startNode
        List<Integer> path = new ArrayList<>(); // euler path
        Stack<Integer> stk = new Stack<>();
        stk.push(startNode);

        while (!stk.isEmpty()) {
            int node = stk.peek();
            if (adj.containsKey(node) && !adj.get(node).isEmpty()) {
                int lastEleInd = adj.get(node).size() - 1;
                
                // stk.push(adj.get(node).getLast());  // java 21 or later
                stk.push(adj.get(node).get(lastEleInd));
                // adj.get(node).removeLast(); // java 21 or later

                adj.get(node).remove(lastEleInd);
            }
            else {
                path.add(node);
                stk.pop();
                // or directly path.add(stk.pop());
            }
        }

        // since we have stored the path while popping out from stack, so the stack now has:
        // in reverse order, i.e., dest -----> src
        Collections.reverse(path);

        // now, build the path
        int numNodesInPath = path.size();
        int[][] res = new int[numNodesInPath - 1][2];

        for (int i = 0; i < numNodesInPath - 1; i++) {
            res[i] = new int[]{path.get(i), path.get(i+1)};
        }
        return res;
    }
}