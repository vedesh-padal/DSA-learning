// Concept kind of same as Euler Circuit

import java.util.*;

class Solution {
  public boolean isCircle(String[] arr) {
    int n = arr.length;
    // Queue with Integer, indicating which string in the arr we are picking
    Queue<Integer> q = new LinkedList<>();
    boolean[] vis = new boolean[n]; // how many strings have been checked
    // to check if incoming edges are equal to outgoing edges, then only there
    // is a chance of cycle / circle to form
    int[] indegree = new int[26];
    int[] outdegree = new int[26];

    // HashMap to to map at what all positions the 
    // first character of each string is present
    Map<Character, ArrayList<Integer>> hmap = new HashMap<>();

    for (int i = 0; i < n; i++) {
      char currStrFirstChar = arr[i].charAt(0);
      if (!hmap.containsKey(currStrFirstChar))  {
        hmap.put(currStrFirstChar, new ArrayList<>());
      }
      hmap.get(currStrFirstChar).add(i);
      indegree[currStrFirstChar - 'a']++;
      char currStrLastChar = arr[i].charAt(arr[i].length() - 1);
      outdegree[currStrLastChar - 'a']++;
    }

    // check if indegree and outdegree is same
    // for all the chars in the hmap
    // if not, then cycle is not possible
    for (char ch: hmap.keySet())  {
      if (indegree[ch - 'a'] != outdegree[ch - 'a'])
        return false;
    }

    // starting from the first string in the array
    q.add(0);
    vis[0] = true;
    int ans = 1;

    while (!q.isEmpty())  {
      int ind = q.poll();
      char ch = arr[ind].charAt(arr[ind].length() - 1);
      for (int x: hmap.get(ch)) {
        if (vis[x] == false)  {
          vis[x] = true;
          q.add(x);
          ans++;
        }
      }
    }
    return ans == n ? true : false;
  }
}