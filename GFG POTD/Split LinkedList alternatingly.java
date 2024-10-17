// 17-10-2024
// EASY
// linked-list

// Given a singly linked list's head. Your task is to complete the function
// alternatingSplitList() that splits the given linked list into two smaller
// lists. The sublists should be made from alternating elements from the
// original list.

// Note:
// The sublist should be in the order with respect to the original list.
// Your have to return an array containing the both sub-linked lists.

// Example:
// Input: LinkedList = 0->1->0->1->0->1
// Output: 0->0->0 , 1->1->1
// Explanation: After forming two sublists of the given list as required, we
// have two lists as: 0->0->0 and 1->1->1.

class Node {
  Node next;
  int data;

  Node(int val) {
    this.data = val;
    this.next = null;
  }
}

class Solution {
  // Function to append a new node with newData at the end of a linked list
  Node[] alternatingSplitList(Node head) {
    // code here
    Node[] ans = new Node[2];
    ans[0] = null;
    ans[1] = null;

    if (head == null)
      return ans;
    if (head.next == null) {
      ans[0] = head;
      return ans;
    }
    ans[0] = new Node(-1);
    ans[1] = new Node(-1);
    Node t1 = ans[0];
    Node t2 = ans[1];

    Node curr = head;

    while (curr != null) {
      t1.next = curr;
      t1 = t1.next;
      curr = curr.next;

      if (curr != null) {
        t2.next = curr;
        t2 = t2.next;
        curr = curr.next;
      }
    }

    t1.next = null;
    t2.next = null;

    ans[0] = ans[0].next;
    ans[1] = ans[1].next;
    return ans;
  }
}
