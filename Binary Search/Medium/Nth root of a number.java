class Solution {
  private static int func(int base, int exp) {
    int ans = 1;
    while (exp > 0) {
      if (exp % 2 == 1) {
        ans = ans * base;
        exp--;
      } else {
        base = base * base;
        exp /= 2;
      }
    }
    return ans;
  }

  // using linear search approach
  public int NthRoot1(int n, int m) {
    // code here
    for (int i = 1; i <= m; i++) {
      if (func(i, n) == m)
        return i;
    }
    return -1;
  }
  
  // -------------------------------------------------------
  
  private static int func(int mid, int n, int m) {
    long res = 1;
    for (int i = 1; i <= n; i++) {
      res = res * mid;
      if (res > m)
        return 2;
    }
    if (res == m)
      return 1;
    return 0;
  }

  // using binary search approach
  public int NthRoot2(int n, int m) {
    // code here
    int l = 1, h = m;

    while (l <= h) {
      int mid = l + (h - l) / 2;
      int midPn = func(mid, n, m);
      if (midPn == 1)
        return mid;
      else if (midPn == 2)
        h = mid - 1;
      else
        l = mid + 1;
    }
    return -1;
  }
}