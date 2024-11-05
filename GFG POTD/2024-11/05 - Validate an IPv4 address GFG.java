// MEDIUM
// strings

// You are given a string s in the form of an IPv4 Address. Your task is to
// validate an IPv4 Address, if it is valid return true otherwise return false.

// IPv4 addresses are canonically represented in dot-decimal notation, which
// consists of four decimal numbers, each ranging from 0 to 255, separated by
// dots, e.g., "172.16.254.1"

// A valid IPv4 Address is of the form x1.x2.x3.x4 where 0 <= (x1, x2, x3, x4)
// <= 255. Thus, we can write the generalized form of an IPv4 address as
// (0-255).(0-255).(0-255).(0-255)

// Note: Here we are considering numbers only from 0 to 255 and any additional
// leading zeroes will be considered invalid.

// Examples :
// Input: s = "222.111.111.111"
// Output: true
// Explanation: Here, the IPv4 address is as per the criteria mentioned and also
// all four decimal numbers lies in the mentioned range.

// Input: s = "5555..555"
// Output: false
// Explanation: "5555..555" is not a valid. IPv4 address, as the middle two
// portions are missing.

// Input: s = "0.0.0.255"
// Output: false

// Constraints:
// 1<= |s| <=15

class Solution {

  public boolean isValid(String s) {
    // Write your code here
    String[] nums = s.split("\\.+"); // one or more .

    // System.out.println(Arrays.toString(nums));

    if (nums.length != 4)
      return false;

    for (String str : nums) {
      if (str.length() > 3)
        return false;

      int num = 0;

      for (char ch : str.toCharArray()) {

        num = num * 10 + ch - '0';

        if (num > 255)
          return false;
      }

      if (num >= 0 && num <= 9) {
        if (str.length() != 1)
          return false;
      } else if (num >= 10 && num <= 99) {
        if (str.length() != 2)
          return false;
      } else if (num >= 100 && num <= 255) {
        if (str.length() != 3)
          return false;
      } else
        return false;
    }

    return true;
  }
}