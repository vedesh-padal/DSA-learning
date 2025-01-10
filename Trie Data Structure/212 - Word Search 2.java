// HARD
// trie, string, back-tracking, matrix

/*
    Given an m x n board of characters and a list of strings words, return all words on the board.

    Each word must be constructed from letters of sequentially adjacent cells,
    where adjacent cells are horizontally or vertically neighboring. The same
    letter cell may not be used more than once in a word.

    Example 1:
    Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
    Output: ["eat","oath"]
    Example 2:
    Input: board = [["a","b"],["c","d"]], words = ["abcb"]
    Output: []

    Constraints:
    m == board.length
    n == board[i].length
    1 <= m, n <= 12
    board[i][j] is a lowercase English letter.
    1 <= words.length <= 3 * 10^4
    1 <= words[i].length <= 10
    words[i] consists of lowercase English letters.
    All the strings of words are unique.
*/
import java.util.*;

class Trie {
    static class Node {
        Node[] links = new Node[26];
        boolean flag = false;   // to indicate if the word ended here
        String word = "";   // if the word ended, we will set it with the word

        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        Node get(char ch) {
            return links[ch - 'a'];
        }

        boolean isEnd() {
            return flag;
        }

        void setEnd(String word) {
            flag = true;
            this.word = word;
        }

        void resetEnd() {
            flag = false;
        }
    }

    Node root;
    Trie() {
        root = new Node();
    }

    void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) {
                node.put(word.charAt(i), new Node());
            }
            node = node.get(word.charAt(i));
        }
        node.setEnd(word);
    }
}

class Solution {
    int m, n;
    List<String> res;

    private boolean isValid(int i, int j) {
        return (i >= 0 && i < m && j >= 0 && j < n);
    }

    private static final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private boolean dfs(char[][] board, int r, int c, Trie.Node node) {
        if (node.isEnd()) {
            res.add(node.word);
            // return true; // reason explained below
            node.resetEnd();
        }
        
        char origChar = board[r][c];
        board[r][c] = '$';
        // boolean found = false;

        for (int[] dir: dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (isValid(nr, nc) && board[nr][nc] != '$') {
                if (node.containsKey(board[nr][nc])) {
                    // if (dfs(board, nr, nc, node.get(board[nr][nc]))) {
                    //     // found = true;    ==> should not early return
                    //     // edge case: ["oa", "oaa"]
                    // }
                    dfs(board, nr, nc, node.get(board[nr][nc]));
                    // instead mark as false for the isEnd, and continue to explore
                }
            }
        }
        board[r][c] = origChar;
        // return found;
        return false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        // cleverly not using visited boolean array
        // rather setting the visited grid positions with a special character '$'
        m = board.length;
        n = board[0].length;
        res = new ArrayList<>();

        Trie trie = new Trie();
        for (String word: words) {
            trie.insert(word);
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // board[i][j] != '$' ==> MEANS VISITED
                if (board[i][j] != '$' && trie.root.containsKey(board[i][j])) {
                    dfs(board, i, j, trie.root.get(board[i][j]));
                }
            }
        }
        return res;
    }
}