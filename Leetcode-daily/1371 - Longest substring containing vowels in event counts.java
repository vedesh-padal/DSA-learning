import java.util.HashMap;
import java.util.Map;

class Solution {

  public int findTheLongestSubstring(String s) {
    // matp to store the first occurence of each bitmask
    Map<Integer, Integer> firstOccurence = new HashMap<>();
    firstOccurence.put(0, -1);

    int bitmask = 0; // to track curr. state of vowels
    int maxLen = 0; // to store max. len of valid substring
    int n = s.length();
    for (int i = 0; i < n; i++) {
      char c = s.charAt(i);

      // update the bitmask based on whether the character is a vowel
      if (c == 'a')
        bitmask ^= (1 << 0); // toggle 0th bit
      else if (c == 'e')
        bitmask ^= (1 << 1); // toggle 1st bit
      else if (c == 'i')
        bitmask ^= (1 << 2);
      else if (c == 'o')
        bitmask ^= (1 << 3);
      else if (c == 'u')
        bitmask ^= (1 << 4);

      // if this bitmask has been seen before, calculate substr len
      if (firstOccurence.containsKey(bitmask))
        maxLen = Math.max(maxLen, i - firstOccurence.get(bitmask));
      else {
        // store the first occ. of this bitmask
        firstOccurence.put(bitmask, i);
      }
    }
    return maxLen;
  }

}
// private boolean isVowel(char c) {
//     return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
// }

//     private boolean checkAllEven(Map<Character, Integer> hmap)  {
//         for (Map.Entry<Character, Integer> e: hmap.entrySet())  {
//             if (e.getValue() % 2 != 0)
//                 return false;
//         }
//         return true;
//     }

//     public int findTheLongestSubstring(String s) {
//         Map<Character, Integer> vowelCount = new HashMap<>();
//         vowelCount.put('a', 0);
//         vowelCount.put('e', 0);
//         vowelCount.put('i', 0);
//         vowelCount.put('o', 0);
//         vowelCount.put('u', 0);

//         int n = s.length();
//         int maxLen = 0;
//         int l = 0;

//         for (int r = 0; r < n; r++) {
//             char c = s.charAt(r);

//             // if the curr. char is vowel, increment its count
//             if (vowelCount.containsKey(c))  {
//                 vowelCount.put(c, vowelCount.get(c) + 1);
//             }

//             // now checking if curr. window contains all even counts for vowels
//             while (!checkAllEven(vowelCount) && l <= r)  {
//                 // shrink the window from left
//                 char leftChar = s.charAt(l);
//                 if (vowelCount.containsKey(leftChar))   {
//                     vowelCount.put(leftChar, vowelCount.get(leftChar) - 1);
//                 }
//                 l++;
//             }
//             maxLen = Math.max(maxLen, r - l + 1);
//         }
//         return maxLen;
//     }
// }

  // MY APPROACH, BUT IN-EFFICIENT AND WRONG
  // public int findTheLongestSubstring(String s) {
  //     int n = s.length();
  //     int[] pre = new int[n];

  //     Map<Character, Integer>[] hmap = new HashMap[n];
  //     for (int i = 0; i < n; i++)
  //         hmap[i] = new HashMap<>();
  //     // hmap[0].put('a', 0);
  //     // hmap[0].put('e', 0);
  //     // hmap[0].put('i', 0);
  //     // hmap[0].put('o', 0);
  //     // hmap[0].put('u', 0);

  //     hmap[0].put(s.charAt(0), 1);
  //     for (int i = 1; i < n; i++) {
  //         hmap[i].putAll(hmap[i-1]);
  //         if (isVowel(s.charAt(i)))
  //             hmap[i].put(s.charAt(i), hmap[i-1].getOrDefault(s.charAt(i), 0) + 1);
  //     }

  //     // for (int i = 0; i < n; i++) {
  //     //     for (Map.Entry<Character, Integer> e: hmap[i].entrySet())    {
  //     //         System.out.println(e.getKey() + " " + e.getValue());
  //     //     }
  //     //     System.out.println("---------");
  //     // }

  //     int l = 0, r = 0;
  //     int maxLen = Integer.MIN_VALUE;
  //     while (r < n)   {
  //         if (checkAllEven(hmap[r]))  {
  //             // System.out.println(s.substring(l, r+1));
  //             maxLen = Math.max(maxLen, (r - l + 1));
  //             r++;
  //         }
  //         else    {
  //             if (l < n && isVowel(s.charAt(l)))   {
  //                 // Get the current count, default to 0 if not present
  //                 int currentCount = hmap[r].getOrDefault(s.charAt(l), 0);
  //                 // Only decrement if the current count is greater than 0
  //                 if (currentCount > 0) {
  //                     hmap[r].put(s.charAt(l), currentCount - 1);
  //                 }
  //             }
  //             l++;
  //             if (l >= n)
  //                 break;
  //         }
  //     }

  //     return maxLen;
  // }
