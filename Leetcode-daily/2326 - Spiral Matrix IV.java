class ListNode {
  int val;
  ListNode next;
  ListNode() {
  }
  ListNode(int val) {
    this.val = val;
  }
  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  public int[][] spiralMatrix(int m, int n, ListNode head) {
    int[][] mat = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++)
        mat[i][j] = -1;
    }
    int left = 0, top = 0, bottom = m - 1, right = n - 1;
    boolean end = false;
    ListNode temp = head;

    while (left <= right && top <= bottom && temp != null) {
      for (int i = left; i <= right; i++) {
        if (temp != null) {
          mat[top][i] = temp.val;
          temp = temp.next;
        } else {
          end = true;
          break;
        }
      }
      if (end)
        break;
      top++;

      for (int i = top; i <= bottom; i++) {
        if (temp != null) {
          mat[i][right] = temp.val;
          temp = temp.next;
        } else {
          end = true;
          break;
        }
      }
      if (end)
        break;
      right--;

      if (top <= bottom) {
        for (int i = right; i >= left; i--) {
          if (temp != null) {
            mat[bottom][i] = temp.val;
            temp = temp.next;
          } else {
            end = true;
            break;
          }
        }
        bottom--;
      }
      if (end)
        break;
      if (left <= right) {
        for (int i = bottom; i >= top; i--) {
          if (temp != null) {
            mat[i][left] = temp.val;
            temp = temp.next;
          } else {
            end = true;
            break;
          }
        }
        left++;
      }
      if (end)
        break;

    }
    return mat;
  }
}