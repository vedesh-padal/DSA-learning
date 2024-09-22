package graphs;

import java.util.*;

class Node {
  public int val;
  public List<Node> neighbors;

  public Node() {
    val = 0;
    neighbors = new ArrayList<Node>();
  }

  public Node(int _val) {
    val = _val;
    neighbors = new ArrayList<Node>();
  }

  public Node(int _val, ArrayList<Node> _neighbors) {
    val = _val;
    neighbors = _neighbors;
  }
}

class Solution {

  private Node clone(Node node, Map<Node, Node> hmap) {
    if (hmap.containsKey(node))
      return hmap.get(node);

    Node copyNode = new Node(node.val);
    hmap.put(node, copyNode);
    for (Node nei : node.neighbors) {
      copyNode.neighbors.add(clone(nei, hmap));
    }
    return copyNode;
  }

  public Node cloneGraph(Node node) {
    if (node == null)
      return null;
    Map<Node, Node> hmap = new HashMap<>();
    return clone(node, hmap);
  }
}