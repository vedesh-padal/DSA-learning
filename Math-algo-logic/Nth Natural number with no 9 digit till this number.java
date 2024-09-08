class Solution {

  // Intuition:
  // Linear TC: going through each digit from 1 to infi, 
  // by checking if the number has 9 as a digit
  // with basic each digit division of while loo:
  long findNth1(long n) {
    long count = 0;
    for (long i = 1; i > 0; i++) {
      if (hasNine(i) == false) {
        count++;
        if (count == n) {
          count = i; // assiging the current number to count to return as nth number with no 9 digit till here is found
          break;
        }

      }
    }
    return count;
  }

  private static boolean hasNine(long n) {
    while (n > 0) {
      int rem = (int) (n % 10);
      if (rem == 9)
        return true;
      n /= 10;
    }
    return false;
  }
  // -------------------------------------------------------------------

  // OPTIMAL APPROACH: Intuition
  // Digits of base 2 number varies from 0 to 1
  // Digits of base 10 number varies from 0 to 9
  // Digits of base 10 number varies from 0 to 8

  // by dry running we will find that
  // nth number in base 9 is equivalent to nth number after skipping numbers
  // containing digit 9
  // so we have to find the nth number in base 9 form

  long findNth2(long n) {
    // code here
    long res = 0; // to store the base 9 number
    int p = 1; // to store the position of the digit

    while (n > 0) {
      res = res + p * (n % 9);
      n = n / 9;
      p = p * 10;
    }
    return res;
  }
}