package org.allincodec.java.graphs;

import java.util.*;

/**
 * Path Detection in Directed Graph
 *
 * <h2>Problem Statement</h2>
 * Given a directed graph having A nodes labelled from 1 to A containing M edges
 * given by matrix B of size M x 2 such that there is an edge directed from node
 * B[i][0] to node B[i][1].
 * <p>
 * Find whether a path exists from node 1 to node A.
 * Return 1 if path exists else return 0.
 *
 * <h3>Constraints</h3>
 * <ul>
 *   <li>2 &lt;= A &lt;= 10^5</li>
 *   <li>1 &lt;= M &lt;= min(200000, A*(A-1))</li>
 *   <li>1 &lt;= B[i][0], B[i][1] &lt;= A</li>
 *   <li>No self-loops in the graph</li>
 *   <li>No multiple edges between two nodes</li>
 *   <li>Graph may or may not be connected</li>
 *   <li>Nodes are numbered from 1 to A</li>
 * </ul>
 *
 * <h3>Input Format</h3>
 * <ul>
 *   <li>First argument: Integer A (number of nodes)</li>
 *   <li>Second argument: Matrix B of size M x 2 (edges)</li>
 *   <li>B[i][0] = source node, B[i][1] = destination node</li>
 * </ul>
 *
 * <h3>Output Format</h3>
 * Return 1 if path exists from node 1 to node A, else return 0
 *
 * <h3>Examples</h3>
 * <pre>
 * Example 1:
 * Input:  A = 5
 *         B = [[1,2], [4,1], [2,4], [3,4], [5,2], [1,3]]
 * Output: 0
 * Explanation: No path exists from node 1 to node 5
 *
 * Graph structure:
 *     1 → 2 → 4
 *     ↓   ↑   ↑
 *     3 → 4   |
 *         5 → 2
 *
 * From node 1, we can reach: {2, 3, 4}
 * But we cannot reach node 5
 *
 * Example 2:
 * Input:  A = 5
 *         B = [[1,2], [2,3], [3,4], [4,5]]
 * Output: 1
 * Explanation: Path exists from node 1 to node 5
 *
 * Graph structure: 1 → 2 → 3 → 4 → 5
 * Path: 1 → 2 → 3 → 4 → 5
 * </pre>
 *
 * <h3>Approach: Use BFS (Breadth-First Search)</h3>
 * <pre>
 * BFS Advantages:
 * ✓ Discovers order dynamically based on graph structure
 * ✓ Handles cycles with visited tracking
 * ✓ Simple and efficient: O(V + E)
 * ✓ Perfect for reachability problems
 * </pre>
 *
 * @author Ankit Sharma
 * @version 1.0
 */
public class PathInDirectedGraph {

    /**
     * Checks if a path exists from node 1 to node A in a directed graph.
     *
     * @param A the number of nodes in the graph (nodes labeled 1 to A)
     * @param B the edge list where B.get(i) = [from, to] representing directed edge
     * @return 1 if path exists from node 1 to node A, otherwise 0
     *
     * @implSpec Time Complexity: O(V + E) where V = nodes, E = edges
     * @implSpec Space Complexity: O(V + E) for adjacency list and visited array
     */
    public static int isDestReachable(int A, List<List<Integer>> B) {
        // Build adjacency list representation of the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> edge : B) {
            int from = edge.get(0);
            int to = edge.get(1);
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        // BFS to check reachability from node 1 to node A
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[A + 1];

        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Found destination
            if (current == A) {
                return 1;
            }

            // Explore neighbors
            List<Integer> neighbors = graph.get(current);
            if (neighbors != null) {
                for (int neighbor : neighbors) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return 0; // Destination not reachable
    }

    public static void main(String[] args) {
        // Test Case 1: Simple path
        int A1 = 2;
        List<List<Integer>> B1 = List.of(
            List.of(1, 2)
        );
        System.out.println("Test 1: " + isDestReachable(A1, B1)); // Expected: 1

        // Test Case 2: Path exists
        int A2 = 5;
        List<List<Integer>> B2 = List.of(
            List.of(1, 2),
            List.of(2, 3),
            List.of(3, 4),
            List.of(4, 5)
        );
        System.out.println("Test 2: " + isDestReachable(A2, B2)); // Expected: 1

        // Test Case 3: No path
        int A3 = 5;
        List<List<Integer>> B3 = List.of(
            List.of(1, 2),
            List.of(4, 1),
            List.of(2, 4),
            List.of(3, 4),
            List.of(5, 2),
            List.of(1, 3)
        );
        System.out.println("Test 3: " + isDestReachable(A3, B3)); // Expected: 0
    }
}
