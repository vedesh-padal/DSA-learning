// 31-10-2024
// EASY - MEDIUM

// Given a sorted doubly linked list and an element x, you need to insert the
// element x into the correct position in the sorted Doubly linked list(DLL).

// Note: The DLL is sorted in ascending order
class Node {
  int data;
  Node prev, next;

  Node(int data) {
    this.data = data;
    this.prev = this.next = null;
  }
}

class Solution {
  public Node sortedInsert(Node head, int x) {
    // add your code here

    // then we have to insert at the first position
    if (head.data > x) {
      Node newNode = new Node(x);
      newNode.next = head;
      head.prev = newNode;
      return newNode;
    }

    Node t = head;
    Node prev = null;

    while (t != null) {
      if (t.data > x) {
        Node newNode = new Node(x);
        t.prev.next = newNode;
        newNode.prev = t.prev;
        newNode.next = t;
        t.prev = newNode;

        return head;
      }
      prev = t;
      t = t.next;
    }

    // that means we were not able to insert, that means
    // it should be inserted at the end
    if (t == null) {
      Node newNode = new Node(x);
      prev.next = newNode;
      newNode.prev = prev;
    }
    return head;
  }
}