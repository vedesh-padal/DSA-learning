// Moore's Voting Algorithm

// Optimal Approach to find Majority Element  [ > n/2 times ]

class Solution {
  public static int majorityElement(int[] arr)  {
    int n = arr.length;
    int cnt = 0; 
    int ele = 0;

    for (int i=0; i<n; i++) {
      if (cnt == 0) {
        cnt = 1;
        ele = arr[i];
      }
      else if (ele == arr[i]) {
        cnt++;
      }
      else 
        cnt--;
    }

    // checking if stored element is majority element
    int cnt1 = 0;
    for (int i: arr)  {
      if (i == ele)
        cnt1++;
    }

    if (cnt1 > n/2)
      return ele;

    return -1;
  }
}
