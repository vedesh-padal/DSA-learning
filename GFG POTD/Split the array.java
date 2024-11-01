// 21-10-2024
// EASY
// arrays, bit-manipulation

// Given an array arr[] of integers, the task is to count the number of ways to
// split given array elements into two non-empty subsets such that the XOR of
// elements of each group is equal. Each element should belong to exactly one
// subset.
// Note:
// The answer could be very large so print it by doing modulo with 109 + 7.
// Subsets with the same elements but derived from different indices are
// different.

// Examples:
// Input : arr[] = [1, 2, 3]
// Output : 3
// Explanation: {(1),(2, 3)}, {(2),(1, 3)}, {(3),(1, 2)} are three ways with
// equal XOR value of two groups.

// Input : arr[] = [5, 2, 3, 2]
// Output : 0
// Explanation: No way to split into two groups whose XOR is equal.
// Input : arr[] = [2, 2, 2, 2]
// Output : 7

// Explanation: There are 7 ways to split the array into two groups with equal
// XOR, such that there are multiple combinations of {(2),
// (2,2,2)},{(2,2),(2,2)}.

// Constraints:
// 1<=arr.size()<=106
// 1<=arr[i]<=106

class Solution {

  public static int countgroup(int arr[]) {
    // Complete the function
    int xr = 0;
    for (int num : arr)
      xr ^= num;

    // it is possible to split the array into two groups
    // if the two groups' XOR is equal => XOR of all elements in the array = 0

    // we can split the array of size n into two parts:
    // total number of ways to split = 2^n
    // but (G1, G2) == (G2, G1) => should be counted only once
    // so we divide by 2: 2^n / 2 = 2^(n-1)
    // and if we have only one element in the array:
    // 2^(1 - 1) = 1 => but, we cannot return this, becoz we need
    // to have atleast two elements in the array to have two groups of
    // 1 element each
    // so, we subtract 1
    // ==> 2^(n-1) - 1
    // we can write this using bit manipulation as follows

    if (xr == 0)
      return (1 << (arr.length - 1)) - 1; // 2^(n-1) - 1

    return 0;
  }
}