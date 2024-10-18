// MEDIUM
// arrays, bit-manipulation

// The bitwise AND of an array nums is the bitwise AND of all integers in nums.

// For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
// Also, for nums = [7], the bitwise AND is 7.
// You are given an array of positive integers candidates. Evaluate the bitwise
// AND of every combination of numbers of candidates. Each number in candidates
// may only be used once in each combination.

// Return the size of the largest combination of candidates with 
// a bitwise AND greater than 0.

// Example 1:
// Input: candidates = [16,17,71,62,12,24,14]
// Output: 4
// Explanation: The combination [16,17,62,24] has a bitwise AND of 16 & 17 & 62
// & 24 = 16 > 0.
// The size of the combination is 4.
// It can be shown that no combination with a size greater than 4 has a bitwise
// AND greater than 0.
// Note that more than one combination may have the largest size.
// For example, the combination [62,12,24,14] has a bitwise AND of 62 & 12 & 24
// & 14 = 8 > 0.

// Example 2:
// Input: candidates = [8,8]
// Output: 2
// Explanation: The largest combination [8,8] has a bitwise AND of 8 & 8 = 8 > 0.
// The size of the combination is 2, so we return 2.

// Constraints:
// 1 <= candidates.length <= 105
// 1 <= candidates[i] <= 107

class Solution {
  public int largestCombination(int[] candidates) {

    // NOTE: AND property: AND of two bits is 1 only if both bits are 1
    // largest combination => max. no. of number when ANDed, 
    // and the result of those AND should be greater than 0

    // and, it will be greater than 0, when atleast one bit
    // in both number is in common and has bit set (1)

    // we need to count no. of times each of those 32 bits is set
    // and the max. count of that bit will indicate our answer
    // i.e., largest combination with bitwise AND > 0

    // to store the freq. that for how many numbers that bit is set
    // the max. freq in this bitCount freq. array
    int[] bitCount = new int[32];

    for (int num : candidates) {
      for (int j = 0; j < 32; j++) {
        if (((1 << j) & num) != 0)
          bitCount[j]++;
      }
    }
    int maxFreq = 0;
    for (int i = 0; i < 32; i++)
      maxFreq = Math.max(maxFreq, bitCount[i]);

    return maxFreq;
  }
}