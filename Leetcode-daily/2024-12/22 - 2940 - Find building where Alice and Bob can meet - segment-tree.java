// HARD
// arrays, binary-search, segment-tree, priority-queue, monotonic-stack, heap, binary-indexed-tree

/*
    You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of the ith building.

    If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].

    You are also given another array queries where queries[i] = [ai, bi].
    On the ith query, Alice is in building ai while Bob is in building bi.

    Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query. 
    If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.

    

    Example 1:
    Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
    Output: [2,5,-1,5,2]
    Explanation: In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2]. 
    In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5]. 
    In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
    In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
    In the fifth query, Alice and Bob are already in the same building.  
    For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
    For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

    Example 2:
    Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
    Output: [7,6,-1,4,6]
    Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
    In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
    In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
    In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
    In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
    For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
    For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

    Constraints:
    1 <= heights.length <= 5 * 104
    1 <= heights[i] <= 109
    1 <= queries.length <= 5 * 104
    queries[i] = [ai, bi]
    0 <= ai, bi <= heights.length - 1
*/

import java.util.*;

class Solution {
    
    private void buildSegTree(int ind, int l, int r, int[] heights, int[] segTree) {
        if (l == r) {
            segTree[ind] = l;   // or `r`
            return;
        }
        int mid = l + (r - l) / 2;
        buildSegTree(2*ind + 1, l, mid, heights, segTree);
        buildSegTree(2*ind + 2, mid + 1, r, heights, segTree);

        segTree[ind] = (heights[segTree[2*ind + 1]] >= heights[segTree[2*ind + 2]]) ? 
                            segTree[2*ind + 1] : segTree[2*ind + 2];
    }

    private int[] buildST(int[] heights, int n) {
        int[] segTree = new int[4*n];
        // idx, l, r, heights, segTree
        buildSegTree(0, 0, n-1, heights, segTree);
        return segTree;
    }


    private int queryTree(int start, int end, int ind, int l, int r, int[] heights, int[] segTree) {
        // completely outside
        if (l > end || r < start) {
            return -1;
        }

        // completely inside
        if (start <= l && r <= end) {
            return segTree[ind];
        }
        int mid = l + (r - l) / 2;
        int leftIdx = queryTree(start, end, 2*ind + 1, l, mid, heights, segTree);
        int rightIdx = queryTree(start, end, 2*ind + 2, mid + 1, r, heights, segTree);

        if (leftIdx == -1)
            return rightIdx;

        if (rightIdx == -1)
            return leftIdx;
            

        return (heights[leftIdx] >= heights[rightIdx]) ? leftIdx : rightIdx;
    }


    // aIdx => Alice Index, bIdx => Bob Index
    private int RMIQ(int start, int end, int[] heights, int[] segTree) {
        return queryTree(start, end, 0, 0, heights.length-1, heights, segTree);
    }


    // VERY SLOW
    public int[] leftmostBuildingQueries_usingSegmentTree(int[] heights, int[][] queries) {
        int n = heights.length;
        int[] segTree = buildST(heights, n);

        int[] res = new int[queries.length];
        int i = 0;

        for (int[] query: queries) {
            int alice = Math.min(query[0], query[1]);   // min. index
            int bob = Math.max(query[0], query[1]);     // max. index

            if (alice == bob) {
                res[i++] = alice;
                continue;
            }
            else if (heights[bob] > heights[alice]) {
                res[i++] = bob;
                continue;
            }

            int l = bob + 1;
            int r = n - 1;
            // we need to find the left most on the right of bob
            // such that the height of the resulting that we are choosing is greater than
            // alice and bob's initial building height

            int resIdx = Integer.MAX_VALUE;

            while (l <= r) {
                int mid = l + (r - l) / 2;
                // Range Max Index Query, since we need the left most index
                // we apply RMIQ in the left half
                int idx = RMIQ(l, mid, heights, segTree);

                if (heights[idx] > Math.max(heights[bob], heights[alice])) {
                    resIdx = idx;   // maybe we should write min. here
                    r = mid - 1;
                }
                else {
                    l = mid + 1;
                }
            }

            res[i++] = (resIdx == Integer.MAX_VALUE) ? -1 : resIdx;
        }

        return res;
    }



    // ============================================
    // EASY APPROACH - PriorityQueue
    // TC: O(n + q*log(q))
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        List<List<List<Integer>>> storeQ = new ArrayList<>(heights.length);
        
        for (int i = 0; i < heights.length; i++) {
            storeQ.add(new ArrayList<>());
        }

