// You are given an m x n matrix of characters box representing a side-view of a
// box. Each cell of the box is one of the following:

// A stone '#'
// A stationary obstacle '*'
// Empty '.'
// The box is rotated 90 degrees clockwise, causing some of the stones to fall
// due to gravity. Each stone falls down until it lands on an obstacle, another
// stone, or the bottom of the box. Gravity does not affect the obstacles'
// positions, and the inertia from the box's rotation does not affect the
// stones' horizontal positions.

// It is guaranteed that each stone in box rests on an obstacle, another stone,
// or the bottom of the box.

// Return an n x m matrix representing the box after the rotation described above.

// Input: box = [["#",".","#"]]
// Output: [["."],
// ["#"],
// ["#"]]

// Input: box = [["#",".","*","."],
//               ["#","#","*","."]]
// Output: [["#","."],
//          ["#","#"],
//          ["*","*"],
//          [".","."]]

// Input: box = [["#","#","*",".","*","."],
// ["#","#","#","*",".","."],
// ["#","#","#",".","#","."]]
// Output: [[".","#","#"],
// [".","#","#"],
// ["#","#","*"],
// ["#","*","."],
// ["#",".","*"],
// ["#",".","."]]

// Constraints:
// m == box.length
// n == box[i].length
// 1 <= m, n <= 500
// box[i][j] is either '#', '*', or '.'.

class Solution {
    public char[][] rotateArray(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        char[][] res = new char[n][m];

        // transpose
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = box[j][i];
            }
        }

        // reverse the rows for 90 degree rotation (clockwise)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m/2; j++) {
                char t = res[i][j];
                res[i][j] = res[i][m - j - 1];
                res[i][m - j - 1] = t;
            }
        }

        // now gravity effect
        for (int j = 0; j < m; j++) {
            int pos = n-1;
            for (int i = n-1; i >= 0; i--) {
                // we have encountered an obstacle we reset
                // the position such that it is just above
                // BECOZ, GIVEN: Gravity does not effect obstacle positiosn
                if (res[i][j] == '*') {
                    pos = i - 1;
                    continue;
                }

                if (res[i][j] == '#') {
                    res[i][j] = '.';
                    res[pos][j] = '#';
                    pos--;
                }
            }
        }
        return res;
    }
}