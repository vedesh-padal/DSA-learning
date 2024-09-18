import java.util.Arrays;

class Solution {
  // original way -> runs fast
  public String largestNumber1(int[] nums) {

    int n = nums.length;
    String[] strNums = new String[n];
    for (int i = 0; i < n; i++)
      strNums[i] = String.valueOf(nums[i]);

    Arrays.sort(strNums, (a, b) -> (b + a).compareTo(a + b)); // descending order => 9, 8, 7 ...

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < n; i++)
      sb.append(strNums[i]);

    String res = sb.toString();
    return res.startsWith("0") ? "0" : res;
  }  

  
  // --------------------------------------------
  // learning to write this type of syntax in java
  // for faster code completion
  // but might take a bit longer time compared to the traditional methods
  public String largestNumber2(int[] nums)  {
    String[] strNums = Arrays.stream(nums)
        .mapToObj(String::valueOf)
        .sorted((a, b) -> (b + a).compareTo(a + b))
        .toArray(String[]::new);

    int n = nums.length;
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < n; i++)
      sb.append(strNums[i]);

    // return sb.toString();
    String result = sb.toString();
    // to handle the case when all numbers in the array are 0
    // then we just return 0 instead of 00000
    return result.startsWith("0") ? "0" : result;

  }
}