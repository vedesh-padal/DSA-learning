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
}