class Solve {
  int[] findTwoElement(int arr[]) {
    // code here
    // x => repeating number
    // y => missing number

    int n = arr.length;
    long sumN = (long) n * (n + 1) / 2;
    long sumN2 = (long) n * (n + 1) * (2 * n + 1) / 6;

    long sn = 0;
    long sn2 = 0;
    for (int i = 0; i < n; i++) {
      sn += (long) arr[i];
      sn2 = sn2 + ((long) arr[i] * (long) arr[i]);
    }

    // x - y = (n * (n + 1) / 2) - sum of nums in array
    // x^2 - y^2 = n*(n+1)*(2n+1) / 6 - sum of squares of nums in array

    long x_y = sn - sumN;
    long x2_y2 = sn2 - sumN2;

    long xPlusY = x2_y2 / x_y;

    long x = (xPlusY + x_y) / 2; // repeating number
    long y = x - x_y; // missing number

    return new int[] { (int) x, (int) y };
  }
}