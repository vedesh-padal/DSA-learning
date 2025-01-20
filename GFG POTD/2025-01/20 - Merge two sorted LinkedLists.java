// MEDIUM
// linked-list

/*
    Given the head of two sorted linked lists consisting of nodes respectively.
    The task is to merge both lists and return the head of the sorted merged list.

    Examples:
    Input: head1 = 5 -> 10 -> 15 -> 40, head2 = 2 -> 3 -> 20
    Output: 2 -> 3 -> 5 -> 10 -> 15 -> 20 -> 40

    Input: head1 = 1 -> 1, head2 = 2 -> 4
    Output: 1 -> 1 -> 2 -> 4

    Constraints:
    1 <= no. of nodes<= 10^3
    0 <= node->data <= 10^5
*/

class Node  {
    int data;
    Node next;
    Node(int d) {data = d; next = null; }
}

class Solution {
    
    // OPTIMAL
    Node sortedMerge(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
            
        Node dummy = new Node(-1);
        Node curr = dummy;
        
        Node t1 = head1, t2 = head2;
        
        while (t1 != null && t2 != null) {
            if (t1.data <= t2.data) {
                curr.next = t1;
                t1 = t1.next;
            }
            else {
                curr.next = t2;
                t2 = t2.next;
            }
            curr = curr.next;
        }
        
        if (t1 != null) {
            curr.next = t1;
        }
        else {
            curr.next = t2;
        }
        
        return dummy.next;
    }
    
    
    // recursive approach
    Node sortedMerge_recursive(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
            
        if (head1.data <= head2.data) {
            head1.next = sortedMerge_recursive(head1.next, head2);
            return head1;
        }
        else {
            head2.next = sortedMerge_recursive(head1, head2.next);
            return head2;
        }
    }
    
    // =========================================
    // naive way
    Node sortedMerge_NAIVE(Node head1, Node head2) {
        // code here
        
        Node t1 = head1;
        Node t2 = head2;
        Node res;
        if (head1.data < head2.data) {
            res = new Node(head1.data);
            t1 = t1.next;
        } else {
            res = new Node(head2.data);
            t2 = t2.next;
        }
        Node head = res;
        
        while (t1 != null && t2 != null) {
            if (t1.data < t2.data) {
                res.next = new Node(t1.data);
                res = res.next;
                t1 = t1.next;
            } else {
                res.next = new Node(t2.data);
                res = res.next;
                t2 = t2.next;
            }
        }
        
        while (t1 != null) {
            res.next = new Node(t1.data);
            res = res.next;
            t1 = t1.next;
        }
        
        while (t2 != null) {
            res.next = new Node(t2.data);
            res = res.next;
            t2 = t2.next;
        }
        
        return head;
    }
}