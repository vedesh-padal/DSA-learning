// using XOR operator

import java.util.List;

class Solution {
  static List<Integer> get(int a, int b) {
    // code here
    a = a ^ b; // a now has a^ab
    b = a ^ b; // => a^b ^ b => a ==> b now has a
    a = a ^ b; // a^b ^ a ==> a now has b

    return List.of(a, b);
  }
}