package org.allincodec.java.queue;

import java.util.*;

/**
 * Given a list of integers, sort them by frequency (ascending).
 * If two elements have the same frequency, sort by value (ascending).
 *
 * <p>Approach:</p>
 * <ol>
 *   <li>Count frequency of each element using a HashMap</li>
 *   <li>Use a min PriorityQueue ordered by frequency, then by value for ties</li>
 *   <li>Poll each entry and add the element repeated by its frequency to result</li>
 * </ol>
 *
 * <pre>
 * Input:  [2, 3, 4, 1, 4, 5, 5]
 * Output: [1, 2, 3, 4, 4, 5, 5]
 * </pre>
 *
 * Time Complexity: O(N log N) — heap operations
 * Space Complexity: O(N) — frequency map and heap
 */
public class FrequencyCount {

    public List<Integer> solve(List<Integer> A) {
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> freqMap = new HashMap<>();
        for(Integer num : A) {
            freqMap.computeIfPresent(num, (k, v) -> {
                int val = freqMap.get(k) + 1;
                freqMap.put(k, val);
                return val;
            });
            freqMap.putIfAbsent(num, 1);
        }
        Queue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>(
                Comparator.comparing((Map.Entry<Integer, Integer> e) -> e.getValue()).thenComparing(Map.Entry::getKey)

        );

        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            q.offer(entry);
        }

        while(!q.isEmpty()) {
            Map.Entry<Integer, Integer> entry = q.poll();
            int val = entry.getValue();
            int key = entry.getKey();
            while(val > 0) {
                result.add(key);
                val--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FrequencyCount f = new FrequencyCount();
        System.out.println(f.solve(List.of(2,3,4,1,4,5,5)));
    }
}
