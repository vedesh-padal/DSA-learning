// MEDIUM
// arrays

// Given an array of integers arr[] representing a permutation, implement the
// next permutation that rearranges the numbers into the lexicographically next
// greater permutation. If no such permutation exists, rearrange the numbers
// into the lowest possible order (i.e., sorted in ascending order).

// Note - A permutation of n numbers is any possible arrangement of all the
// integers in range [1-n] where each integer occurs exactly once.

// Examples:
// Input: arr = [2, 4, 1, 7, 5, 0]
// Output: [2, 4, 5, 0, 1, 7]
// Explanation: The next permutation of the given array is {2, 4, 5, 0, 1, 7}.

// Input: arr = [3, 2, 1]
// Output: [1, 2, 3]
// Explanation: As arr[] is the last permutation, the next permutation is the lowest one.

// Input: arr = [3, 4, 2, 5, 1]
// Output: [3, 4, 5, 1, 2]
// Explanation: The next permutation of the given array is {3, 4, 5, 1, 2}.

// Constraints:
// 1 ≤ arr.size() ≤ 105
// 1 ≤ arr[i] ≤ 105

class Solution {
    void nextPermutation(int[] arr) {
        // code here
        int n = arr.length;
        int ind = -1; // the break point

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                ind = i;
                break;
            }
        }
        // that means the array was in decreasing order
        // so, as in Q, we should make it in increasing order
        // so, just reverse it, instead of sorting
        if (ind == -1) {
            reverseArr(arr, 0, n - 1);
            return;
        }

        // now, we have to swap the break point element
        // with the element in the array that is smallest but just
        // greater than arr[ind]
        for (int i = n - 1; i > ind; i--) {
            if (arr[i] > arr[ind]) {
                int t = arr[i];
                arr[i] = arr[ind];
                arr[ind] = t;
                break;
            }
        }

        // now, since we want in lexicographical order, we want to make sure
        // that the right elements of the break point, after swapping with the
        // smallest greater element are in sorted (increasing order) to ensure in
        // lexicographical order

        // here, the right side part will be in increasing order
        // so, instead of sorting, we can just reverse the right side part
        reverseArr(arr, ind + 1, n - 1);
    }

    void reverseArr(int[] arr, int s, int e) {
        while (s < e) {
            int t = arr[s];
            arr[s] = arr[e];
            arr[e] = t;
            s++;
            e--;
        }
    }
}