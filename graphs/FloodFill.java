package graphs;

public class FloodFill {
  private void dfs(int row, int col, int[][] ans, int[][] img, int[] delRow, int[] delCol, int newColor, int iniColor)    {
      ans[row][col] = newColor;
      int n = img.length, m = img[0].length;

      for (int i=0; i<4; i++) {
          int nrow = row + delRow[i];
          int ncol = col + delCol[i];

          if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
              img[nrow][ncol] == iniColor && ans[nrow][ncol] != newColor) {
                  ans[nrow][ncol] = newColor;
                  dfs(nrow, ncol, ans, img, delRow, delCol, newColor, iniColor);
              }
      }

  }

  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
      int iniColor = image[sr][sc];
      int[][] ans = image;

      int[] delRow = {-1, 0, 1, 0};
      int[] delCol = {0, 1, 0, -1};
      dfs(sr, sc, ans, image, delRow, delCol, color, iniColor);

      return ans;
  }
}