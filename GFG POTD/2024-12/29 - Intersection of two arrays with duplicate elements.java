// EASY
// hash-table, arrays, sorting

/*
    Given two integer arrays a[] and b[], you have to find the intersection of
    the two arrays. Intersection of two arrays is said to be elements that are
    common in both arrays. The intersection should not have duplicate elements
    and the result should contain items in any order.

    Note: The driver code will sort the resulting array in increasing order before printing.

    Examples:
    Input: a[] = [1, 2, 1, 3, 1], b[] = [3, 1, 3, 4, 1]
    Output: [1, 3]
    Explanation: 1 and 3 are the only common elements and we need to print only
    one occurrence of common elements.

    Input: a[] = [1, 1, 1], b[] = [1, 1, 1, 1, 1]
    Output: [1]
    Explanation: 1 is the only common element present in both the arrays.

    Input: a[] = [1, 2, 3], b[] = [4, 5, 6]
    Output: []
    Explanation: No common element in both the arrays.

    Constraints:
    1 ≤ a.size(), b.size() ≤ 10^5
    1 ≤ a[i], b[i] ≤ 10^5
*/
import java.util.*;

class Solution {
    
    // without extra space, sortina and two pointers
    public ArrayList<Integer> intersectionWithDuplicates(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int n1 = a.length, n2 = b.length;
        ArrayList<Integer> res = new ArrayList<>();
        
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (a[i] == b[j]) {
                // if already taken, avoid duplicates
                // move pointers in both
                if (res.isEmpty() || res.get(res.size() - 1) != a[i]) {
                    res.add(a[i]);
                }
                i++;
                j++;
            }
            else if (a[i] < b[j]) {
                i++;
            }
            else {
                j++;
            }
        }
        return res;
    }
    
    // used Extra Space
    public ArrayList<Integer> intersectionWithDuplicates_withExtraSpace(int[] a, int[] b) {
        // code here
        Set<Integer> hset = new HashSet<>();
        for (int num: a)
            hset.add(num);
        
        ArrayList<Integer> res = new ArrayList<>();
        for (int num: b) {
            if (hset.contains(num)) {
                res.add(num);
                hset.remove(num);
            }
        }
        return res;
    }
}