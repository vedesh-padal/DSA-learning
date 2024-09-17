import java.util.Arrays;

class JobSequencing {

  class Job {
    int id;
    int profit;
    int deadline;
    public Job(int x, int y, int z) {
      this.id = x;
      this.profit = y;
      this.deadline = z;
    }
  }

  public int[] sequenceJobs(Job[] jobs, int n) {  // n => no. of jobs

    // first schedule jobs acc. to profit in desc order
    Arrays.sort(jobs, (job1, job2) -> Integer.compare(job2.profit, job1.profit));

    // next find the max. deadline among all jobs
    int maxDeadLine = -1;
    for (int i = 0; i < n; i++) {
      maxDeadLine = Math.max(maxDeadLine, jobs[i].deadline);
    }

    // hash to store on which day which job was performed
    // as, acc. to question, only one job can be performed on each day
    int[] hash = new int[maxDeadLine + 1];

    int totalProfit = 0;
    int count = 0;  // no. of jobs performed

    for (int i = 0; i < n; i++) {
      
      // INTUITION:
      // MAX PROFIT IS PREFERED FIRST  and 
      // and THE ONE WITH MAX. DEADLINE IS PREFERED LAST
      // hence, below, we are trying to fill at the day nearest to deadline
      
      for (int j = jobs[i].deadline; j > 0; j--)  {
        if (hash[j] == -1)  {
          hash[j] = jobs[i].id;
          count++;
          totalProfit += jobs[i].profit;
          break;
        }
      }
    }
    return new int[]{ count, totalProfit };
  }
}