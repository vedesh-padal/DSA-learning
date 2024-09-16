/*
  Given a list of 24-hour clock time points in "HH:MM" format, return the 
  minimum minutes difference between any two time-points in the list.

  Example 1:
  Input: timePoints = ["23:59","00:00"]
  Output: 1

  Example 2:
  Input: timePoints = ["00:00","23:59","00:00"]
  Output: 0

  Constraints:
  2 <= timePoints.length <= 2 * 104
  timePoints[i] is in the format "HH:MM".
*/

import java.util.Arrays;
import java.util.List;

class Solution {
  public int findMinDifference(List<String> timePoints) {
    int n = timePoints.size();
    int[] timeInMins = new int[n];

    for (int i = 0; i < n; i++) {
      String[] time = timePoints.get(i).split(":");
      timeInMins[i] = 60 * Integer.parseInt(time[0]) + Integer.parseInt(time[1]);
    }

    Arrays.sort(timeInMins);
    int minDiff = Integer.MAX_VALUE;
    for (int i = 1; i < n; i++) {
      minDiff = Math.min(minDiff, timeInMins[i] - timeInMins[i-1]);
    }
    
    // checking the max. time and min. time diff
    // becoz, 23:59 ~ 1 min less to 00:00
    minDiff = Math.min(minDiff, 24*60 - (timeInMins[n-1] - timeInMins[0]));
    return minDiff;
  }
}