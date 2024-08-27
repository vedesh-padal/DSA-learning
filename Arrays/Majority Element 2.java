import java.util.*;
// extended Moore's voting algorithm

class Solution {
  public List<Integer> majorityElement(int[] nums) {
    int cnt1 = 0, cnt2 = 0;
    int ele1 = Integer.MIN_VALUE, ele2 = Integer.MIN_VALUE;
    int n = nums.length;

    for (int i = 0; i < n; i++) {
      if (cnt1 == 0 && ele2 != nums[i]) {
        cnt1 = 1;
        ele1 = nums[i];
      } else if (cnt2 == 0 && ele1 != nums[i]) {
        cnt2 = 1;
        ele2 = nums[i];
      } else if (nums[i] == ele1)
        cnt1++;
      else if (nums[i] == ele2)
        cnt2++;
      else {
        cnt1--;
        cnt2--;
      }
    }

    List<Integer> ls = new ArrayList<>(); // list of answers

    // Manually check if the stored elements in
    // el1 and el2 are the majority elements:
    cnt1 = 0;
    cnt2 = 0;
    for (int i = 0; i < n; i++) {
      if (nums[i] == ele1)
        cnt1++;
      if (nums[i] == ele2)
        cnt2++;
    }

    int mini = (int) (n / 3) + 1;
    if (cnt1 >= mini)
      ls.add(ele1);
    if (cnt2 >= mini)
      ls.add(ele2);

    // if told to sort:
    // Collections.sort(ls); //TC --> O(2*log2) ~ O(1);

    return ls;
  }
}