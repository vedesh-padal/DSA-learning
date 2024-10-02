// k positions to right

class Solution {
  public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
    if (n == 1 && k > n)
      return;

    rotateArr(nums, 0, n - k - 1);
    rotateArr(nums, n - k, n - 1);
    rotateArr(nums, 0, n - 1);
  }

  private void rotateArr(int[] arr, int s, int e) {
    while (s <= e) {
      int t = arr[s];
      arr[s++] = arr[e];
      arr[e--] = t;
    }
  }
}