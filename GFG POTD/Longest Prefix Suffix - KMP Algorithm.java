/*
  Given a string of characters, find the length of the longest proper prefix which is also a proper suffix.

  NOTE: Prefix and suffix can be overlapping but they should not be equal to the entire string.

  Examples :

  Input: str = "abab"
  Output: 2
  Explanation: "ab" is the longest proper prefix and suffix. 
  
  Input: str = "aaaa"
  Output: 3
  Explanation: "aaa" is the longest proper prefix and suffix. 
  
  Expected Time Complexity: O(|str|)
  Expected Auxiliary Space: O(|str|)

  Constraints:
  1 ≤ |str| ≤ 106
  str contains lower case English alphabets
*/

class Solution {
  // GIVES TLE O(N^2)
  int lps_N2(String str) {
    // code here
    int n = str.length();
    int longest = 0;
    for (int i = 1; i < n; i++) {
      if (str.substring(0, i).equals(str.substring(n - i))) {
        longest = Math.max(str.substring(0, i).length(), longest);
      }
    }
    return longest;
  }

  ///// Knoth Morris Patt (KMP) ALGORITHM
  // This function calculates the length of the longest prefix which is also a suffix
  // using the Knuth-Morris-Pratt (KMP) algorithm's preprocessing technique.
  // This implementation runs in O(n) time complexity, making it efficient for
  // large strings.
  int lps(String str) {
    int n = str.length(); 
    int[] arr = new int[n]; // Create an array to store the LPS values for each index
    int pre = 0; // Pointer for the length of the current longest prefix
    int suf = 1; // Pointer for iterating through the string

    // Iterate through the string to fill the LPS array
    while (suf < n) {
      // If characters at pre and suf match, we have found a longer prefix-suffix
      if (str.charAt(pre) == str.charAt(suf)) {
        arr[suf] = pre + 1; // Set LPS value at index suf
        pre++; // Move to the next character in prefix
        suf++; // Move to the next character in suffix
      } else {
        // If there is a mismatch and we are at the start of the prefix
        if (pre == 0) {
          arr[suf] = 0; // No prefix-suffix found, set LPS to 0
          suf++; // Move to the next character in suffix
        } else {
          // Update pre to point to the previous longest prefix-suffix length
          pre = arr[suf - 1];
          // This step allows us to avoid unnecessary comparisons by skipping characters
          // that have already been matched as part of a previous prefix.
        }
      }
    }

    return arr[n - 1]; // Return the length of the longest prefix which is also a suffix
  }
}