// 19-10-2024
// MEDIUM
// string, recursion, simulation

// Given two positive integers n and k, the binary string Sn is formed as follows:

// S1 = "0"
// Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
// Where + denotes the concatenation operation, reverse(x) returns the reversed
// string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).

// For example, the first four strings in the above sequence are:

// S1 = "0"
// S2 = "011"
// S3 = "0111001"
// S4 = "011100110110001"
// Return the kth bit in Sn. It is guaranteed that k is valid for the given n.

// Example 1:
// Input: n = 3, k = 1
// Output: "0"
// Explanation: S3 is "0111001".
// The 1st bit is "0".

// Example 2:
// Input: n = 4, k = 11
// Output: "1"
// Explanation: S4 is "011100110110001".
// The 11th bit is "1".

// Constraints:
// 1 <= n <= 20
// 1 <= k <= 2n - 1

class Solution {

  public char findKthBit(int n, int k) {

    // base case, starts building up from this
    if (n == 1)
      return '0';

    // of the resulting string
    int len = (1 << n) - 1; // 2^n - 1

    if (k < Math.ceil(len / 2.0)) {
      // jo `n` ka kth bit hey, vo hi `n-1` ka kth bit hoga
      return findKthBit(n - 1, k);
    }

    else if (k == Math.ceil(len / 2.0)) {
      return '1'; // becoz middle char and middle char is always 1 (becoz recursively built)
    }

    else {
      // `len - k - 1` becoz the right part is reverse of the left part
      char ch = findKthBit(n - 1, (len - (k - 1))); // handle reversed
      // now, we have to flip the bit
      return (ch == '0') ? '1' : '0'; // handle flipped bit
    }

  }
  
  // =============================================
  // MY NAIVE TRY
  private String getRevStr(String s) {
    StringBuffer sb = new StringBuffer();
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == '1')
        sb.append("0");
      else
        sb.append("1");
    }
    return sb.toString();
  }

  private String find(int n, String prevStr) {
    if (n == 1)
      return prevStr;

    String revStr = getRevStr(prevStr);

    return find(n - 1, prevStr + "1" + revStr);
  }

  public char findKthBit_MY_TRY(int n, int k) {

    String resStr = find(n, "0");
    return resStr.charAt(k - 1);
  }
}