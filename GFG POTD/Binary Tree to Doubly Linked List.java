class TreeNode {
  TreeNode left, right; // Child pointers
  int val; // Node value

  public TreeNode(int d) {
    this.val = d; // Initialize value
    left = right = null; // Set children to null
  }
}

// Class to convert a binary tree to a doubly linked list (DLL)
class BTreeToDLL {
  TreeNode prev = null; // Previous node in DLL
  TreeNode head = null; // Head of the DLL

  private void convert(TreeNode root) {
    if (root == null)
      return; // Base case

    convert(root.left); // Convert left subtree

    // Set head and link nodes
    if (prev == null)
      head = root;
    else {
      prev.right = root; // Link previous to current
      root.left = prev; // Link current to previous
    }

    prev = root; // Update previous
    convert(root.right); // Convert right subtree
  }

  public TreeNode convertBTreeToDLL(TreeNode root) {
    if (root == null)
      return null; // Check for empty tree

    convert(root); // Start conversion
    return head; // Return head of DLL
  }
}