// Given two singly linked lists, return the point where two linked lists intersect.

// Note: If the linked lists do not merge at any point, return -1.

import java.util.HashSet;

class Node {
  int data;
  Node next;

  Node(int d) {
    data = d;
    next = null;
  }
}

class Intersect {
  // Function to find intersection point in Y shaped Linked Lists.
  int intersectPoint_withSpace(Node head1, Node head2) {
    // code here
    // with using extra space
    HashSet<Node> hset = new HashSet<>();
    Node t = head1;
    while (t != null) {
      hset.add(t);
      t = t.next;
    }

    t = head2;
    while (t != null) {
      if (hset.contains(t)) {
        return t.data;
      }
      hset.add(t);
      t = t.next;
    }
    return -1;
  }

  // ==============================
  // Approach - 2 O(m+n) TC, O(1) SC

  private int findLength(Node head) {
    Node curr = head;
    int len = 0;
    while (curr != null) {
      len++;
      curr = curr.next;
    }
    return len;
  }

  private int getIntersection(int d, Node head1, Node head2) {
    Node curr1 = head1;
    Node curr2 = head2;

    for (int i = 0; i < d; i++) {
      if (curr1 == null)
        return -1;
      curr1 = curr1.next;
    }

    while (curr1 != null && curr2 != null) {
      if (curr1 == curr2) {
        return curr1.data;
      }
      curr1 = curr1.next;
      curr2 = curr2.next;
    }

    return -1;
  }

  int intersectPoint_optimal_1(Node head1, Node head2) {

    if (head1 == null || head2 == null)
      return -1;

    int len1 = findLength(head1);
    int len2 = findLength(head2);

    int inter = -1001;

    int d = len1 - len2;
    if (d < 0) {
      inter = getIntersection(Math.abs(d), head2, head1);
    } else {
      inter = getIntersection(d, head1, head2);
    }
    return inter == -10001 ? -1 : inter;
  }

  // =============================================
  // BEST APPROACH - 2 POINTERS

  int intersectPoint(Node head1, Node head2) {

    if (head1 == null || head2 == null)
      return -1;

    Node curr1 = head1;
    Node curr2 = head2;

    while (curr1 != curr2) {
      curr1 = curr1 != null ? curr1.next : head2;
      curr2 = curr2 != null ? curr2.next : head1;
    }

    return curr1.data;

  }

}
