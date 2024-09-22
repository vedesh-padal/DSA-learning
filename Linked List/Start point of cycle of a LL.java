class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  public ListNode detectCycle(ListNode head) {
    // first detect if cycle exists
    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        // if cycle exists, SINCE
        // distance between HEAD of LL and first Node of cycle = cycle detect to first
        // node of cycle
        // we can reset the slow pointer to head
        slow = head;
        while (slow != fast) {
          slow = slow.next;
          fast = fast.next;
        }
        return slow;
      }
    }
    // if loop doesn't exist
    return null;
  }
}