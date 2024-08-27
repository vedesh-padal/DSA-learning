class Solve {
  int[] findTwoElement(int arr[], int n) {
    // long Sn1 = 0, Sn2 = 0;
    long Sn1 = (long) n * (n + 1) / 2;
    long Sn2 = (long) n * (n + 1) * (2 * n + 1) / 6;

    long S1 = 0, S2 = 0;
    for (int i = 0; i < n; i++) {
      S1 += (long) arr[i];
      S2 = S2 + ((long) arr[i] * (long) arr[i]);
    }

    long x_y = S1 - Sn1;
    long xy = S2 - Sn2;
    xy = xy / x_y;
    long x = (x_y + xy) / 2;
    long y = x - x_y;

    return new int[] { (int) x, (int) y };
  }
}