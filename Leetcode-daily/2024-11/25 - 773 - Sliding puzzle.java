// HARD
// matrix, arrays, BFS

/* 
    On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square 
    represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

    The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

    Given the puzzle board board, return the least number of moves required so that the 
    state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

    Input: board = [[1,2,3],[4,0,5]]
    Output: 1
    Explanation: Swap the 0 and the 5 in one move.

    Input: board = [[1,2,3],[5,4,0]]
    Output: -1
    Explanation: No number of moves will make the board solved.

    Input: board = [[4,1,2],[5,0,3]]
    Output: 5
    Explanation: 5 is the smallest number of moves that solves the board.
    An example path:
    After move 0: [[4,1,2],[5,0,3]]
    After move 1: [[4,1,2],[0,5,3]]
    After move 2: [[0,1,2],[4,5,3]]
    After move 3: [[1,0,2],[4,5,3]]
    After move 4: [[1,2,0],[4,5,3]]
    After move 5: [[1,2,3],[4,5,0]]

    Constraints:
    - board.length == 2
    - board[i].length == 3
    - 0 <= board[i][j] <= 5
    - Each value board[i][j] is unique.

*/

import java.util.*;

class Solution {
    public int slidingPuzzle(int[][] board) {
        StringBuilder start = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                start.append(board[i][j]);
            }
        }

        String target = "123450";

        Queue<String> q = new LinkedList<>();
        q.offer(start.toString());

        Map<Integer, int[]> hmap = new HashMap<>();
        hmap.put(0, new int[] { 1, 3 });
        hmap.put(1, new int[] { 0, 2, 4 });
        hmap.put(2, new int[] { 1, 5 });
        hmap.put(3, new int[] { 0, 4 });
        hmap.put(4, new int[] { 1, 3, 5 });
        hmap.put(5, new int[] { 2, 4 });

        Set<String> vis = new HashSet<>();
        vis.add(start.toString());

        int level = 0;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String curr = q.poll();

                if (curr.equals(target)) {
                    return level;
                }

                int indexOfZero = curr.indexOf('0');
                for (int swapInd : hmap.get(indexOfZero)) {
                    char[] newStateArr = curr.toCharArray();

                    // swap positions
                    char temp = newStateArr[swapInd];
                    newStateArr[swapInd] = newStateArr[indexOfZero];
                    newStateArr[indexOfZero] = temp;

                    String newState = new String(newStateArr);

                    // if not visited, add to the queue
                    if (!vis.contains(newState)) {
                        q.offer(newState);
                        vis.add(newState);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}