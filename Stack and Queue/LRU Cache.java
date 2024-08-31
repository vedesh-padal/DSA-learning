import java.util.Map;
import java.util.HashMap;

class LRUCache {
  Map<Integer, Node> map = new HashMap<>();
  int capacity;
  // two dummy pointers, head and tail, denoting start and end of the DLL, b/w
  // this only all pairs lie
  Node head = new Node(0, 0), tail = new Node(0, 0);

  public LRUCache(int capacity) {
    this.capacity = capacity;
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      // since you have accessed this recently, put it in the front (after head) (most
      // recently used near head)
      remove(node);
      // remove from where it is present, and place to the left most, indicating it is
      // accessed recently
      insert(node);
      return node.value;
    }
    return -1;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      remove(map.get(key));
    }

    if (map.size() == capacity) {
      remove(tail.prev); // remove the least recently used
    }

    insert(new Node(key, value));
  }

  private void remove(Node node) {
    map.remove(node.key);
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void insert(Node node) {
    map.put(node.key, node);
    node.next = head.next;
    node.next.prev = node;
    head.next = node;
    node.prev = head;
  }

  class Node {
    Node prev, next;
    int key, value;

    Node(int _key, int _value) {
      key = _key;
      value = _value;
    }
  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */