import java.util.*;

class Solution {

  // to generate till 'numRows'th row:
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 1; i <= numRows; i++) {
      ans.add(generateRow(i));
    }
    return ans;
  }

  private List<Integer> generateRow(int r) {
    List<Integer> al = new ArrayList<>();
    al.add(1);
    int col = 1;
    for (int i = 1; i < r; i++) {
      col = col * (r - i);
      col = col / (i);
      al.add((int) col);
    }
    return al;
  }



  // ---------------------------------------------
  // to print the nth row and rth col element

  public static long nCr(int n, int r) {
    long res = 1;

    // calculating nCr:
    for (int i = 0; i < r; i++) {
      res = res * (n - i);
      res = res / (i + 1);
    }
    return res;
  }

  public static int pascalTriangle(int r, int c) {
    int element = (int) nCr(r - 1, c - 1);
    return element;
  }

  public static void main(String[] args) {
    int r = 5; // row number
    int c = 3; // col number
    int element = pascalTriangle(r, c);
    System.out.println("The element at position (r,c) is: " + element);
  }
}
