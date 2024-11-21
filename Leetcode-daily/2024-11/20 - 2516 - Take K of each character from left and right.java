// MEDIUM
// sliding-window, strings, hashMap

// You are given a string s consisting of the characters 'a', 'b', and 'c' and a
// non-negative integer k. Each minute, you may take either the leftmost
// character of s, or the rightmost character of s.

// Return the minimum number of minutes needed for you to take at least k of
// each character, or return -1 if it is not possible to take k of each character.

// Example 1:
// Input: s = "aabaaaacaabc", k = 2
// Output: 8
// Explanation:
// Take three characters from the left of s. You now have two 'a' characters,
// and one 'b' character.
// Take five characters from the right of s. You now have four 'a' characters,
// two 'b' characters, and two 'c' characters.
// A total of 3 + 5 = 8 minutes is needed.
// It can be proven that 8 is the minimum number of minutes needed.

// Example 2:
// Input: s = "a", k = 1
// Output: -1
// Explanation: It is not possible to take one 'b' or 'c' so return -1.

// Constraints:
// 1 <= s.length <= 105
// s consists of only the letters 'a', 'b', and 'c'.
// 0 <= k <= s.length

class Solution {
    // THOUGHTS:
    // we cannot be greedy here, becoz, we dont know what can be the next chars
    // that we can get when we move forward or back when using two pointers
    // we would be confused which one to pick

    // So, we could use Recursion (5 variables: i, j, cntA, cntB, cntC)
    // even if we memoize it, it would be: O(n^5), given the constraints, it would
    // be O(10^25)
    // which is obviously bad

    // here comes, Sliding window:
    // If it seems difficult: always INVERT THE PROBLEM, and approach it

    // INTUITION:
    // since, we can pick either from leftmost or rightmost at each minute,
    // and minimize no. of picks (minutes)
    // Invert thought process:
    // maximize the middle part substring, such that outside part (left and right)
    // should have atleast 'k' count of a, b, c
    // then our answer would be `len(s) - maxWindowSize`

    public int takeCharacters(String s, int k) {
        int n = s.length();

        // count of chars outside the window
        int[] cnt = new int[3];

        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        int mini = Math.min(cnt[0], Math.min(cnt[1], cnt[2]));

        // if we dont have atleast k chars of a,b,c in string, s, we return -1
        if (mini < k)
            return -1;

        int maxWindow = Integer.MIN_VALUE;

        // sliding window
        int l = 0;
        for (int r = 0; r < n; r++) {
            // expand the window
            // since this window does not represent our answer
            // and expanding means, we are reducing the no. of chars outside, so
            // we decrement the corresponding count
            cnt[s.charAt(r) - 'a']--;

            mini = Math.min(cnt[0], Math.min(cnt[1], cnt[2]));
            // which means, this middle part that we have now, is not possible
            // since we need count of a,b,c should be atleast k
            // so, we shrink the middle window and move left pointer
            while (mini < k) {
                // shrinking => including the character on the left and right side (answer)
                cnt[s.charAt(l) - 'a']++;
                l++;
                mini = Math.min(cnt[0], Math.min(cnt[1], cnt[2]));
            }
            // once, we have the valid middle window (which means outer left and right
            // and right parts have a,b,c count atleast k), we update maxWindow size
            maxWindow = Math.max(maxWindow, (r - l + 1));
        }

        return (n - maxWindow);
    }
}