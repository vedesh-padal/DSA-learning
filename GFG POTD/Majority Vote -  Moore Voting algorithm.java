// You are given an array of integer nums[] where each number represents a vote
// to a candidate. Return the candidates that have votes greater than one-third
// of the total votes, If there's not a majority vote, return -1.

// Note: The answer should be returned in an increasing format.

// Examples:

// Input: nums = [2, 1, 5, 5, 5, 5, 6, 6, 6, 6, 6]
// Output: [5, 6]
// Explanation: 5 and 6 occur more n/3 times.
// Input: nums = [1, 2, 3, 4, 5]
// Output: [-1]
// Explanation: no candidate occur more than n/3 times.
// Expected Time Complexity: O(n)
// Expected Space Complexity: O(1)

// Constraint:
// 1 <= nums.size() <= 106
// 0 <= nums[i] <= 109

import java.util.*;

class Solution {
  // Function to find the majority elements in the array
  // Boyr-Moore Majority Voting algorithm
  public List<Integer> findMajority(List<Integer> nums) {
    // candidates having greater than n/3 votes, can be
    // atmost 2 candidates

    int cand1 = -1, cand2 = -1, cnt1 = 0, cnt2 = 0;
    int n = nums.size();

    // so, in 1st pass, we find the potential candidates

    for (int num : nums) {
      if (cnt1 == 0 && cand2 != num) {
        cand1 = num;
        cnt1 = 1;
      } else if (cnt2 == 0 && cand1 != num) {
        cand2 = num;
        cnt2 = 1;
      } else if (cand1 == num) {
        cnt1++;
      } else if (cand2 == num) {
        cnt2++;
      } else {
        cnt1--;
        cnt2--;
      }
    }

    // in 2nd pass, we count the occurences of potential candidates
    cnt1 = 0;
    cnt2 = 0;

    for (int num : nums) {
      if (num == cand1)
        cnt1++;
      else if (num == cand2)
        cnt2++;
    }

    List<Integer> res = new ArrayList<>();
    if (cnt1 > n / 3)
      res.add(cand1);
    if (cnt2 > n / 3)
      res.add(cand2);

    if (res.isEmpty())
      res.add(-1);
    else
      Collections.sort(res);

    return res;

  }

  public List<Integer> findMajority_MY_TRY(List<Integer> nums) {
    // Your code goes here.
    int n = nums.size();
    Collections.sort(nums);

    int cnt = 0;
    int threshold = n / 3;
    List<Integer> res = new ArrayList<>();

    for (int i = 1; i < n; i++) {
      if (nums.get(i) != nums.get(i - 1))
        cnt = 1;
      else {
        cnt++;

        if (cnt > threshold) {
          res.add(nums.get(i));
          while (i + 1 < n && nums.get(i + 1) == nums.get(i))
            i++;
          // cnt = 1;
        }
      }
    }
    return res.size() != 0 ? res : new ArrayList<>(Arrays.asList(-1));
  }

}

// my try, but it seems that it is a famous Algorithm
