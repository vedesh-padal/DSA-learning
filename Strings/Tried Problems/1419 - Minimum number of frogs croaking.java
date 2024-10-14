// medium
// strings, counting

// You are given the string croakOfFrogs, which represents a combination of the
// string "croak" from different frogs, that is, multiple frogs can croak at the
// same time, so multiple "croak" are mixed.

// Return the minimum number of different frogs to finish all the croaks in the
// given string.

// A valid "croak" means a frog is printing five letters 'c', 'r', 'o', 'a', and
// 'k' sequentially. The frogs have to print all five letters to finish a croak.
// If the given string is not a combination of a valid "croak" return -1.

// Example 1:
// Input: croakOfFrogs = "croakcroak"
// Output: 1
// Explanation: One frog yelling "croak" twice.

// Example 2:
// Input: croakOfFrogs = "crcoakroak"
// Output: 2
// Explanation: The minimum number of frogs is two.
// The first frog could yell "crcoakroak".
// The second frog could yell later "crcoakroak".

// Example 3:
// Input: croakOfFrogs = "croakcrook"
// Output: -1
// Explanation: The given string is an invalid combination of "croak" from
// different frogs.

// Constraints:
// 1 <= croakOfFrogs.length <= 105
// croakOfFrogs is either 'c', 'r', 'o', 'a', or 'k'.

class Solution {
  public int minNumberOfFrogs(String croakOfFrogs) {
    // max frogs that can croak at a time
    int maxFrogs = 0;
    int cCount = 0, rCount = 0, oCount = 0, aCount = 0, kCount = 0;

    for (char ch : croakOfFrogs.toCharArray()) {
      if (ch == 'c') {
        cCount++;
        maxFrogs = Math.max(maxFrogs, cCount - kCount);
      } else if (ch == 'r') {
        rCount++;
        if (rCount > cCount)
          return -1;
      } else if (ch == 'o') {
        oCount++;
        if (oCount > rCount)
          return -1;
      } else if (ch == 'a') {
        aCount++;
        if (aCount > oCount)
          return -1;
      } else if (ch == 'k') {
        kCount++;
        if (kCount > aCount)
          return -1;
      } else {
        // invalid character
        return -1;
      }
    }
    // edge cases examples:
    // croacroak
    // croakcroa
    if (cCount == rCount && rCount == oCount && oCount == aCount && kCount == cCount)
      return maxFrogs;
    else
      return -1;
  }
}