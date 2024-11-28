import java.util.PriorityQueue;

class Solution {
    public static int kthSmallest(int[] arr, int k) {
        // Your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++)
            pq.add(arr[i]);

        int rem = k - 1;

        while (rem > 0) {
            pq.poll();
            rem--;
        }

        return pq.poll();

    }
}