// EASY
// hashset, string
/*
    The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

    For example, "ACGAATTCCG" is a DNA sequence.
    When studying DNA, it is useful to identify repeated sequences within the DNA.

    Given a string s that represents a DNA sequence, return all the
    10-letter-long sequences (substrings) that occur more than once in a DNA
    molecule. You may return the answer in any order.

    Example 1:
    Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
    Output: ["AAAAACCCCC","CCCCCAAAAA"]

    Example 2:
    Input: s = "AAAAAAAAAAAAA"
    Output: ["AAAAAAAAAA"]

    Constraints:
    1 <= s.length <= 10^5
    s[i] is either 'A', 'C', 'G', or 'T'.
*/
import java.util.*;

class Solution {

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeat = new HashSet<>();

        for (int i = 0; i + 9 < s.length(); i++) {
            String t = s.substring(i, i+10);
            // .add() in hashset returns false, if already present
            if (!seen.add(t)) {
                repeat.add(t);
            }
        }
        return new ArrayList<>(repeat);
    }

    public List<String> findRepeatedDnaSequences_firstTry(String s) {

        List<String> res = new ArrayList<>();
        int n = s.length();
        if (n < 10)
            return res;

        Map<String, Integer> hmap = new HashMap<>();
        hmap.put(s.substring(0, 10), 1);
        
        StringBuilder curr = new StringBuilder(s.substring(0, 10));
        for (int i = 10; i < n; i++) {
            curr.deleteCharAt(0);
            curr.append(s.charAt(i));
            String t = curr.toString();
            hmap.put(t, hmap.getOrDefault(t, 0) + 1);
        }

        for (String str: hmap.keySet()) {
            if (hmap.get(str) > 1)
                res.add(str);
        }
        return res;
    }
}