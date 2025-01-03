// HARD
// strings, simulation, greedy

/*
    Given an array of strings words and a width maxWidth, format the text such
    that each line has exactly maxWidth characters and is fully (left and right) justified.

    You should pack your words in a greedy approach; that is, pack as many words
    as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

    Extra spaces between words should be distributed as evenly as possible. If
    the number of spaces on a line does not divide evenly between words, the
    empty slots on the left will be assigned more spaces than the slots on the right.

    For the last line of text, it should be left-justified, and no extra space is inserted between words.

    Note:
    - A word is defined as a character sequence consisting of non-space characters only.
    - Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
    - The input array words contains at least one word.
    

    Example 1:
    Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
    Output:
    [
    "This    is    an",
    "example  of text",
    "justification.  "
    ]

    Example 2:
    Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
    Output:
    [
    "What   must   be",
    "acknowledgment  ",
    "shall be        "
    ]
    Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last 
    line must be left-justified instead of fully-justified.
    Note that the second line is also left-justified because it contains only one word.

    Example 3:
    Input: words = ["Science","is","what","we","understand","well","enough","to","explain",
    "to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
    Output:
    [
    "Science  is  what we",
    "understand      well",
    "enough to explain to",
    "a  computer.  Art is",
    "everything  else  we",
    "do                  "
    ]
    
    Constraints:
    1 <= words.length <= 300
    1 <= words[i].length <= 20
    words[i] consists of only English letters and symbols.
    1 <= maxWidth <= 100
    words[i].length <= maxWidth
*/

import java.util.*;

class Solution {

    private int MAX_WIDTH;

    private String findLine(int i, int j, int eachSingleSpace, int extraSingleSpaces, String[] words) {
        StringBuilder sb = new StringBuilder();

        for (int k = i; i < j; k++) {
            sb.append(words[k]);

            // EDGE CASE:
            // we have reached the last word, and it doesn't make sense to follow the next conditions
            // we just break
            if (k == j-1) {
                break;
                // continue     // also can be written instead of break
            }

            for (int z = 1; z <= eachSingleSpace; z++) {
                sb.append(" ");
            }

            // if there are any non-even spaces division, we add it to the left most gaps
            // and that too evenly should be distributed, so `if` instead of `while`
            if (extraSingleSpaces > 0) {
                sb.append(" ");
                extraSingleSpaces--;
            }
        }
        // if after adding all words, still, we haven't reaached maxWidth, add extra space to right
        while (sb.length() < MAX_WIDTH) {
            sb.append(" ");
        }

        return sb.toString();
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        List<String> res = new ArrayList<>();

        MAX_WIDTH = maxWidth;

        int i = 0;
        while (i < n) {
            int lettersCount = words[i].length();
            int j = i + 1;
            int singleSpaces = 0;   // represents the gap between words in the line

            while (j < n && (words[j].length() + 1 + singleSpaces + lettersCount) <= maxWidth) {
                lettersCount += words[j].length();
                singleSpaces++;
                j++;
            }

            // here, lettersCount represents the cummulative sum of just words in the string
            int totalSpaces = maxWidth - lettersCount;

            // filling the single spaces with more spaces greedily to justify
            int eachSingleSpace = (singleSpaces == 0) ? 0 : totalSpaces / singleSpaces;
            int extraSingleSpaces = (singleSpaces == 0) ? 0 : totalSpaces % singleSpaces;

            // if this is the last line -- means, j is the end word
            // so, we want it to be left aligned
            if (j == n) {
                eachSingleSpace = 1;
                extraSingleSpaces = 0;
            }

            res.add(findLine(i, j, eachSingleSpace, extraSingleSpaces, words));

            i = j;
        }
        return res;
    }
}