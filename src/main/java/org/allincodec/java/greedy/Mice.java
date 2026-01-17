package org.allincodec.java.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Mice and Holes Problem Solution
 *
 * <p><b>Problem Description:</b></p>
 * N mice and N holes are placed on a straight line. Each hole can accommodate only one mouse.
 * The positions of mice are given in array A, and positions of holes are given in array B.
 *
 * <p><b>Mouse Movement Rules:</b></p>
 * <ul>
 *   <li>A mouse can stay at its current position</li>
 *   <li>A mouse can move one step right from position x to x + 1</li>
 *   <li>A mouse can move one step left from position x to x − 1</li>
 *   <li>Each move consumes 1 minute</li>
 * </ul>
 *
 * <p><b>Goal:</b> Assign each mouse to a unique hole such that the time when the last mouse
 * enters a hole is minimized.</p>
 *
 * <p><b>Problem Constraints:</b></p>
 * <ul>
 *   <li>1 ≤ N ≤ 10^5</li>
 *   <li>-10^9 ≤ A[i], B[i] ≤ 10^9</li>
 * </ul>
 *
 * <p><b>Input Format:</b></p>
 * <ul>
 *   <li>First argument: Integer array A representing positions of N mice</li>
 *   <li>Second argument: Integer array B representing positions of N holes</li>
 * </ul>
 *
 * <p><b>Output Format:</b></p>
 * Return an integer denoting the minimum time (in minutes) when the last mouse enters a hole.
 *
 * <p><b>Algorithm Approach: Greedy Sorting with Binary Search</b></p>
 * <ol>
 *   <li>Sort both mice and holes arrays in ascending order</li>
 *   <li>Use Greedy Strategy: Match i-th mouse with i-th hole after sorting</li>
 *   <li>Calculate Distance: For each mouse-hole pair, compute absolute distance (time required)</li>
 *   <li>Find Maximum: Return the maximum time among all assignments</li>
 * </ol>
 *
 * <p><b>Why Greedy Works:</b></p>
 * Sorting both arrays and matching them sequentially ensures optimal assignment.
 * This prevents mice from "crossing over" each other, which would only increase total time.
 *
 * <p><b>Time Complexity:</b> O(N log N) - Due to sorting</p>
 * <p><b>Space Complexity:</b> O(N) - For sorted arrays</p>
 *
 * @author allincodec
 * @version 1.0
 */
public class Mice {

    public static int mice(List<Integer> A, List<Integer> B) {
      A.sort(Comparator.naturalOrder());
      B.sort(Comparator.naturalOrder());
      int maxTimeTaken = 0;

      for(int i=0; i < A.size(); i++) {
          int timeTaken = Math.abs(A.get(i) - B.get(i));
          maxTimeTaken = Math.max(maxTimeTaken, timeTaken);
      }


      return maxTimeTaken;
    }

    public static void main(String[] args) {
        mice(new ArrayList<>(List.of(-4, 2, 3)), new ArrayList<>(List.of(0, -2, 4)));

    }
}
