// Given elements as nodes of the two singly linked lists. The task is to
// multiply these two linked lists, say L1 and L2.
// Note: The output could be large take modulo 10^9+7.

// Examples :
// Input: LinkedList L1 : 3->2 , LinkedList L2 : 2
// Output: 64

// Input: LinkedList L1: 1->0->0 , LinkedList L2 : 1->0
// Output: 1000

class Node {
  Node next, random;
  int data;

  Node(int val) {
    this.data = val;
    random = next = null;
  }
}

class Solution {

  public long MOD = (int) (1e9 + 7);

  private long findNumber(Node node) {
    Node temp = node;
    long num = 0;
    while (temp != null) {
      num = (num * 10 + temp.data) % MOD;
      temp = temp.next;
    }
    return num;
  }

  public long multiplyTwoLists(Node first, Node second) {
    // Code here
    long n1 = findNumber(first);
    long n2 = findNumber(second);

    return (n1 * n2) % MOD;
  }
}