import java.util.Arrays;

class Solution {
  public int carFleet1(int target, int[] position, int[] speed) {
    int n = position.length;
    double[][] cars = new double[n][2];
    for (int i = 0; i < n; i++) {
      cars[i][0] = position[i];
      cars[i][1] = speed[i];
    }

    Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));

    double lastTime = 0;
    int fleetCount = 0;

    for (int i = n - 1; i >= 0; i--) {
      double reachTime = (target - cars[i][0]) / cars[i][1];

      if (reachTime > lastTime) {
        fleetCount++;
        lastTime = reachTime;
      }
      // if reach time of the current car is within the last time ( <= ), then it will
      // join the just nearer fleet
    }
    return fleetCount;
  }

  // ------------------------------------------------------------------------------------
  // even optimized approach
  public int carFleet2(int target, int[] position, int[] speed) {
    int n = position.length;
    float[] time = new float[target + 1];
    for (int i = 0; i < n; i++) {
      time[position[i]] = (float) (target - position[i]) / speed[i];
    }

    float lastTime = 0;
    int numFleet = 0;

    for (int i = time.length - 1; i >= 0; i--) {
      if (time[i] > lastTime) {
        numFleet++;
        lastTime = time[i];
      }
    }
    return numFleet;
  }
}