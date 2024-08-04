class Solution {
  public int minimumEnergy(int arr[], int N) {
    // code here
    // this seems to be a DP problem
    // doing it with tabulation method and space optimization

    int prev2 = 0, prev = 0;
    int curr = 0;
    for (int i = 1; i < N; i++) {
      int first = prev2 + Math.abs(arr[i] - arr[i - 1]); // can either jump 1 step at a time
      int second = Integer.MAX_VALUE;
      if (second > 1) {
        second = prev + Math.abs(arr[i] - arr[i - 2]); // or can just two steps at a time
      }
      curr = Math.min(first, second);
      prev2 = prev;
      prev = curr;
    }
    return prev;
  }
}