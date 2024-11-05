// MEDIUM
// strings

// Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6"
// if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.

// A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255
// and xi cannot contain leading zeros. For example, "192.168.1.1" and
// "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00",
// and "192.168@1.1" are invalid IPv4 addresses.

// A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:

// 1 <= xi.length <= 4
// xi is a hexadecimal string which may contain digits, lowercase English letter
// ('a' to 'f') and upper-case English letters ('A' to 'F').
// Leading zeros are allowed in xi.
// For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and
// "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while
// "2001:0db8:85a3::8A2E:037j:7334" and
// "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.

// Example 1:
// Input: queryIP = "172.16.254.1"
// Output: "IPv4"
// Explanation: This is a valid IPv4 address, return "IPv4".

// Example 2:
// Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
// Output: "IPv6"
// Explanation: This is a valid IPv6 address, return "IPv6".

// Example 3:
// Input: queryIP = "256.256.256.256"
// Output: "Neither"
// Explanation: This is neither a IPv4 address nor a IPv6 address.

// Constraints:
// queryIP consists only of English letters, digits and the characters '.' and ':'.

class Solution {
  public String validIPAddress(String queryIP) {
    // shortest possible: ex: 2.3.8.0
    if (queryIP.length() < 7)
      return "Neither";

    if (queryIP.contains(":"))
      return IPv6(queryIP);
    else
      return IPv4(queryIP);

  }

  private String IPv4(String s) {
    String no = "Neither";
    String yes = "IPv4";

    if (s.charAt(0) == '.' || s.charAt(s.length() - 1) == '.')
      return no;

    String[] nums = s.split("\\.");

    if (nums.length != 4)
      return no;

    for (String num : nums) {
      // 3rd condition is to check for leading 0s
      if (num.length() < 1 || num.length() > 3 || (num.charAt(0) == '0' && num.length() > 1))
        return no;

      // now, check if the num has all INTs
      for (char ch : num.toCharArray()) {
        if (ch - '0' < 0 || ch - '9' > 0)
          return no;
      }

      // now, convert it to integer number
      int n = Integer.parseInt(num);
      if (n < 0 || n > 255)
        return no;
    }

    return yes;
  }

  private String IPv6(String s) {
    String no = "Neither";
    String yes = "IPv6";

    // since the string can contain . too, and we need to avoid them
    if (s.charAt(0) == ':' || s.charAt(s.length() - 1) == ':' || s.charAt(0) == '.'
        || s.charAt(s.length() - 1) == '.') {
      return no;
    }

    String[] nums = s.split(":");

    if (nums.length != 8)
      return no;

    for (String num : nums) {
      for (int i = 0; i < num.length(); i++) {
        char ch = num.charAt(i);
        // if not alphabets from a-f or A-F
        // first extreme ranges: A, f, then in b/w F and a
        // and there is a chance that ch can be . too
        if (ch == '.' || ch - 'f' > 0 || ch - 'A' < 0 || (ch - 'F' > 0 && ch - 'a' < 0)) {
          // or, if not numbers too
          if (ch - '0' < 0 || ch - '9' > 0)
            return no;
        }
      }
    }

    return yes;
  }

  public String validIPAddress_usingREGEX(String queryIP) {
    if (queryIP.matches(
        "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])")) {
      return "IPv4";
    } 
    else if (queryIP.matches("((([0-9a-fA-F]){1,4})\\:){7}(([0-9a-fA-F]){1,4})")) {
      return "IPv6";
    } 
    else
      return "Neither";
  }
}