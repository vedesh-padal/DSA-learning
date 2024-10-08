// We are given an array asteroids of integers representing asteroids in a row.

// For each asteroid, the absolute value represents its size, and the sign
// represents its direction (positive meaning right, negative meaning left).
// Each asteroid moves at the same speed.

// Find out the state of the asteroids after all collisions. If two asteroids
// meet, the smaller one will explode. If both are the same size, both will
// explode. Two asteroids moving in the same direction will never meet.

// Example 1:
// Input: asteroids = [5,10,-5]
// Output: [5,10]
// Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never
// collide.

// Example 2:
// Input: asteroids = [8,-8]
// Output: []
// Explanation: The 8 and -8 collide exploding each other.

// Example 3:
// Input: asteroids = [10,2,-5]
// Output: [10]
// Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide
// resulting in 10.

// Constraints:
// 2 <= asteroids.length <= 104
// -1000 <= asteroids[i] <= 1000
// asteroids[i] != 0

import java.util.List;
import java.util.ArrayList;

class Solution {

  public int[] asteroidCollision(int[] asteroids) {
    List<Integer> stk = new ArrayList<>();
    int n = asteroids.length;

    for (int i = 0; i < n; i++) {
      if (asteroids[i] > 0)
        stk.add(asteroids[i]);
      else {
        while (!stk.isEmpty() && stk.get(stk.size() - 1) > 0 && stk.get(stk.size() - 1) < Math.abs(asteroids[i]))
          stk.remove(stk.size() - 1);

        if (!stk.isEmpty() && stk.get(stk.size() - 1) == Math.abs(asteroids[i]))
          stk.remove(stk.size() - 1);

        // we only push the -ve size (i.e., size moving in opposite direction)
        // to stack, when stack is empty or stk's top is -ve
        else if (stk.isEmpty() || stk.get(stk.size() - 1) < 0)
          stk.add(asteroids[i]);
      }
    }

    int stkSize = stk.size();
    int[] res = new int[stkSize];
    for (int i = 0; i < stkSize; i++)
      res[i] = stk.get(i);

    return res;
  }

}