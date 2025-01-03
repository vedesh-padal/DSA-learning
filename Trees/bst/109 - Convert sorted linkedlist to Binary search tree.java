package trees.bst;

/*
    Given the head of a singly linked list where elements are sorted in ascending order, convert it to a 
    height-balanced binary search tree.

    Example 1:
    Input: head = [-10,-3,0,5,9]
    Output: [0,-3,9,-10,null,5]
    Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.

    Example 2:
    Input: head = []
    Output: []

    Constraints:
    The number of nodes in head is in the range [0, 2  104].
    -10^5 <= Node.val <= 10^5
*/
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    ListNode node;
    // inorder traversal
    private TreeNode createTree(int l, int r) {
        if (l > r)
            return null;

        int mid = l + (r - l) / 2;

        TreeNode left = createTree(l, mid - 1);
        TreeNode root = new TreeNode(node.val);
        root.left = left;
        node = node.next;
        TreeNode right = createTree(mid + 1, r);
        root.right = right;

        return root;
    }

    // trying to solve optimally
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;

        // inorder traversal approach - if wanted to solve without using extra space
        // for that you need to know the height of the tree you are constructing
        ListNode t = head;
        int n = 0;  // linkedlist size
        while (t != null) {
            n++;
            t = t.next;
        }
        
        node = head;
        return createTree(0, n-1);
    }


    // =================================================
    private TreeNode create(TreeNode node, List<Integer> arr, int l, int r) {
        if (l > r) 
            return null;

        int mid = l + (r - l) / 2;
        TreeNode t = new TreeNode(arr.get(mid));
        t.left = create(t, arr, l, mid-1);
        t.right = create(t, arr, mid+1, r);

        return t;
    }

    public TreeNode sortedListToBST_ExtraSpace(ListNode head) {
        ListNode t = head;
        List<Integer> arr = new ArrayList<>();
        while (t != null) {
            arr.add(t.val);
            t = t.next;
        }

        return create(null, arr, 0, arr.size() - 1);
    }
}
