// MEDIUM
// arrays, binary-search

// Koko loves to eat bananas. There are n piles of bananas, the ith pile has
// piles[i] bananas. The guards have gone and will come back in h hours.

// Koko can decide her bananas-per-hour eating speed of k. Each hour, she
// chooses some pile of bananas and eats k bananas from that pile. If the pile
// has less than k bananas, she eats all of them instead and will not eat any
// more bananas during this hour.

// Koko likes to eat slowly but still wants to finish eating all the bananas
// before the guards return.

// Return the minimum integer k such that she can eat all the bananas within h hours.

// Example 1:
// Input: piles = [3,6,7,11], h = 8
// Output: 4

// Example 2:
// Input: piles = [30,11,23,4,20], h = 5
// Output: 30

// Example 3:
// Input: piles = [30,11,23,4,20], h = 6
// Output: 23

// Constraints:
// 1 <= piles.length <= 104
// piles.length <= h <= 109
// 1 <= piles[i] <= 109


// BINARY SEARCH ON ANSWERS - type of problem
// clear explanation provided for similar kind of problem - LC - 2064

class Solution {
  private boolean isPossible(int[] piles, int rate, int h) {
    for (int pile : piles) {
      // int hrs = (int) Math.ceil((double)pile / rate);
      int hrs = (pile + rate - 1) / rate; // fancy way of writing ceil

      h -= hrs;

      // koko still has bananas to eat, but time is up
      if (h < 0) {
        return false;
      }
    }
    return true;
  }

  public int minEatingSpeed(int[] piles, int h) {
    int maxi = 0; // max. no. of bananas in any pile
    for (int pile : piles) {
      maxi = Integer.max(maxi, pile);
    }

    int l = 1, r = maxi;
    int res = r;
    while (l <= r) {
      int mid = l + (r - l) / 2;

      if (isPossible(piles, mid, h)) {
        res = mid;
        r = mid - 1;
      } 
      else {
        l = mid + 1;
      }
    }

    return res;
  }
}