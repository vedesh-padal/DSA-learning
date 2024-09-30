// Design a data structure to store the strings' count with the ability to
// return the strings with minimum and maximum counts.

// Implement the AllOne class:

// AllOne() Initializes the object of the data structure.
// inc(String key) Increments the count of the string key by 1. If key does not
// exist in the data structure, insert it with count 1.
// dec(String key) Decrements the count of the string key by 1. If the count of
// key is 0 after the decrement, remove it from the data structure. It is
// guaranteed that key exists in the data structure before the decrement.
// getMaxKey() Returns one of the keys with the maximal count. If no element
// exists, return an empty string "".
// getMinKey() Returns one of the keys with the minimum count. If no element
// exists, return an empty string "".
// Note that each function must run in O(1) average time complexity.

// Example 1:

// Input
// ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey",
// "getMinKey"]
// [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
// Output
// [null, null, null, "hello", "hello", null, "hello", "leet"]

// Explanation
// AllOne allOne = new AllOne();
// allOne.inc("hello");
// allOne.inc("hello");
// allOne.getMaxKey(); // return "hello"
// allOne.getMinKey(); // return "hello"
// allOne.inc("leet");
// allOne.getMaxKey(); // return "hello"
// allOne.getMinKey(); // return "leet"

// Constraints:

// 1 <= key.length <= 10
// key consists of lowercase English letters.
// It is guaranteed that for each call to dec, key is existing in the data
// structure.
// At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.

import java.util.*;

class AllOne {
  TreeMap<Integer, HashSet<String>> tmap;
  HashMap<String, Integer> hmap;

  public AllOne() {
    tmap = new TreeMap<>();
    hmap = new HashMap<>();
  }

  public void inc(String key) {
    if (hmap.containsKey(key) && tmap.containsKey(hmap.get(key))) {
      tmap.get(hmap.get(key)).remove(key);
      if (tmap.get(hmap.get(key)).isEmpty())
        tmap.remove(hmap.get(key));
    }

    hmap.put(key, hmap.getOrDefault(key, 0) + 1);

    if (tmap.containsKey(hmap.get(key)))
      tmap.get(hmap.get(key)).add(key);
    else
      tmap.put(hmap.get(key), new HashSet<>(Set.of(key)));

    // tmap.put(key, tmap.getOrDefault(key, 0) + 1);
  }

  public void dec(String key) {

    // if (tmap.containsKey(hmap.get(key))) {
    // if (tmap.get(hmap.get(key)).contains(key)) {
    tmap.get(hmap.get(key)).remove(key);
    if (tmap.get(hmap.get(key)).isEmpty())
      tmap.remove(hmap.get(key));
    // }
    // }

    // hmap.put(key, hmap.getOrDefault(key, 0) - 1);
    hmap.put(key, hmap.get(key) - 1);
    if (hmap.get(key) == 0)
      hmap.remove(key);
    else {
      if (tmap.containsKey(hmap.get(key)))
        tmap.get(hmap.get(key)).add(key);
      else
        tmap.put(hmap.get(key), new HashSet<>(Set.of(key)));

    }
    // tmap.put(hmap.get(key), )

    // tmap.put(key, tmap.getOrDefault(key, 0) - 1);
    // if (tmap.get(key) == 0)
    // tmap.remove(key);
  }

  public String getMaxKey() {
    if (!tmap.isEmpty()) {
      for (String s : tmap.lastEntry().getValue()) {
        return s;
      }
    }
    return "";
  }

  public String getMinKey() {
    if (!tmap.isEmpty()) {
      for (String s : tmap.firstEntry().getValue()) {
        return s;
      }
    }
    return "";
  }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */