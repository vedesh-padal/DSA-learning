// MEDIUM - EASY
// strings, hash-table

/*
    Given an array of strings, return all groups of strings that are anagrams.
    The groups must be created in order of their appearance in the original array.
    Look at the sample case for clarification.

    Note: The final output will be in lexicographic order.

    Examples:
    Input: arr[] = ["act", "god", "cat", "dog", "tac"]
    Output: [["act", "cat", "tac"], ["god", "dog"]]
    Explanation: There are 2 groups of anagrams "god", "dog" make group 1. "act", "cat", "tac" make group 2.

    Input: arr[] = ["no", "on", "is"]
    Output: [["is"], ["no", "on"]]
    Explanation: There are 2 groups of anagrams "is" makes group 1. "no", "on" make group 2.

    Input: arr[] = ["listen", "silent", "enlist", "abc", "cab", "bac", "rat", "tar", "art"]
    Output: [["abc", "cab", "bac"], ["listen", "silent", "enlist"], ["rat", "tar", "art"]]
    Explanation:
    Group 1: "abc", "bac", and "cab" are anagrams.
    Group 2: "listen", "silent", and "enlist" are anagrams.
    Group 3: "rat", "tar", and "art" are anagrams.

    Constraints:
    1<= arr.size() <=100
    1<= arr[i].size() <=10
*/
import java.util.*;

class Solution {
    public ArrayList<ArrayList<String>> anagrams(String[] arr) {
        // using HashMap, but I doubt if lexicographical order is preserved - it actually worked
        // string => each character and count, appended as string
        Map<String, ArrayList<String>> hmap = new HashMap<>();
        
        for (String s: arr) {
            int[] cnt = new int[26];
            for (int i = 0; i < s.length(); i++) {
                cnt[s.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append((char)(i + 'a')).append(cnt[i]);
            }
            hmap.computeIfAbsent(sb.toString(), k -> new ArrayList<>()).add(s);
        }
        
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry: hmap.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
}
