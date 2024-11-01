// MEDIUM
// greedy, math

// There is a broken calculator that has the integer startValue on its display
// initially. In one operation, you can:

// - multiply the number on display by 2, or
// - subtract 1 from the number on display.

// Given two integers startValue and target, return the MINIMUM number of
// operations needed to display target on the calculator.

// Example 1:
// Input: startValue = 2, target = 3
// Output: 2
// Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.

// Example 2:
// Input: startValue = 5, target = 8
// Output: 2
// Explanation: Use decrement and then double {5 -> 4 -> 8}.

// Example 3:
// Input: startValue = 3, target = 10
// Output: 3
// Explanation: Use double, decrement and double {3 -> 6 -> 5 -> 10}.

// Constraints:
// 1 <= startValue, target <= 109

class Solution {

  // we can go from start to target also
  // by applying recursion ~ bfs and exploring two options
  // and base cases being we should not have currVal as -ve
  // and if we have already reached target, we dont go further
  // by using visited array or hashmap
  // but can be complex and can be solved simply using Greedy approach

  // By, going from target val to start val
  // since we are greedy to reach target, whenever we see
  // that target is even number, we divide by 2
  // (DIVIDE, becoz we are moving from target to startVal)
  // and when target is odd number, we add 1 (becoz: startVal <---- targetVal)

  // and before dividing by 2, we check if the targetVal > startVal
  // if not, we add 1 'k' no. of times
  // where k is the difference between startVal and curr. targetVal
  // we have no option than adding 1 that many times, as startVa > targetVal

  public int brokenCalc(int startValue, int target) {
    if (startValue == target)
      return 0;

    if (startValue > target)
      return startValue - target;

    if (target % 2 == 0) {
      // + 1 becoz, i operation
      return 1 + brokenCalc(startValue, target / 2);
    } else {
      return 1 + brokenCalc(startValue, target + 1);
    }
  }
}