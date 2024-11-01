class Solution {

  // Function to return sum of count of set bits in the integers from 1 to n.
  public static int countSetBits(int n) {

    if (n == 0)
      return 0;

    // Your code here
    int x = findGreaterPowerLessThanOrEqualN(n);

    int bitsUpto2RaiseX = (1 << (x - 1)) * x;

    int msbFrom2RaiseXToN = n - (1 << x) + 1;

    // remainng number we should call for recursively
    int rest = n - (1 << x);

    // we call it recursively
    int ans = bitsUpto2RaiseX + msbFrom2RaiseXToN + countSetBits(rest);

    return ans;

  }

  private static int findGreaterPowerLessThanOrEqualN(int n) {
    int x = 0; // here x represents the power
    // you are basically checking all the powers,
    // once it is greater than n, we break

    while ((1 << x) <= n)
      x++;

    return (x - 1); // since we crossed one, and if we would
    // have returned x, it would be 2^x > n, so we return x - 1

  }
}