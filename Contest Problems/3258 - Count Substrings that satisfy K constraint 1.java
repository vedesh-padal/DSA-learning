// EASY
/*
    You are given a binary string s and an integer k.
    A binary string satisfies the k-constraint if either of the following conditions holds:
    The number of 0's in the string is at most k.
    The number of 1's in the string is at most k.
    Return an integer denoting the number of substrings of s that satisfy the k-constraint.

    Example 1:
    Input: s = "10101", k = 1
    Output: 12
    Explanation:
    Every substring of s except the substrings "1010", "10101", and "0101" satisfies the k-constraint.

    Example 2:
    Input: s = "1010101", k = 2
    Output: 25
    Explanation:
    Every substring of s except the substrings with a length greater than 5 satisfies the k-constraint.

    Example 3:
    Input: s = "11111", k = 1
    Output: 15
    Explanation:
    All substrings of s satisfy the k-constraint.

    Constraints:
    1 <= s.length <= 50
    1 <= k <= s.length
    s[i] is either '0' or '1'.
*/

class Solution {

    // optimal: sliding window
    public int countKConstraintSubstrings(String s, int k) {
        int n = s.length();
        // till this index, how many no. of 1s are present
        // once we have no. of 1s, we can find no. of 0s by subtracting from the length
        int[] preSum1 = new int[n];

        preSum1[0] = (s.charAt(0) == '1') ? 1 : 0;
        for (int i = 1; i < n; i++) {
            preSum1[i] = (s.charAt(i) == '1') ? preSum1[i-1] + 1 : preSum1[i-1];
        }

        // System.out.println(Arrays.toString(preSum1));

        int res = 0;
        int l = 0;
        for (int r = 0; r < n; r++) {
            int cnt1 = preSum1[r] - ((l - 1 >= 0) ? preSum1[l-1] : 0);
            int cnt0 = (r - l + 1) - cnt1;

            while (cnt1 > k && cnt0 > k) {
                l++;
                cnt1 = preSum1[r] - ((l - 1 >= 0) ? preSum1[l-1] : 0);
                cnt0 = (r - l + 1) - cnt1;
            }

            res += (r - l + 1);
        }
        return res;
    }

    // =======================================================================
    public int countKConstraintSubstrings_BRUTE(String s, int k) {
        int n = s.length();
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            int num0 = 0, num1 = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1')
                    num1++;
                else
                    num0++;

                if (num0 <= k || num1 <= k)
                    res++;
            }
        }
        return res;
    }
}