import java.util.HashMap;

class Solution {
  public static int countSubArraysWithXORk(int[] arr, int k) {
    int n = arr.length;
    int cnt = 0;
    int xr = 0;

    HashMap<Integer, Integer> hmap = new HashMap<>();
    hmap.put(xr, 1); // setting the value of 0 : if the first element is same as k, then when xor, we
                     // don't want to put the len of subarray as 0, instead we put as 1 explicitly

    for (int i = 0; i < n; i++) {
      // prefix xor till index i
      xr = xr ^ arr[i];

      int x = xr ^ k;

      // add occurenc eof xr ^ k to the count
      if (hmap.containsKey(x)) {
        cnt += hmap.get(x);
      }

      // insert prefix xor till index i into the map
      hmap.put(xr, hmap.getOrDefault(xr, 0) + 1);

      // if (hmap.containsKey(xr)) {
      //   hmap.put(xr, hmap.get(xr) + 1);
      // }
      // else {
      //   hmap.put(xr, 1);
      // }

    }

    return cnt;
  }

  public static void main(String[] args) {
    int[] a = { 4, 2, 2, 6, 4 };
    int k = 6;
    int ans = countSubArraysWithXORk(a, k);
    System.out.println("The number of subarrays with XOR k is: " + ans);
  }
}