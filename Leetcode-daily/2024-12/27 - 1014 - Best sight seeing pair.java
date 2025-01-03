// MEDIUM
// arrays

/*
    You are given an integer array values where values[i] represents the value of
    the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.

    The score of a pair (i < j) of sightseeing spots is
    values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.

    Return the maximum score of a pair of sightseeing spots.

    Example 1:
    Input: values = [8,1,5,2,6]
    Output: 11
    Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11

    Example 2:
    Input: values = [1,2]
    Output: 2

    Constraints:
    2 <= values.length <= 5 * 104
    1 <= values[i] <= 1000
*/

// kind of possibilities checking problem, but cleverly

class Solution {

    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        // since we have no control of what will come next, 
        // so, we will take curr. index as j, and prev. ind (one of the possible) as i
        // given equation: values[i] + values[j] - (j - i)      where i < j
        
        // [ TIP: whenever there is a relation given b/w index and value] ( try to rearrange)
        // can be rearranged as: 
        // (values[i] + i) + (values[j] - j)
        // we maintain max. of (values[i] + i) seen so far when travelling from left to right
        // and iterate over j of values (from 0 to n-1), and try to maximize the sum


        // INSTEAD OF STORING IN ARRAY LIKE THIS:
        // int[] valI = new int[n];    // max. so far of (values[i] + i)
        // valI[0] = values[0] + 0;
        // for (int i = 1; i < n; i++) {
        //     valI[i] = Math.max(valI[i-1], values[i] + i);
        // }

        // // System.out.println(Arrays.toString(valI));

        // int res = Integer.MIN_VALUE;
        // for (int i = 1; i < n; i++) {
        //     res = Math.max(res, valI[i-1] + values[i] - i);
        // }
        // return res;


        int maxiLeft = values[0] + 0;   // max. seen so far of value (values[i] + i)
        int res = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            res = Math.max(res, maxiLeft + values[i] - i);
            maxiLeft = Math.max(maxiLeft, values[i] + i);
        }
        return res;
    }

    // ================================================================================
    // greedy - something wrong??? - initial try
    // we can't know what is coming after, we have no control about it
    // and just greedily picking doesn't give correct result
    public int maxScoreSightseeingPair_greedy(int[] values) {
        // return the max. score of a pair of sightseeing spots
        int n = values.length;
        // first, finding the max. value
        int maxiVal = Integer.MIN_VALUE;
        int maxiInd = -1;

        for (int i = 0; i < n; i++) {
            if (values[i] > maxiVal) {
                maxiVal = values[i];
                maxiInd = i;
            }
        }

        int res = Integer.MIN_VALUE;    // stores the max. score of sight-seeing spots
        // try to maximize this equation:
        // values[i] + values[j] - (j - i)
        // where: i < j     [ will apply Math.min() and max on indices ]
        for (int i = 0; i < n; i++) {
            if (maxiInd == i)
                continue;
            
            int rInd = Math.max(maxiInd, i);
            int lInd = Math.min(maxiInd, i);
            int val = values[i] + values[maxiInd] - (rInd - lInd);  // sum of values - distance b/w them
            // System.out.println(lInd + " " + rInd + " " + val);
            res = Math.max(val, res);
        }
        return res;
    }
}