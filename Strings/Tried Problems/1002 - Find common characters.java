// Given a string array words, return an array of all characters that show up in
// all strings within the words (including duplicates). You may return the
// answer in any order.

// Example 1:
// Input: words = ["bella","label","roller"]
// Output: ["e","l","l"]

// Example 2:
// Input: words = ["cool","lock","cook"]
// Output: ["c","o"]

// Constraints:
// 1 <= words.length <= 100
// 1 <= words[i].length <= 100
// words[i] consists of lowercase English letters.

import java.util.List;
import java.util.ArrayList;

class Solution {
  public List<String> commonChars(String[] words) {
    int[] cnt = new int[26];

    for (int i = 0; i < words[0].length(); i++) {
      cnt[words[0].charAt(i) - 'a']++;
    }

    // we will store only the min. of the count of each character in cnt
    // from now on, that helps in finding the common chars in all strings

    for (int i = 1; i < words.length; i++) {
      int[] currCnt = new int[26];
      for (int j = 0; j < words[i].length(); j++) {
        currCnt[words[i].charAt(j) - 'a']++;
      }

      // intersection among two counts such that only min. is stored
      // which helps in keeping track of the common among all
      for (int j = 0; j < 26; j++) {
        cnt[j] = Math.min(cnt[j], currCnt[j]);
      }
    }

    List<String> res = new ArrayList<>();
    for (int i = 0; i < 26; i++) {
      if (cnt[i] != 0) {
        char ch = (char) (i + 'a'); // to obtain the character
        while (cnt[i] > 0) {
          res.add(String.valueOf(ch));
          cnt[i]--;
        }
      }
    }
    return res;
  }
}