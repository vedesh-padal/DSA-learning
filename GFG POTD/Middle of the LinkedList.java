class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
  }
}

class Solution {
  int getMiddle(Node head) {
    // Your code here.
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null && slow != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow.data;
  }
}
