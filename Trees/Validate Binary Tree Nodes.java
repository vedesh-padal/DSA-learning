// MEDIUM - HARD
// trees, dfs, bfs, union-find, graph

// You have n binary tree nodes numbered from 0 to n - 1 where node i has two
// children leftChild[i] and rightChild[i], return true if and only if all the
// given nodes form exactly one valid binary tree.

// If node i has no left child then leftChild[i] will equal -1, similarly for
// the right child.

// Note that the nodes have no values and that we only use the node numbers in
// this problem.

// Example 1:
// Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
// Output: true

// Example 2:
// Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
// Output: false

// Example 3:
// Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
// Output: false

// Constraints:
// n == leftChild.length == rightChild.length
// 1 <= n <= 104
// -1 <= leftChild[i], rightChild[i] <= n - 1

package trees;
import java.util.*;

class Solution {
  public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
    // parent to Children
    Map<Integer, List<Integer>> pToC = new HashMap<>();
    // child to parent
    Map<Integer, Integer> cToP = new HashMap<>();

    for (int i = 0; i < n; i++) {
      int node = i;
      int leftC = leftChild[i];
      int rightC = rightChild[i];

      if (leftC != -1) {
        // link with child
        pToC.computeIfAbsent(node, k -> new ArrayList<>()).add(leftC);

        // if the parent of the child is already marked, then binary tree property not
        // satisfied
        if (cToP.get(leftC) != null)
          return false;
        else
          cToP.put(leftC, node);
      }

      if (rightC != -1) {
        // link with child
        pToC.computeIfAbsent(node, k -> new ArrayList<>()).add(rightC);

        // if the parent of the child is already marked, then binary tree property not
        // satisfied
        if (cToP.get(rightC) != null)
          return false;
        else
          cToP.put(rightC, node);
      }
    }

    // now we have linked the children and formed like a graph
    // and also know who is the parent of each node
    int root = -1;
    // we now find the root of the tree
    // if a node doesn't have it's parent, that is our root
    for (int i = 0; i < n; i++) {
      if (!cToP.containsKey(i)) {
        // if root is already marked
        // that means we have another tree
        // disjoint / two components, invalid binary tree, so false
        if (root != -1)
          return false;
        else
          root = i;
      }
    }

    if (root == -1)
      return false;

    // there can be cases where you find that only one node is missing from
    // child_to_parent map
    // which you will assume as root but that root will belong to a separate
    // component.
    // For example :
    // 4
    // [1,0,3,-1]
    // [-1,-1,-1,-1]

    // now we traverse the complete tree using BFS
    // to check if we have all the nodes in our tree
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[n];
    q.add(root);
    visited[root] = true;
    int cnt = 1;

    while (!q.isEmpty()) {
      int node = q.poll();

      if (pToC.containsKey(node)) {
        for (int child : pToC.get(node)) {
          if (visited[child] == false) {
            visited[child] = true;
            cnt++;
            q.add(child);
          }
        }
      }
    }

    return cnt == n;

  }
}