        // keeps track of the left most index for each right most height index of the query
        PriorityQueue<List<Integer>> maxIdx = new PriorityQueue<>((a, b) -> a.get(0) - b.get(0));

        int[] res = new int[queries.length];
        Arrays.fill(res, -1);

        // store mappings for all queries in storeQueries
        // TC: q*log(q)
        for (int q = 0; q < queries.length; q++) {
            int a = queries[q][0], b = queries[q][1];

            if (a < b && heights[a] < heights[b]) {
                res[q] = b;
            }
            else if (a > b && heights[a] > heights[b]) {
                res[q] = a;
            }
            else if (a == b) {
                res[q] = a; // or b
            }
            else {
                // right most person
                storeQ.get(Math.max(a, b)).add(
                    Arrays.asList(Math.max(heights[a], heights[b]), q)
                );
            }

        }

        // if the PQ's min. pair value is less than the curr. index of height, it is an answer to the query
        // NOTE THAT:
        // for below TC is not n*qlog(q), becoz, for each index, the algo.
        // checks and pushes relevant queries from storeQ
        // hence, O(n + q*log(q))
        for (int i = 0; i < heights.length; i++) {
            while (!maxIdx.isEmpty() && maxIdx.peek().get(0) < heights[i]) {
                res[maxIdx.peek().get(1)] = i;
                maxIdx.poll();
            }

            // push the element with their max. index as the curr. index of the priority queue
            for (List<Integer> element: storeQ.get(i)) {
                maxIdx.offer(element);
            }
        }

        return res;
    }






    // =========================================
    // MONOTONIC STACK AND BINARY SEARCH APPROACH

    class Pair {
        int height;
        int qPos;
        Pair(int height, int qPos) {
            this.height = height;
            this.qPos = qPos;
        }
    }

    // TC: (q*logn + n)
    public int[] leftmostBuildingQueries_monoStack(int[] heights, int[][] queries) {
        // monotonic stack, taking as list, becoz need to iterate over the elements in the stack later
        List<Pair> stk = new ArrayList<>();
        int n = heights.length, m = queries.length;
        int[] res = new int[m];
        Arrays.fill(res, -1);

        // grouping the queries based on the rightmost person (bob)
        List<List<Pair>> newQueries = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            newQueries.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int alice = queries[i][0];
            int bob = queries[i][1];
            if (alice > bob) {
                int t = alice;
                alice = bob;
                bob = t;
            }

            if (alice == bob || heights[bob] > heights[alice]) {
                res[i] = bob;
            }
            else {
                newQueries.get(bob).add(new Pair(heights[alice], i));
            }
        }

        // coming from back
        for (int i = n-1; i >= 0; i--) {
            int stkSize = stk.size();
            for (Pair pair: newQueries.get(i)) {
                int pos = search(pair.height, stk);
                if (pos < stkSize && pos >= 0) {
                    res[pair.qPos] = stk.get(pos).qPos;
                }
            }

            while (!stk.isEmpty() && stk.get(stk.size() - 1).height <= heights[i]) {
                stk.remove(stk.size() - 1);
            }
            stk.add(new Pair(heights[i], i));
        }

        return res;
    }

    private int search(int height, List<Pair> stk) {
        int l = 0, r = stk.size() - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (stk.get(mid).height > height) {
                ans = Math.max(ans, mid);
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return ans;
    }


    // ========================

    public int[] leftmostBuildingQueries_BRUTE(int[] heights, int[][] queries) {
        // BRUTE FORCE
        int n = heights.length, m = queries.length;
        int[] res = new int[m];
        Arrays.fill(res, -1);

        for (int i = 0; i < m; i++) {
            // Arrays.sort(query);
            // int alice = query[0];   // or can be bob, becoz we sorted, doesnt matter
            // int bob = query[1];

            // int alice = Math.min(queries[i][0], queries[i][1]); // left-most
            // int bob = Math.max(queries[i][0], queries[i][1]);   // right-most

            int alice = queries[i][0];
            int bob = queries[i][1];
            if (alice > bob) {
                int t = alice;
                alice = bob;
                bob = t;
            }

            if (alice == bob || heights[bob] > heights[alice]) {
                res[i] = bob;
                continue;
            }
            int maxi = Math.max(heights[alice], heights[bob]);
            for (int j = bob + 1; j < n; j++) {
                if (heights[j] > maxi) {
                    res[i] = j;
                    break;
                }
            }
        }

        return res;
    }
}