package trees.bst;

class Node {
  int data;
  Node left;
  Node right;
  
  public Node (int data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}

class Tree {
  // Function to return the ceil of given number in BST.
  int findCeil(Node root, int key) {
    // Code here
    if (root == null)
      return -1;

    int ceil = -1;

    while (root != null) {
      if (root.data == key) {
        ceil = root.data;
        return ceil;
      } else if (root.data < key) {
        root = root.right;
      } else { // root.data > key
        ceil = root.data;
        root = root.left;
      }
    }
    return ceil;
  }
}