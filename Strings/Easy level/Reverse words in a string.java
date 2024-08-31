class Solution {

  // THE GOD LEVEL APPROACH
  public String reverseWords(String s) {
    if (s == null)
      return null;

    // convert the string to a char array
    char[] arr = s.toCharArray();
    int n = arr.length;

    // reverse the whole string
    reverse(arr, 0, n - 1);
    // reverse each word in the reversed string
    reverseWords(arr, n);
    // clean up spaces
    return cleanSpaces(arr, n);

  }

  public void reverse(char[] a, int i, int j) {
    while (i < j) {
      char t = a[i];
      a[i++] = a[j];
      a[j--] = t;
    }
  }

  public void reverseWords(char[] a, int n) {
    int i = 0; // to keep track of the traversing in the array of chars
    int j = 0; // to keep track of the non-space chars
    while (i < n) {
      while (i < j || i < n && a[i] == ' ')
        i++; // skip the spaces
      while (j < i || j < n && a[j] != ' ')
        j++; // skips non-spaces and gets till the index of
      reverse(a, i, j - 1); // reverse this specific word
    }
  }

  public String cleanSpaces(char[] a, int n) {
    int i = 0; // to keep track of the actual storing index with cleaned spces
    int j = 0; // to traverse the array
    while (j < n) {
      while (j < n && a[j] == ' ')
        j++; // skip spaces
      while (j < n && a[j] != ' ')
        a[i++] = a[j++]; // put the non-space chars at correct positions
      while (j < n && a[j] == ' ')
        j++; // skip the spaces in between words
      if (j < n)
        a[i++] = ' '; // after skipping all the spaces, add a space at the end of the word manually
    }
    return new String(a).substring(0, i);
  }

  // -----------------------------------------------------------------
  // simple code, but using string functions [ BUT, NOT PREFERED IN INTERVIEWS ]
  public String reverseWords1(String s) {
    String[] arr = s.trim().split("\\s+");
    int i = 0, j = arr.length - 1;
    // reverse the words
    while (i < j) {
      String t = arr[j];
      arr[j--] = arr[i];
      arr[i++] = t;
    }
    // convert them back in string form
    return String.join(" ", arr);
  }

  ////// this doesn't work for leading, trailing, spaces inbetween words
  public String reverseWords2(String s) {
    int i = 0, last = s.length() - 1;
    String temp = "", ans = "";

    while (i <= last) {
      char ch = s.charAt(i);
      if (ch != ' ') {
        temp += ch;
      } else if (ch == ' ') {
        if (!ans.equals("")) {
          ans = temp + " " + ans;
        } else {
          ans = temp;
        }
        temp = "";
      }
      i++;
    }

    // for the last word
    if (!temp.equals("")) {
      if (!ans.equals("")) {
        ans = temp + " " + ans;
      } else {
        ans = temp;
      }
    }
    return ans;
  }
}