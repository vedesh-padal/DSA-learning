// EASY
// binary-search, arrays
/*
    We are playing the Guess Game. The game is as follows:

    I pick a number from 1 to n. You have to guess which number I picked.

    Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.

    You call a pre-defined API int guess(int num), which returns three possible results:

    -1: Your guess is higher than the number I picked (i.e. num > pick).
    1: Your guess is lower than the number I picked (i.e. num < pick).
    0: your guess is equal to the number I picked (i.e. num == pick).
    Return the number that I picked.

    Example 1:
    Input: n = 10, pick = 6
    Output: 6

    Example 2:
    Input: n = 1, pick = 1
    Output: 1

    Example 3:
    Input: n = 2, pick = 1
    Output: 1
    
    Constraints:
    1 <= n <= 231 - 1
    1 <= pick <= n
*/

/**
 * Forward declaration of guess API.
 * 
 * @param num your guess
 * @return -1 if num is higher than the picked number
 *         1 if num is lower than the picked number
 *         otherwise return 0
 *         int guess(int num);
 */

class Solution {
    private int guess(int num) {
        return 1;   // -1 / 0 / 1
    }

    public int guessNumber(int n) {
        int l = 1, h = n;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            int dec = guess(mid); // decision
            if (dec == 0)
                return mid;
            else if (dec == 1)
                l = mid + 1;
            // else if (dec == -1)
            else
                h = mid - 1;
        }
        return -1;
    }
}