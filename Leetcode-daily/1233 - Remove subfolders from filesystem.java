// 25-10-2024
// MEDIUM
// array, string, dfs, trie

// Given a list of folders folder, return the folders after removing all
// sub-folders in those folders. You may return the answer in any order.

// If a folder[i] is located within another folder[j], it is called a sub-folder
// of it. A sub-folder of folder[j] must start with folder[j], followed by a
// "/". For example, "/a/b" is a sub-folder of "/a", but "/b" is not a
// sub-folder of "/a/b/c".

// The format of a path is one or more concatenated strings of the form: '/'
// followed by one or more lowercase English letters.

// For example, "/leetcode" and "/leetcode/problems" are valid paths while an
// empty string and "/" are not.

// Example 1:
// Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
// Output: ["/a","/c/d","/c/f"]
// Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of
// folder "/c/d" in our filesystem.

// Example 2:
// Input: folder = ["/a","/a/b/c","/a/b/d"]
// Output: ["/a"]
// Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are
// subfolders of "/a".

// Example 3:
// Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
// Output: ["/a/b/c","/a/b/ca","/a/b/d"]

// Constraints:
// - 1 <= folder.length <= 4 * 104
// - 2 <= folder[i].length <= 100
// - folder[i] contains only lowercase letters and '/'.
// - folder[i] always starts with the character '/'.
// - Each folder name is unique.

import java.util.*;

class Solution {

  public List<String> removeSubfolders(String[] folder) {
    // stores the non-subfolders
    List<String> res = new ArrayList<>();

    // after sorting, we consider if the current folder is 
    // subfolder of previous one or not
    // note: subfolder, when the curr. folder ends with `/` 
    // and previous path is already seen
    Arrays.sort(folder);

    // we check for each curr. folder, if it is present in parent
    for (String s : folder) {
      if (res.isEmpty() || !s.startsWith(res.get(res.size() - 1) + '/'))
        res.add(s);
    }

    return res;
  }

  // ===============================================
  // 116ms (9.8%), 43.27% mem
  public List<String> removeSubfolders_MY_TRY_BAD_TC(String[] folder) {
    @SuppressWarnings("unused")
    List<String> res = new ArrayList<>();

    Set<String> hset = new HashSet<>();
    Arrays.sort(folder);

    for (String s : folder) {
      if (s.length() == 1 && s.charAt(0) == '/')
        continue;

      boolean isSubfolder = false;

      String currPath = "";

      for (int i = 0; i < s.length(); i++) {
        currPath += s.charAt(i);
        if (hset.contains(currPath)) {
          if (i != s.length() - 1 && s.charAt(i + 1) == '/') {
            isSubfolder = true;
            break;
          }
          // isSubfolder = true;
          // break;
        }
      }

      if (!isSubfolder)
        hset.add(s);

    }

    System.out.println(hset);
    return new ArrayList<String>(hset);
  }
}