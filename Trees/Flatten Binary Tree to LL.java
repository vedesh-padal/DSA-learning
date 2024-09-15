import java.util.Stack;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class Solution {
  // APPROACH - 1: MOVE COMPLETE RIGHT AND JOIN WITH PREV
  public TreeNode prev = null;

  public void flattenRecursive(TreeNode root) {
    if (root == null)
      return;

    flattenRecursive(root.right);
    flattenRecursive(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
  }

  // ITERATIVE APPROACH - using stack
  public void flattenIterative(TreeNode root) {
    if (root == null)
      return;

    Stack<TreeNode> stk = new Stack<>();
    stk.push(root);

    while (!stk.isEmpty()) {
      TreeNode curr = stk.pop();

      if (curr.right != null)
        stk.push(curr.right);
      if (curr.left != null)
        stk.push(curr.left);

      if (!stk.isEmpty())
        curr.right = stk.peek();
      curr.left = null;
    }

  }

  // APPROACH - 3:
  // MORRIS TRAVERSAL
  // MOVE TOWARDS COMPLETE RIGHT IN THE LEFT SUBTREE
  // AND JOIN IT TO THE RIGHT OF CURR
  public void flattenMorris(TreeNode root) {
    if (root == null)
      return;

    TreeNode curr = root;
    while (curr != null) {
      if (curr.left != null) {
        TreeNode prev = curr.left;
        while (prev.right != null)
          prev = prev.right;

        prev.right = curr.right;
        curr.right = curr.left;
        curr.left = null;
      }
      curr = curr.right;
    }
  }
}