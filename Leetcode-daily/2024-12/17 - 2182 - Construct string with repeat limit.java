// MEDIUM
// heap, string, hashtable, counting

/*
    You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString 
    using the characters of s such that no letter appears more than repeatLimit times in a row. 
    You do not have to use all characters from s.

    Return the lexicographically largest repeatLimitedString possible.

    A string a is lexicographically larger than a string b if in the first position where a and b differ, 
    string a has a letter that appears later in the alphabet than the corresponding letter in b. 
    If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.

    

    Example 1:
    Input: s = "cczazcc", repeatLimit = 3
    Output: "zzcccac"
    Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
    The letter 'a' appears at most 1 time in a row.
    The letter 'c' appears at most 3 times in a row.
    The letter 'z' appears at most 2 times in a row.
    Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
    The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
    Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, 
    so it is not a valid repeatLimitedString.

    Example 2:
    Input: s = "aababab", repeatLimit = 2
    Output: "bbabaa"
    Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa". 
    The letter 'a' appears at most 2 times in a row.
    The letter 'b' appears at most 2 times in a row.
    Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
    The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
    Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, 
    so it is not a valid repeatLimitedString.
    
    Constraints:
    1 <= repeatLimit <= s.length <= 10^5
    s consists of lowercase English letters.
*/

import java.util.PriorityQueue;

class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        // previously i maintained Pair (ch, cnt)
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> Character.compare(b, a));

        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                pq.offer((char)(i + 'a'));
            }
        }

        StringBuilder sb = new StringBuilder();

        // can still be optimized by keeping track of the index, (starting from last char -> 'z', then moving left)
        while (!pq.isEmpty()) {
            char ch = pq.poll();
            int currCnt = cnt[ch - 'a'];
            
            // this i was missing (could have got, but i have sem exam tmrw)
            int toRem = Math.min(currCnt, repeatLimit);

            for (int i = 0; i < toRem; i++) {
                sb.append(ch);
            }

            cnt[ch - 'a'] -= toRem;

            if (cnt[ch - 'a'] > 0 && !pq.isEmpty()) {
                char next = pq.poll();
                sb.append(next);
                cnt[next - 'a']--;  // greedily, just decrementing count of next char by only 1
                // becoz, we need lexicographically greatest string
                if (cnt[next - 'a'] > 0) {
                    pq.offer(next);
                }
                // now, we add the curr. character (lexicographically greatest)
                pq.offer(ch);
            }
        }

        return sb.toString();
    }
}
