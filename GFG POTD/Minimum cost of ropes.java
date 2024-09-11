/*
  Given an array arr containing the lengths of the different ropes, we need to connect these 
  ropes to form one rope. The cost to connect two ropes is equal to sum of their lengths. 
  The task is to connect the ropes with minimum cost.  

  Example:
  Input: arr[] = [4, 3, 2, 6]
  Output: 29
  Explanation: We can connect the ropes in following ways.
  1) First connect ropes of lengths 2 and 3. Which makes the array [4, 5, 6]. Cost of this operation 2 + 3 = 5. 
  2) Now connect ropes of lengths 4 and 5. Which makes the array [9, 6]. Cost of this operation 4 + 5 = 9.
  3) Finally connect the two ropes and all ropes have connected. Cost of this operation 9 + 6 =15
  Total cost for connecting all ropes is 5 + 9 + 15 = 29. This is the optimized cost for connecting ropes. 
  Other ways of connecting ropes would always have same or more cost. For example, if we connect 4 
  and 6 first (we get three rope of 3, 2 and 10), then connect 10 and 3 (we gettwo rope of 13 and 2). 
  Finally we connect 13 and 2. Total cost in this way is 10 + 13 + 15 = 38.
*/
import java.util.PriorityQueue;
// EASY
class Solution {

  // Function to return the minimum cost of connecting the ropes.
  public long minCost(long[] arr) {
    // code here
    PriorityQueue<Long> pq = new PriorityQueue<>();
    for (long i : arr)
      pq.add(i);

    long ans = 0;

    // while you have two elements in the priority queue
    while (pq.size() > 1) {
      long lowest = pq.poll();
      long nextLowest = pq.poll();

      ans += lowest + nextLowest;
      // add the connected rope back to the priority queue
      pq.offer(lowest + nextLowest);
    }
    return ans;
  }
}