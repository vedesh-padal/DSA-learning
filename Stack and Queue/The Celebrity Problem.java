// medium - hard
// stack, arrays

// A celebrity is a person who is known to all but does not know anyone at a
// party. A party is being organized by some people. A square matrix mat is used
// to represent people at the party such that if an element of row i and column
// j is set to 1 it means ith person knows jth person. You need to return the
// index of the celebrity in the party, if the celebrity does not exist, return -1.

// Note: Follow 0-based indexing.

// Examples:
// Input: mat[][] = [[0 1 0],
// [0 0 0],
// [0 1 0]]
// Output: 1
// Explanation: 0th and 2nd person both know 1. Therefore, 1 is the celebrity.

// Input: mat[][] = [[0 1],
// [1 0]]
// Output: -1
// Explanation: The two people at the party both know each other. None of them
// is a celebrity.
// Expected Time Complexity: O(n2)
// Expected Auxiliary Space: O(1)

// Constraints:
// 1 <= mat.size()<= 3000
// 0 <= mat[i][j]<= 1

// actually this is a stack problem
// but we even simplified it here in the 
// optimal solution

class Solution {
  // Function to find if there is a celebrity in the party or not.

  // OPTIMAL WAY OF SOLVING
  public int celebrity(int mat[][]) {
    int n = mat.length;

    int top = 0, down = n - 1;

    // NOTE:
    // if mat[top][down] = 1 => means, top knows down
    // if mat[down][top] = 1 => means, down knows top

    while (top < down) {
      // then top cannot be a celebrity
      if (mat[top][down] == 1)
        top++;
      // then down cannot be a celebrity
      else if (mat[down][top] == 1)
        down--;
      else {
        // if neither of them don't know
        // each other, then, they cannot
        // be celebrities
        top++;
        down--;
      }
    }

    // for a celebrity to exist, top and down should be equal
    // indicating that there is one celebrity
    // and there can only be one celebrity
    if (top > down)
      return -1;

    // now, we have to actually check if the pointing one
    // is actually the celebrity
    // by checking it's row, each of it should be 0
    // indicating that he/she knows no-one
    // and his col. should be all 1s,
    // indicating everyone knows him

    // now, we have top == down. so we can choose any
    for (int i = 0; i < n; i++) {

      // and we skip the diagonal elements since,
      // one knowing himself == 0, and doesn't make any sense
      if (i == top)
        continue;

      if ((mat[top][i] == 0) && (mat[i][top] == 1))
        continue;
      else
        return -1;
    }
    return top;
  }

  // Naive way of solving - easy to understand
  // O(n^2) and extra space (O(2N))
  public int celebrity_EXTRA_SPACE(int mat[][]) {
    // code here
    int n = mat.length;
    int[] iKnow = new int[n];
    int[] knowMe = new int[n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (mat[i][j] == 1) {
          iKnow[i]++;
          knowMe[j]++;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      if (knowMe[i] == n - 1 && iKnow[i] == 0)
        return i; // and there wil be only one celebrity
    }
    return -1;
  }
}