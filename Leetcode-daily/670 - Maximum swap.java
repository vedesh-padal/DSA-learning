// 17-10-2024
// MEDIUM
// arrays, greedy

// You are given an integer num. You can swap two digits at most once to get the
// maximum valued number.

// Return the maximum valued number you can get.

// Example 1:
// Input: num = 2736
// Output: 7236
// Explanation: Swap the number 2 and the number 7.

// Example 2:
// Input: num = 9973
// Output: 9973
// Explanation: No swap.

// Constraints:
// 0 <= num <= 108

class Solution {

  public int maximumSwap(int num) {
    char[] numStr = Integer.toString(num).toCharArray();
    // note that: when we do: numStr[i] = we get numerical value, since ascii
    int n = numStr.length;
    int maxiInd = -1; // maxi. digit index
    // indices to swap
    int ind1 = -1, ind2 = -1;

    // traverse from back, and find the positions to swap
    for (int i = n - 1; i >= 0; i--) {
      if (maxiInd == -1 || numStr[i] > numStr[maxiInd]) {
        maxiInd = i;
      } 
      else if (numStr[i] < numStr[maxiInd]) {
        // left-most index digit will be smallest
        ind1 = i;
        ind2 = maxiInd;
      }
    }

    // once we have found the appropriate indices, swap
    if (ind1 != -1 && ind2 != -1) {
      char t = numStr[ind1];
      numStr[ind1] = numStr[ind2];
      numStr[ind2] = t;

      return Integer.parseInt(new String(numStr));
    } 
    else {
      return num;
    }

  }

  // not optimal
  public int maximumSwap_NOT_OPTIMAL(int num) {

    int one = -1;
    int two = -1;

    String numStr = Integer.toString(num);
    int n = numStr.length();
    System.out.println(numStr);
    // System.out.println()

    for (int i = 0; i < n; i++) {
      int maxiInd = i;
      int maxValAtInd = numStr.charAt(i) - '0';
      for (int j = i + 1; j < n; j++) {
        // we are finding the index of the number greater than
        // or equal to the current pivot
        if (numStr.charAt(j) - '0' >= maxValAtInd) {
          maxValAtInd = numStr.charAt(j) - '0';
          maxiInd = j;
        }
      }
      // if after traversing on to the right
      // if we found one greater of equal to the curr pivot
      // if that found position index is same (no greater found on right)
      // or if the found number is same as the curr. pivot number
      // we do nothing
      // else, we swap, and since we did one swap, we break
      // here, since we are just tracking where that swap index is
      // since we stored the num in string
      if (i != maxiInd && maxValAtInd != numStr.charAt(i) - '0') {
        one = i;
        two = maxiInd;
        break;
      }
    }
    System.out.println(one + " " + two);
    System.out.println(numStr);

    if (one != -1 && two != -1) {
      numStr = numStr.substring(0, one) + numStr.charAt(two) +
          numStr.substring(one + 1, two) + numStr.charAt(one) + numStr.substring(two + 1);
    }

    System.out.println(numStr);

    return Integer.parseInt(numStr);

    // if (one != two) {
    // char[] arr = numStr.toCharArray();
    // char temp = arr[one];
    // arr[one] = arr[two];
    // arr[two] = temp;
    // return Integer.parseInt(new String(arr));
    // }

    // return num;
  }
}