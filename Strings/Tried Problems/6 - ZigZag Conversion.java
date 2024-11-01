// The string "PAYPALISHIRING" is written in a zigzag pattern on a given
// number of rows like this: (you may want to display this pattern in a 
// fixed font for better legibility)

// P   A   H   N
// A P L S I I G
// Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"

// Write the code that will take a string and make this conversion given a number of rows:

// string convert(string s, int numRows);

// Example 1:
// Input: s = "PAYPALISHIRING", numRows = 3
// Output: "PAHNAPLSIIGYIR"

// Example 2:
// Input: s = "PAYPALISHIRING", numRows = 4
// Output: "PINALSIGYAHRPI"
// Explanation:
// P     I    N
// A   L S  I G
// Y A   H R
// P     I

// Example 3:
// Input: s = "A", numRows = 1
// Output: "A"

// Constraints:
// 1 <= s.length <= 1000
// s consists of English letters (lower-case and upper-case), ',' and '.'.
// 1 <= numRows <= 1000

import java.util.List;
import java.util.ArrayList;

class Solution {
  public String convert(String s, int numRows) {

    if (numRows == 1)
      return s;

    List<List<Character>> al = new ArrayList<>();
    for (int i = 0; i < numRows; i++)
      al.add(new ArrayList<>());

    int n = s.length();
    boolean vert = true;
    int i = 0; // index in string
    int row = 0; // row currently being filled

    while (i < n) {
      al.get(row).add(s.charAt(i));
      i++;

      if (vert) {
        // -1 becoz, in next iteration we add the char
        if (row < numRows - 1)
          row++;
        else {
          vert = false;
          row--;
        }
      } else {
        if (row > 0)
          row--;
        else {
          vert = true;
          row++;
        }
      }
    }

    StringBuffer sb = new StringBuffer();
    for (i = 0; i < numRows; i++) {
      for (char ch : al.get(i))
        sb.append(ch);
    }
    return sb.toString();
  }
}