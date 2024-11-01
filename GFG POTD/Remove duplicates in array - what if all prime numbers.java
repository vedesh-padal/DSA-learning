// 28-10-2024
// EASY
// arrays, hashmap, prime

// Given an array arr consisting of positive integers numbers, remove all
// duplicates numbers.

// Example:
// Input: arr[] = [2, 2, 3, 3, 7, 5]
// Output: [2, 3, 7, 5]
// Explanation: After removing the duplicates 2 and 3 we get 2 3 7 5.

// Input: arr[] = [2, 2, 5, 5, 7, 7]
// Output: [2, 5, 7]
// Explanation: After removing the duplicates 2, 5 and 7 we get 2 5 7.
// Expected Time Complexity: O(n)
// Expected Auxiliary Space: O(1)

// Constraints:
// 1<= arr.size() <=106
// 2<=arr[i]<=100

import java.util.ArrayList;
import java.util.HashSet;

class Solution {
  ArrayList<Integer> removeDuplicate(int arr[]) {
    // code here
    ArrayList<Integer> al = new ArrayList<>();
    HashSet<Integer> hset = new HashSet<>();

    for (int num : arr) {
      if (!hset.contains(num)) {
        al.add(num);
        hset.add(num);
      }
    }
    return al;
  }
}

// IF the array HAS JUST PRIME NUMBERS, we can make use of the prime numbers property:
// This approach only works when the product of all distinct primes is fewer
// than 10^18 and all the numbers in the array should be prime. The property of
// primes of having no divisors except 1 or that number itself is used to arrive
// at the solution. As the array elements are removed from the array, they keep
// a value(product) which will contain the product of all distinct primes found
// previously in the array, so that if the element divides the product, they can
// be sure proved that the element has previously occurred in the array and
// hence the number will be rejected.

// int prod = vect[0];
// int res_ind = 1;
// for (int i = 1; i < vect.length; i++) {
//     if (prod % vect[i] != 0) {
//         vect[res_ind++] = vect[i];
//         prod *= vect[i];
//     }
// }
// return Arrays.copyOf(vect, res_ind);