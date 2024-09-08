/*
  Given an array arr[] of non-negative integers. Each array element represents the maximum 
  length of the jumps that can be made forward from that element. This means if arr[i] = x, 
  then we can jump any distance y such that y ≤ x.
  
  Find the minimum number of jumps to reach the end of the array starting from the first element. 
  If an element is 0, then you cannot move through that element.
  Note:  Return -1 if you can't reach the end of the array.

  Examples : 
  Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
  Output: 3 
  Explanation:First jump from 1st element to 2nd element with value 3. 
  From here we jump to 5th element with value 9, and from here we will jump to the last. 
  
  Input: arr = {1, 4, 3, 2, 6, 7}
  Output: 2 
  Explanation: First we jump from the 1st to 2nd element and then jump to the last element.
  
  Input: arr = {0, 10, 20}
  Output: -1
  Explanation: We cannot go anywhere from the 1st element.
  
  Expected Time Complexity: O(n)
  Expected Space Complexity: O(1)

  Constraints:
  0 ≤ arr[i] ≤ 105
  2 ≤ arr.size() ≤ 10^6
*/

class Solution {
  static int minJumps(int[] arr) {
      // your code here
      // this solution can be solved in 3 ways:
      // 1. recursion: (but too much time is required)
      // Start from the first element and recursively call for all the elements 
      // reachable from the first element. The minimum number of jumps to reach
      // end from first can be calculated using the minimum value from the recursive calls. 
      // minJumps(start, end) = 1 + Min(minJumps(k, end)) for all k reachable from start.
      
      // 2. DP: memoization and tabulation    (but, space is reqd)
      // 3. Greedy approach: best one, solved here in this way
      
      int n = arr.length;
      if (n <= 1)
          return 0;   // no jump reqd
      
      // if the first element itself has a max. jump number >= last index, then we can go there in 1 jump
      // 1 jump becoz, we require the min. jumps to reach the end
      if (arr[0] >= n - 1)
          return 1;
          
      // if first element itself is 0, we can't reach the end
      if (arr[0] == 0)
          return -1;
      
      int maxReach = 0;   // stores the max. reachable index 
      int choice = 0;
      int jumps = 0;  // keeps track of the min. jumps reqd to reach the last element
      
      // going till (n-2)th element becoz, from there we can easily reach the end element
      for (int i = 0; i < n - 1; i++) {
          
          // max. index where can be reached from current position
          maxReach = Math.max(maxReach, arr[i] + i);
          if (i == choice)    {
              choice = maxReach;
              jumps++;
          }
          if (choice >= n-1)
              return jumps;
      }
      return -1;
  }
}