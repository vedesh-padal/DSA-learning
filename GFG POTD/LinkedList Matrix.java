// EASY
// linkedlist, array
// Convert N*N matrix to a 2D LL

class Node {
  int data;
  Node right, down;

  Node(int data) {
    this.data = data;
    right = null;
    down = null;
  }
}

class Solution {
  static Node construct(int arr[][]) {
    // Add your code here.

    // connecting right and down pointer
    // for the curr. node simultaneously without extra space
    int n = arr.length;
    Node res = new Node(arr[0][0]);
    Node curr = res;

    for (int i = 0; i < n; i++) {
      Node firstCol = curr;

      for (int j = 0; j < n; j++) {
        // connect the right pointer of current node
        // if curr. node is not in the last column
        if (j + 1 < n)
          curr.right = new Node(arr[i][j + 1]);

        // connect the down pointer of current node
        // if curr. node is not in the last row
        if (i + 1 < n)
          curr.down = new Node(arr[i + 1][j]);

        // move to the next node to thr right
        // becoz inner for loop goes right in the row (col increases)
        curr = curr.right;
      }
      curr = firstCol.down;
    }
    return res;
  }
}