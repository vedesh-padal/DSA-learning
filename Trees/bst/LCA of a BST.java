package trees.bst;

class Solution {

  // INTUITION:
  // if the curr. node's val is less than or greater than both the node's vals
  // then move to the other side
  // Whenever, the both node's are not on the same side => we have a split
  // we return that node as our LCA of the give two nodes

  public TreeNode lowestCommonAncestor_recursive(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null)
      return null;

    if (root.val < p.val && root.val < q.val)
      return lowestCommonAncestor_recursive(root.right, p, q);
    else if (root.val > p.val && root.val > q.val)
      return lowestCommonAncestor_recursive(root.left, p, q);
    else
      return root;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode curr = root;

    while (curr != null) {
      if (curr.val < p.val && curr.val < q.val) {
        curr = curr.right;
      } 
      else if (curr.val > p.val && curr.val > q.val) {
        curr = curr.left;
      } 
      else
        return curr;
    }
    return curr; // here it will be null
  }
}