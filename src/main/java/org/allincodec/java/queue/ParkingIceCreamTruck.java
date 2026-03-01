package org.allincodec.java.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Sliding Window Maximum — given an array A and window size B, return the maximum
 * of each B consecutive elements.
 *
 * <p>You are an ice cream truck driver. The beach has sections with varying customer
 * counts given by array A. You can only park across B consecutive sections at a time.
 * Find the busiest section (max customers) for every possible window of size B.</p>
 *
 * <p>Approach: Monotonic deque storing indices in decreasing order of their values.
 * Front of deque always holds the index of the current window's maximum.</p>
 *
 * <pre>
 * Input:  A = [5, 12, 3, 4, 8, 10, 2, 7], B = 3
 * Output: [12, 12, 8, 10, 10, 10]
 * </pre>
 *
 * <p>NOTE: If B &gt; length of array, return a single element with the max of the array.</p>
 *
 * Time Complexity: O(N) — each element is added and removed from deque at most once
 * Space Complexity: O(B) — deque holds at most B indices
 */
public class ParkingIceCreamTruck {

    public int[] slidingMaximum(final int[] A, int B) {
        if (B > A.length) {
            int max = A[0];
            for (int v : A) max = Math.max(max, v);
            return new int[]{max};
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[A.length - B + 1];

        for (int i = 0; i < A.length; i++) {
            // front check: remove index if it's outside the window
            if (!deque.isEmpty() && deque.peekFirst() <= i - B) {
                deque.removeFirst();
            }
            // back check: remove indices  whose values are smaller than current
            int current = A[i];
            while (!deque.isEmpty() && A[deque.peekLast()] < current) {
                deque.removeLast();
            }
            deque.addLast(i);
            // record max once first full window is complete
            if (i >= B - 1 && !deque.isEmpty()) {
                result[i - B + 1] = A[deque.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int []  A = {1, 3, -1, -3, 5, 3, 6, 7};
        ParkingIceCreamTruck parkingIceCreamTruck = new ParkingIceCreamTruck();
        System.out.println(Arrays.toString(parkingIceCreamTruck.slidingMaximum(A, 3)));
        System.out.println(Arrays.toString(parkingIceCreamTruck.slidingMaximum(new int[]{1}, 1)));
        System.out.println(Arrays.toString(parkingIceCreamTruck.slidingMaximum(new int[]{10,9,8,7,6,5,4,3,2,1}, 2)));
    }


}
