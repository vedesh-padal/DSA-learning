// MEDIUM
// arrays, prefix-sum, segment-trees, mathematical

/*
    You are given an array arr[] of n integers and q queries in an array
    queries[] of length 2*q containing l, r pair for all q queries. You need to
    compute the following sum over q queries.

    \sum_{i=l}^{r} \text{arr}[i-1]   => SUM OF EACH RANGE

    Note : Array is 1-Indexed.

    Examples :

    Input: n = 4, arr = {1, 2, 3, 4}, q = 2, queries = {1, 4, 2, 3}
    Output: 10 5
    Explaination: In the first query we need sum from 1 to 4 which is 1+2+3+4 = 10.
    In the second query we need sum from 2 to 3 which is 2 + 3 = 5.

    Input: n = 5, arr = {26, 30, 48, 29, 8}, q = 2, queries = {4, 4, 2, 3}
    Output: 29 78
    Explaination: In the first query we need sum from 4 to 4 which is 29. In the
    second query we need sum from 2 to 3 which is 30 + 48 = 78.

    Your Task:
    You don't need to read input or print anything. Your task is to complete the
    function querySum() which takes n, arr, q and queries as input parameters and
    returns the answer for all the queries.

    Expected Time Complexity: O(n+q)
    Expected Auxiliary Space: O(n)

    Constraints:
    1 ≤ n, q ≤ 105
    1 ≤ arri ≤ 103
    1 ≤ l ≤ r ≤ n
*/
import java.util.*;

class Solution{
    List<Integer> querySum(int n, int arr[], int q, int queries[])  {
        
        // SEGMENT TREE based problem
        // for range queries - efficient way of qeuryinh
        
        // first build the tree
        // here, tree is Binary tree - can be represented in a array
        // size of the segment tree (no. of nodes):
            // when size of the array is n is power of 2:
                //  = ~2n (to be precise: n (left nodes) + (n-1) (non-leaf nodes))
            // when size of the array is n, and is power of 2:
                // we should take for the safe side: 4*n 
                    // proof and why?
                    // initially, is safe that we assume that we need 2n leaf nodes
                    // to build the tree when n is not power of 2
                    // when we calculate the no. of nodes that we need for infinite GP:
                    // S(infinity) = a / (1 - r)
                        // = (2n) / (1 - 1/2) = 4*n
                        
        
        // BUILDING THE SEGMENT TREE
        int[] segTree = new int[4*n];
        
        // index (of the node represented in segTree array), l, r, arr, segTree
        buildTree(0, 0, n-1, arr, segTree);
        
        // now we do querying
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < 2*q; i+=2) {
            int start = queries[i] - 1;
            int end = queries[i+1] - 1;
            
            int rangeSum = queryTree(start, end, 0, 0, n-1, segTree);
            res.add(rangeSum);
        }
        
        return res;
    }
    
    
    // TC: O(2*(no. of nodes)) - becoz we visit each node two times,
    // becoz we backtrack to put the values in upper nodes
    private void buildTree(int ind, int l, int r, int[] arr, int[] segTree) {
        
        // we are at the leaf nodes in the segment tree, base case
        // this is the first thing that we put into the tree and 
        // this will help in building the segment tree to upper levels
        if (l == r) {
            segTree[ind] = arr[l];    // or arr[r]
            return;
        }
        
        int mid = l + (r - l) / 2;
        
        // left subtree
        buildTree(2*ind + 1, l, mid, arr, segTree);
        buildTree(2*ind + 2, mid + 1, r, arr, segTree);
        
        segTree[ind] = segTree[2*ind + 1] + segTree[2*ind + 2];

    }
    
    private int queryTree(int start, int end, int ind, int l, int r, int[] segTree) {
        // there will be 3 cases:
        
        // 1. [l,r] range is completely out of bound of [start, end]
        // then, we consider nothing, and return 0
        if (l > end || r < start) {
            return 0;
        }
        
        // 2. if [l,r] range is completely within [start, end]
        // then we totally consume it
        if (start <= l && r <= end) {
            return segTree[ind];
        }
        
        int mid = l + (r - l) / 2;
        // else, find the matching one from left and right sub trees
        // and since we need sum, we add them
        return (
                queryTree(start, end, 2*ind + 1, l, mid, segTree) +
                queryTree(start, end, 2*ind + 2, mid + 1, r, segTree)
            );
    }
}