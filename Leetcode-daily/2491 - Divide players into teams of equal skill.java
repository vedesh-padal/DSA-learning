// You are given a positive integer array skill of even length n where skill[i]
// denotes the skill of the ith player. Divide the players into n / 2 teams of
// size 2 such that the total skill of each team is equal.

// The chemistry of a team is equal to the product of the skills of the players
// on that team.

// Return the sum of the chemistry of all the teams, or return -1 if there is no
// way to divide the players into teams such that the total skill of each team
// is equal.

// Example 1:
// Input: skill = [3,2,5,1,3,4]
// Output: 22
// Explanation:
// Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where
// each team has a total skill of 6.
// The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 +
// 9 = 22.

// Example 2:
// Input: skill = [3,4]
// Output: 12
// Explanation:
// The two players form a team with a total skill of 7.
// The chemistry of the team is 3 * 4 = 12.

// Example 3:
// Input: skill = [1,1,2,3]
// Output: -1
// Explanation:
// There is no way to divide the players into teams such that the total skill of
// each team is equal.

// Constraints:
// 2 <= skill.length <= 105
// skill.length is even.
// 1 <= skill[i] <= 1000

import java.util.*;

class Solution {
  public long dividePlayers(int[] skill) {
    int n = skill.length;
    int totalSum = 0;
    for (int num : skill)
      totalSum += num;

    // divide into n/2 teams of 2 size each
    if (totalSum % (n / 2) != 0)
      return -1;

    int target = totalSum / (n / 2); // should be a whole number

    Arrays.sort(skill); // SORTING IS KEY HERE
    int l = 0, r = n - 1;

    long chemistry = 0;

    while (l < r) {
      int sum = skill[l] + skill[r];
      if (sum == target) {
        chemistry += (skill[l] * skill[r]);
        l++;
        r--;
      } else
        return -1;
    }
    return chemistry;
  }

  // ===========================================================
  // HASHMAP - KIND OF COMPLICATED LOGIC
  // FAILS FOR TEST CASE WHEN DUPLICATE ELEMENTS
  // so, instead use two pointer approach after sorting
  // to solve the problem for duplicate elements
  // Input
  // skill =
  // [10,14,16,15,9,4,4,4]

  // Use Testcase
  // Output
  // 150
  // Expected
  // -1
  public long dividePlayers_HASHMAP(int[] skill) {
    // n/2 teams such that total sum is equal
    // first we find the total sum

    int totalSum = 0;
    for (int i : skill)
      totalSum += i;

    int n = skill.length;
    int numTeams = n / 2; // since told skill[] is even length, we get a whole number

    // for total sum of each team to be equal, we calculate it as
    if (totalSum % numTeams != 0)
      return -1;

    int target = totalSum / numTeams;

    // val, index
    Map<Integer, ArrayList<Integer>> hmap = new HashMap<>();

    @SuppressWarnings("unused")
    Set<Integer> seen = new HashSet<>();

    // List<int[]> al = new ArrayList<>();
    long res = 0;

    for (int i = 0; i < n; i++) {
      int req = target - skill[i];
      if (hmap.containsKey(req)) {

        res += (long) req * skill[i];
        // remove the used player
        hmap.get(req).remove(0);
        if (hmap.get(req).isEmpty())
          hmap.remove(req);

        // if (!seen.contains(hmap.get(req))) {
        //   seen.add(hmap.get(req));
        //   al.add(new int[] { hmap.get(req), i });
        // }
        // else
      } else {
        hmap.putIfAbsent(skill[i], new ArrayList<>());
        hmap.get(skill[i]).add(i); // Add current index to list

        // hmap.put(skill[i], hmap.get(skill[i]).add(i));
      }

    }

    // for (int[] arr : al) {
    //   System.out.println(skill[arr[0]] + " " + skill[arr[1]]);
    //   res += skill[arr[0]] * skill[arr[1]];
    // }

    return res == 0 ? -1 : res;
  }
}