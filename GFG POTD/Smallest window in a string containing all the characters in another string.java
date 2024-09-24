class Solution {
  public static String smallestWindow(String s, String p) {
    int n = s.length(), m = p.length();
    if (m > n)
      return "-1";

    // related to the satisfying window:
    // satisfying window: windows (of s) containing all chars in p (only once -> for smallest)
    int start = -1, end = -1, smallestWindLen = Integer.MAX_VALUE;

    int c1 = 0, c2 = 0; // count of chars of p in each of the strings (s, p)

    int[] cntCharsP = new int[m];
    int[] cntCharsS = new int[n];

     // put the count of each chars in the count array of p
    for (int i = 0; i < m; i++) {
      cntCharsP[p.charAt(i) - 'a']++;

      // if this is the first time this char occurred, 
      // we increment the count (c1)

      // if this is the first time, we should count it
      // so that we will be able to track if each char in p
      // appeared in the substring of s
      if (cntCharsP[p.charAt(i) - 'a'] == 1)
        c1++;
    }

    int l = 0;  // left pointer

    for (int r = 0; r < n; r++) {
      int ch = s.charAt(r);
      
      // we are incrementing of all chars in 's'
      cntCharsS[ch - 'a']++;

      // but, we care for only the chars present in 'p'
      // this checks for only first occurrence
      // if appeared for the firs time, c2 is incremented
      if (cntCharsS[ch - 'a'] == cntCharsP[ch - 'a']) {
        c2++;
      }

      // if we have found a window consisting of all 
      if (c1 == c2) {
        while (cntCharsS[s.charAt(r) - 'a'] > cntCharsP[s.charAt(r) - 'a']) {
          cntCharsS[s.charAt(l) - 'a']--;
          l++;
        }

        // now, we have the window with only 1 char of each of that in string p
        if (smallestWindLen > (r - l + 1))  {
          start = l;
          end = r;
          smallestWindLen = (r - l + 1);
        }
      }
    }

    if (start == -1)
      return "-1";

    return s.substring(start, end + 1);
  }
}