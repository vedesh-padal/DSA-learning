import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

  public static List<List<Integer>> findTriplet1(int n, int[] arr) {
    Set<List<Integer>> st = new HashSet<>();

    for (int i = 0; i < n; i++) {
      Set<Integer> hashset = new HashSet<>();
      for (int j = i + 1; j < n; j++) {
        // Calculate the 3rd element:
        int third = -(arr[i] + arr[j]);

        // Find the element in the set:
        if (hashset.contains(third)) {
          List<Integer> temp = Arrays.asList(arr[i], arr[j], third);
          temp.sort(null);
          st.add(temp);
        }
        hashset.add(arr[j]);
      }
    }

    // store the set elements in the answer:
    List<List<Integer>> ans = new ArrayList<>(st);
    return ans;
  }


  // -------------------------------------------------------------------------
  // OPTIMAL SOLUTION

  public static List<List<Integer>> findTriplet2(int[] arr) {
    
    int n = arr.length;
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(arr);
    
    for (int i = 0; i < n; i++) {
      if (i != 0 && arr[i] == arr[i - 1])
        continue;

      int j = i + 1;
      int k = n - 1;

      while (j < k) {
        int sum = arr[i] + arr[j] + arr[k];
        if (sum > k)
          k--;
        else if (sum < k)
          j++;

        else {
          ans.add(Arrays.asList(arr[i], arr[j], arr[k]));
          j++;
          k--;

          // skip the duplicates
          while (j < k && arr[j] == arr[j - 1])
            j++;
          while (j < k && arr[k] == arr[k + 1])
            k--;

        }
      }

      return ans;
    }

    return ans;
  }
}