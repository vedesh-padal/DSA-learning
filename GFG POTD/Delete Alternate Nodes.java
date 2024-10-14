// 13-10-2024
// easy
// linked-list

class Node {
  int data;
  Node next;

  public Node(int data) {
    this.data = data;
  }
}

class Solution {
  public void deleteAlt(Node head) {
    // Code Here
    if (head == null || head.next == null)
      return;

    Node curr = head;

    while (curr.next != null && curr.next.next != null) {
      curr.next = curr.next.next;
      curr = curr.next;
    }

    if (curr.next != null) {
      curr.next = null;
    }
  }
}