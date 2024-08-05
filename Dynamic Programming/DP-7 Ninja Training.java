import java.util.Arrays;

class Solution {


  // Memoized solution
  private static int find(int day, int last, int[][] arr, int[][] dp) {

    if (dp[day][last] != -1)
      return dp[day][last];

    if (day == 0) {
      int maxi = 0;
      for (int task = 0; task < 3; task++) {
        if (task != last) {
          maxi = Math.max(maxi, arr[0][task]);
        }
      }
      return dp[day][last] = maxi;
    }

    int maxi = 0;
    for (int task = 0; task < 3; task++) {
      if (task != last) {
        int points = arr[day][task] + find(day - 1, task, arr, dp);
        maxi = Math.max(points, maxi);

      }
    }
    return dp[day][last] = maxi;
  }

  public int maximumPoints1(int arr[][], int N) {
    // code here
    int[][] dp = new int[N][4];
    for (int[] row : dp)
      Arrays.fill(row, -1);

    return find(N - 1, 3, arr, dp);
  }

  // -----------------------------------------------------

  // TABULATION METHOD
  public int maximumPoints2(int arr[][], int N) {
    // code here
    int[][] dp = new int[N][4];

    dp[0][0] = Math.max(arr[0][1], arr[0][2]);
    dp[0][1] = Math.max(arr[0][0], arr[0][2]);
    dp[0][2] = Math.max(arr[0][0], arr[0][1]);
    dp[0][3] = Math.max(Math.max(arr[0][0], arr[0][1]), arr[0][2]);

    // iterate through each day and activity
    for (int day = 1; day < N; day++) {
      for (int last = 0; last < 4; last++) {
        dp[day][last] = 0; // initialiize the max. points for the current day and last activity
        for (int task = 0; task < 3; task++) {
          if (task != last) {
            // calculate the poitns for the current day and last activity
            int point = arr[day][task] + arr[day - 1][task];
            // update the max. point for the current day and last activity
            dp[day][last] = Math.max(dp[day][last], point);
          }
        }
      }
    }
    // return the max. points achieable after all days (last activity is 3)
    return dp[N - 1][3];
  }

  // --------------------------------------------

  // space optimized tabulation method:
  public int maximumPoint3(int arr[][], int N) {
    int[] dp = new int[4];
    dp[0] = Math.max(arr[0][1], arr[0][2]);
    dp[1] = Math.max(arr[0][0], arr[0][2]);
    dp[2] = Math.max(arr[0][0], arr[0][1]);
    dp[3] = Math.max(Math.max(arr[0][0], arr[0][1]), arr[0][2]);

    for (int day = 1; day < N; day++) {
      int[] temp = new int[4];
      for (int last = 0; last < 4; last++) {
        temp[last] = 0; // initialiize the max. points for the current day and last activity
        for (int task = 0; task < 3; task++) {
          if (task != last) {
            // calculate the points for the current day and last activity
            int point = arr[day][task] + dp[task];
            // update the max. point for the current day and last activity
            temp[last] = Math.max(temp[last], point);
          }
        }
      }
      dp = temp;
    }
    return dp[3];
  }
}