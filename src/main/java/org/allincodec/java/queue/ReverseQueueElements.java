package org.allincodec.java.queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ReverseQueueElements {

    public int[] solve(int[] A, int B) {
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i < B; i++) {
            q.offer(A[i]);
        }
        while(!q.isEmpty()) {
            int val = q.poll();
            A[B-1] = val;
            B--;
        }
        return A;
    }

    public static void main(String[] args) {
            ReverseQueueElements r = new ReverseQueueElements();
            System.out.println(Arrays.toString(r.solve(new int[]{1, 2, 3, 4, 5}, 3)));
    }
}
