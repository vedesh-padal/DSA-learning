// Given inorder and postorder traversals of a binary tree(having n nodes)
//  in the arrays inorder[] and postorder[] respectively. The task is to 
// construct a unique binary tree from these traversals and return its root.
// Driver code will print the preorder traversal of the constructed tree.

// Note: The inorder and postorder traversals contain unique values, and 
// every value present in the postorder traversal is also found in the inorder traversal.

// Examples:

// Input: inorder[] = [4, 8, 2, 5, 1, 6, 3, 7], postorder[] = [8, 4, 5, 2, 6, 7, 3, 1]
// Output: [1, 2, 4, 8, 5, 3, 6, 7]
// Explanation: For the given postorder and inorder traversal of tree the 
// resultant binary tree will be
//           1
//        /      \
//      2        3
//    /  \      /  \
//   4   5     6    7
//    \
//     8
// Input: inorder[] = [9, 5, 2, 3, 4], postorder[] = [5, 9, 3, 4, 2]
// Output: [2, 9, 5, 4, 3]
// Explanation: The resultant binary tree will be
//            2
//         /     \
//        9      4
//         \     /
//          5   3
// Constraints:
// 1 <= number of nodes <= 103
// 1 <= in[i], post[i] <= 106

package trees;

import java.util.*;

class Node {
  int data;
  Node left;
  Node right;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
  }
}

class Solution {

  // is = inorderStart, ie = inorderEnd
  // ps = postOrderStart, pe = postOrderEnd

  private Node buildWithInAndPost(int[] inorder, int is, int ie,
      int[] postorder, int ps, int pe, Map<Integer, Integer> hmap) {

    if (is > ie || ps > pe) {
      return null;
    }

    Node root = new Node(postorder[pe]);

    int inorderRootInd = hmap.get(postorder[pe]);

    // number of nodes to the left of the root of this subtree
    int numsLeft = inorderRootInd - is;

    root.left = buildWithInAndPost(inorder, is, inorderRootInd - 1,
        postorder, ps, ps + numsLeft - 1, hmap);

    root.right = buildWithInAndPost(inorder, inorderRootInd + 1, ie,
        postorder, ps + numsLeft, pe - 1, hmap);

    return root;

  }

  // GIVEN INORDER AND PREORDER
  Node buildTree1(int[] inorder, int[] postorder) {
    // code here
    if (inorder == null || postorder == null || inorder.length != postorder.length)
      return null;

    // HashMap to store the index of each element of inorder
    // inorder to be able to find the elements for left and right subtree

    // (element, index)
    Map<Integer, Integer> hmap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      hmap.put(inorder[i], i);
    }

    return buildWithInAndPost(inorder, 0, inorder.length - 1,
        postorder, 0, postorder.length - 1, hmap);
  }

  // ===================================================================
  // GIVEN PRE-ORDER AND INORDER
  private TreeNode buildFromInAndPre(int[] inorder, int is, int ie, int[] preorder, int ps, int pe,
      Map<Integer, Integer> hmap) {
    if (is > ie || ps > pe)
      return null;

    TreeNode root = new TreeNode(preorder[ps]);

    int inorderRootInd = hmap.get(preorder[ps]);

    int numsLeft = inorderRootInd - is;

    root.left = buildFromInAndPre(inorder, is, inorderRootInd - 1, preorder, ps + 1, ps + numsLeft, hmap);

    root.right = buildFromInAndPre(inorder, inorderRootInd + 1, ie, preorder, ps + numsLeft + 1, pe, hmap);

    return root;
  }

  public TreeNode buildTree2(int[] preorder, int[] inorder) {
    if (inorder == null || preorder == null || inorder.length != preorder.length)
      return null;

    Map<Integer, Integer> hmap = new HashMap<>();

    for (int i = 0; i < inorder.length; i++)
      hmap.put(inorder[i], i);

    return buildFromInAndPre(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1, hmap);
  }
}