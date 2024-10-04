// Given a Circular Linked List. The task is to delete the given node, key in
// the circular linked list, and reverse the circular linked list.

// Note: You don't have to print anything, just return head of the modified list
// in each function.

class Node {
  int data;
  Node next;

  Node(int d) {
    data = d;
    next = null;
  }
}

class Solution {
  // Function to reverse a circular linked list
  Node reverse(Node head) {
    // code here

    // finding the last node in the CLL
    Node temp = head;
    while (temp.next != head) {
      temp = temp.next;
    }
    Node lastNode = temp;

    Node prev = lastNode;
    Node curr = head;

    while (curr != lastNode) {
      Node next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    curr.next = prev;

    return curr;
  }

  // Function to delete a node from the circular linked list
  Node deleteNode(Node head, int key) {
    // code here

    if (head == null)
      return null;

    if (head.data == key) {
      Node prevHead = head;

      // new Head
      head = head.next;

      Node temp = head;
      while (temp.next != prevHead) {
        temp = temp.next;
      }
      temp.next = head;

      return head;
    }

    boolean notFound = false;
    Node temp = head;
    while (temp.next.data != key) {
      temp = temp.next;
      if (temp == head) {
        notFound = true;
        break;
      }
    }
    if (!notFound) {
      Node next = temp.next.next;
      temp.next = next;
    }

    return head;
  }
}