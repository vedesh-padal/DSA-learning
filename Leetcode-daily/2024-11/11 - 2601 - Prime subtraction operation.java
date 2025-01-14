// MEDIUM
// prime-factorization, sieve-method, arrays

// You are given a 0-indexed integer array nums of length n.

// You can perform the following operation as many times as you want:

// Pick an index i that you haven’t picked before, and pick a prime p strictly
// less than nums[i], then subtract p from nums[i].
// Return true if you can make nums a strictly increasing array using the above
// operation and false otherwise.

// A strictly increasing array is an array whose each element is strictly
// greater than its preceding element.

// Example 1:
// Input: nums = [4,9,6,10]
// Output: true
// Explanation: In the first operation: Pick i = 0 and p = 3, and then subtract
// 3 from nums[0], so that nums becomes [1,9,6,10].
// In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums
// becomes equal to [1,2,6,10].
// After the second operation, nums is sorted in strictly increasing order, so
// the answer is true.

// Example 2:
// Input: nums = [6,8,11,12]
// Output: true
// Explanation: Initially nums is sorted in strictly increasing order, so we
// don't need to make any operations.

// Example 3:
// Input: nums = [5,8,3]
// Output: false
// Explanation: It can be proven that there is no way to perform operations to
// make nums sorted in strictly increasing order, so the answer is false.

// Constraints:
// 1 <= nums.length <= 1000
// 1 <= nums[i] <= 1000
// nums.length == n

import java.util.Arrays;

class Solution {

  private boolean[] isPrime = new boolean[1000]; // given constraint: nums[i] = 1000

  // TC: O(sqrt(n))
  @SuppressWarnings("unused")
  private boolean checkIsPrime(int n) {
    if (n <= 1)
      return false;
    if (n <= 3)
      return true;

    if (n%2 == 0 || n%3 == 0)
      return false;

    // dry running and cross checking, you will appreciate this logic
    for (int i = 5; i*i <= n; i += 6) {
      if (n % i == 0 || n % (i+2) == 0)
        return false;
    }
    return true;
  }

  // Implementing Sieve of Eratosthenes
  private void sieve() {

    Arrays.fill(isPrime, true);
    isPrime[0] = false;
    isPrime[1] = false;

    for (int i = 2; i * i < 1000; i++) {
      if (isPrime[i]) {
        for (int j = i * i; j < 1000; j += i) {
          isPrime[j] = false;
        }
      }
    }
  }

  public boolean primeSubOperation(int[] nums) {
    int n = nums.length;

    sieve();  // populates isPrime[] with prime indicators

    // coming from end for the sorted order -> easy this way
    // as we can avoid finding the upper bound prime number when we were travelling from left
    for (int i = n - 2; i >= 0; i--) {
      // already in strictly increasing order, so continue
      if (nums[i] < nums[i + 1]) {
        continue;
      }

      // if not, decrease nums[i] atleast less than nums[i+1] with a prime number

      // check prime numbers less than nums[i]

      // i.e., Find the largest prime `p` less than `nums[i]`
      for (int p = 2; p < nums[i]; p++) {
        if (!isPrime[p])
          continue;

        if (nums[i] - p < nums[i + 1]) {
          nums[i] -= p;
          break;
        }
      }

      // we were not able to find such prime number
      // that means we cannot make the array in strictly increasing order
      if (nums[i] >= nums[i + 1])
        return false;
    }

    return true;
  }
}