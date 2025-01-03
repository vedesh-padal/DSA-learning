// MEDIUM
// strings, two-pointer

// In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a
// move consists of either replacing one occurrence of "XL" with "LX", or
// replacing one occurrence of "RX" with "XR". Given the starting string start
// and the ending string result, return True if and only if there exists a
// sequence of moves to transform start to result.

// Example 1:
// Input: start = "RXXLRXRXL", result = "XRLXXRRLX"
// Output: true
// Explanation: We can transform start to result following these steps:
// RXXLRXRXL ->
// XRXLRXRXL ->
// XRLXRXRXL ->
// XRLXXRRXL ->
// XRLXXRRLX

// Example 2:
// Input: start = "X", result = "L"
// Output: false

// Constraints:
// 1 <= start.length <= 104
// start.length == result.length
// Both start and result will only consist of characters in 'L', 'R', and 'X'.

class Solution {
    public boolean canTransform(String start, String result) {
        int n = start.length();
        int cntSL = 0, cntSR = 0, cntTL = 0, cntTR = 0;

        for (int i = 0; i < n; i++) {
            if (start.charAt(i) == 'L') cntSL++;
            if (start.charAt(i) == 'R') cntSR++;
            if (result.charAt(i) == 'L') cntTL++;
            if (result.charAt(i) == 'R') cntTR++;
        }

        if (cntSL != cntTL || cntTR != cntSR) {
            return false;
        }

        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X')
                i++;
            while (j < n && result.charAt(j) == 'X') 
                j++;

            if (i < n && j < n) {
                char s = start.charAt(i);
                char r = result.charAt(j);

                if (s != r)
                    return false;

                // L cannot be moved further right, becoz of another piece
                if (s == 'L' && i < j)
                    return false;

                // R cannot be moved further left, becoz of another piece
                if (r == 'R' && i > j)
                    return false;
            }
            i++;
            j++;
        }

        return true;
    }
}