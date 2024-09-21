import java.util.ArrayList;
import java.util.List;

class Solution {
  private static void dfs(int curr, int n, List<Integer> res) {
    if (curr > n)
      return;

    res.add(curr);
    curr *= 10;
    for (int i = 0; i < 10; i++) {
      dfs(curr + i, n, res);
    }
  }

  // Applying DFS
  public List<Integer> lexicalOrderRec(int n) {
    List<Integer> res = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
      dfs(i, n, res);
    }
    return res;
  }

  public List<Integer> lexicalOrder(int n) {
    List<Integer> res = new ArrayList<>();
    int curr = 1;

    // // doing this is quite in-efficient:
    // while (res.size() < n)

    // so, run it for n times
    for (int i = 1; i <= n; i++) {
      res.add(curr);

      // highest precendence, acc. to lexicographical order, so first multiply by 10
      if (curr * 10 <= n) { // if that is less than or equal to our n
        curr *= 10;
      } else {
        // if you find the end number n, backtrack
        // or if you find the last digit of the curr to be 9, you backtrack
        // here, it is while loop instead of if statement, becoz, you might be at very
        // end
        // i mean: 19 <- 199 <- 1999 [ you should be able to backtrack that many times
        // continuously]
        while (curr == n || (curr % 10) == 9) {
          curr = curr / 10;
        }
        curr++;
      }
    }
    return res;
  }

  
  // MORE ELEGANT SOLUTION WITH BETTER UNDERSTANDING IN LC SOLUTIONS:

  public List<Integer> lexicalOrderBEST(int n) {
    List<Integer> lexicographicalNumbers = new ArrayList<>();
    // Start generating numbers from 1 to 9
    for (int start = 1; start <= 9; ++start) {
      generateLexicalNumbers(start, n, lexicographicalNumbers);
    }
    return lexicographicalNumbers;
  }
  
  // basically again DFS, but with early stopping
  private void generateLexicalNumbers(
      int currentNumber,
      int limit,
      List<Integer> result) {
        // If the current number exceeds the limit, stop recursion
        if (currentNumber > limit)
      return;

    // Add the current number to the result
    result.add(currentNumber);
    
    // Try to append digits from 0 to 9 to the current number
    for (int nextDigit = 0; nextDigit <= 9; ++nextDigit) {
      int nextNumber = currentNumber * 10 + nextDigit;
      // If the next number is within the limit, continue recursion
      if (nextNumber <= limit) {
        generateLexicalNumbers(nextNumber, limit, result);
      } else {
        break; // No need to continue if nextNumber exceeds limit
      }
    }
  }

}