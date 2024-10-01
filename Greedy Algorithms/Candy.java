// There are n children standing in a line. Each child is assigned a rating
// value given in the integer array ratings.

// You are giving candies to these children subjected to the following
// requirements:

// Each child must have at least one candy.
// Children with a higher rating get more candies than their neighbors.
// Return the minimum number of candies you need to have to distribute the
// candies to the children.

// Example 1:
// Input: ratings = [1,0,2]
// Output: 5
// Explanation: You can allocate to the first, second and third child with 2, 1,
// 2 candies respectively.

// Example 2:
// Input: ratings = [1,2,2]
// Output: 4
// Explanation: You can allocate to the first, second and third child with 1, 2,
// 1 candies respectively.
// The third child gets 1 candy because it satisfies the above two conditions.

// Constraints:
// n == ratings.length
// 1 <= n <= 2 * 104
// 0 <= ratings[i] <= 2 * 104

class Solution {
  public int candySIMPLE(int[] ratings) {
    int n = ratings.length;
    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = 1;
    right[n - 1] = 1;

    // comparing with left neighbour and alloting candy
    for (int i = 1; i < n; i++) {
      if (ratings[i] > ratings[i - 1])
        left[i] = left[i - 1] + 1;
      else
        left[i] = 1;
    }

    for (int i = n - 2; i >= 0; i--) {
      if (ratings[i] > ratings[i + 1])
        right[i] = right[i + 1] + 1;
      else
        right[i] = 1;
    }

    // now pick the max. of both so that it satisfies both conditions
    // especially, candy to the current child whose rating is more 
    // than it's neighbours
    int numCandies = 0;
    for (int i = 0; i < n; i++) {
      numCandies += Math.max(left[i], right[i]);
    }

    return numCandies;
  }

  // ==============================================
  // USING SLOPE APPROACH
  // NO EXTRA SPACE AND TRAVERSAL
  // TC: O(N) , SC: O(N)
  public int candy(int[] ratings) {
    int n = ratings.length;

    // n children with their rating
    // acc. to first condition, each child should get
    // atleast one candy
    int numCandies = n;

    // GOING TOWARDS LEFT
    int i = 1;
    // we will start comparing from 2nd element, hence i = 1
    while (i < n) {
      // one iteration of this outer while loop,
      // until there is:
      // flat OR increasing and decreasing [ mountain ]

      // if the prev. and curr. rating is same
      // do nothing
      if (ratings[i - 1] == ratings[i]) {
        i++;
        continue;
      }

      // INCREASING SLOPE - PEAK
      int peak = 0;
      while (ratings[i - 1] < ratings[i]) {
        peak++;
        numCandies += peak;
        i++;
        if (i == n)
          return numCandies;
      }

      // DECREASING SLOPE - DIP
      int dip = 0;
      // i < n condition, becoz, we will need to subtract the extra addition
      while (i < n && ratings[i - 1] > ratings[i]) {
        dip++;
        numCandies += dip;
        i++;
      }

      // during this process
      // up and down
      // we will have added extra candy

      // at the peak, we will keep the max that was added,
      // and remove the min (extra)

      // we are removing it from numCandies, since that was
      // keeping track of the candies given to each child
      numCandies -= Math.min(peak, dip);
      // that is why we were adding `peak` and `dip` to numCandies
      // instead of +1, so that we could remove it after one mountain

    }
    return numCandies;
  }
}