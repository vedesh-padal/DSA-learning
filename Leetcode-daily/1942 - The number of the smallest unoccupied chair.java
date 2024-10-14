// 11-10-2024
// MEDIUM
// heap, arrays

// There is a party where n friends numbered from 0 to n - 1 are attending.
// There is an infinite number of chairs in this party that are numbered from 0
// to infinity. When a friend arrives at the party, they sit on the unoccupied
// chair with the smallest number.

// For example, if chairs 0, 1, and 5 are occupied when a friend comes, they
// will sit on chair number 2.
// When a friend leaves the party, their chair becomes unoccupied at the moment
// they leave. If another friend arrives at that same moment, they can sit in
// that chair.

// You are given a 0-indexed 2D integer array times where times[i] = [arrivali,
// leavingi], indicating the arrival and leaving times of the ith friend
// respectively, and an integer targetFriend. All arrival times are distinct.

// Return the chair number that the friend numbered targetFriend will sit on.

// Example 1:
// Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
// Output: 1
// Explanation:
// - Friend 0 arrives at time 1 and sits on chair 0.
// - Friend 1 arrives at time 2 and sits on chair 1.
// - Friend 1 leaves at time 3 and chair 1 becomes empty.
// - Friend 0 leaves at time 4 and chair 0 becomes empty.
// - Friend 2 arrives at time 4 and sits on chair 0.
// Since friend 1 sat on chair 1, we return 1.

// Example 2:
// Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
// Output: 2
// Explanation:
// - Friend 1 arrives at time 1 and sits on chair 0.
// - Friend 2 arrives at time 2 and sits on chair 1.
// - Friend 0 arrives at time 3 and sits on chair 2.
// - Friend 1 leaves at time 5 and chair 0 becomes empty.
// - Friend 2 leaves at time 6 and chair 1 becomes empty.
// - Friend 0 leaves at time 10 and chair 2 becomes empty.
// Since friend 0 sat on chair 2, we return 2.

// Constraints:

// n == times.length
// 2 <= n <= 104
// times[i].length == 2
// 1 <= arrivali < leavingi <= 105
// 0 <= targetFriend <= n - 1
// Each arrival(i) time is distinct.

import java.util.*;

class Pair {
  int depTime;
  int chairNum;

  public Pair(int depTime, int chairNum) {
    this.depTime = depTime;
    this.chairNum = chairNum;
  }
}

class Solution {

  // Optimal Approach using Min Heap
  public int smallestChair(int[][] times, int targetFriend) {
    int n = times.length;

    // 2 Min Heaps are required
    // 1. {departureTime, chairNum}
    // 2. {freeChair}

    // at this departure time, the chair that will get free
    // { depTime, chairNum }
    PriorityQueue<Pair> occupied = new PriorityQueue<>((a, b) -> a.depTime - b.depTime);

    // min. indexed available chair
    PriorityQueue<Integer> free = new PriorityQueue<>();

    int targetFriendArrTime = times[targetFriend][0];

    int chairNum = 0;

    // sort `times` based on arrival time
    Arrays.sort(times, (a, b) -> a[0] - b[0]);

    for (int i = 0; i < n; i++) {
      int arr = times[i][0];
      int dep = times[i][1];

      // before the arrival of this friend,
      // who all already left needs to be removed from occupied Min Heap
      while (!occupied.isEmpty() && occupied.peek().depTime <= arr) {
        free.offer(occupied.peek().chairNum);
        occupied.poll();
      }

      // if I dont have free chairs available yet,
      // to assign it to the incoming friend, THEN,
      // we assign the least available chair (that is tracked with chairNum)
      // to the incoming friend
      if (free.isEmpty()) {
        occupied.offer(new Pair(dep, chairNum));
        if (arr == targetFriendArrTime)
          return chairNum;

        chairNum++;
      } else {
        int leastChairAvailable = free.peek();
        free.poll();
        if (arr == targetFriendArrTime)
          return leastChairAvailable;

        occupied.offer(new Pair(dep, leastChairAvailable));
      }

    }
    return -1;
  }

  // ====================================================
  // Brute Force - O(N^2)
  public int smallestChair_BRUTE(int[][] times, int targetFriend) {
    int n = times.length;
    int targetFriendArrTime = times[targetFriend][0];

    // sort times based on arrival time
    Arrays.sort(times, (a, b) -> a[0] - b[0]);

    int[] chairs = new int[n];
    Arrays.fill(chairs, -1);

    for (int i = 0; i < n; i++) {
      int arr = times[i][0];
      int dep = times[i][1];

      // check from least chair which is available
      // to place the next incoming friend
      for (int j = 0; j < n; j++) {
        if (chairs[j] <= arr) {
          chairs[j] = dep;

          // if the incoming friend is the target friend
          // then we return the chair number on which we are placing
          if (arr == targetFriendArrTime) {
            return j;
          }

          // we have placed the incoming friend
          // on a chair, so break, and move onto next friend
          break;
        }
      }
    }
    return -1;
  }
}
