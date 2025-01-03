// HARD
// arrays, back-tracking, recursion

/*
    The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

    Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

    Each solution contains a distinct board configuration of the n-queens'
    placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

    Example 1:
    Input: n = 4
    Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

    Example 2:
    Input: n = 1
    Output: [["Q"]]
    
    Constraints:
    1 <= n <= 9
*/
import java.util.*;

class Solution {

    List<List<String>> res;

    private void solve2(int row, int n, char[][] board, 
        Set<Integer> vertical, Set<Integer> rightDiag, Set<Integer> leftDiag) {
        
        if (row >= n) {
            List<String> newBoard = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                newBoard.add(new String(board[i]));
            }
            res.add(newBoard);
            return;
        }

        for (int col = 0; col < n; col++) {

            if (!vertical.contains(col) && !rightDiag.contains(row + col) && !leftDiag.contains(row - col)) {

                vertical.add(col);
                rightDiag.add(row + col);
                leftDiag.add(row - col);
                board[row][col] = 'Q';

                solve2(row + 1, n, board, vertical, rightDiag, leftDiag);

                board[row][col] = '.';
                vertical.remove(col);
                rightDiag.remove(row + col);
                leftDiag.remove(row - col);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        // we will store what all positions in which queen was placed
        // for column it is straight forward
        // here i, j are indicies of row and column respectively
        // for upward right diagonal, we have a math relation,
        // in that diagonal => (i + j) is equal
        // for upward left diagonal, we have this math relation:
        // in that diagonal => (i - j) is equal
        Set<Integer> vertical = new HashSet<>();
        Set<Integer> rightDiag = new HashSet<>();
        Set<Integer> leftDiag = new HashSet<>();

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        // start from row 0
        solve2(0, n, board, vertical, rightDiag, leftDiag);
        return res;
    }

    // ======================================================================
    private boolean isValid(int row, int col, char[][] board) {
        // vertically upward
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q')
                return false;
        }

        // upward left (diagonally left)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        // upward right (diagonally right)
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    private void solve1(int row, int n, char[][] board) {
        if (row >= n) {
            List<String> newBoard = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                newBoard.add(new String(board[i]));
            }
            res.add(newBoard);
            return;
        }

        // WE CAN IMPROVE THIS VALID POSITION CHECKING function
        // which is currently taking O(N) TC
        for (int col = 0; col < n; col++) {
            // if the current Queen position is valid
            if (isValid(row, col, board)) {
                board[row][col] = 'Q';
                solve1(row + 1, n, board);
                board[row][col] = '.';
            }
        }
    }

    // APPROACH 1 - Queen position validity function Time complexity can be improved
    public List<List<String>> solveNQueens_approach1(int n) {
        res = new ArrayList<>();

        // first, create the board, with no queens placed
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        // starting from row 0
        solve1(0, n, board);
        return res;
    }
}