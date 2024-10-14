// 14-10-2024

// DAMN EASY
// linked-list

// Given a singly linked list. The task is to find the length of the linked
// list, where length is defined as the number of nodes in the linked list.

class Node {
  int data;
  Node next;
  Node(int a) {
    data = a;
    next = null;
  }
}

class Solution {
  // Function to count nodes of a linked list.
  public int getCount(Node head) {
    // code here
    Node t = head;
    int cnt = 0;
    while (t != null) {
      t = t.next;
      cnt++;
    }
    return cnt;
  }
}