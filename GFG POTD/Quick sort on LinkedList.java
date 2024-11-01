// 29-10-2024
// MEDIUM


class Node {
  int data;
  Node next;

  Node(int key) {
    data = key;
    next = null;
  }

}

class GfG {

  static Node getTail(Node cur) {
    while (cur != null && cur.next != null)
      cur = cur.next;
    return cur;
  }

  // Partitions the list taking the first element as the pivot
  static Node partition(Node head, Node tail) {

    // Select the first node as the pivot node
    Node pivot = head;

    // 'pre' and 'curr' are used to shift all
    // smaller nodes' data to the left side of the pivot node
    Node pre = head;
    Node curr = head;

    // Traverse the list until you reach the node after the tail
    while (curr != tail.next) {

      // If current node's data is less than the pivot's data
      if (curr.data < pivot.data) {

        int temp = curr.data;
        curr.data = pre.next.data;
        pre.next.data = temp;

        pre = pre.next;
      }

      curr = curr.next;
    }

    // Swap the pivot's data with 'pre' data
    int currData = pivot.data;
    pivot.data = pre.data;
    pre.data = currData;

    return pre;
  }

  // Helper function for quick sort
  static void quickSortHelper(Node head, Node tail) {

    // Base case: if the list is empty or consists of a single node
    if (head == null || head == tail) {
      return;
    }

    // Call partition to find the pivot node
    Node pivot = partition(head, tail);

    // Recursive call for the left part of
    // the list (before the pivot)
    quickSortHelper(head, pivot);

    // Recursive call for the right part of
    // the list (after the pivot)
    quickSortHelper(pivot.next, tail);
  }

  // The main function for quick sort.
  // This is a wrapper over quickSortHelper
  static Node quickSort(Node head) {

    // Find the tail of the list
    Node tail = getTail(head);

    // Call the helper function to sort the list
    quickSortHelper(head, tail);
    return head;
  }

}