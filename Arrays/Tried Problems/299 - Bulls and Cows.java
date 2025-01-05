/*
    You are playing the Bulls and Cows game with your friend.

    You write down a secret number and ask your friend to guess what the number
    is. When your friend makes a guess, you provide a hint with the following info:

    The number of "bulls", which are digits in the guess that are in the correct position.
    - The number of "cows", which are digits in the guess that are in your secret
        number but are located in the wrong position. Specifically, the non-bull
        digits in the guess that could be rearranged such that they become bulls.
    - Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.

    The hint should be formatted as "xAyB", where x is the number of bulls and y
    is the number of cows. Note that both secret and guess may contain duplicate digits.

    Example 1:
    Input: secret = "1807", guess = "7810"
    Output: "1A3B"
    Explanation: Bulls are connected with a '|' and cows are underlined:
    "1807"
    |
    "7810"

    Example 2:
    Input: secret = "1123", guess = "0111"
    Output: "1A1B"
    Explanation: Bulls are connected with a '|' and cows are underlined:
    "1123"        "1123"
    |      or     |
    "0111"        "0111"
    Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.

    Constraints:
    1 <= secret.length, guess.length <= 1000
    secret.length == guess.length
    secret and guess consist of digits only.
*/

class Solution {
    public String getHint(String secret, String guess) {
        // bulls is basically how many each index wise iteration on both is giving the same digit
        // and `cows` count can be calculated by total no. of each digit - no. of correct
        int[] cnt1 = new int[10];
        int[] cnt2 = new int[10];

        int bulls = 0;
        for (int i = 0; i < secret.length(); i++) {
            cnt1[secret.charAt(i) - '0']++;
            cnt2[guess.charAt(i) - '0']++;
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
                cnt1[secret.charAt(i) - '0']--;
                cnt2[guess.charAt(i) - '0']--;
            }
        }
        
        int cows = 0;
        for (int i = 0; i < 10; i++) {
            if (cnt1[i] != 0 && cnt2[i] != 0) {
                cows += Math.min(cnt1[i], cnt2[i]);
            }
        }
        return bulls + "A" + cows + "B";
    }
}