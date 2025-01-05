// MEDIUM
// arrays, prefix-sum, string
/*
    You are given a string s of lowercase English letters and a 2D integer array
    shifts where shifts[i] = [starti, endi, directioni]. For every i, shift the
    characters in s from the index starti to the index endi (inclusive) forward
    if directioni = 1, or shift the characters backward if directioni = 0.

    Shifting a character forward means replacing it with the next letter in the
    alphabet (wrapping around so that 'z' becomes 'a'). Similarly, shifting a
    character backward means replacing it with the previous letter in the
    alphabet (wrapping around so that 'a' becomes 'z').

    Return the final string after all such shifts to s are applied.

    Example 1:
    Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
    Output: "ace"
    Explanation: Firstly, shift the characters from index 0 to index 1 backward.
    Now s = "zac".
    Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
    Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".

    Example 2:
    Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
    Output: "catz"
    Explanation: Firstly, shift the characters from index 0 to index 0 backward.
    Now s = "cztz".
    Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".

    Constraints:
    1 <= s.length, shifts.length <= 5 * 10^4
    shifts[i].length == 3
    0 <= starti <= endi < s.length
    0 <= directioni <= 1
    s consists of lowercase English letters.
*/

class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        // since there are multiple queries, and we have to perform one of the two operations:
        // shift left => -1
        // shift right => +1
        // ==> we can use `LINE SWEEP TECHNIQUE`, that involves creating a diff array, 
        // and based on the range we subtract or add 1 (based on the question), for those ranges
        // then have the prefix Sum, this helps in finding our final result

        int n = s.length();
        int[] diff = new int[n];    
        for (int[] shift: shifts) {
            int start = shift[0];
            int end = shift[1];
            if (shift[2] == 1) {    // shift right
                diff[start]++;
                if (end + 1 < n)
                    diff[end + 1]--;
            }
            else {  // shift[2] == 0    // shift left
                diff[start]--;
                if (end + 1 < n)
                    diff[end + 1]++;
            }
        }

        // now prefixSum
        for (int i = 1; i < n; i++) {
            diff[i] = diff[i-1] + diff[i];
            // diff[i] = diff[i] % 26;
            // if (diff[i] < 0) {
            //     diff[i] += 26;
            // }
        }
        // System.out.println(Arrays.toString(diff));

        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int k = chars[i] - 'a';
            k = ( k + diff[i] ) % 26;
            if (k < 0)
                k += 26;
            chars[i] = (char)(k + 'a');
        }
        // System.out.println(new String(chars));
        return new String(chars);
    }
}