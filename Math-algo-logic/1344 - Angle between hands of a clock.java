// Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

// Answers within 10-5 of the actual value will be accepted as correct.

// Example 1:
// Input: hour = 12, minutes = 30
// Output: 165

// Example 2:
// Input: hour = 3, minutes = 30
// Output: 75

// Example 3:
// Input: hour = 3, minutes = 15
// Output: 7.5
 
// Constraints:
// 1 <= hour <= 12
// 0 <= minutes <= 59

class Solution {
    public double angleClock(int hour, int minutes) {
        // for hour degrees:
        // amount to subtract from the minutes angle:

        // for each minute, the hour hand movees by 1/2 degree
        // minutes / 60;    => each part for a minute in hr
        double hrDeg = (double)1/2 * (minutes) + hour * 30;

        double minDeg = (minutes / (double)5) * 30;

        // System.out.println(hrDeg + " " + minDeg);

        double diff = Math.abs(minDeg - hrDeg);

        return Math.min(diff, 360 - diff);
    }
}