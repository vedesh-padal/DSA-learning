// MEDIUM
// array, hash-table, binary-search, design, segment-tree

/*
    Design a data structure to find the frequency of a given value in a given subarray.

    The frequency of a value in a subarray is the number of occurrences of that value in the subarray.

    Implement the RangeFreqQuery class:

    - RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
    - int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].

    A subarray is a contiguous sequence of elements within an array.
    arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).

    Example 1:
    Input
    ["RangeFreqQuery", "query", "query"]
    [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]

    Output
    [null, 1, 2]

    Explanation
    RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
    rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
    rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.

    Constraints:
    1 <= arr.length <= 105
    1 <= arr[i], value <= 104
    0 <= left <= right < arr.length
    At most 105 calls will be made to query
*/

import java.util.Map;
import java.util.HashMap;

class RangeFreqQuery {
    private int[] arr;
    private Map<Integer, Integer>[] segTree;

    public RangeFreqQuery(int[] arr) {
        this.arr = arr;
        segTree = new HashMap[4*arr.length];

        buildSegTree(0, 0, arr.length - 1, arr, segTree);
    }

    private void buildSegTree(int ind, int l, int r, int[] arr, Map<Integer, Integer>[] segTree) {
        if (l == r) {
            Map<Integer, Integer> newMap = new HashMap<>();
            newMap.put(arr[l], 1);
            segTree[ind] = newMap;
            return;
        }

        int mid = l + (r - l) / 2;
        buildSegTree(2*ind + 1, l, mid, arr, segTree);
        buildSegTree(2*ind + 2, mid + 1, r, arr, segTree);

        // update the upper node (parent) with the sum of frequencies of the below children
        Map<Integer, Integer> newMap = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry: segTree[2*ind + 1].entrySet())  {
            newMap.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<Integer, Integer> entry: segTree[2*ind + 2].entrySet()) {
            newMap.put(entry.getKey(), newMap.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }

        segTree[ind] = newMap;
    }
    
    public int query(int left, int right, int value) {
        return query(0, 0, arr.length - 1, left, right, value);
    }

    private int query(int ind, int l, int r, int start, int end, int value) {
        // if this range is completely out of bounds
        if (l > end || r < start) {
            return 0;
        }

        // if completely within the range
        if (start <= l && r <= end) {
            return segTree[ind].getOrDefault(value, 0);
        }

        // overlapping
        int mid = l + (r - l) / 2;
        return (
            query(2*ind + 1, l, mid, start, end, value) +
            query(2*ind + 2, mid + 1, r, start, end, value)
        );
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */