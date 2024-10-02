// Alice has some number of cards and she wants to rearrange the cards into
// groups so that each group is of size groupSize, and consists of groupSize
// consecutive cards.

// Given an integer array hand where hand[i] is the value written on the ith
// card and an integer groupSize, return true if she can rearrange the cards, or
// false otherwise.

// Example 1:
// Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
// Output: true
// Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]

// Example 2:
// Input: hand = [1,2,3,4,5], groupSize = 4
// Output: false
// Explanation: Alice's hand can not be rearranged into groups of 4.

// Constraints:
// 1 <= hand.length <= 104
// 0 <= hand[i] <= 109
// 1 <= groupSize <= hand.length

import java.util.*;

class Solution {

  public boolean isNStraightHand(int[] hand, int groupSize) {
    if (hand.length % groupSize != 0)
      return false;

    Arrays.sort(hand); // O(nlogn)
    Map<Integer, Integer> cntMap = new HashMap<>();

    for (int card : hand)
      cntMap.put(card, cntMap.getOrDefault(card, 0) + 1);

    // O(n*groupSize)
    for (int card : hand) {
      // the starting card of the group,
      // does not exists or was consumed
      // so consider next element in the sorted cards
      // as the starting element of the group
      if (cntMap.get(card) == 0)
        continue;

      // considering the current card => `card` as the
      // starting element of the group
      // check if we can form a group of consecutive elements
      for (int i = 0; i < groupSize; i++) {
        int currCard = card + i;

        // if the next consecutive element
        // we were checking from the sorted elements of hand
        // u had the first one or few elements of the group
        // but the next card's freq is 0, means it does not exist in the cards
        // which means we cannot form the group, inspite having told
        // that `groupSize` no. of groups can be formed
        // so we return false
        if (cntMap.getOrDefault(currCard, 0) == 0) {
          return false;
        }
        // we have considered the currCard in our group
        // so decrease it's count
        cntMap.put(currCard, cntMap.get(currCard) - 1);
      }
    }
    return true;
  }

  // =======================================================
  public boolean isNStraightHand_SIMPLE_MINHEAP(int[] hand, int groupSize) {
    if (hand.length % groupSize != 0)
      return false;

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // O(nlogn)
    for (int card : hand)
      minHeap.add(card);

    while (!minHeap.isEmpty()) {
      int startEleOfGroup = minHeap.poll();
      // starting from 1, becoz we already removed the starting element of the group
      for (int i = 1; i < groupSize; i++) {

        // if next consecutive element of the group does not exists
        if (!minHeap.remove(startEleOfGroup + i))
          return false;
      }
    }
    return true;
  }

  // ====================================================
  public boolean isNStraightHand_TREEMAP(int[] hand, int groupSize) {
    if (hand.length % groupSize != 0)
      return false;

    // num, freq
    TreeMap<Integer, Integer> tmap = new TreeMap<>();

    // O(nlogn)
    for (int num : hand) {
      tmap.put(num, tmap.getOrDefault(num, 0) + 1);
    }

    while (!tmap.isEmpty()) {
      int curr = tmap.firstEntry().getKey();

      for (int i = 0; i < groupSize; i++) {
        // there is no consecutive element
        if (!tmap.containsKey(curr + i))
          return false;

        tmap.put(curr + i, tmap.get(curr + i) - 1);

        // freq. of it is 0
        if (tmap.get(curr + i) < 1)
          tmap.remove(curr + i);

      }
    }
    return true;
  }

  // =======================================================
  //// MY TRY - WAS GIVING TLE
  public boolean isNStraightHand_MY_TRY(int[] hand, int groupSize) {
    Set<Integer> hset = new HashSet<>();
    for (int num : hand)
      hset.add(num);

    int n = hset.size();
    int[] nums = new int[n];

    Iterator<Integer> it = hset.iterator();
    int i = 0;
    while (it.hasNext())
      nums[i] = it.next();

    Arrays.sort(nums);
    int numGroups = 0;
    int start = 0;
    while (n - start >= groupSize) {
      i = start + 1;
      int cnt = 1;
      while (cnt != groupSize) {
        if (nums[i - 1] == nums[i])
          continue;
        else {
          if (nums[i] == nums[i - 1] + 1)
            cnt++;
          else {
            // return false;
            i++;
            break; // becoz, we are not having consecutive cards
          }
        }
        i++;
      }
      if (cnt == groupSize)
        numGroups++;

      // start = i-1;
      start = start + 1;
    }
    return numGroups == groupSize;
  }
}