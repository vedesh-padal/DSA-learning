import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class N_Queen_Hard {

    // static void dfs(int col, char[][] board, List<List<String>> res) {
    // if (col == board.length) {
    // res.add(construct(board));
    // return;
    // }
    // for (int row=0; row < board.length; row++) {
    // if (validate(board, row, col)) {
    // board[row][col] = 'Q';
    // dfs(col+1, board, res);
    // board[row][col] = '.';
    // }
    // }
    // }

    // static boolean validate(char[][] board, int row, int col) {
    // int dupRow = row;
    // int dupCol = col;

    // // left upper diagonal checking logic
    // while (row >= 0 && col >= 0) {
    // if (board[row][col] == 'Q') return false; // because there already exists a
    // queen
    // row--;
    // col--;
    // }

    // row = dupRow;
    // col = dupCol;

    // // left row checking logic
    // while (col >= 0) {
    // if (board[row][col] == 'Q') return false;
    // col--;
    // }

    // row = dupRow;
    // col = dupCol;

    // // left lower diagonal checking logic
    // while (col >= 0 && row < board.length) {
    // if (board[row][col] == 'Q') return false;
    // col--;
    // row++;
    // }

    // // if all of them do not have any Queen, then we say that this position is
    // safe to fill
    // return true;
    // }

    static List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }

    static void solve(int col, char[][] board, List<List<String>> res, int[] leftRow, int[] lowerDiagonal,
            int[] upperDiagonal) {
        if (col == board.length) {
            res.add(construct(board));
            return;
        }

        for (int row = 0; row < board.length; row++) {
            if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0
                    && upperDiagonal[board.length - 1 + col - row] == 0) {
                board[row][col] = 'Q';
                leftRow[row] = 1;
                lowerDiagonal[row + col] = 1;
                upperDiagonal[board.length - 1 + col - row] = 1;
                solve(col + 1, board, res, leftRow, lowerDiagonal, upperDiagonal);
                board[row][col] = '.';
                lowerDiagonal[row + col] = 0;
                upperDiagonal[board.length - 1 + col - row] = 0;
                leftRow[row] = 0;
            }
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        // first we create the board
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();

        // APPRACH - 1 (brute force)
        // dfs(0, board, res); // here 0 is the column number
        // return res;

        // APPROACH - 2 (efficient)
        int[] leftRow = new int[n];
        int[] upperDiagonal = new int[2 * n - 1];
        int[] lowerDiagonal = new int[2 * n - 1];
        solve(0, board, res, leftRow, upperDiagonal, lowerDiagonal);

        return res;
    }

    public static void main(String args[]) {
        int N = 8;
        List<List<String>> queen = solveNQueens(N);
        int i = 1;
        for (List<String> it : queen) {
            System.out.println("Arrangement " + i);
            for (String s : it) {
                System.out.println(s);
            }
            System.out.println();
            i += 1;
        }
        System.out.println("Total number of Arrangements: " + queen.size());
    }
}