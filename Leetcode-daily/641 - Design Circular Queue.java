// Design your implementation of the circular double-ended queue (deque).

// Implement the MyCircularDeque class:

// MyCircularDeque(int k) Initializes the deque with a maximum size of k.
// boolean insertFront() Adds an item at the front of Deque. Returns true if the
// operation is successful, or false otherwise.
// boolean insertLast() Adds an item at the rear of Deque. Returns true if the
// operation is successful, or false otherwise.
// boolean deleteFront() Deletes an item from the front of Deque. Returns true
// if the operation is successful, or false otherwise.
// boolean deleteLast() Deletes an item from the rear of Deque. Returns true if
// the operation is successful, or false otherwise.
// int getFront() Returns the front item from the Deque. Returns -1 if the deque
// is empty.
// int getRear() Returns the last item from Deque. Returns -1 if the deque is
// empty.
// boolean isEmpty() Returns true if the deque is empty, or false otherwise.
// boolean isFull() Returns true if the deque is full, or false otherwise.

// Example 1:

// Input
// ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront",
// "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
// [[3], [1], [2], [3], [4], [], [], [], [4], []]
// Output
// [null, true, true, true, false, 2, true, true, true, 4]

// Explanation
// MyCircularDeque myCircularDeque = new MyCircularDeque(3);
// myCircularDeque.insertLast(1); // return True
// myCircularDeque.insertLast(2); // return True
// myCircularDeque.insertFront(3); // return True
// myCircularDeque.insertFront(4); // return False, the queue is full.
// myCircularDeque.getRear(); // return 2
// myCircularDeque.isFull(); // return True
// myCircularDeque.deleteLast(); // return True
// myCircularDeque.insertFront(4); // return True
// myCircularDeque.getFront(); // return 4

// Constraints:

// 1 <= k <= 1000
// 0 <= value <= 1000
// At most 2000 calls will be made to insertFront, insertLast, deleteFront,
// deleteLast, getFront, getRear, isEmpty, isFull.


class MyCircularDeque {
  private int[] cq;
  private int front, rear, capacity, size;

  public MyCircularDeque(int k) {
    this.cq = new int[k];
    this.front = -1;
    this.rear = -1;
    this.capacity = k;
    this.size = 0;
  }

  public boolean insertFront(int value) {
    if (isFull())
      return false;
    if (front == -1 && rear == -1) {
      front++;
      rear++;
      cq[front] = value;
    } else if (front == 0) {
      front = capacity - 1;
      cq[front] = value;
    } else {
      cq[--front] = value;
    }
    size++;
    return true;
  }

  public boolean insertLast(int value) {
    if (isFull())
      return false;
    // if this is the first element to be inserted
    if (front == -1 && rear == -1) {
      front++;
      rear++;
      cq[rear] = value;
    } else {
      rear = (rear + 1) % capacity;
      cq[rear] = value;
    }
    size++;
    return true;
  }

  public boolean deleteFront() {
    if (isEmpty())
      return false;

    // that means there is only one element
    if (front == rear) {
      front = -1;
      rear = -1;
    } else if (front == capacity - 1) {
      front = 0;
    } else {
      front = (front + 1) % capacity;
    }
    size--;
    return true;
  }

  public boolean deleteLast() {
    if (isEmpty())
      return false;

    if (front == rear) {
      front = -1;
      rear = -1;
    } else if (rear == 0)
      rear = capacity - 1;
    else
      rear--;

    size--;
    return true;
  }

  public int getFront() {
    if (isEmpty())
      return -1;
    return cq[front];
  }

  public int getRear() {
    if (isEmpty())
      return -1;
    return cq[rear];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == capacity;
  }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */