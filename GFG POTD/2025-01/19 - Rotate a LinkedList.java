/*
   Given the head of a singly linked list, your task is to left rotate the linked list k times.

    Examples:
    Input: head = 10 -> 20 -> 30 -> 40 -> 50, k = 4
    Output: 50 -> 10 -> 20 -> 30 -> 40
    Explanation:
    Rotate 1: 20 -> 30 -> 40 -> 50 -> 10
    Rotate 2: 30 -> 40 -> 50 -> 10 -> 20
    Rotate 3: 40 -> 50 -> 10 -> 20 -> 30
    Rotate 4: 50 -> 10 -> 20 -> 30 -> 40

    Input: head = 10 -> 20 -> 30 -> 40 , k = 6
    Output: 30 -> 40 -> 10 -> 20 

    Constraints:
    1 <= number of nodes <= 10^5
    0 <= k <= 10^9
    0 <= data of node <= 10^9
*/

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class Solution {
    public Node rotate(Node head, int k) {
        // add code here
        // first find the length of the LL
        if (head == null || k == 0)
            return head;

        int n = 0;
        Node t = head;
        while (t != null) {
            t = t.next;
            n++;
        }

        // intuition after dry running:
        k = k % n;
        // left rotating here, is nothing but:
        // traverse k steps, kth node (1-based indexing) is your newHead
        // put the next of the just prev. node of the newHead to null
        // and connect the end of the orig. LL to head
        if (k == 0)
            return head;

        Node prevHead = head;
        t = head;
        Node prev = null;
        while (k > 0) {
            prev = t;
            t = t.next;
            k--;
        }

        // System.out.println(t.data);
        Node newHead = t;
        prev.next = null;
        while (t.next != null) {
            t = t.next;
        }
        t.next = prevHead;

        return newHead;
    }
}