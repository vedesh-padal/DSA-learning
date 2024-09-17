import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Meeting {
  int start, end, pos;

  public Meeting(int x, int y, int z) {
    this.start = x;
    this.end = y;
    this.pos = z;
  }
}

class Solution {
  // Function to find the maximum number of meetings that can
  // be performed in a meeting room.
  public int maxMeetings(int n, int start[], int end[]) {
    // add your code here

    Meeting[] meetings = new Meeting[n];
    for (int i = 0; i < n; i++) {
      meetings[i] = new Meeting(start[i], end[i], i + 1);
    }

    // sort the meetings acc. to end time
    // Intuition:
    // the meetings that consider fast should be given chance first
    // so sort the meetings in asc. order of end time of meeting
    Arrays.sort(meetings, (m1, m2) -> Integer.compare(m1.end, m2.end));
    List<Integer> happenMeetings = new ArrayList<>();

    // end of the prev. meeting
    // next meeting can start from next time from this
    int count = 1;
    happenMeetings.add(meetings[0].pos);
    int freeTime = meetings[0].end;

    for (int i = 1; i < n; i++) {
      if (meetings[i].start > freeTime) {
        count++;
        happenMeetings.add(meetings[i].pos);
        freeTime = meetings[i].end;
      }
    }
    return count;
  }
}