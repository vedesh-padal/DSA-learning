import java.util.ArrayList;

class Solution {
  // Function to sort an array of 0s, 1s, and 2s
  public void sort012(ArrayList<Integer> arr) {
    // code here
    int i = 0; // to keep track of 0s
    int j = 0; // to keep track of 1s
    int k = arr.size() - 1; // to keep track of 2s
    int t = 0;
    while (j <= k) {
      if (arr.get(j) == 0) {
        t = arr.get(i);
        arr.set(i++, arr.get(j));
        arr.set(j++, t);
      } else if (arr.get(j) == 1) {
        j++;
      } else {
        t = arr.get(k);
        arr.set(k--, arr.get(j));
        arr.set(j, t);
      }
    }
  }
}