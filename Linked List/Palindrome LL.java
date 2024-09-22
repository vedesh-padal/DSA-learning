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

  ///// recursive reversal is taking time
  public ListNode reverseLLRecursive(ListNode head) {
    if (head == null || head.next == null)
      return head;

    ListNode newHead = reverseLLRecursive(head.next);
    ListNode front = head.next;
    front.next = head;
    head.next = null;

    return newHead;
  }

  public ListNode reverseLL(ListNode head) {
    ListNode prev = null, curr = head;

    while (curr != null) {
      ListNode front = curr.next;
      curr.next = prev;
      prev = curr;
      curr = front;
    }
    return prev;
  }

  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null)
      return true;

    ListNode slow = head, fast = head;

    // since the ll can have odd or even no. of nodes
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode newHead = reverseLL(slow.next);
    ListNode first = head;
    ListNode second = newHead;

    while (second != null) {
      if (first.val != second.val) {
        // reverseLL(newHead); // if you want to obtain the original LL as it is
        return false;
      }

      first = first.next;
      second = second.next;
    }

    // reverseLL(newHead); // if you want to obtain the original LL as it is
    return true;
  }
}