// MEDIUM - EASY
// arrays, hashtable

/*
    You are given a 0-indexed integer array arr, and an m x n integer matrix mat.
    arr and mat both contain all the integers in the range [1, m * n].

    Go through each index i in arr starting from index 0 and paint the cell in
    mat containing the integer arr[i].

    Return the smallest index i at which either a row or a column will be
    completely painted in mat.

    Example 1:
    Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
    Output: 2
    Explanation: The moves are shown in order, and both the first row and second
    column of the matrix become fully painted at arr[2].

    Example 2:
    Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
    Output: 3
    Explanation: The second column becomes fully painted at arr[3].

    Constraints:
    m == mat.length
    n = mat[i].length
    arr.length == m * n
    1 <= m, n <= 10^5
    1 <= m * n <= 10^5
    1 <= arr[i], mat[r][c] <= m * n
    All the integers of arr are unique.
    All the integers of mat are unique.
*/

class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        // probably not the best way to do  => but, turns out after submission this is the way to go
        // hashing each number with it's co-ord pos
        // Map<Integer, int[]> hmap = new HashMap<>();  // using hashmap takes time, becoz of collisions
        int m = mat.length, n = mat[0].length;
        int len = m*n;
        int[] rowPos = new int[len + 1];
        int[] colPos = new int[len + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // hmap.put(mat[i][j], new int[]{i, j});
                rowPos[mat[i][j]] = i;
                colPos[mat[i][j]] = j;
            }
        }

        int[] row = new int[m];
        int[] col = new int[n];
        int maxiR = Integer.MIN_VALUE;  // if this becomes m, we have found all painted row
        int maxiC = Integer.MIN_VALUE;  // if this becomes n, we have found all painted col

        for (int i = 0; i < len; i++) {
            // int rPos = hmap.get(arr[i])[0];
            // int cPos = hmap.get(arr[i])[1];
            int rPos = rowPos[arr[i]];
            int cPos = colPos[arr[i]];

            row[rPos]++;
            maxiR = Math.max(maxiR, row[rPos]);
            if (maxiR == n)
                return i;

            col[cPos]++;
            maxiC = Math.max(maxiC, col[cPos]);
            if (maxiC == m)
                return i;
        }
        return -1;  // will never reach here
    }
}