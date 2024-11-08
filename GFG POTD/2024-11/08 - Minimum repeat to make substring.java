class Solution {

  // nevertheless, this is O(n*m) solution, SC: O(1)
  // optimal would be to use KMP algo.
  // should go through the related article:
  // https://www.geeksforgeeks.org/minimum-number-of-times-a-has-to-be-repeated-such-that-b-is-a-substring-of-it/
  static int minRepeats(String s1, String s2) {

    //// THIS IS LEADING TO TLE DUE TO SOME JAVA RUNTIME ISSUE
    // for (int i = 0; i < s2.length(); i++) {
    //   // if s1 does not contain s2's char
    //   // then there is not point in repeating,
    //   // becoz we wont get that char
    //   if (s1.indexOf(s2.charAt(i)) == -1) {
    //     return -1;
    //   }
    // }

    for (char ch : s2.toCharArray()) {
      if (!s1.contains(String.valueOf(ch))) {
        return -1;
      }
    }

    int cnt = 1;
    StringBuffer sb = new StringBuffer(s1);
    while (sb.length() < s2.length()) {
      sb.append(s1);
      cnt++;
    }

    if (sb.toString().contains(s2)) {
      return cnt;
    }

    // else give another try, by repeating again
    sb.append(s1);
    cnt++;
    if (sb.toString().contains(s2)) {
      return cnt;
    }

    return -1;
  }

  // ==========================================================
  // my Try => gives TLE O(N^2)
  static int minRepeats_MyTry(String s1, String s2) {
    // code here
    int cnt = 1;
    String orig = new String(s1);

    // first check if all the chars in s2 are present in s1
    int[] charCnt2 = new int[26];
    int[] charCnt1 = new int[26];

    for (int i = 0; i < s2.length(); i++) {
      charCnt2[s2.charAt(i) - 'a']++;
    }

    for (int i = 0; i < s1.length(); i++) {
      charCnt1[s1.charAt(i) - 'a']++;
    }

    // System.out.println(Arrays.toString(charCnt1));
    // System.out.println(Arrays.toString(charCnt2));

    for (int i = 0; i < 26; i++) {
      if (charCnt2[i] != 0 && charCnt1[i] == 0) {
        return -1;
      }
    }

    boolean flag = false;

    while (true) {
      if (s1.contains(s2)) {
        flag = true;
        break;
      }

      if (s1.length() > s2.length() + orig.length()) {
        // System.out.println(s1);
        // System.out.println(s2);
        // System.out.println(orig);
        break;
      }

      s1 = s1 + orig;
      cnt++;

    }
    return flag ? cnt : -1;
  }
};