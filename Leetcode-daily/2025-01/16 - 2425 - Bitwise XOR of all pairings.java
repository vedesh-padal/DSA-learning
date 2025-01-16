// MEDIUM
// bit-manipulation, brain-teaser
/*
    You are given two 0-indexed arrays, nums1 and nums2, consisting of
    non-negative integers. There exists another array, nums3, which contains the
    bitwise XOR of all pairings of integers between nums1 and nums2 (every
    integer in nums1 is paired with every integer in nums2 exactly once).

    Return the bitwise XOR of all integers in nums3.

    Example 1:
    Input: nums1 = [2,1,3], nums2 = [10,2,5,0]
    Output: 13
    Explanation:
    A possible nums3 array is [8,0,7,2,11,3,4,1,9,1,6,3].
    The bitwise XOR of all these numbers is 13, so we return 13.

    Example 2:
    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 0
    Explanation:
    All possible pairs of bitwise XORs are nums1[0] ^ nums2[0], nums1[0] ^ nums2[1], nums1[1] ^ nums2[0],
    and nums1[1] ^ nums2[1].
    Thus, one possible nums3 array is [2,5,1,6].
    2 ^ 5 ^ 1 ^ 6 = 0, so we return 0.

    Constraints:
    1 <= nums1.length, nums2.length <= 10^5
    0 <= nums1[i], nums2[j] <= 10^
*/

class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        // appraoch - 1: first did the below logic using hashmap, by maintaining count => TC: 42ms
        // appraoch - 2: then tried with arrays and also just doing the even or odd length, this gave MLE
        
        // int maxi = Integer.MIN_VALUE;
        // for (int num: nums1)
        //     if (num > maxi)
        //         maxi = num;
        
        // for (int num: nums2)
        //     if (num > maxi)
        //         maxi = num;

        // int[] freq = new int[maxi + 1];

        // for (int num: nums1) 
        //    // freq[num] += nums1.length;
        //     freq[num] += (nums1.length & 1);

        
        // for (int num: nums2)
        //    // freq[num] += nums2.length;
        //     freq[num] += (nums2.length & 1);

        // int res = 0;
        // for (int i = 1; i <= maxi; i++) {
        //     // if odd count, count it in pairings XOR (res)
        //     if ((freq[i] & 1) == 1) {
        //         res ^= i;
        //     }
        // }
        // return res;

        // =====================
        // OPTIMAL WAY:
        int x = 0, y = 0;
        for (int num: nums1)
            x ^= num;
        
        for (int num: nums2)
            y ^= num;


        // now, we have `x` containing only those num's xor which are in odd number of times
        // same for `y`
        // each num on left is repeating nums2.length times, vice versa
        
        // since we have `x` as odd repeating numbers, when multiplied by length of nums2, that nums2.length
        // should be odd number else, it would make no sense
        return ((nums1.length & 1) * y) ^ ((nums2.length & 1) * x);

    }
}