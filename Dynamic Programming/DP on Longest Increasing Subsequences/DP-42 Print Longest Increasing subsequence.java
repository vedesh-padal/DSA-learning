import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
  public ArrayList<Integer> printLongestIncreasingSubsequence(int n, int[] arr)  {
    
    int[] dp = new int[n];  // to keep track of the length of the increasing subsequence till that specific index
    Arrays.fill(dp, 1);   // init with 1, because given that even if no element is present before it, 
                              // it will atleast have 1 as length of the longest subsequence till this index

    int[] hash = new int[n];  // helps in backtracking, stores the prev. index of the curr. index element
    // hash will be initialised with array indices

    int maxi = 1; // to keep track of the longest increasing subsequence length, init with based on the dp init reason

    int lastIndex = 0;  // to keep track of the last index of the till now LIS having ending index

    for (int i = 0; i < n; i++) {
      for (int prev = 0; prev < i; prev++)  {

        if ( arr[i] > arr[prev] && 1 + dp[prev] > dp[i] ) {
          
          // update dp[i], when the num. of elements before it are
          // in asc. order and greater than the previously stored number of elements
          dp[i] = 1 + dp[prev];

          hash[i] = prev; // will be updated to keep track of the prev. element index of the curr. element
        }
      }

      if (dp[i] > maxi) {
        maxi = dp[i];
        lastIndex = i;
      }
    }

    ArrayList<Integer> al = new ArrayList<>();
    al.add(arr[lastIndex]);

    while (hash[lastIndex] != lastIndex)  {
      lastIndex = hash[lastIndex];
      al.add(arr[lastIndex]);
    }

    // becoz, we backtracked till now, and to obtain the list in asc order
    // i.e., from start to end of the original arr, we need to reverse the `al`
    Collections.reverse(al);
    
    return al;
  }
}