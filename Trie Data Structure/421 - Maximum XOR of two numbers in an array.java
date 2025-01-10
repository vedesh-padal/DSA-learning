// MEDIUM
// arrays, bit-manipulation, trie
/*
    Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.

    Example 1:
    Input: nums = [3,10,5,25,2,8]
    Output: 28
    Explanation: The maximum result is 5 XOR 25 = 28.

    Example 2:
    Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
    Output: 127

    Constraints:
    1 <= nums.length <= 2 * 10^5
    0 <= nums[i] <= 23^1 - 1
*/

class Solution {

    class TrieNode {
        TrieNode left;
        TrieNode right;
        TrieNode() {
            this.left = null;
            this.right = null;
        }
    }

    private void insertInTrie(int num, TrieNode root) {
        // here, node is the crawler node in our Trie
        TrieNode node = root;
        // start inserting from most significant bit, becoz we need maxi. XOR
        for (int i = 31; i >= 0; i--) {
            int bit = ((num >> i) & 1); // ith bit
            if (bit == 0) { // put it in left node
                if (node.left == null) {
                    node.left = new TrieNode();
                }
                node = node.left;
            }
            else {
                if (node.right == null) {
                    node.right = new TrieNode();
                }
                node = node.right;
            }
        }
    }

    private int findMaxXOR(int num, TrieNode root) {
        int maxXor = 0;
        TrieNode node = root;
        
        for (int i = 31; i >= 0; i--) {
            int bit = ((num >> i) & 1);
            if (bit == 0) { // we search for bit = 1 in the Trie, so that we get max. xor
                if (node.right != null) {
                    maxXor |= (1 << i);
                    node = node.right;
                }
                else    // if not present we take what is there
                    node = node.left;
            }
            else {
                if (node.left != null) {
                    maxXor |= (1 << i);
                    node = node.left;
                }
                else
                    node = node.right;
            }
        }
        return maxXor;
    }

    // SOLVED USING TRIE, especially the concept of "Bit Trie"
    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        // first we will insert all the nums into the trie
        for (int num: nums) {
            insertInTrie(num, root);
        }

        int maxi = 0;
        // now find maxi. XOR with each number including itself, as mentioned in the question
        for (int num: nums) {
            int temp = findMaxXOR(num, root);
            maxi = Math.max(temp, maxi);
        }
        return maxi;
    }

    // ===============================================================================
    // BRUTE FORCE: -- obviously will not work
    public int findMaximumXOR_BRUTE(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                maxi = Math.max(maxi, nums[i] ^ nums[j]);
            }
        }
        return maxi;
    }
}