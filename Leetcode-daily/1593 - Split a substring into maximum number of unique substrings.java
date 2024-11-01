// 21-10-2024
// MEDIUM
// string, hashtable, backtracking

// Given a string s, return the maximum number of unique substrings 
// that the given string can be split into.

// You can split string s into any list of non-empty substrings, where 
// the concatenation of the substrings forms the original string. 
// However, you must split the substrings such that all of them are unique.

// A substring is a contiguous sequence of characters within a string.

// Example 1:
// Input: s = "ababccc"
// Output: 5
// Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. 
// Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.

// Example 2:
// Input: s = "aba"
// Output: 2
// Explanation: One way to split maximally is ['a', 'ba'].

// Example 3:
// Input: s = "aa"
// Output: 1
// Explanation: It is impossible to split the string any further.

// Constraints:
// 1 <= s.length <= 16
// s contains only lower case English letters.

import java.util.Set;
import java.util.HashSet;

class Solution {

  private int backtrack(int ind, String s, Set<String> hset) {
    if (ind == s.length())
      return 0;

    int res = 0; // we were not able to split this substring
    for (int i = ind; i < s.length(); i++) {
      String subStr = s.substring(ind, i + 1);
      if (hset.contains(subStr))
        continue;
      hset.add(subStr);
      // here `i + 1` is where the next split to be done
      // +1, becoz, we made a split, and we want to count that split
      res = Math.max(res, backtrack(i + 1, s, hset) + 1);
      hset.remove(subStr);
    }
    return res;
  }

  public int maxUniqueSplit(String s) {
    return backtrack(0, s, new HashSet<>());
  }

  // =====================================================

  // greedy, won't work, as we are just trying to pick the min. length
  // but this way, we are missing the other ways of considering
  // substrings from the beginning that will lead to max. splits

  // using greedy, we dont know what will be the chars coming next
  // so, we use recursion/backtracking
  public int maxUniqueSplit_GREEDY_WONT_WORK(String s) {
    int i = 0;
    Set<String> hset = new HashSet<>();
    int start = 0;
    while (i < s.length()) {
      if (hset.contains(String.valueOf(s.substring(start, i + 1)))) {
        i++;
      } else {
        hset.add(String.valueOf(s.substring(start, i + 1)));
        i++;
        start = i;
      }
    }
    System.out.println(hset);
    return hset.size();
  }
}