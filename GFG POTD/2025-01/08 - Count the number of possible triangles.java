// MEDIUM
// arrays, sorting, binary-search, 2-pointers

/*
    Given an integer array arr[]. Find the number of triangles that can be formed
    with three different array elements as lengths of three sides of the triangle.

    A triangle with three given sides is only possible if sum of any two sides is always greater than the third side.

    Examples:
    Input: arr[] = [4, 6, 3, 7]
    Output: 3
    Explanation: There are three triangles possible [3, 4, 6], [4, 6, 7] and [3, 6, 7]. 
    Note that [3, 4, 7] is not a possible triangle.

    Input: arr[] = [10, 21, 22, 100, 101, 200, 300]
    Output: 6
    Explanation: There can be 6 possible triangles: [10, 21, 22], [21, 100, 101],
    [22, 100, 101], [10, 100, 101], [100, 101, 200] and [101, 200, 300]

    Input: arr[] = [1, 2, 3]
    Output: 0
    Explanation: No triangles are possible.

    Constraints:
    3 <= arr.size() <= 10^3
    1 <= arr[i] <= 10^5
*/

class Solution {
    
    // can be used for brute force approach, but for better appraoches, not needed
    // as we just need to count such sides, instead of checking each
    // and that is possible becoz we have the sides in sorted order
    @SuppressWarnings("unused")
    private static boolean isPossible(int a, int b, int c) {
        return ( a+b > c || b+c > a || a+c > b);
    }
    

    // using 2-pointer approach
    // O(N^2)
    static int countTriangles(int arr[]) {
        int n = arr.length;
        java.util.Arrays.sort(arr);
        int res = 0;
        
        for (int i = 2; i < n; i++) {
            int l = 0, r = i-1;
            
            while (l < r) {
                if (arr[l] + arr[r] > arr[i]) {
                    res += (r - l);
                    // this is possible, now check if lesser side is possible
                    r--;
                }
                else {
                    l++;
                }
            }
        }
        return res;
    }
    
    
    // =============================================================================
    // Function to count the number of possible triangles.
    static int countTriangles_using_Sort_BinarySearch(int arr[]) {
        // code here
        java.util.Arrays.sort(arr);
        int n = arr.length;
        int res = 0;
        
        // O(n^2 logn)
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                // 2 points fixed, now search for the 3rd side
                // we apply binary search
                // find the 2nd side such that it's length is less
                // than other two sides, i.e., arr[i], arr[j]
                int l = j+1, r = n;
                int target = arr[i] + arr[j];
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (arr[mid] < target) {
                        l = mid + 1;
                    }
                    else {
                        r = mid;
                    }
                }
                
                res += (l - j - 1); // -1, becoz, we moved past one element
            }
        }
        return res;
    }
}