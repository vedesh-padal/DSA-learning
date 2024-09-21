import java.util.HashMap;
import java.util.Map;

class Node {
  Node next, random;
  int data;

  Node(int val) {
    this.data = val;
    random = next = null;
  }
}

class Solution {

  private void insertCopyInBetween(Node head) {
    Node temp = head;
    while (temp != null) {
      Node copy = new Node(temp.data);
      copy.next = temp.next;
      temp.next = copy;
      temp = copy.next; // temp = temp.next.next;
    }
    return;
  }

  private void connectRandomPointers(Node head) {
    Node temp = head;
    while (temp != null) {
      Node copy = temp.next;
      if (temp.random != null) {
        copy.random = temp.random.next; // random.next becoz new node lies there
      } else {
        copy.random = null;
      }
      temp = temp.next.next;
    }
  }

  // removing the links between orig and copy
  private Node getDeepCopyList(Node head) {
    Node dummyNode = new Node(-1);
    Node res = dummyNode;
    Node temp = head;

    while (temp != null) {
      Node copyNode = temp.next;
      res.next = copyNode;
      res = res.next;
      // resetting the link, linking the original node to its next node
      temp.next = temp.next.next;
      temp = temp.next; // now moving forward in original ll
    }
    return dummyNode.next; // deep copy list head node
  }

  // Function to clone a linked list with next and random pointer.
  Node copyListComplex(Node head) {
    // your code here
    if (head == null)
      return null;

    insertCopyInBetween(head);
    connectRandomPointers(head);
    return getDeepCopyList(head);
  }
  // ------------------------------------------------------------------
  // EVEN SIMPLER APPROACH
  Node copyList(Node head) {
    Map<Node, Node> hmap = new HashMap<>();
    hmap.put(null, null);

    Node temp = head;
    while (temp != null) {
      Node copy = new Node(temp.data);
      hmap.put(temp, copy);
      temp = temp.next;
    }

    temp = head;
    while (temp != null) {
      Node copy = hmap.get(temp);
      copy.next = hmap.get(temp.next); // null case is handled because of null, null insertion at the beginning
      copy.random = hmap.get(temp.random);
      temp = temp.next;
    }

    return hmap.get(head);
  }
}
