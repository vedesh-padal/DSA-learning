import java.util.Arrays;

class Data {
  int start, end, pos;

  public Data(int start, int end, int pos) {
    this.start = start;
    this.end = end;
    this.pos = pos;
  }
}

class Solution {
  public int eraseOverlapIntervalsUnderstandable(int[][] intervals) {
    // similar to Meeting rooms question
    // but, here we have to find it reverse

    int n = intervals.length;
    Data[] data = new Data[n];

    for (int i = 0; i < n; i++) {
      data[i] = new Data(intervals[i][0], intervals[i][1], i + 1);
    }

    Arrays.sort(data, (a, b) -> a.end - b.end);

    int maxi = 1; // max. number of non-overlapping intervals
    int gEnd = data[0].end; // stores greatest end till now

    for (int i = 1; i < n; i++) {
      // >= becoz, [1, 2] and [2, 3] are non-overlapping
      if (data[i].start >= gEnd) {
        maxi++;
        gEnd = data[i].end;
      }
    }

    // now, we need to erase the overlapping intervals
    // such that no. of removals is minimum
    // that means, when we maximize the non-overlapping intervals,
    // and subtact this from total no. of intervals,
    // we have our required answer
    return (n - maxi);
  }

  //// SIMPLER WAY OF DOING
  public int eraseOverlapIntervals(int[][] intervals) {
    int n = intervals.length;
    Arrays.sort(intervals, (a, b) -> (a[1] - b[1]));

    int prevEnd = intervals[0][1];
    int cnt = 0;

    for (int i = 1; i < n; i++) {
      if (prevEnd > intervals[i][0])
        cnt++;
      else
        prevEnd = intervals[i][1];
    }
    return cnt;
  }
}