class Solution {
  public void reverseArray(int arr[]) {
    int n = arr.length;
    for (int i = 0; i < n / 2; i++) {
      int t = arr[n - i - 1];
      arr[n - i - 1] = arr[i];
      arr[i] = t;
    }
  }
}