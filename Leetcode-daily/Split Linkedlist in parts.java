class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
 

class Solution {
  public ListNode[] splitListToParts(ListNode head, int k)  { // split to k parts
    int n = 0;
    ListNode temp = head;
    while (temp != null)  {
      n++;
      temp = temp.next;
    }

    int partSize = n / k;
    int extraNodes = n % k;
    temp = head;
    ListNode[] res = new ListNode[k];
    ListNode s = head;

    for (int i = 0; i < k; i++) {
      res[i] = s;

      int currPartSize = partSize + ( i < extraNodes ? 1 : 0);

      for (int j = 1; j < currPartSize; j++)  {
        if (temp != null)
          temp = temp.next;
      }

      if (temp != null) {
        s = temp.next;
        temp.next = null;
        temp = s;
      }
    }
    return res;
  }
}