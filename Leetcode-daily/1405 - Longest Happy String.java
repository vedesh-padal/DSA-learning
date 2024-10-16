// 16-10-2024
// MEDIUM
// string, greedy, heap

// A string s is called happy if it satisfies the following conditions:

// s only contains the letters 'a', 'b', and 'c'.
// s does not contain any of "aaa", "bbb", or "ccc" as a substring.
// s contains at most a occurrences of the letter 'a'.
// s contains at most b occurrences of the letter 'b'.
// s contains at most c occurrences of the letter 'c'.

// Given three integers a, b, and c, return the longest possible happy string.
// If there are multiple longest happy strings, return any of them. If there is
// no such string, return the empty string "".

// A substring is a contiguous sequence of characters within a string.

// Example 1:
// Input: a = 1, b = 1, c = 7
// Output: "ccaccbcc"
// Explanation: "ccbccacc" would also be a correct answer.

// Example 2:
// Input: a = 7, b = 1, c = 0
// Output: "aabaa"
// Explanation: It is the only correct answer in this case.

// Constraints:
// 0 <= a, b, c <= 100
// a + b + c > 0

import java.util.PriorityQueue;

class Pair {
  int cnt;
  char ch;

  public Pair(int cnt, char ch) {
    this.cnt = cnt;
    this.ch = ch;
  }
}

class Solution {
  public String longestDiverseString(int a, int b, int c) {
    StringBuffer res = new StringBuffer();
    PriorityQueue<Pair> pq = new PriorityQueue<>((p, q) -> q.cnt - p.cnt);
    if (a > 0)
      pq.add(new Pair(a, 'a'));
    if (b > 0)
      pq.add(new Pair(b, 'b'));
    if (c > 0)
      pq.add(new Pair(c, 'c'));

    while (!pq.isEmpty()) {
      int cnt = pq.peek().cnt;
      char ch = pq.peek().ch;
      pq.poll();

      // if adding this to the string would result in the 3 consecutive chars
      // we add the next frequent element
      if (res.length() >= 2 && res.charAt(res.length() - 1) == ch && ch == res.charAt(res.length() - 2)) {
        // if the pq is empty, that is we have reached the atmost count of all chars
        // we break, and return the string we have
        if (pq.isEmpty())
          break;

        // considering next freq. character
        Pair nextFreq = pq.poll();
        res.append(nextFreq.ch);
        if (nextFreq.cnt - 1 > 0)
          pq.add(new Pair(nextFreq.cnt - 1, nextFreq.ch));
      }
      // not forming 3 consecutive chars,
      // so add the curr. most freq. char
      else {
        cnt--;
        res.append(ch);
      }

      // if after decr. count, it is not 0
      // we add the remaining count of that char to pq
      // AND, if in case we did not modify the most freq. cnt
      // and just modified the next most freq. one becoz of condition
      // (and updated it in the pq)
      // we put the most freq. cnt as it is (as we have polled it prev.sly)
      if (cnt > 0)
        pq.add(new Pair(cnt, ch));
    }
    return res.toString();
  }
}