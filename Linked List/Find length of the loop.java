class Node {
  int data;
  Node next;

  Node(int d) {
    data = d;
    next = null;
  }
}

// Function should return the length of the loop in LL.

class Solution {
  // Function to find the length of a loop in the linked list.
  public int countNodesinLoop(Node head) {
    // Add your code here.
    Node slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        fast = fast.next;
        int cnt = 1;
        while (slow != fast) {
          fast = fast.next;
          cnt++;
        }
        return cnt;
      }
    }
    return 0;
  }
}