package org.allincodec.java.graphs;

import java.util.*;

/**
 * Problem: Number of Islands
 * <p>
 * Given a matrix of integers A of size N x M consisting of 0 and 1.
 * A group of connected 1's forms an island. Count the total number of islands.
 * </p>
 *
 * <h3>Island Definition:</h3>
 * <p>
 * From a cell (i, j) where A[i][j] = 1, you can visit any cell that shares a corner with (i, j)
 * and has value 1. This means <strong>8-directional connectivity</strong> (including diagonals).
 * </p>
 *
 * <h3>Valid Moves from cell (i, j):</h3>
 * <ul>
 *   <li>(i-1, j) - Up</li>
 *   <li>(i+1, j) - Down</li>
 *   <li>(i, j-1) - Left</li>
 *   <li>(i, j+1) - Right</li>
 *   <li>(i-1, j-1) - Top-left diagonal</li>
 *   <li>(i+1, j+1) - Bottom-right diagonal</li>
 *   <li>(i-1, j+1) - Top-right diagonal</li>
 *   <li>(i+1, j-1) - Bottom-left diagonal</li>
 * </ul>
 * <p>All moves must be within matrix bounds and the target cell must have value 1.</p>
 *
 * <h3>Input:</h3>
 * <ul>
 *   <li>Matrix A of size N x M</li>
 *   <li>A[i][j] = 0 (water) or 1 (land)</li>
 * </ul>
 *
 * <h3>Output:</h3>
 * <p>Return the number of islands</p>
 *
 * <h3>Note:</h3>
 * <ul>
 *   <li>Rows are numbered from top to bottom</li>
 *   <li>Columns are numbered from left to right</li>
 * </ul>
 *
 * <h3>Example:</h3>
 * <pre>
 * Input:
 * [1, 1, 0, 0, 0]
 * [0, 1, 0, 0, 0]
 * [1, 0, 0, 1, 1]
 * [0, 0, 0, 0, 0]
 * [1, 0, 1, 0, 1]
 *
 * Output: 5
 *
 * Explanation:
 * Island 1: (0,0)→(0,1)→(1,1)→(2,0) [connected via diagonal]
 * Island 2: (2,3)→(2,4)
 * Island 3: (4,0)
 * Island 4: (4,2)
 * Island 5: (4,4)
 * </pre>
 *
 * <h3>Approach:</h3>
 * <ol>
 *   <li>Iterate through each cell in the matrix</li>
 *   <li>When a cell with value 1 is found and not visited, increment island count</li>
 *   <li>Use BFS to mark all connected cells (all 8 directions) as visited</li>
 *   <li>Continue until all cells are processed</li>
 * </ol>
 *
 * <h3>Time Complexity:</h3>
 * <p>O(N * M) where N is rows and M is columns</p>
 *
 * <h3>Space Complexity:</h3>
 * <p>O(N * M) for the visited array and queue</p>
 */
public class NumberOfIsland {

    public static int countIslands(List<List<Integer>> m) {
        int count = 0;
        // create a graph from the matrix
        int[][] visited = new int[m.size()][m.getFirst().size()];
        for(int i=0; i < m.size(); i++) {
            for(int j=0; j < m.get(i).size(); j++) {
                if(m.get(i).get(j) == 1 && visited[i][j] != 1) {
                    visited[i][j] = 1;
                    count++;
                    // BFS traverse from src to all the adjacent nodes and mark them as visited
                    traverseAdjacentNodes(m, visited, i,j);
                }
            }
        }

        return count;
    }

    private static void traverseAdjacentNodes(List<List<Integer>> m, int[][] visited, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i,j});

        while(!q.isEmpty()) {
            int[] current = q.poll();
            int x  = current[0];
            int y = current[1];
            // check top
            if(x-1 >= 0 && x-1 < m.size() && m.get(x-1).get(y) == 1 && visited[x-1][y] != 1) {
                visited[x-1][y] = 1;
                q.offer(new int[]{x-1, y});
            }
            if( x+1 >=0 && x+1 < m.size() && m.get(x+1).get(y) == 1 && visited[x+1][y] != 1) {
                visited[x+1][y] = 1;
                q.offer(new int[]{x+1, y});
            }
            if( y-1 >= 0 && y-1 < m.getFirst().size() && m.get(x).get(y-1) == 1 && visited[x][y-1] != 1) {
                visited[x][y-1] = 1;
                q.offer(new int[]{x, y-1});
            }
            if( y+1 >= 0 && y+1 < m.getFirst().size() && m.get(x).get(y+1) == 1 && visited[x][y+1] != 1) {
                visited[x][y+1] = 1;
                q.offer(new int[]{x, y+1});
            }
            if(x+1 >= 0 && y+1 >= 0 && x+1 < m.size() && y+1 < m.getFirst().size()
                    && m.get(x+1).get(y+1) == 1 && visited[x+1][y+1] != 1) {
                visited[x+1][y+1] = 1;
                q.offer(new int[]{x+1, y+1});
            }
            if(x-1 >= 0 && y-1 >= 0 && x-1 < m.size() && y-1 < m.getFirst().size()
                    && m.get(x-1).get(y-1) == 1 && visited[x-1][y-1] != 1) {
                visited[x-1][y-1] = 1;
                q.offer(new int[]{x-1, y-1});
            }
            if(x-1 >= 0 && y+1 >= 0 && x-1 < m.size() && y+1 < m.getFirst().size()
                    && m.get(x-1).get(y+1) == 1 && visited[x-1][y+1] != 1) {
                visited[x-1][y+1] = 1;
                q.offer(new int[]{x-1, y+1});
            }
            if(x+1 >= 0 && y-1 >= 0 && x+1 < m.size() && y-1 < m.getFirst().size()
                    && m.get(x+1).get(y-1) == 1 && visited[x+1][y-1] != 1) {
                visited[x+1][y-1] = 1;
                q.offer(new int[]{x+1, y-1});
            }
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> arr = List.of(
                List.of(0,0,1,0,1,0,1,1,1),
                List.of(0,1,0,0,1,1,1,0,1));

         System.out.println(countIslands(arr));

        }

}
