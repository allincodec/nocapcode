package org.allincodec.java.heap;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargest {


    public static int KthLargestElement(List<Integer> list, int k) {

        Queue<Integer> q = new PriorityQueue<>(Comparator.naturalOrder());
        for(int i : list) {
            q.offer(i);
        }

        while(!q.isEmpty()) {
            int current = q.poll();
            if(k == 1) {
                return current;
            }
            k--;
        }

        return -1;
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(3, 1, 5, 12, 2, 11, 4);
        System.out.println("Kth Largest Element: " + KthLargestElement(list, 1));
        System.out.println("Kth Largest Element: " + KthLargestElement(list, 3));
        System.out.println("Kth Largest Element: " + KthLargestElement(list, 5));
    }
}
