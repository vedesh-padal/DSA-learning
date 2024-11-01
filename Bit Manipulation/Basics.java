class Solution {

  // i is 1-indexed
  public void getSetClearAtPosition(int num, int i) {
    long a = (num >> (i - 1)) & 1;
    int b = num | (1 << (i - 1));
    int c = num & ~(1 << (i - 1));

    System.out.print(a + " " + b + " " + c);
  }

  public boolean kthBitIsSetOrNot(int n, int k) {
    // Your code here
    return ((n >> k) & 1) == 1 ? true : false;
  }

  public String oddOrEven(int n) {
    return (n & 1) == 1 ? "odd" : "even";
  }

  public static int countSetBits(int n) {
    int cnt = 0;
    while (n > 0) {
      cnt += n & 1; // check if right most bit is set
      n = n >> 1; // right shift
      // and do this till the number becomes 0
    }
    return cnt;
  }

}