package trees;
import java.util.*;

class Solution {

    private void preorder(TreeNode root, int lvl, List<List<Integer>> res) {
        if (root == null)
            return;

        // if this is the first time reaching that level, then ony
        // this condition will satisfy, that is left-most traversing deep
        if (lvl == res.size()) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            res.add(list);
        }
        else {
            // we already have alteast one element in that level
            res.get(lvl).add(root.val);
        }

        // pre-order traversal
        preorder(root.left, lvl + 1, res);
        preorder(root.right, lvl + 1, res);
    }

    // using pre-order traversal - DFS -- MIND = BLOWN - actaully nice
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        // root, level, levelOrderTravList
        preorder(root, 0, res);
        return res;
    }


    // ===================================================
    // using Queue - general way
    public List<List<Integer>> levelOrder_usingQueue(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        res.add(new ArrayList<>(Arrays.asList(root.val)));

        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> lvl = new ArrayList<>();

            for (int i = 0; i < sz; i++) {
                TreeNode curr = q.poll();
                if (curr.left != null) {
                    q.offer(curr.left);
                    lvl.add(curr.left.val);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                    lvl.add(curr.right.val);
                }
            }
            if (!lvl.isEmpty())
                res.add(lvl);
        }
        return res;
    }
}