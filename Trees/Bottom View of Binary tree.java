// MEDIUM
// trees

package trees;
import java.util.*;

class Pair {
  int val;
  int level;
  public Pair(int val, int level) {
    this.val = val;
    this.level = level;
  }
}

class Solution {

  private void bottomView(int vInd, int currLevel, Node root, Map<Integer, Pair> tmap) {

    if (root == null)
      return;

    if (!tmap.containsKey(vInd) || tmap.get(vInd).level <= currLevel) {
      tmap.put(vInd, new Pair(root.data, currLevel));
    }

    bottomView(vInd - 1, currLevel + 1, root.left, tmap);

    bottomView(vInd + 1, currLevel + 1, root.right, tmap);
  }

  // Function to return a list containing the bottom view of the given tree.
  public ArrayList<Integer> bottomView(Node root) {

    ArrayList<Integer> res = new ArrayList<>();

    if (root == null)
      return res;

    // (hd, val of that node)
    // vertical Index = horizontal distance
    Map<Integer, Pair> tmap = new TreeMap<>();

    // verticalIndex, level, node, tmap
    bottomView(0, 0, root, tmap);

    for (Map.Entry<Integer, Pair> entry : tmap.entrySet())
      res.add(entry.getValue().val);

    return res;
  }
}