// 23-10-2024
// EASY
// linked-list

class Node {
  int data;
  Node next;

  public Node(int data) {
    this.data = data;
    this.next = null;
  }
}

class Solution {

  // Return the sum of last k nodes
  public int sumOfLastN_Nodes(Node head, int n) {
    // write code here
    int totalSum = 0;

    Node one = head;

    while (one != null && n > 0) {
      totalSum += one.data;
      one = one.next;
      n--;
    }

    Node two = head;

    int firstLen_NSum = 0;

    while (one != null) {
      firstLen_NSum += two.data;
      totalSum += one.data;
      two = two.next;
      one = one.next;
    }

    return (totalSum - firstLen_NSum);
  }
}