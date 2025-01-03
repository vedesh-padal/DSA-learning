// MEDIUM
// arrays, greedy, monotonic-stack
/*
    You are given an integer array arr of length n that represents a permutation of the integers in the range [0, n - 1].

    We split arr into some number of chunks (i.e., partitions), and individually
    sort each chunk. After concatenating them, the result should equal the sorted array.

    Return the largest number of chunks we can make to sort the array.

    Example 1:
    Input: arr = [4,3,2,1,0]
    Output: 1
    Explanation:
    Splitting into two or more chunks will not return the required result.
    For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.

    Example 2:
    Input: arr = [1,0,2,3,4]
    Output: 4
    Explanation:
    We can split into two chunks, such as [1, 0], [2, 3, 4].
    However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
    
    Constraints:
    n == arr.length
    1 <= n <= 10
    0 <= arr[i] < n
    All the elements of arr are unique.
*/
class Solution {

    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        // in this approach, instead of cummulative sum
        // we can just verify if till this index, we have max. element 
        // matching to the sorted element, if yes, then this chunk (start of this chunk was
        // from where the previous chunk ended) is possible to be sorted to 
        // satisfy the problem statement
        
        int maxSoFar = -1;
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            maxSoFar = Math.max(maxSoFar, arr[i]);
            if (maxSoFar == i) 
                cnt++;       
        }
        return cnt;
    }

    public int maxChunksToSorted_cummSum(int[] arr) {
        int n = arr.length;
        // compare with the sorted positions, and keep track of the 
        // given array positions cummulative sum and the sorted cummulative sum
        // if equal => this chunk has all the elements in the sorted one, so can be sorted -> increase chunk count

        int cnt = 0;
        int cummSum = 0, oCummSum = 0;

        for (int i = 0; i < n; i++) {
            cummSum += arr[i];
            oCummSum += i;
            
            if (cummSum == oCummSum) 
                cnt++;
        }
        return cnt;
    }


    public int maxChunksToSorted_preMax_suffMin(int[] arr) {
        // maintaining what is the max. element seen so far on the left
        // and what is the min. element seen so far on the right (travelling from right to left)
        int n = arr.length;
        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];
        
        prefixMax[0] = arr[0];
        suffixMin[n-1] = arr[n-1];

        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], arr[i]);
            suffixMin[n-i-1] = Math.min(suffixMin[n-i], arr[n-i-1]);
        }
        // System.out.println(Arrays.toString(prefixMax));
        // System.out.println(Arrays.toString(suffixMin));

        int cnt = 0;    // chunks count
        // will be incremented when the below condition satisfies
        // if the max on left < min on right, indicating at that split index,
        // the left and right part of the splits can be sorted, indicating we have a chunk

        for (int i = 0; i < n; i++) {
            int maxOnLeft = (i > 0) ? prefixMax[i-1] : -1;
            int minOnRight = suffixMin[i];

            if (maxOnLeft < minOnRight) {
                cnt++;
            }
        }

        return cnt;
    }
}