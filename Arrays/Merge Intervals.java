import java.util.*;

class MergeIntervals  {
  public static int[][] mergeIntervals(int[][] arr) {
    int n = arr.length;

    Arrays.sort(arr, new Comparator<int[]>() {
      public int compare(int[] a, int[] b)  {
        return a[0] - b[0];
      }
    });

    List<List<Integer>> al = new ArrayList<>();

    for (int i=0; i<n; i++) {
      int start = arr[i][0];
      int end = arr[i][1];

      if (!al.isEmpty() && end <= al.get(al.size() - 1).get(1))
        continue;

      for (int j = i+1; j < n; j++) {
        if (arr[j][1] <= end) {
          end = Math.max(end, arr[j][1]);
        }
        else {
          break;
        }
      }

      al.add(Arrays.asList(start, end));

    }

    // int len = al.size();

    // int[][] ans = new int[len][2];

    // for (int i=0; i<len; i++) {
    //   ans[i][0] = al.get(i).get(0);
    //   ans[i][1] = al.get(i).get(1);
    // }

    // return ans;

    return al.toArray(new int[al.size()][2]);

  }

  // space optimized approach
  public static List<List<Integer>> mergeIntervals2(int[][] arr)  {

    List<List<Integer>> al = new ArrayList<>();
    int n = arr.length;
    for (int i=0; i<n; i++) {
      
      // if the current interval does not lie in the last interval
      if (al.isEmpty() || arr[i][0] > al.get(al.size() - 1).get(1))
        al.add(Arrays.asList(arr[i][0], arr[i][1]));


      // if the current interval lies in the last interval
      else {
        al.get(al.size() - 1).set(1, Math.max(arr[i][1], al.get(al.size() - 1).get(1)));
      }

    }

    return al;
  }
}