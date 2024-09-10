/*
  Given a list of accounts where each element accounts[i] is a list of strings, where the first 
  element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

  Now, we would like to merge these accounts. Two accounts definitely belong to the same person 
  if there is some common email to both accounts. Note that even if two accounts have the same name, 
  they may belong to different people as people could have the same name. A person can have any number 
  of accounts initially, but all of their accounts definitely have the same name.

  After merging the accounts, return the accounts in the following format: the first element of 
  each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

  

  Example 1:

  Input: 
  accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],
  ["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
  Output: 
  [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
  Explanation:
  The first and second John's are the same person as they have the common email "johnsmith@mail.com".
  The third John and Mary are different people as none of their email addresses are used by other accounts.
  We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
  ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
  
  Example 2:

  Input: 
  accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
  ["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],
  ["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
  Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],
  ["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],
  ["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
  

  Constraints:
  1 <= accounts.length <= 1000
  2 <= accounts[i].length <= 10
  1 <= accounts[i][j].length <= 30
  accounts[i][0] consists of English letters.
  accounts[i][j] (for j > 0) is a valid email.
*/

import java.util.*;

class DisjointSet {
  List<Integer> rank = new ArrayList<>();
  List<Integer> parent = new ArrayList<>();
  List<Integer> size = new ArrayList<>();

  public DisjointSet(int n) {
    for (int i = 0; i <= n; i++) {
      rank.add(0);
      parent.add(i);
      size.add(1);
    }
  }

  public int findUltPar(int node) {
    if (node == parent.get(node))
      return node;

    int ulp = findUltPar(parent.get(node));
    parent.set(node, ulp);
    return parent.get(node);
  }

  public void findUnionByRank(int u, int v) {
    int ulp_u = findUltPar(u);
    int ulp_v = findUltPar(v);

    if (ulp_u == ulp_v)
      return;

    if (rank.get(ulp_u) == rank.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
    } else if (rank.get(ulp_v) < rank.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
    } else {
      parent.set(ulp_v, ulp_u);
      int rankU = rank.get(ulp_u);
      rank.set(ulp_u, rankU + 1);
    }
  }

  public void findUnionBySize(int u, int v) {
    int ulp_u = findUltPar(u);
    int ulp_v = findUltPar(v);

    if (ulp_u == ulp_v)
      return;

    if (size.get(ulp_u) < size.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
      size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
    }
    // for other cases, equiality also same result
    else {
      parent.set(ulp_v, ulp_u);
      size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
    }
  }

}

class Solution {
  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    int n = accounts.size();
    Map<String, Integer> mapMailNode = new HashMap<>();
    DisjointSet ds = new DisjointSet(n);

    // making the components using DisjointSet data structure
    for (int i = 0; i < n; i++) {
      for (int j = 1; j < accounts.get(i).size(); j++) {
        String currMail = accounts.get(i).get(j);
        if (!mapMailNode.containsKey(currMail))
          mapMailNode.put(currMail, i);
        else
          ds.findUnionBySize(i, mapMailNode.get(currMail));
      }
    }

    List<String>[] mergedMails = new ArrayList[n];
    for (int i = 0; i < n; i++)
      mergedMails[i] = new ArrayList<String>();

    for (Map.Entry<String, Integer> it : mapMailNode.entrySet()) {
      String mail = it.getKey();
      int ultPar = ds.findUltPar(it.getValue());
      mergedMails[ultPar].add(mail);
    }

    List<List<String>> ans = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      if (mergedMails[i].size() == 0)
        continue;
      Collections.sort(mergedMails[i]);
      List<String> temp = new ArrayList<>();
      temp.add(accounts.get(i).get(0));

      for (String s : mergedMails[i])
        temp.add(s);

      ans.add(temp);
    }
    return ans;
  }
}