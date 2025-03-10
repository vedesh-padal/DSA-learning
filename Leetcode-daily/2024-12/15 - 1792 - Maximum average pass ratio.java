// MEDIUM
// arrays, heap, greedy
/*
    There is a school that has classes of students and each class will be having
    a final exam. You are given a 2D integer array classes, where classes[i] =
    [passi, totali]. You know beforehand that in the ith class, there are totali
    total students, but only passi number of students will pass the exam.

    You are also given an integer extraStudents. There are another extraStudents
    brilliant students that are guaranteed to pass the exam of any class they are
    assigned to. You want to assign each of the extraStudents students to a class
    in a way that maximizes the average pass ratio across all the classes.

    The pass ratio of a class is equal to the number of students of the class
    that will pass the exam divided by the total number of students of the class.
    The average pass ratio is the sum of pass ratios of all the classes divided
    by the number of the classes.

    Return the maximum possible average pass ratio after assigning the
    extraStudents students. Answers within 10-5 of the actual answer will be accepted.

    Example 1:
    Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
    Output: 0.78333
    Explanation: You can assign the two extra students to the first class. The
    average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.

    Example 2:
    Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
    Output: 0.53485

    Constraints:
    1 <= classes.length <= 10^5
    classes[i].length == 2
    1 <= passi <= totali <= 10^5
    1 <= extraStudents <= 10^5
*/

import java.util.PriorityQueue;

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // the class to which 1 student is added, and which shows the potential improvement, it is considered first
        // and that change it will contribute is max. for the first time, conseqeuntly
        // the amount of change decreases as more students are added

        // { pass, total }, based on potential improvement on adding new extraStudent
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> {
            double newA = ((a[0] + 1) / (a[1] + 1)) - (a[0] / a[1]);
            double newB = ((b[0] + 1) / (b[1] + 1)) - (b[0] / b[1]);

            return Double.compare(newB, newA);
        });

        for (int[] cl: classes) {
            pq.offer(new double[]{cl[0], cl[1]});
        }

        while (extraStudents > 0) {
            double[] cl = pq.poll();
            cl[0]++;
            cl[1]++;
            pq.offer(cl);
            extraStudents--;
        }

        double maxAvg = 0;
        while (!pq.isEmpty()) {
            double[] cl = pq.poll();
            maxAvg += (cl[0] / cl[1]);
        }
        return (maxAvg / classes.length);
    }
}