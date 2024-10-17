// MEDIUM

// You are given two non-empty linked lists representing two non-negative
// integers. The most significant digit comes first and each of their nodes
// contains a single digit. Add the two numbers and return the sum as a linked
// list.

// You may assume the two numbers do not contain any leading zero, except the
// number 0 itself.

// Example 1:
// Input: l1 = [7,2,4,3], l2 = [5,6,4]
// Output: [7,8,0,7]

// Example 2:
// Input: l1 = [2,4,3], l2 = [5,6,4]
// Output: [8,0,7]

// Example 3:
// Input: l1 = [0], l2 = [0]
// Output: [0]

// Constraints:
// The number of nodes in each linked list is in the range [1, 100].
// 0 <= Node.val <= 9
// It is guaranteed that the list represents a number that does not have leading
// zeros.

// Follow up: Could you solve it without reversing the input lists?
//  ==> can be done USING STACK (same as add two numbers 1)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

  private ListNode reverseLL(ListNode node) {
    ListNode prev = null, curr = node;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    // first reverse the LLs, so that we have LSB as heads
    // as this will make easy for us to add carry to the next digit

    ListNode nl1 = reverseLL(l1);
    ListNode nl2 = reverseLL(l2);
    int sum = 0, carry = 0;
    ListNode ansNode = new ListNode(0);

    while (nl1 != null || nl2 != null) {
      if (nl1 != null) {
        sum += nl1.val;
        nl1 = nl1.next;
      }
      if (nl2 != null) {
        sum += nl2.val;
        nl2 = nl2.next;
      }

      ansNode.val = sum % 10;
      carry = sum / 10;
      ListNode newNode = new ListNode(carry);
      newNode.next = ansNode;
      // we are adding this newNode at the beginning to avoid
      // another reversal of the resulting LL
      ansNode = newNode;
      sum = carry;
    }
    // we will return ansNode.next
    // becoz, we were running this while loop till
    // we encounter atleast one list has some value
    // if that is not the case, we have created a newNode
    // with carry = 0
    // and our ansNode is now with val = 0
    // so, we return ansNode.next
    // EDGE CASE:
    // if carry = 1, which means: both LLs / numbers
    // were of same length
    // if there existed a carry, we should add it

    // return ansNode.next;
    return (carry == 0) ? ansNode.next : ansNode;
  }
}