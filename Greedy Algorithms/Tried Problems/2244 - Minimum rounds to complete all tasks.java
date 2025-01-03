// MEDIUM
// hashtable, arrays, greedy

/*
 * You are given a 0-indexed integer array tasks, where tasks[i] represents the
 * difficulty level of a task. In each round, you can complete either 2 or 3
 * tasks of the same difficulty level.
 * 
 * Return the minimum rounds required to complete all the tasks, or -1 if it is
 * not possible to complete all the tasks.
 * 
 * Example 1:
 * Input: tasks = [2,2,3,3,2,4,4,4,4,4]
 * Output: 4
 * Explanation: To complete all the tasks, a possible plan is:
 * - In the first round, you complete 3 tasks of difficulty level 2.
 * - In the second round, you complete 2 tasks of difficulty level 3.
 * - In the third round, you complete 3 tasks of difficulty level 4.
 * - In the fourth round, you complete 2 tasks of difficulty level 4.
 * It can be shown that all the tasks cannot be completed in fewer than 4
 * rounds, so the answer is 4.
 *
 * Example 2:
 * Input: tasks = [2,3,3]
 * Output: -1
 * Explanation: There is only 1 task of difficulty level 2, but in each round,
 * you can only complete either 2 or 3 tasks of the same difficulty level.
 * Hence, you cannot complete all the tasks, and the answer is -1.
 * 
 * Constraints:
 * 1 <= tasks.length <= 10^5
 * 1 <= tasks[i] <= 10^9
 */
import java.util.*;

 class Solution {
    // similar to: LC- 2870 - Minimum no. of operations required to empty the arrray
    // infact same question
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> taskCnt = new HashMap<>();
        for (int task: tasks) {
            taskCnt.put(task, taskCnt.getOrDefault(task, 0) + 1);
        }
        // since we are dealing with only completing 2 or 3 tasks in a round
        // the count of each task can be depicted in the form of 3x + 2y form
        // and no. of rounds required for each task = (x + y)

        // => 3k, 3k + 1, 3k + 2 ===> every count of task will be in any of these 3 forms

        // IMPORTANT OBSERVATION:
        // for 3k + 1 form numbers:
        //  10 => 3 3 3 1 => not possible, so: 3 3 2 2   ==> 4 rounds
        //  13 => 3 3 3 3 1 => not possible, so: 3 3 3 2 2 => 4 rounds
        
        // for 3k + 2 form numbers:
        // 8 => 3 3 2 => 3 rounds => 8/3 + 1 = 3
        // 14 => 3 3 3 3 2 => 5 rounds => 14/3 + 1 = 5

        // THEREFORE for numbers of the form (3k + 1) or (3k + 2) 
        // no. of rounds required = (n / 3 + 1)

        // edge case: if it was just 1 count for the task, then in any way it is not possible

        int rounds = 0;
        for (int cnt: taskCnt.values()) {
            if (cnt == 1)
                return -1;
            if (cnt % 3 == 0) {
                rounds += cnt / 3;
            }
            else { // remainder 1 or 2
                rounds += cnt / 3 + 1;
            }
        }
        return rounds;
    }

    // ===========================================
    // USING PRIORITY QUEUE - BAD
    public int minimumRounds_HEAP(int[] tasks) {
        Map<Integer, Integer> taskCnt = new HashMap<>();
        for (int task: tasks) {
            taskCnt.put(task, taskCnt.getOrDefault(task, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int cnt: taskCnt.values()) {
            pq.offer(cnt);
        }

        int rounds = 0;
        int notPossible = 0;    // count of not possibile removals
        // i.e., each task count which is less than 2 difficulty
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            if (curr >= 3 && curr % 3 == 0) {
                if (curr % 3 == 0)
                    curr -= 3;
                else if (curr % 2 == 0)
                    curr -= 2;
                else
                    curr -= 3;
                rounds++;
            }
            else if (curr >= 2) {
                curr -= 2;
                rounds++;
            }
            else {
                notPossible++;
            }
            if (notPossible == pq.size() + 1) {
                break;
            }
            if (curr > 0)
                pq.offer(curr);
        }

        return (notPossible > 0) ? -1 : rounds;

    }
}