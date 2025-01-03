// MEDIUM
// string, hashset, permuations, backtracking

/*
    Given an array of strings nums containing n unique binary strings each of
    length n, return a binary string of length n that does not appear in nums. If
    there are multiple answers, you may return any of them.

    Example 1:

    Input: nums = ["01","10"]
    Output: "11"
    Explanation: "11" does not appear in nums. "00" would also be correct.
    Example 2:

    Input: nums = ["00","01"]
    Output: "11"
    Explanation: "11" does not appear in nums. "10" would also be correct.
    Example 3:

    Input: nums = ["111","011","001"]
    Output: "101"
    Explanation: "101" does not appear in nums. "000", "010", "100", and "110"
    would also be correct.

    Constraints:

    n == nums.length
    1 <= n <= 16
    nums[i].length == n
    nums[i] is either '0' or '1'.
    All the strings of nums are unique.
*/
import java.util.*;

class Solution {
    
    // Cantor's Diagonal solution, works here - superb
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;

        // flipping each digit of each number
        // will make sure that the resulting binary string that we
        // get is the one that is not present in nums[]

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = nums[i].charAt(i);
            if (ch == '1') 
                sb.append('0');
            else
                sb.append('1');
        }
        return sb.toString();
    }


    // =========================================================
    private String find(String curr, Set<String> hset) {
        if (curr.length() == hset.size()) {
            if (!hset.contains(curr)) {
                return curr;
            }
            return "";
        }

        String addZero = find("0" + curr, hset);
        // if adding the above 0 as prefix to the curr. string and 
        // repeating it till end till curr.length() == n, if that is not present in hset
        // then we have the string that is not present
        if (addZero.length() > 0) {
            return addZero;
        }

        // we get an empty string, that means from here permuation 
        // generated isn't forming a valid not present string
        // so, we add "1"
        return find("1" + curr, hset);
    }

    // PERMUTATIONS WAY - TC:O(N^2) - not O(2^n), becoz in above recursion call
    // if we get a non-empty string length, we are returning without making any further calls
    // this will make this algo's worst TC as : O(n^2)
    public String findDifferentBinaryString_permuations(String[] nums) {
        // generate all possible binary strings that are not present in nums
        Set<String> hset = new HashSet<>();
        for (String s: nums)
            hset.add(s);

        return find("", hset);
    }
    
    // =========================================================
    public String findDifferentBinaryString_usingIntConversion(String[] nums) {
        int n = nums.length;

        Set<Integer> hset = new HashSet<>();

        for (String s: nums) {
            int num = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '1') {
                    num += (1 << (s.length() - i - 1));
                }
            }

            // or just find the base-10 number by:
            // Integer.parseInt(s, 2);
            hset.add(num);
        }


        // obviously it will be found
        // worst case: O(n^2)
        for (int i = 0; i <= n; i++) {
            if (!hset.contains(i)) {
                // String str = Integer.toBinaryString(i);
                String str = getNumAsString(i);
                while (str.length() < n) {
                    str = "0" + str;
                }
                return str;
            }
        }

        return "";
    }

    private String getNumAsString(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            if (num % 2 == 1) 
                sb.append('1');
            else
                sb.append('0');
            
            num /= 2;
        }
        return sb.reverse().toString();
    }
}