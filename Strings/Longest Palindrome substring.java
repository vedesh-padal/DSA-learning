class Solution {

  public String longestPalindrome(String s) {
    if (s.length() <= 1)
      return s;

    int start = 0, end = 0;

    for (int i = 0; i < s.length(); i++) {

      // since the string can be of odd length or even length, we have (2n-1) possible
      // centers to expand around

      int len1 = expandAroundCenter(s, i, i); // for odd length string
      int len2 = expandAroundCenter(s, i, i + 1); // for even length string, expanding around the gap between elements
      int len = Math.max(len1, len2);

      // update start index, becoz we found a longer palindrome substring when
      // expanded around this center
      // ( can be the `index` [ for the odd length string] or the `gap` [ between 2
      // elements for the even length string ] )
      if (len > end - start) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }

    }
    return s.substring(start, end + 1);
  }

  private static int expandAroundCenter(String s, int l, int r) {
    // expand until l and r characters are equal and until they are do not go out of
    // bound index
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
      l--;
      r++;
    }
    return (r - l - 1); // -1 because we have incremented by 1 index on both sides, we don't have a match, so we decrement by 1
  }
}