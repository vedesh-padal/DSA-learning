// MEDIUM
// arrays, binary-search

/*
    You are given an array arr[] of integers, where each element arr[i] represents the number 
    of pages in the ith book. You also have an integer k representing the number of students. 
    The task is to allocate books to each student such that:

    Each student receives atleast one book.
    Each student is assigned a contiguous sequence of books.
    No book is assigned to more than one student.
    The objective is to minimize the maximum number of pages assigned to any student. 
    In other words, out of all possible allocations, find the arrangement where the 
    student who receives the most pages still has the smallest possible maximum.

    Note: Return -1 if a valid assignment is not possible, and allotment should be in contiguous order 

    Examples:
    Input: arr[] = [12, 34, 67, 90], k = 2
    Output: 113
    Explanation: Allocation can be done in following ways:
    [12] and [34, 67, 90] Maximum Pages = 191
    [12, 34] and [67, 90] Maximum Pages = 157
    [12, 34, 67] and [90] Maximum Pages = 113.
    Therefore, the minimum of these cases is 113, which is selected as the output.

    Input: arr[] = [15, 17, 20], k = 5
    Output: -1
    Explanation: Allocation can not be done.
    Input: arr[] = [22, 23, 67], k = 1
    Output: 112

    Constraints:
    1 <=  arr.size() <= 10^6
    1 <= arr[i] <= 10^3
    1 <= k <= 10^3 
*/

// BINARY SEARCH ON ANSWER RELATED

class Solution {
    
    // here, maxPages is the pageLimit that we are setting
    // and checking that if ensuring this page limit, we are able to 
    // distribute each children atleast one book, without exceeding the set page limit (maxPages)
    private static boolean possible(int[] arr, int k, int maxPages) {
        int cnt = 1;    // one student is given the first book
        int sumPages = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (sumPages + arr[i] > maxPages) {
                cnt++;
                sumPages = arr[i];
                
                // either early break, or return at the end
                if (cnt > k)
                    return false;
                    
            }
            else {
                sumPages += arr[i];
            }
        }
        
        // becoz we want to ensure that no book is assigned to more than one student
        // return (cnt <= k);
        
        return true;
        
    }
    
    public static int findPages(int[] arr, int k) {
        // code here
        int n = arr.length;
        if (n < k)
            return -1;
        
        
        int l = Integer.MIN_VALUE;
        int h = 0;
        for (int i = 0; i < n; i++) {
            l = Math.max(l, arr[i]);    // since we want to minimize the maximum
            // and the least maximum is the max. element among the array
            
            // and h is the all books assigned to one student
            h += arr[i];
        }
        
        int minMaxi = Integer.MAX_VALUE;
        
        while (l <= h) {
            int mid = l + (h - l) / 2;
            
            if (possible(arr, k, mid)) {
                minMaxi = mid;
                h = mid - 1;    // becoz we are greedy to find the min. possible 
                // max. no. of pages we want to assign to students
            }
            else {
                l = mid + 1;
            }
        }
        return minMaxi;
    }
}