class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
      
      // int[] res = new int[m+n];
      // int i = 0;
      // int l = 0, r = 0;
      // while (l < m && r < n)  {
      //     if (nums1[l] <= nums2[r])   {
      //         res[i++] = nums1[l];
      //         l++;
      //     }
      //     else {
      //         res[i++] = nums2[r];
      //         r++;
      //     }
      // }

      // while (l < m)   {
      //     res[i++] = nums1[l];
      //     l++;
      // }

      // while (r < n)   {
      //     res[i++] = nums2[r];
      //     r++;
      // }

      // for (i=0; i<(m+n); i++) {
      //     nums1[i] = res[i];
      // }


      // Approach - 2: with no extra space
      int i = m + n - 1;
      int l = m - 1, r = n - 1;

      // merge in reverse order
      while (r >= 0)    {
          if (l >= 0 && nums1[l] > nums2[r])
              nums1[i--] = nums1[l--];
          else 
              nums1[i--] = nums2[r--];
      }

  }
}