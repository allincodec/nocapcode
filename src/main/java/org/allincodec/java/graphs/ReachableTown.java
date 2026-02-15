package org.allincodec.java.graphs;

import java.util.*;

/**
 * Problem: Reachable Towns
 * <p>
 * Given N towns (1 to N), all towns are connected via unique directed paths.
 * The task is to determine if town B is reachable from town C without repeating any edge.
 * </p>
 *
 * <h3>Graph Construction:</h3>
 * <ul>
 *   <li>Input array A is 0-indexed with size N</li>
 *   <li>Towns are numbered from 1 to N</li>
 *   <li>For every 1 <= i < N, there exists a directed edge from A[i] to (i+1)</li>
 *   <li>Constraint: A[i] <= i for every 1 <= i < N</li>
 *   <li>A[0] = 1 (can be ignored as it doesn't represent any edge)</li>
 * </ul>
 *
 * <h3>Query Format:</h3>
 * <p>B C : Find whether B is reachable from C</p>
 *
 * <h3>Constraints:</h3>
 * <ul>
 *   <li>1 <= N <= 100000</li>
 *   <li>1 <= B, C <= N</li>
 * </ul>
 *
 * <h3>Input:</h3>
 * <ul>
 *   <li>Array A of size N</li>
 *   <li>Integer B (destination town)</li>
 *   <li>Integer C (source town)</li>
 * </ul>
 *
 * <h3>Output:</h3>
 * <p>Return 1 if B is reachable from C, otherwise return 0</p>
 *
 * <h3>Examples:</h3>
 * <pre>
 * Example 1:
 * Input:  A = [1, 1, 2], B = 1, C = 2
 * Output: 0
 * Explanation: Graph is 1→2→3, so 1 is NOT reachable from 2
 *
 * Example 2:
 * Input:  A = [1, 1, 2], B = 2, C = 1
 * Output: 1
 * Explanation: Graph is 1→2→3, so 2 IS reachable from 1
 * </pre>
 *
 * <h3>Approach:</h3>
 * <ol>
 *   <li>Build an adjacency list representation of the directed graph</li>
 *   <li>Use BFS starting from town C</li>
 *   <li>Check if we can reach town B during traversal</li>
 * </ol>
 */
public class ReachableTown {

    public static void main(String[] args) {
        int[] A = {1, 1, 2}; // index (1,2,3) are town from 1-n
        int B = 2;
        int C = 1;
        // can we rach town B from town C
        System.out.println(isTownReachable(A, B, C));
    }

    private static int isTownReachable(int[] A, int B, int C) {
        // build graph
        Map<Integer, List<Integer>> graph = new LinkedHashMap<>();
        for(int i=0; i < A.length; i++) {
            int key = A[i];
            int current = i;

            graph.putIfAbsent(key, new ArrayList<>());
            if(current +1 == key ) {
                continue;
            }

            graph.computeIfPresent(key, (k, v) ->{
                v.add(current+1);
                return v;
            });
        }

        // check if we can reach B from C, will do BFS
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[A.length+1];
        q.offer(C);
        visited[C] = true;

        while(!q.isEmpty()) {
            int current = q.poll();
            if(current  == B) {
                return 1;
            }
            List<Integer> towns = graph.getOrDefault(current, Collections.emptyList());
            for(int town: towns) {
                if(!visited[town]) {
                    q.offer(town);
                    visited[town] = true;
                }
            }
        }
       return 0;
    }
}
