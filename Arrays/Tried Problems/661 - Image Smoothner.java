// An image smoother is a filter of the size 3 x 3 that can be applied to each
// cell of an image by rounding down the average of the cell and the eight
// surrounding cells (i.e., the average of the nine cells in the blue smoother).

// If one or more of the surrounding cells of a cell is not present, we do not
// consider it in the average (i.e., the average of the four cells in the red smoother).

// Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.

class Solution {

    private boolean isValid(int i, int j, int m, int n) {
        return (i >= 0 && i < m && j >= 0 && j < n);
    }

    public int[][] imageSmoother(int[][] img) {
        // complete 3x3 smoothner co-ordinate positions relative to curr position
        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int m = img.length, n = img[0].length;

        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                int sum = 0;
                for (int[] dir: dirs) {
                    int nrow = i + dir[0];
                    int ncol = j + dir[1];
                    if (isValid(nrow, ncol, m, n)) {
                        cnt++;
                        sum += img[nrow][ncol];
                    }
                }
                res[i][j] = sum / cnt;  // floor automatically
            }
        }
        return res;
    }
}