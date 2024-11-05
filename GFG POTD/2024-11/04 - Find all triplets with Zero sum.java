import java.util.*;

class Solution {
  public List<List<Integer>> findTriplets_BRUTE(int[] arr) {
    List<List<Integer>> res = new ArrayList<>();
    int n = arr.length;

    for (int i = 0; i < n-2; i++) {
      for (int j = i+1; j < n-1; j++) {
        for (int k = j+1; k < n; k++) {
          if (arr[i] + arr[j] + arr[k] == 0) {
            res.add(new ArrayList<>(List.of(i, j, k)));
          }
        }
      }
    }
    return res;
  }

  public List<List<Integer>> findTriplets_HASHMAP(int[] arr) {
    
    int n = arr.length;
    Set<ArrayList<Integer>> hset = new HashSet<>();

    // two pair sum, list of pair indices with that pair sum
    Map<Integer, List<int []>> hmap = new HashMap<>();

    // first store those pair sum and indices in the hashmap
    for (int i = 0; i < n; i++) {
      for (int j = i+1; j < n; j++) {
        hmap.computeIfAbsent(arr[i] + arr[j], k -> new ArrayList<>()).add(new int[]{i, j});
      }
    }

    // now we traverse the array to find the triplet
    for (int i = 0; i < n; i++) {
      // remaining value to find the triplet whose sum will be 0
      int rem = -arr[i];

      if (hmap.containsKey(rem)) {
        List<int[]> pairs = hmap.get(rem);

        for (int[] p: pairs) {
          // ensure there are no two indices are same in triplet (becoz, given in Q)
          if (p[0] != i && p[1] != i) {
            ArrayList<Integer> triplet = new ArrayList<>(Arrays.asList(i, p[0], p[1]));

            Collections.sort(triplet);
            hset.add(triplet);
          }
        }
      }
    }
    return new ArrayList<>(hset);
  }


  public List<List<Integer>> findTriplets_SORTING(int[] arr) {
    List<List<Integer>> res = new ArrayList<>();
    int n = arr.length;

    List<Pair> al = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      al.add(new Pair(arr[i], i));
    }

    Collections.sort(al, (a, b) -> a.val - b.val);

    for (int i = 0; i < n; i++) {
      if (i > 0 && al.get(i).val == al.get(i-1).val)
        continue;

      int l = i + 1;
      int r = arr.length - 1;

      while (l < r) {
        int sum = al.get(i).val + al.get(l).val + al.get(r).val;

        if (sum == 0) {
          List<Integer> triplet = new ArrayList<>();
          triplet.add(al.get(i).ind);
          triplet.add(al.get(l).ind);
          triplet.add(al.get(r).ind);

          Collections.sort(triplet);
          res.add(triplet);

          l++;
          r--;

          // skip duplicates
          while (l < r && al.get(l).val == al.get(l-1).val)
            l++;
          
          while (l < r && al.get(r).val == al.get(r+1).val)
            r--;

        }

        else if (sum < 0)
          l++;
        
        else 
          r--;
      }
    }

    return res;
  }
  
}

class Pair {
  int val, ind;
  Pair(int val, int ind) {
    this.val = val;
    this.ind = ind;
  }
}