import java.util.PriorityQueue;

class DLLNode {
  int data;
  DLLNode prev, next;
  public DLLNode(int val) {
    this.data = val;
    this.prev = null;
    this.next = null;
  }
}

class Solution {
  public DLLNode sortKDoublyLL(DLLNode head, int k) {
    // we will use Prioity Queue to get the smallest element
    // first we will add k + 1 nodes (becoz, given till k index)

    // min heap based on node data
    PriorityQueue<DLLNode> pq = new PriorityQueue<>((a, b) -> a.data - b.data);

    for (int i = 0; i < k + 1 && head != null; i++) {
      pq.add(head);
      head = head.next;
    }

    DLLNode first = null, last = null;

    while (!pq.isEmpty()) {
      if (first == null)  {
        first = pq.poll();
        first.prev = null;
        last = first;
      }
      else {
        last.next = pq.poll();
        last.next.prev = last;
        last = last.next;
      }

      // if there is next node in the input DLL
      if (head != null) {
        pq.add(head);
        head = head.next;
      }
    }
    // since we are changing nodes and links
    // we add last node's next to null
    last.next = null;

    return first;
  }
}