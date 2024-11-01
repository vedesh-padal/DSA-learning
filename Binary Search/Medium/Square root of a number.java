class Solution {
  long floorSqrt(long n) {
    // Your code here
    long l = 1, h = n;
    long ans = 0;
    while (l <= h) {
      long mid = l + (h - l) / 2;
      if (mid * mid <= (long) n) {
        ans = mid;
        l = mid + 1;
      } else {
        h = mid - 1;
      }
    }
    return ans;
  }

  public int mySqrt(int x) {
    // binary search
    if (x == 0 || x == 1)
      return x;

    int start = 1;
    int end = x;
    int mid;
    while (start <= end) {
      mid = start + (end - start) / 2;

      if ((long) mid * mid > (long) x)
        end = mid - 1;

      else if (mid * mid == x)
        return mid;
        
      else
        start = mid + 1;
    }

    return Math.round(end);
  }
}