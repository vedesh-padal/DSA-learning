import java.util.*;

class LFUCache {

  final int capacity;
  int currSize;
  int minFrequency;
  Map<Integer, DLLNode> cache;
  Map<Integer, DoublyLinkedList> freqMap;

  public LFUCache(int capacity) {
    this.capacity = capacity;
    currSize = 0;
    this.minFrequency = 0;

    this.cache = new HashMap<>();
    this.freqMap = new HashMap<>();
  }

  
  // get node value by key, adn then update node freq as well as relocate that node
  public int get(int key) {
    if (cache.containsKey(key)) {
      DLLNode currNode = cache.get(key);
      updateNode(currNode);
      return currNode.value;
    }
    return -1;
  }

  public void put(int key, int value) {
    // corner case: check cache capacity initialization
    if (capacity == 0)
      return;

    if (cache.containsKey(key)) {
      DLLNode currNode = cache.get(key);
      currNode.value = value;
      updateNode(currNode);
    }
    else {
      currSize++;
      if (currSize > capacity)  {
        // get minimum freq. list
        DoublyLinkedList minFreqList = freqMap.get(minFrequency);
        cache.remove(minFreqList.tail.prev.key);
        minFreqList.removeNode(minFreqList.tail.prev);
        currSize--;
      }

      // reset min freq to 1 because of adding new node
      minFrequency = 1;
      DLLNode newNode = new DLLNode(key, value);

      // get the list with freq 1, and then add new node into the list, as well as into LFU cache
      DoublyLinkedList currList = freqMap.getOrDefault(1, new DoublyLinkedList());
      currList.addNode(newNode);
      freqMap.put(1, currList); // updating the freq. map with updated element/key-value pair
      cache.put(key, newNode);
    }
  }

  public void updateNode(DLLNode currNode)  {
    int currFreq = currNode.freq;
    DoublyLinkedList currList = freqMap.get(currFreq);
    currList.removeNode(currNode);

    // if curr list isi the last list which has least freq and curr node is the only node int he list
    // we need to remove the entire list and then increaese min freq value by 1
    if (currFreq == minFrequency && currList.listSize == 0) {
      minFrequency++;
    }

    currNode.freq++;

    // add curr node to another list has current frequency + 1,
    // if we do not have the list with this freq, initialize it
    DoublyLinkedList newList = freqMap.getOrDefault(currNode.freq, new DoublyLinkedList());
    newList.addNode(currNode);
    freqMap.put(currNode.freq, newList);
  }


  class DLLNode {
    int key, value, freq;
    DLLNode prev, next;

    public DLLNode(int key, int value)  {
      this.key = key;
      this.value = value;
      this.freq = 1;
    }
  }

  class DoublyLinkedList {
    int listSize;
    DLLNode head, tail;
    
    public DoublyLinkedList() {
      this.listSize = 0;
      this.head = new DLLNode(0, 0);
      this.tail = new DLLNode(0, 0);
      head.next = tail;
      tail.prev = head;
    }

    // add new node into head of list and increase list size by 1
    public void addNode(DLLNode currNode)  {
      DLLNode nextNode = head.next;
      currNode.next = nextNode;
      currNode.prev = head;
      head.next = currNode;
      nextNode.prev = currNode;
      listSize++;
    }


    // remove input node and decrease lsit size by 1
    public void removeNode(DLLNode currNode) {
      DLLNode prevNode = currNode.prev;
      DLLNode nextNode = currNode.next;
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
      listSize--;
    }

  }
}