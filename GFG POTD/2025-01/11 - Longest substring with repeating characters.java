/*
    Given a string s, find the length of the longest substring with all distinct characters. 

    Examples:
    Input: s = "geeksforgeeks"
    Output: 7
    Explanation: "eksforg" is the longest substring with all distinct characters.

    Input: s = "aaa"
    Output: 1
    Explanation: "a" is the longest substring with all distinct characters.

    Input: s = "abcdefabcbb"
    Output: 6
    Explanation: The longest substring with all distinct characters is "abcdef", which has a length of 6.

    Constraints:
    1<= s.size()<=3*10^4
    All the characters are in lowercase.
*/

class Solution {
    // better approach
    public int longestUniqueSubstr(String s) {
        int n = s.length();
        int l = 0;
        int[] lastInd = new int[26];    // last seen index of each character
        // this will skip traversal, will be less that O(N)
        
        java.util.Arrays.fill(lastInd, -1);
        int maxi = 1;
        
        for (int r = 0; r < n; r++) {
            // becoz we need unique chars in the window
            // we start the window, from the next position of the already occuring index
            l = Math.max(l, lastInd[s.charAt(r) - 'a'] + 1);
            
            maxi = Math.max(maxi, (r - l + 1));
            lastInd[s.charAt(r) - 'a'] = r;
        }
        return maxi;
    }
    
    public int longestUniqueSubstr_approach1(String s) {
        // code here
        int n = s.length();
        int l = 0;
        int maxi = 1;
        boolean[] vis = new boolean[26];
        
        for (int r = 0; r < n; r++) {
            while (vis[s.charAt(r) - 'a'] == true) {
                vis[s.charAt(l) - 'a'] = false;
                l++;
            }
            
            vis[s.charAt(r) - 'a'] = true;
            maxi = Math.max(maxi, (r - l + 1));
        }
        return maxi;
    }
}