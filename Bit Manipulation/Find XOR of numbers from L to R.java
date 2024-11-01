class Solution {
    
  @SuppressWarnings("unused")
  // but this is O(N)
  private static int xor_On(int n)   {
      int x = 0;
      for (int i = 1; i <= n; i++)
          x = x ^ i;
          
      return x;
  }
  
  // we will notice a pattern
  /*
      n = 1:   1              1
      n = 2: 1^2              3
      n = 3: 1^2^3            0
      n = 4: 1^2^3^4          4
      n = 5: 1^2^3^4^5        1
      n = 6:                  7
      n = 7:                  0
      n = 8:                  8
      n = 9:                  1
      n = 10:                 11
      
  */
  
  // we check the last digit, remainder when divided by 4
  // O(1)
  private static int xorPattern(int num)  {
      if (num % 4 == 1)
          return 1;
      else if (num % 4 == 2)
          return (num + 1);
      else if (num % 4 == 3)
          return 0;
      else    // completely divisible by 4
          return num;
  }
  
  
  public static int findXOR(int l, int r) {
      
      // this will xor the common elements in both
      // resulting in the xor of our range
      // return xor(r) ^ xor(l - 1);
      
      return xorPattern(r) ^ xorPattern(l - 1);
      
  }
}