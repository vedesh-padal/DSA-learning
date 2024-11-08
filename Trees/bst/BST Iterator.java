package trees.bst;

// design data structure

// Implement the BSTIterator class that represents an iterator over the in-order
// traversal of a binary search tree (BST):

// BSTIterator(TreeNode root) Initializes an object of the BSTIterator class.
// The root of the BST is given as part of the constructor. The pointer should
// be initialized to a non-existent number smaller than any element in the BST.

// boolean hasNext() Returns true if there exists a number in the traversal to
// the right of the pointer, otherwise returns false.

// int next() Moves the pointer to the right, then returns the number at the pointer.

// Notice that by initializing the pointer to a non-existent smallest number,
// the first call to next() will return the smallest element in the BST.

// You may assume that next() calls will always be valid. That is, there will be
// at least a next number in the in-order traversal when next() is called.

// Input
// ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
// [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
// Output
// [null, 3, 7, true, 9, true, 15, true, 20, false]

// Explanation:
// BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
// bSTIterator.next();    // return 3
// bSTIterator.next();    // return 7
// bSTIterator.hasNext(); // return True
// bSTIterator.next();    // return 9
// bSTIterator.hasNext(); // return True
// bSTIterator.next();    // return 15
// bSTIterator.hasNext(); // return True
// bSTIterator.next();    // return 20
// bSTIterator.hasNext(); // return False
 

// Constraints:
// The number of nodes in the tree is in the range [1, 105].
// 0 <= Node.val <= 106
// At most 105 calls will be made to hasNext, and next.
 
// Follow up:
// Could you implement next() and hasNext() to run in average O(1) time
// and use O(h) memory, where h is the height of the tree?

import java.util.Stack;

class BSTIterator {

  private Stack<TreeNode> stk;

  public BSTIterator(TreeNode root) {
    stk = new Stack<>();
    pushAll(root);
  }

  public int next() {
    TreeNode next = stk.pop();
    
    if (next.right != null)
      pushAll(next.right);

    return next.val;
  }


  public boolean hasNext() {
    return !stk.isEmpty();
  }

  private void pushAll(TreeNode node) {
    // one line way of writing
    for ( ; node != null; stk.push(node), node = node.left);
  }

}


// prev and next
class BSTIterator_BOTH {
  private Stack<TreeNode> stack;
  private TreeNode lastVisited; // Tracks the last node returned for reverse traversal
  private boolean movingForward; // Flag to check if we're moving forward or backward

  public BSTIterator_BOTH(TreeNode root) {
    stack = new Stack<>();
    lastVisited = null;
    movingForward = true; // Start with forward traversal
    pushLeft(root); // Initialize with the leftmost path in the stack
  }

  public boolean hasNext() {
    // return !stack.isEmpty() || (lastVisited != null && lastVisited.right != null);
    // OR JUST:
    return !stack.isEmpty();
  }

  public int next() {
    if (!movingForward && lastVisited != null) {
      // Adjust the stack for forward traversal if last move was prev()
      pushLeft(lastVisited.right);
    }

    movingForward = true;
    TreeNode current = stack.pop();
    lastVisited = current;
    pushLeft(current.right); // Prepare next element in in-order traversal
    return current.val;
  }

  public boolean hasPrev() {
    return lastVisited != null;
  }

  public int prev() {
    if (movingForward) {
      // If the last move was next(), adjust to move backwards
      pushRight(lastVisited.left);
    }

    movingForward = false;
    lastVisited = stack.pop();
    return lastVisited.val;
  }

  private void pushLeft(TreeNode node) {
    while (node != null) {
      stack.push(node);
      node = node.left;
    }
  }

  private void pushRight(TreeNode node) {
    while (node != null) {
      stack.push(node);
      node = node.right;
    }
  }
}
