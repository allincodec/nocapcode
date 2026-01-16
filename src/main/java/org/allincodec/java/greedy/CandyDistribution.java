package org.allincodec.java.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Candy Distribution Problem Solution
 *
 * <p><b>Problem Description:</b></p>
 * N children are standing in a line, each with a rating value. You must distribute candies
 * to these children with the following constraints:
 * <ul>
 *   <li>Each child must receive at least one candy</li>
 *   <li>Children with a higher rating than their neighbors must receive more candies than those neighbors</li>
 * </ul>
 *
 * <p><b>Goal:</b> Find the minimum number of candies needed.</p>
 *
 * <p><b>Problem Constraints:</b></p>
 * <ul>
 *   <li>1 ≤ N ≤ 10^5</li>
 *   <li>-10^9 ≤ A[i] ≤ 10^9</li>
 * </ul>
 *
 * <p><b>Algorithm Approach: Greedy Two-Pass Strategy</b></p>
 * <ol>
 *   <li>Initialize: Give each child 1 candy</li>
 *   <li>First Pass (Left to Right): If a child's rating is higher than the left neighbor,
 *       increase candies to be more than the left neighbor</li>
 *   <li>Second Pass (Right to Left): If a child's rating is higher than the right neighbor
 *       but doesn't have enough candies, increase accordingly</li>
 *   <li>Handle Edge Cases: Ensure first and last children satisfy all constraints</li>
 * </ol>
 *
 * <p><b>Time Complexity:</b> O(N) - Multiple linear passes</p>
 * <p><b>Space Complexity:</b> O(N) - For the candies array</p>
 *
 * @author allincodec
 * @version 1.0
 */
public class CandyDistribution {

    public static int candy(List<Integer> A) {
        //consider each child has 1 candy initially
        int n = A.size();

        List<Integer> candies = new ArrayList<>();
        for(int i=0; i < A.size(); i++) {
            candies.add(1);
        }

        // first traverse from left to right
        for(int i= 1; i < n-1; i++) {
            if(A.get(i) > A.get(i-1)) {
                candies.set(i, candies.get(i-1) + 1);
            }
            if(A.get(i) > A.get(i+1)) {
                candies.set(i, Math.max(candies.get(i), candies.get(i+1)+1));
            }
        }

        for(int i=n-2; i>0; i--) {
            if(A.get(i) > A.get(i+1) && candies.get(i) <= candies.get(i+1)) {
                candies.set(i, Math.max(candies.get(i), candies.get(i+1)+1));
            }
        }

        // finally handle the edges
        if( n > 1 && A.get(0) > A.get(1)) {
            candies.set(0, candies.get(1)+1);
        }

        if(n > 1 && A.get(n-1) > A.get(n-2)) {
            candies.set(n-1, candies.get(n-2)+1);
        }


        // output the total candies
        return candies.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
//        int minNumOfCandies = candy(List.of(1, 5, 2, 1));
//        int minNumOfCandies = candy(List.of(1,2));
        int minNumOfCandies = candy(List.of(-500));

        System.out.println("Minimum number of candies needed: " + minNumOfCandies);
    }
}
