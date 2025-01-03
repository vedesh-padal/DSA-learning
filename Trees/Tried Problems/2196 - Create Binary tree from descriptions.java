// MEDIUM
// trees

/*
    You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] 
    indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

    If isLefti == 1, then childi is the left child of parenti.
    If isLefti == 0, then childi is the right child of parenti.
    Construct the binary tree described by descriptions and return its root.

    The test cases will be generated such that the binary tree is valid.


    Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
    Output: [50,20,80,15,17,19]
    Explanation: The root node is the node with value 50 since it has no parent.
    The resulting binary tree is shown in the diagram.

    Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
    Output: [1,2,null,null,3,4]
    Explanation: The root node is the node with value 1 since it has no parent.
    The resulting binary tree is shown in the diagram.

    Constraints:
    1 <= descriptions.length <= 104
    descriptions[i].length == 3
    1 <= parenti, childi <= 105
    0 <= isLefti <= 1
    The binary tree described by descriptions is valid.
*/
import java.util.*;
import trees.TreeNode;

 class Pair {
    int left, right;
    Pair(int l, int r) {
        this.left = l;
        this.right = r;
    }
}

class Solution {

    private TreeNode buildTree(Map<Integer, Pair> hmap, int currRoot) {

        TreeNode root = new TreeNode(currRoot);
        if (hmap.containsKey(currRoot)) {
            if (hmap.get(currRoot).left != 0) {
                root.left = buildTree(hmap, hmap.get(currRoot).left);
            }

            if (hmap.get(currRoot).right != 0) {
                root.right = buildTree(hmap, hmap.get(currRoot).right);
            }
        }
        return root;
    }

    public TreeNode createBinaryTree(int[][] descriptions) {
        // put all the childs in a hashset
        // iterate over parents and if it is not present in child, that is our root
        Set<Integer> children = new HashSet<>();
        Map<Integer, Pair> hmap = new HashMap<>();

        for (int[] d: descriptions) {
            if (d[2] == 1) {
                if (hmap.containsKey(d[0]))
                    hmap.get(d[0]).left = d[1];
                else
                    hmap.put(d[0], new Pair(d[1], 0));
            }
            else {
                if (hmap.containsKey(d[0]))
                    hmap.get(d[0]).right = d[1];
                else
                    hmap.put(d[0], new Pair(0, d[1]));
            }
            children.add(d[1]);
        }

        int root = -1;
        for (int[] desc: descriptions) {
            if (!children.contains(desc[0])) {
                root = desc[0];
                break;
            }
        }

        return buildTree(hmap, root);
    }
}