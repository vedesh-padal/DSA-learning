package trees;

import java.util.*;

class TreeConstructor {
  public static String treeConstructor(String[] strArr) {
    HashMap<Integer, Integer> parentCount = new HashMap<>();
    HashSet<Integer> children = new HashSet<>();

    for (String str : strArr) {
      // Parse the string to get i1 and i2
      String[] parts = str.substring(1, str.length() - 1).split(",");
      int child = Integer.parseInt(parts[0]);
      int parent = Integer.parseInt(parts[1]);

      // Count occurrences of each parent
      parentCount.put(parent, parentCount.getOrDefault(parent, 0) + 1);
      if (parentCount.get(parent) > 2) {
        return "false"; // More than 2 children
      }

      // Track child nodes
      children.add(child);
    }

    // Check for a single root node
    int rootCount = 0;
    for (Integer parent : parentCount.keySet()) {
      if (!children.contains(parent)) {
        rootCount++;
      }
    }

    return rootCount == 1 ? "true" : "false";
  }

  public static void main(String[] args) {
    System.out.println(treeConstructor(new String[] { "(1,2)", "(2,4)", "(7,2)", "(5,7)", "(9,5)" })); // Output: true
    System.out.println(treeConstructor(new String[] { "(1,2)", "(3,2)", "(2,12)", "(5,2)" })); // Output: false
  }
}