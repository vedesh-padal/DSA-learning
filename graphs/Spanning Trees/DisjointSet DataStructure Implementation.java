import java.util.ArrayList;
import java.util.List;

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

  public int findUltPair(int node)  {
    if (node == parent.get(node))
      return node;
    
      int ulp = findUltPair(parent.get(node));
      parent.set(node, ulp);
      return parent.get(node);
  }


  public void findUnionByRank(int u, int v) {
    int ulp_u = findUltPair(u);
    int ulp_v = findUltPair(v);

    if (ulp_u == ulp_v)
      return;

    if (rank.get(ulp_u) == rank.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
    }
    else if (rank.get(ulp_v) < rank.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
    }
    else  {
      parent.set(ulp_v, ulp_u);
      int rankU = rank.get(ulp_u);
      rank.set(ulp_u, rankU + 1);
    }
  }

  public void findUnionBySize(int u, int v) {
    int ulp_u = findUltPair(u);
    int ulp_v = findUltPair(v);

    if (ulp_u == ulp_v)
      return;

    if (size.get(ulp_u) == size.get(ulp_v)) {
      parent.set(ulp_u, ulp_v);
      size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
    }
    // for other cases, equiality alaso same reult
    else {
      parent.set(ulp_v, ulp_u);
      size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
    }
  }

}


class Main {
  public static void main(String[] args) {
    DisjointSet ds = new DisjointSet(7);

    ds.findUnionByRank(1, 2);
    ds.findUnionByRank(2, 3);
    ds.findUnionByRank(4, 5);
    ds.findUnionByRank(6, 7);
    ds.findUnionByRank(5, 7);
    
    // checking if u, v are of single component - before adding
    if (ds.findUltPair(3) == ds.findUltPair(7))
      System.out.println("same");
    else  
      System.out.println("not same");


    // checking if u, v are of single component - after adding
    ds.findUnionByRank(3, 7);
    if (ds.findUltPair(3) == ds.findUltPair(7))
      System.out.println("same");
    else  
      System.out.println("not same");

  }
}