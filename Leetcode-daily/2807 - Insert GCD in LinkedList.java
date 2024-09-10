/*
  Given the head of a linked list head, in which each node contains an integer value.
  Between every pair of adjacent nodes, insert a new node with a value
  equal to the greatest common divisor of them. Return the linked list after insertion.
  The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
*/

class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

  private static int findGCD(int a, int b) {
    while (b != 0) {
      int t = b;
      b = a % b;
      a = t;
    }
    return a;
  }

  public ListNode insertGreatestCommonDivisors(ListNode head) {

    if (head.next == null)
      return head;

    ListNode t = head;
    while (t.next != null) {
      int gcd = findGCD(t.val, t.next.val);
      ListNode gcdNode = new ListNode(gcd);
      gcdNode.next = t.next;
      t.next = gcdNode;
      t = gcdNode.next;
    }
    return head;
  }
}